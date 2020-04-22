import java.awt.Button;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;


public class AddTeacher {

	public JFrame frmAddTeacher;
	private JTextField user_field;
	private JTextField name_field;
	private JTextField branch_field;
	private JTextField phone_field;
	private JTextField pass_field;

	public AddTeacher() {
		initialize();
	}

	private void initialize() {
		frmAddTeacher = new JFrame();
		frmAddTeacher.setTitle("Add Teacher");
		frmAddTeacher.setBounds(100, 100, 600, 450);
		frmAddTeacher.getContentPane().setBackground(new Color(240, 248, 255));
		frmAddTeacher.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmAddTeacher.getContentPane().setLayout(null);
		
		JLabel lblHelloAdmin = new JLabel("Enter New Teacher Details");
		lblHelloAdmin.setForeground(new Color(153, 0, 204));
		lblHelloAdmin.setHorizontalAlignment(SwingConstants.CENTER);
		lblHelloAdmin.setBounds(145, 13, 292, 30);
		lblHelloAdmin.setFont(new Font("Montserrat", Font.BOLD, 18));
		frmAddTeacher.getContentPane().add(lblHelloAdmin);
		
		JLabel user_title = new JLabel("Registration No. :");
		user_title.setBounds(59, 70, 174, 42);
		user_title.setFont(new Font("Montserrat", Font.BOLD, 15));
		user_title.setHorizontalAlignment(SwingConstants.CENTER);
		frmAddTeacher.getContentPane().add(user_title);
		
		
		JLabel name_title = new JLabel("Name :");
		name_title.setBounds(59, 120, 174, 42);
		name_title.setFont(new Font("Montserrat", Font.BOLD, 15));
		name_title.setHorizontalAlignment(SwingConstants.CENTER);
		frmAddTeacher.getContentPane().add(name_title);
		
		JLabel branch_title = new JLabel("Branch :");
		branch_title.setBounds(59, 170, 174, 42);
		branch_title.setFont(new Font("Montserrat", Font.BOLD, 15));
		branch_title.setHorizontalAlignment(SwingConstants.CENTER);
		frmAddTeacher.getContentPane().add(branch_title);
		
		JLabel phone_title = new JLabel("Phone No. :");
		phone_title.setBounds(59, 220, 174, 42);
		phone_title.setFont(new Font("Montserrat", Font.BOLD, 15));
		phone_title.setHorizontalAlignment(SwingConstants.CENTER);
		frmAddTeacher.getContentPane().add(phone_title);
		
		JLabel pass_title = new JLabel("Set Password :");
		pass_title.setBounds(59, 270, 174, 42);
		pass_title.setFont(new Font("Montserrat", Font.BOLD, 15));
		pass_title.setHorizontalAlignment(SwingConstants.CENTER);
		frmAddTeacher.getContentPane().add(pass_title);
		
		user_field = new JTextField();
		user_field.setToolTipText("Enter Registration No...");
		user_field.setColumns(10);
		user_field.setBounds(304, 75, 180, 30);
		frmAddTeacher.getContentPane().add(user_field);
		
		name_field = new JTextField();
		name_field.setToolTipText("Enter Name...");
		name_field.setColumns(10);
		name_field.setBounds(304, 125, 180, 30);
		frmAddTeacher.getContentPane().add(name_field);
		
		branch_field = new JTextField();
		branch_field.setToolTipText("Enter Branch...");
		branch_field.setColumns(10);
		branch_field.setBounds(304, 175, 180, 30);
		frmAddTeacher.getContentPane().add(branch_field);
		
		phone_field = new JTextField();
		phone_field.setToolTipText("Enter Phone No...");
		phone_field.setColumns(10);
		phone_field.setBounds(304, 225, 180, 30);
		frmAddTeacher.getContentPane().add(phone_field);
		
		pass_field = new JTextField();
		pass_field.setText("nitap2020");
		pass_field.setToolTipText("Set Password...");
		pass_field.setColumns(10);
		pass_field.setBounds(304, 275, 180, 30);
		frmAddTeacher.getContentPane().add(pass_field);
		
		Button submit = new Button("Submit");
		submit.setBackground(new Color(50, 205, 50));
		submit.setBounds(169, 330, 100, 45);
		
		submit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				addNew();
			}  
		});
		
		frmAddTeacher.getContentPane().add(submit);
		
		Button cancel = new Button("Cancel");
		cancel.setBackground(new Color(255, 69, 0));
		cancel.setBounds(304, 330, 100, 45);
		
		cancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				frmAddTeacher.dispose();
			}  
		});
		
		frmAddTeacher.getContentPane().add(cancel);
		
		frmAddTeacher.setVisible(true);
	}

	protected void addNew() {
			String user = user_field.getText();
			String name = name_field.getText();
			String branch = branch_field.getText();
			String phone = phone_field.getText();
			String pass = pass_field.getText();
			
			if(user.isEmpty() || name.isEmpty() || branch.isEmpty() || phone.isEmpty()) {
				JOptionPane.showMessageDialog(null, "Enter every value to submit.");
			}
			
			Database db = new Database();
			try {
				ResultSet rs = db.executeQuery("SELECT * FROM teacher_details WHERE iduser=\""+user+"\";");	
				if(rs.next() == true) {
					JOptionPane.showMessageDialog(null, "Registration No. already exists. Enter new Registration No.");
				}
				else {
					String pattern = "^0\\d{5}";
					if(user.matches(pattern)) {
						db.executeQuery("INSERT INTO `student_ams`.`teacher_details` (`iduser`, `name`, `branch`, `phone`) VALUES ('"+user+"', '"+name+"', '"+branch+"', '"+phone+"');");
						
						db.executeQuery("INSERT INTO `student_ams`.`credentials` (`iduser`, `password`, `mode`) VALUES ('"+user+"', '"+pass+"', 'Teacher');");
						
						JOptionPane.showMessageDialog(null, "New Teacher Added Successfully");
						frmAddTeacher.dispose();
						
					}else {
						JOptionPane.showMessageDialog(null, "Invalid Registration No.");
					}
				}
			} catch (SQLException e) {

				e.printStackTrace();
			}
		
	}

}
