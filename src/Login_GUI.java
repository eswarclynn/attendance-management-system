
import java.awt.*;
import java.awt.event.*;  
import javax.swing.*;
import java.awt.event.ActionListener;
import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import java.awt.event.ActionEvent;
import java.sql.*;

@SuppressWarnings("unused")
public class Login_GUI {

	private JFrame frmLoginAms;
	private JTextField txtUsername;
	private JPasswordField txtPassword;
	private JLabel lblType;
	private JComboBox comboBox;

	public static void main(String[] args) {
				try {
					Login_GUI window = new Login_GUI();
					window.frmLoginAms.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
	}

	
	public Login_GUI() {
		initialize();
	}

	private void initialize() {
		frmLoginAms = new JFrame();
		frmLoginAms.setBackground(new Color(0, 0, 0));
		frmLoginAms.setTitle("Login - AMS");
		frmLoginAms.setForeground(new Color(102, 205, 170));
		frmLoginAms.getContentPane().setBackground(new Color(240, 248, 255));
		frmLoginAms.setBounds(100, 100, 495, 395);
		frmLoginAms.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmLoginAms.getContentPane().setLayout(null);
		
		JLabel lblLogIn = new JLabel("Log In");
		lblLogIn.setBounds(0, 0, 477, 65);
		lblLogIn.setHorizontalAlignment(SwingConstants.CENTER);
		lblLogIn.setFont(new Font("Bebas", Font.BOLD, 31));
		frmLoginAms.getContentPane().add(lblLogIn);
		
		JLabel lblUsername = new JLabel("Username :");
		lblUsername.setBounds(0, 70, 233, 65);
		lblUsername.setFont(new Font("Montserrat", Font.BOLD, 15));
		lblUsername.setHorizontalAlignment(SwingConstants.CENTER);
		frmLoginAms.getContentPane().add(lblUsername);
		
		txtUsername = new JTextField();
		txtUsername.setBounds(250, 85, 150, 30);
		txtUsername.setToolTipText("Enter Username...");
		frmLoginAms.getContentPane().add(txtUsername);
		txtUsername.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password :");
		lblPassword.setBounds(0, 140, 233, 65);
		lblPassword.setHorizontalAlignment(SwingConstants.CENTER);
		lblPassword.setFont(new Font("Montserrat", Font.BOLD, 15));
		frmLoginAms.getContentPane().add(lblPassword);
		
		txtPassword = new JPasswordField();
		txtPassword.setBounds(250, 155, 150, 30);
		txtPassword.setToolTipText("Enter Password...");
		frmLoginAms.getContentPane().add(txtPassword);
		txtPassword.setColumns(10);
		
		lblType = new JLabel("Type :");
		lblType.setBounds(94, 234, 45, 16);
		lblType.setHorizontalAlignment(SwingConstants.CENTER);
		lblType.setFont(new Font("Montserrat", Font.BOLD, 15));
		frmLoginAms.getContentPane().add(lblType);
		
		comboBox = new JComboBox();
		comboBox.setBounds(250, 225, 150, 30);
		comboBox.setEditable(false);
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Student", "Teacher", "Admin"}));
		frmLoginAms.getContentPane().add(comboBox);
		
		Button button = new Button("Log In");
		button.setBackground(new Color(30, 144, 255));
		button.setBounds(202, 290, 80, 35);
		
		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				checkLogin();
			}  
		});
		
		frmLoginAms.getContentPane().add(button);
		

	}


	protected void checkLogin() {
		try {
			String username = txtUsername.getText();
			String password = new String(txtPassword.getPassword());
			String mode = (String)comboBox.getSelectedItem();
			
			Boolean logged = false;
			
			Database db = new Database();
			
			String sql;
			sql = "SELECT iduser,password,mode FROM credentials";
			ResultSet rs = db.executeQuery(sql);
			

			while(rs.next()){
				
				String iduser  = rs.getString("iduser");
				String pass = rs.getString("password");
				String type = rs.getString("mode");
				
				if(username.equals(iduser) && password.equals(pass) && type.equals(mode)) {
					logged = true;
					break;
				}
			}
			if(logged == true) {
				JOptionPane.showMessageDialog(null, "Log In Success!");
				if(mode.contentEquals("Student")) {
					Student_GUI detWindow = new Student_GUI(username);
					System.out.println("Calling Student");
				}
				else if(mode.contentEquals("Teacher")) {
					Teacher_GUI teaWindow = new Teacher_GUI(username);
					System.out.println("Calling Teacher");
				}
				else {
					Admin_GUI adminWin = new Admin_GUI(username);
					System.out.println("Calling Admin");
				}

				frmLoginAms.dispose();
			}
			else {
				JOptionPane.showMessageDialog(null, "Invalid username or password or type!");
			}
			
		}
		catch(Exception e) {
			System.out.println(e);
		}
	}
}
