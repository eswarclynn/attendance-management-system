import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.ArrayList;

import javax.swing.*;



public class Attendance_GUI {

	public JFrame frmSubmitAttendance;
	private JComboBox roll_field;
	private String date;
	private String course;

	public Attendance_GUI(String course1, String date1) {
		date = date1;
		course = course1.toLowerCase();
		initialize(course1,date1);
	}

	private void initialize(String course, String date) {
		frmSubmitAttendance = new JFrame();
		frmSubmitAttendance.setTitle("Submit Attendance");
		frmSubmitAttendance.setBounds(100, 100, 600, 250);
		frmSubmitAttendance.getContentPane().setBackground(new Color(240, 248, 255));
		frmSubmitAttendance.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmSubmitAttendance.getContentPane().setLayout(null);
		

		
		JLabel lblHelloAdmin = new JLabel("Submit Attendance for "+course+" on "+date);
		lblHelloAdmin.setForeground(new Color(153, 0, 204));
		lblHelloAdmin.setHorizontalAlignment(SwingConstants.CENTER);
		lblHelloAdmin.setBounds(12, 13, 558, 30);
		lblHelloAdmin.setFont(new Font("Montserrat", Font.BOLD, 18));
		frmSubmitAttendance.getContentPane().add(lblHelloAdmin);
		
		JLabel roll_title = new JLabel("Registration No.");
		roll_title.setBounds(14, 70, 132, 42);
		roll_title.setFont(new Font("Montserrat", Font.BOLD, 15));
		roll_title.setHorizontalAlignment(SwingConstants.CENTER);
		frmSubmitAttendance.getContentPane().add(roll_title);
		
		roll_field = new JComboBox();
		roll_field.setEditable(false);
		roll_field.setModel(new DefaultComboBoxModel(getStudents(course)));
		roll_field.setToolTipText("Enter Course ID...");
		roll_field.setBounds(155, 75, 180, 30);
		frmSubmitAttendance.getContentPane().add(roll_field);
		
		
		Button present = new Button("Present");
		present.setBackground(new Color(50, 205, 50));
		present.setBounds(346, 67, 100, 45);
		
		present.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				markAttendance((String)roll_field.getSelectedItem(), true);
			}  
		});
		
		frmSubmitAttendance.getContentPane().add(present);
		
		Button absent = new Button("Absent");
		absent.setBackground(new Color(255, 69, 0));
		absent.setBounds(459, 67, 100, 45);
		
		absent.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				markAttendance((String)roll_field.getSelectedItem(),false);
			}  
		});
		
		frmSubmitAttendance.getContentPane().add(absent);
		
		Button submit = new Button("Submit");
		submit.setForeground(Color.BLACK);
		submit.setBackground(Color.ORANGE);
		submit.setBounds(228, 128, 120, 45);
		
		submit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				ArrayList<String> remain = checkAttendance();
				if(remain.isEmpty() ) {
					JOptionPane.showMessageDialog(frmSubmitAttendance, "Attendance Submitted Successfully");
					frmSubmitAttendance.dispose();
				}else {
					JOptionPane.showMessageDialog(frmSubmitAttendance, "Remaining Students :\n"+remain.toString());
				}
				
			}  
		});
		
		frmSubmitAttendance.getContentPane().add(submit);
		
		frmSubmitAttendance.setVisible(true);
		
	}

	protected ArrayList<String> checkAttendance() {
		try {
			ArrayList<String> remain = new ArrayList<String>();
			Database db = new Database();
			String getCurr = "SELECT idUser,attendance FROM student_ams."+course+";";
			ResultSet rs = db.executeQuery(getCurr);
			while(rs.next()) {
				String currAtt = rs.getString("attendance");
				if(currAtt == null || !currAtt.contains(date)) {
					remain.add(rs.getString("idUser"));
				}
			}
			
			return remain;

		}catch(Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	protected void markAttendance(String roll, boolean isPresent) {
		try {
			Database db = new Database();
			String getCurr = "SELECT attendance FROM student_ams."+course+" WHERE idUser ='"+roll+"';";
			ResultSet rs = db.executeQuery(getCurr);
			rs.next();
			String currAtt = rs.getString("attendance");
			
			if(isPresent) {
				if(currAtt == null || currAtt.isEmpty()) {
					String newAtt = date;
					String addSQL ="UPDATE `student_ams`.`"+ course +"` SET `attendance` = '"+newAtt+"' WHERE (`idUser` = '"+roll+"');";
					db.executeQuery(addSQL);
				}
				else {
					int pos = currAtt.indexOf(date);
					if(pos == -1) {
						String newAtt = currAtt+","+date;
						String addSQL ="UPDATE `student_ams`.`"+ course +"` SET `attendance` = '"+newAtt+"' WHERE (`idUser` = '"+roll+"');";
						db.executeQuery(addSQL);
					}
					else {
						if(pos-1 != -1 && currAtt.charAt(pos-1) == 'X') {
							StringBuffer mod = new StringBuffer(currAtt);
							mod.deleteCharAt(pos-1);
							String addSQL ="UPDATE `student_ams`.`"+ course +"` SET `attendance` = '"+mod.toString()+"' WHERE (`idUser` = '"+roll+"');";
							db.executeQuery(addSQL);
							
						}
						else {
							JOptionPane.showMessageDialog(null, "Student already marked present at given date");
						}
					}

				}
			}
			else {
				if(currAtt == null || currAtt.isEmpty()) {
					String newAtt = "X"+date;
					String addSQL ="UPDATE `student_ams`.`"+ course +"` SET `attendance` = '"+newAtt+"' WHERE (`idUser` = '"+roll+"');";
					db.executeQuery(addSQL);
				}
				else {
					int pos = currAtt.indexOf(date);
					if(pos == -1) {
						String newAtt = currAtt+",X"+date;
						String addSQL ="UPDATE `student_ams`.`"+ course +"` SET `attendance` = '"+newAtt+"' WHERE (`idUser` = '"+roll+"');";
						db.executeQuery(addSQL);
					}
					else {
						if(pos-1 != -1 && currAtt.charAt(pos-1) == 'X') {
							JOptionPane.showMessageDialog(null, "Student already marked absent at given date");
							
						}
						else {
							StringBuffer mod = new StringBuffer(currAtt);
							mod.insert(pos, 'X');
							String addSQL ="UPDATE `student_ams`.`"+ course +"` SET `attendance` = '"+mod.toString()+"' WHERE (`idUser` = '"+roll+"');";
							db.executeQuery(addSQL);
						}
					}

				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
	}

	private Object[] getStudents(String course) {
		
		ArrayList<String> list = new ArrayList<String>();
		try {
			Database db = new Database();
			String stud = "SELECT idUser FROM "+course.toLowerCase()+";";
			ResultSet rs = db.executeQuery(stud);
			
			if(!rs.isBeforeFirst()) {
				JOptionPane.showMessageDialog(null, "No students in selected course.\nPlease close the window and try again with another Course ID.");
				frmSubmitAttendance.dispose();
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
	
	public static void main(String[] args) {
		Attendance_GUI ag = new Attendance_GUI("ec101","10/04/2020");
	}
}
