import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;
import java.awt.Font;
import javax.swing.SwingConstants;

public class Teacher_GUI {

	private JFrame frmTeacherHome;

	public Teacher_GUI(String user) {
		initialize(user);
	}


	private void initialize(String user) {
		frmTeacherHome = new JFrame();
		frmTeacherHome.setTitle("Teacher Home");
		frmTeacherHome.setBounds(100, 100, 450, 300);
		frmTeacherHome.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmTeacherHome.getContentPane().setLayout(null);
		frmTeacherHome.getContentPane().setBackground(new Color(240, 248, 255));
		
		
		
		Button takeAtt = new Button("Take Attendance");
		takeAtt.setBounds(42, 71, 150, 55);
		takeAtt.setBackground(new Color(30, 144, 255));
		
		takeAtt.addActionListener(new ActionListener() {

			
			@Override
			public void actionPerformed(ActionEvent e) {

				TakeAttendance attWin = new TakeAttendance(user);
			}  
		});
		
		frmTeacherHome.getContentPane().add(takeAtt);
		
		Button btnReport = new Button("Attendance Report");
		btnReport.setBounds(243, 71, 150, 55);
		btnReport.setBackground(new Color(30, 144, 255));
		
		btnReport.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				AttendanceReport repWin = new AttendanceReport(user);
			}  
		});
		
		frmTeacherHome.getContentPane().add(btnReport);
		
		Button btnPass = new Button("Change Password");
		btnPass.setBounds(243, 163, 150, 55);
		btnPass.setBackground(new Color(30, 144, 255));
		
		btnPass.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				ChangePassword cp = new ChangePassword(user);
			}  
		});
		
		frmTeacherHome.getContentPane().add(btnPass);
		
		Button btnDetails = new Button("Get Details");
		btnDetails.setBounds(42, 163, 150, 55);
		btnDetails.setBackground(new Color(30, 144, 255));
		
		btnDetails.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				Details_GUI cp = new Details_GUI(user,"Teacher");
			}  
		});
		
		frmTeacherHome.getContentPane().add(btnDetails);
		
		JLabel head_title = new JLabel("Hello Teacher!");
		head_title.setHorizontalAlignment(SwingConstants.CENTER);
		head_title.setForeground(new Color(153, 0, 204));
		head_title.setFont(new Font("Montserrat", Font.BOLD, 18));
		head_title.setBounds(70, 13, 292, 30);
		frmTeacherHome.getContentPane().add(head_title);
		
		
		frmTeacherHome.setVisible(true);
	}
}
