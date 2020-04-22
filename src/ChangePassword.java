import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class ChangePassword {

	private JFrame frmChangePassword;
	private JTextField old_field;
	private JTextField new_field;
	private JTextField confirm_field;

	public ChangePassword(String user) {
		initialize(user);
	}


	private void initialize(String user) {
		frmChangePassword = new JFrame();
		frmChangePassword.setTitle("Change Password");
		frmChangePassword.setBounds(100, 100, 450, 300);
		frmChangePassword.getContentPane().setBackground(new Color(240, 248, 255));
		frmChangePassword.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmChangePassword.getContentPane().setLayout(null);
		
		JLabel head_title = new JLabel("Change Password for " +user);
		head_title.setBounds(0, 0, 432, 49);
		head_title.setHorizontalAlignment(SwingConstants.CENTER);
		head_title.setForeground(new Color(153, 0, 204));
		head_title.setFont(new Font("Montserrat", Font.BOLD, 18));
		frmChangePassword.getContentPane().add(head_title);
		
		JLabel old_title = new JLabel("Current Password :");
		old_title.setBounds(48,66,148,20);
		old_title.setFont(new Font("Tahoma",Font.BOLD,14));
		frmChangePassword.getContentPane().add(old_title);
		
		old_field = new JPasswordField();
		old_field.setBounds(230,62,148,30);
		old_field.setToolTipText("Enter Current Password...");
		frmChangePassword.getContentPane().add(old_field);
		
		JLabel new_title = new JLabel("New Password :");
		new_title.setBounds(48,110,148,20);
		new_title.setFont(new Font("Tahoma",Font.BOLD,14));
		frmChangePassword.getContentPane().add(new_title);
		
		new_field = new JPasswordField();
		new_field.setBounds(230,106,148,30);
		new_field.setToolTipText("Enter New Password...");
		frmChangePassword.getContentPane().add(new_field);
		
		JLabel confirm_title = new JLabel("Confirm Password :");
		confirm_title.setBounds(48,156,148,20);
		confirm_title.setFont(new Font("Tahoma",Font.BOLD,14));
		frmChangePassword.getContentPane().add(confirm_title);
		
		confirm_field = new JPasswordField();
		confirm_field.setBounds(230,152,148,30);
		confirm_field.setToolTipText("Confirm New Password...");
		frmChangePassword.getContentPane().add(confirm_field);
		
		Button submit = new Button("Submit");
		submit.setFont(new Font("Tahoma", Font.PLAIN, 18));
		submit.setForeground(new Color(255, 255, 153));
		submit.setBounds(165,193,90,40);
		submit.setBackground(new Color(102, 102, 255));
		
		submit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				passCheck(user);
			}
		});
		frmChangePassword.getContentPane().add(submit);
		
		frmChangePassword.setVisible(true);
	}


	protected void passCheck(String user) {
		
		try {
			Database db = new Database();
			String getSQL = "SELECT password FROM student_ams.credentials WHERE iduser="+user+";";
			ResultSet rs = db.executeQuery(getSQL);
			rs.next();
			String curr = rs.getString("password");
			if(curr.equals(old_field.getText())) {
				if(curr.equals(new_field.getText())) {
					JOptionPane.showMessageDialog(frmChangePassword, "New Password cannot be the same as Current Password!");
				}else {
					if(!new_field.getText().isEmpty() && new_field.getText().equals(confirm_field.getText())) {
						String setPassword = new_field.getText();
						String setSQL = "UPDATE student_ams.credentials SET password='"+setPassword+"' WHERE iduser="+user+";";
						db.executeQuery(setSQL);
						JOptionPane.showMessageDialog(frmChangePassword, "Password Changed Successfully!");
						frmChangePassword.dispose();
					}else {
						JOptionPane.showMessageDialog(frmChangePassword, "New Password and Confirm Password doesn't match!");
					}
				}
			}else {
				JOptionPane.showMessageDialog(frmChangePassword, "Current Password Incorrect!");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}
