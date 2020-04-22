import java.awt.Font;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.swing.JFrame;

import java.awt.Button;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;

public class CheckAttendance {

	private JFrame frmCheckAttendance;

	public CheckAttendance(String user) {
		initialize(user);
	}

	private void initialize(String user) {
		frmCheckAttendance = new JFrame();
		frmCheckAttendance.setTitle("Check Attendance");
		frmCheckAttendance.setBounds(650, 100, 450, 362);
		frmCheckAttendance.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmCheckAttendance.getContentPane().setBackground(new Color(240, 248, 255));
		frmCheckAttendance.getContentPane().setLayout(null);
		
		JLabel lblWelcome = new JLabel("Check Attendance");
		lblWelcome.setBounds(0, 0, 432, 75);
		lblWelcome.setHorizontalAlignment(SwingConstants.CENTER);
		lblWelcome.setFont(new Font("Bebas", Font.BOLD, 20));
		frmCheckAttendance.getContentPane().add(lblWelcome);
		
		JLabel reg_title = new JLabel("Registration No.");
		reg_title.setBounds(60, 100, 150, 16);
		reg_title.setFont(new Font("Montserrat", Font.BOLD, 15));
		frmCheckAttendance.getContentPane().add(reg_title);
		
		JLabel reg_field = new JLabel(user);
		reg_field.setBounds(250, 99, 160, 16);
		frmCheckAttendance.getContentPane().add(reg_field);

		JLabel course_title = new JLabel("Course");
		course_title.setBounds(60, 149, 150, 16);
		course_title.setFont(new Font("Montserrat", Font.BOLD, 15));
		frmCheckAttendance.getContentPane().add(course_title);
		
		JComboBox course_field = new JComboBox();
		course_field.setBounds(250, 149, 160, 22);
		course_field.setModel(new DefaultComboBoxModel(getCourses(user)));
		frmCheckAttendance.getContentPane().add(course_field);
		
		Button getAtt = new Button("Get Attendance");
		getAtt.setBounds(140, 197, 150, 35);
		getAtt.setBackground(new Color(30, 144, 255));
		frmCheckAttendance.getContentPane().add(getAtt);
		
		getAtt.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {

				 StudentReport repWin = new StudentReport(user, (String)course_field.getSelectedItem());
			}
		});
		
		frmCheckAttendance.setVisible(true);
	}

	private Object[] getCourses(String user) {
		
		ArrayList<String> cour_list = new ArrayList<String>();
		ArrayList<String> stud_cour = new ArrayList<String>();

		try {
			Database db = new Database();
			String courseSQL = "SELECT idcourse FROM student_ams.courses_list;";
			ResultSet rs = db.executeQuery(courseSQL);
			
			while(rs.next()) {
				cour_list.add(rs.getString("idcourse"));
			}
			
			for(String course: cour_list) {
				String sql = "SELECT idUser FROM student_ams."+course.toLowerCase()+" WHERE idUser='"+user+"';";
				rs = db.executeQuery(sql);
				
				if(rs.next() ) {
					stud_cour.add(course);
				}
			}
			if(stud_cour.isEmpty()) {
				JOptionPane.showMessageDialog(null, "Student not enrolled in any course.\nContact admin for Course Enrollment");
				frmCheckAttendance.dispose();
				return null;
			}
			return stud_cour.toArray();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
