import java.awt.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.*;


public class TakeAttendance {

	private JFrame frmTakeAttendance;
	private JComboBox course_field;
	private JTextField date_field;


	public TakeAttendance(String user) {
		initialize(user);
	}


	private void initialize(String user) {		
	
		frmTakeAttendance = new JFrame();
		frmTakeAttendance.setTitle("Take Attendance");
		frmTakeAttendance.setBounds(100, 100, 600, 300);
		frmTakeAttendance.getContentPane().setBackground(new Color(240, 248, 255));
		frmTakeAttendance.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmTakeAttendance.getContentPane().setLayout(null);
	
		JLabel lblHelloAdmin = new JLabel("Enter Details");
		lblHelloAdmin.setForeground(new Color(153, 0, 204));
		lblHelloAdmin.setHorizontalAlignment(SwingConstants.CENTER);
		lblHelloAdmin.setBounds(145, 13, 292, 30);
		lblHelloAdmin.setFont(new Font("Montserrat", Font.BOLD, 18));
		frmTakeAttendance.getContentPane().add(lblHelloAdmin);
		
		JLabel user_title = new JLabel("Course ID :");
		user_title.setBounds(59, 70, 174, 42);
		user_title.setFont(new Font("Montserrat", Font.BOLD, 15));
		user_title.setHorizontalAlignment(SwingConstants.CENTER);
		frmTakeAttendance.getContentPane().add(user_title);
		
		JLabel date_title = new JLabel("Enter Date:");
		date_title.setBounds(59, 120, 174, 42);
		date_title.setFont(new Font("Montserrat", Font.BOLD, 15));
		date_title.setHorizontalAlignment(SwingConstants.CENTER);
		frmTakeAttendance.getContentPane().add(date_title);
		
		course_field = new JComboBox();
		course_field.setEditable(false);
		
		frmTakeAttendance.setVisible(true);
		
		
		course_field.setModel(new DefaultComboBoxModel(getCourses(user)));
		course_field.setToolTipText("Enter Course ID...");
		course_field.setBounds(304, 75, 180, 30);
		frmTakeAttendance.getContentPane().add(course_field);
		
		java.util.Date date = new java.util.Date();
		SimpleDateFormat ft = new SimpleDateFormat("dd/MM/yyyy");
		String setDate = ft.format(date);
		
		date_field = new JTextField();
		date_field.setText(setDate);
		date_field.setToolTipText("Enter Date for marking attendance");
		date_field.setColumns(10);
		date_field.setBounds(304, 125, 180, 30);
		frmTakeAttendance.getContentPane().add(date_field);
		
		Button submit = new Button("Take Attendance");
		submit.setBackground(new Color(50, 205, 50));
		submit.setBounds(221, 180, 120, 45);
		
		submit.addActionListener(new ActionListener() {
	
			@Override
			public void actionPerformed(ActionEvent e) {
				
				String attDate = date_field.getText();
				String course = (String)course_field.getSelectedItem();
				Attendance_GUI ag = new Attendance_GUI(course,attDate);
				
			}  
		});
		
		frmTakeAttendance.getContentPane().add(submit);
		
		
		

	}


	private Object[] getCourses(String user) {
		
		ArrayList<String> cour_list = new ArrayList<String>();
		try {
			Database db = new Database();
			String courseSQL = "SELECT idcourse FROM student_ams.courses_list WHERE idteacher='"+user+"';";
			ResultSet rs = db.executeQuery(courseSQL);
			
			if(!rs.isBeforeFirst()) {
				JOptionPane.showMessageDialog(null, "Teacher :"+user+" has no course.");
				frmTakeAttendance.dispose();
			}
			while(rs.next()) {
				cour_list.add(rs.getString("idcourse"));
			}
				
			return cour_list.toArray();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
