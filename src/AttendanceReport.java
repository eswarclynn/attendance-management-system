import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.*;


public class AttendanceReport {

	private JFrame frmAttendanceReport;
	private JComboBox course_field;
	private JComboBox stud_field;
	private JTextField date_field;
	char mode = 'X';

	public AttendanceReport(String user) {
		initialize(user);
	}


	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void initialize(String user) {
		frmAttendanceReport = new JFrame();
		frmAttendanceReport.setTitle("Attendance Report");
		frmAttendanceReport.setBounds(100, 100, 450, 400);
		frmAttendanceReport.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmAttendanceReport.getContentPane().setBackground(new Color(240, 248, 255));
		frmAttendanceReport.getContentPane().setLayout(null);
		
		JLabel head = new JLabel("Attendance Report");
		head.setHorizontalAlignment(SwingConstants.CENTER);
		head.setForeground(new Color(153, 0, 204));
		head.setFont(new Font("Montserrat", Font.BOLD, 18));
		head.setBounds(70, 13, 292, 30);
		frmAttendanceReport.getContentPane().add(head);
		
		JLabel subhead  = new JLabel("Select Mode");
		subhead.setHorizontalAlignment(SwingConstants.CENTER);
		subhead.setFont(new Font("Montserrat", Font.BOLD, 16));
		subhead.setBounds(70, 43, 292, 30);
		frmAttendanceReport.getContentPane().add(subhead);
		
		JLabel cour_title = new JLabel("Course ID :");
		cour_title.setBounds(55, 99, 150, 16);
		cour_title.setFont(new Font("Montserrat", Font.BOLD, 15));
		frmAttendanceReport.getContentPane().add(cour_title);
		
		course_field = new JComboBox();
		course_field.setEditable(false);
		
		frmAttendanceReport.setVisible(true);
		
		
		course_field.setModel(new DefaultComboBoxModel(getCourses(user)));
		course_field.setToolTipText("Enter Course ID...");
		course_field.setBounds(191, 91, 180, 30);
		frmAttendanceReport.getContentPane().add(course_field);
		
		JLabel date_title = new JLabel("Date :");
		date_title.setForeground(SystemColor.textInactiveText);
		date_title.setBounds(55, 148, 150, 16);
		date_title.setFont(new Font("Montserrat", Font.BOLD, 15));
		frmAttendanceReport.getContentPane().add(date_title);
		
		java.util.Date date = new java.util.Date();
		SimpleDateFormat ft = new SimpleDateFormat("dd/MM/yyyy");
		String setDate = ft.format(date);
		
		date_field = new JTextField();
		date_field.setEnabled(false);
		date_field.setText(setDate);
		date_field.setToolTipText("Enter Date...");
		date_field.setBounds(191, 140, 180, 30);
		frmAttendanceReport.getContentPane().add(date_field);
		
		JLabel stud_title = new JLabel("Student ID :");
		stud_title.setForeground(SystemColor.textInactiveText);
		stud_title.setBounds(55, 196, 150, 16);
		stud_title.setFont(new Font("Montserrat", Font.BOLD, 15));
		frmAttendanceReport.getContentPane().add(stud_title);
		
		stud_field = new JComboBox();
		stud_field.setEnabled(false);
		stud_field.setEditable(false);
		stud_field.setModel(new DefaultComboBoxModel(getStudents((String)course_field.getSelectedItem())));
		stud_field.setToolTipText("Enter Student ID...");
		stud_field.setBounds(191, 188, 180, 30);
		frmAttendanceReport.getContentPane().add(stud_field);
		
		Button btnDate = new Button("By Date");
		btnDate.setBounds(59, 240, 130, 35);
		btnDate.setBackground(new Color(30, 144, 255));
		
		
		
		btnDate.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				date_field.setEnabled(true);
				date_title.setForeground(new Color(0,0,0));
				stud_field.setEnabled(false);
				stud_title.setForeground(SystemColor.textInactiveText);
				
				mode = 'D';
				JOptionPane.showMessageDialog(frmAttendanceReport, "Attendance Report by Date.");
				
			}  
		});
		
		frmAttendanceReport.getContentPane().add(btnDate);
		
		Button btnStudent = new Button("By Student");
		btnStudent.setBounds(234, 240, 130, 35);
		btnStudent.setBackground(new Color(30, 144, 255));
		
		btnStudent.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				date_field.setEnabled(false);
				date_title.setForeground(SystemColor.textInactiveText);
				stud_field.setEnabled(true);
				stud_title.setForeground(new Color(0,0,0));
				
				mode = 'S';
				JOptionPane.showMessageDialog(frmAttendanceReport, "Attendance Report by Student.");
				
			}  
		});
		
		frmAttendanceReport.getContentPane().add(btnStudent);
		
		Button btnSubmit = new Button("Submit");
		btnSubmit.setBounds(146, 291, 130, 45);
		btnSubmit.setBackground(new Color(0, 204, 102));
		
		btnSubmit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(mode == 'S') {
					StudentReport sr = new StudentReport((String)stud_field.getSelectedItem(),(String)course_field.getSelectedItem());					
				}else if(mode == 'D') {
					DateReport dr = new DateReport(date_field.getText(),(String)course_field.getSelectedItem());
				}else {
					JOptionPane.showMessageDialog(frmAttendanceReport, "Please select atleast one mode.");
				}
				
			}  
		});
		
		frmAttendanceReport.getContentPane().add(btnSubmit);
		

	}
	
	private Object[] getCourses(String user) {
		
		ArrayList<String> cour_list = new ArrayList<String>();
		try {
			Database db = new Database();
			String courseSQL = "SELECT idcourse FROM student_ams.courses_list WHERE idteacher='"+user+"';";
			ResultSet rs = db.executeQuery(courseSQL);
			
			if(!rs.isBeforeFirst()) {
				JOptionPane.showMessageDialog(null, "Teacher :"+user+" has no course.");
				frmAttendanceReport.dispose();
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
	
	private Object[] getStudents(String course) {
		
		if(course == null) {
			frmAttendanceReport.dispose();
			return null;
		}
		
		ArrayList<String> list = new ArrayList<String>();
		try {
			Database db = new Database();
			String stud = "SELECT idUser FROM "+course.toLowerCase()+";";
			ResultSet rs = db.executeQuery(stud);
			
			if(!rs.isBeforeFirst()) {
				JOptionPane.showMessageDialog(null, "No students in selected course.\nPlease close the window and try again with another Course ID.");
			}
			while(rs.next()) {
				list.add(rs.getString("idUser"));
			}
				
			return list.toArray();

		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		
		return null;
	}
	

}
