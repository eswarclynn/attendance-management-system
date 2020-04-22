import java.awt.Button;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;

public class Student_GUI {

	private JFrame frmStudentHome;

	public Student_GUI(String user) {
		initialize(user);
	}


	private void initialize(String user) {
		frmStudentHome = new JFrame();
		frmStudentHome.setTitle("Student Home");
		frmStudentHome.setBounds(100, 100, 450, 362);
		frmStudentHome.getContentPane().setBackground(new Color(240, 248, 255));
		frmStudentHome.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		
		Button btnReport = new Button("Check Attendance");
		btnReport.setBounds(139, 70, 150, 55);
		btnReport.setBackground(new Color(30, 144, 255));
		
		btnReport.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				CheckAttendance repWin = new CheckAttendance(user);
			}  
		});
		frmStudentHome.getContentPane().setLayout(null);
		
		frmStudentHome.getContentPane().add(btnReport);
		
		Button btnPass = new Button("Change Password");
		btnPass.setBounds(139, 230, 150, 55);
		btnPass.setBackground(new Color(30, 144, 255));
		
		btnPass.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				ChangePassword cp = new ChangePassword(user);
			}  
		});
		
		frmStudentHome.getContentPane().add(btnPass);
		
		Button btnDetails = new Button("Get Details");
		btnDetails.setBounds(139, 150, 150, 55);
		btnDetails.setBackground(new Color(30, 144, 255));
		
		btnDetails.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				Details_GUI cp = new Details_GUI(user,"Student");
			}  
		});
		
		frmStudentHome.getContentPane().add(btnDetails);
		
		JLabel lblWelcomeStudent = new JLabel("Welcome Student");
		lblWelcomeStudent.setHorizontalAlignment(SwingConstants.CENTER);
		lblWelcomeStudent.setForeground(new Color(153, 0, 204));
		lblWelcomeStudent.setFont(new Font("Montserrat", Font.BOLD, 18));
		lblWelcomeStudent.setBounds(70, 13, 292, 30);
		frmStudentHome.getContentPane().add(lblWelcomeStudent);
		
		frmStudentHome.setVisible(true);
	}

}
