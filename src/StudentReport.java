import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.BorderLayout;
import java.awt.Button;

public class StudentReport {

	private JFrame frmStudentReport;

	public StudentReport(String roll, String course) {
		initialize(roll,course);
	}

	private void initialize(String roll, String course) {
		frmStudentReport = new JFrame();
		frmStudentReport.setBounds(580, 100, 450, 400);
		frmStudentReport.getContentPane().setLayout(null);
		frmStudentReport.getContentPane().setBackground(new Color(240, 248, 255));
		frmStudentReport.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		JLabel lblWelcome = new JLabel("Attendance Report");
		lblWelcome.setForeground(new Color(153, 0, 153));
		lblWelcome.setBounds(140, 13, 150, 16);
		lblWelcome.setHorizontalAlignment(SwingConstants.CENTER);
		lblWelcome.setFont(new Font("Bebas", Font.BOLD, 19));
		frmStudentReport.getContentPane().add(lblWelcome, BorderLayout.NORTH);
		
		JLabel lblDetails = new JLabel("For "+roll+" for course "+course);	
		lblDetails.setForeground(new Color(153, 0, 153));
		lblDetails.setBounds(64, 39, 308, 16);
		lblDetails.setHorizontalAlignment(SwingConstants.CENTER);
		lblDetails.setFont(new Font("Bebas", Font.BOLD, 19));
		frmStudentReport.getContentPane().add(lblDetails, BorderLayout.NORTH);
		
		ArrayList<Double> attNumbers = getNumbers(roll,course);
		
		JLabel total_title = new JLabel("Total Classes :");
		total_title.setBounds(76, 99, 150, 16);
		total_title.setFont(new Font("Montserrat", Font.BOLD, 15));
		frmStudentReport.getContentPane().add(total_title);
		
		JLabel total_field = new JLabel(attNumbers.get(0).toString());
		total_field.setFont(new Font("Tahoma", Font.PLAIN, 18));
		total_field.setBounds(283, 99, 103, 16);
		frmStudentReport.getContentPane().add(total_field);
		
		JLabel pres_title = new JLabel("Present Classes :");
		pres_title.setBounds(76, 159, 150, 16);
		pres_title.setFont(new Font("Montserrat", Font.BOLD, 15));
		frmStudentReport.getContentPane().add(pres_title);
		
		JLabel pres_field = new JLabel(attNumbers.get(1).toString());
		pres_field.setFont(new Font("Tahoma", Font.PLAIN, 18));
		pres_field.setBounds(283, 159, 103, 16);
		frmStudentReport.getContentPane().add(pres_field);
		
		JLabel absent_title = new JLabel("Absent Classes :");
		absent_title.setBounds(76, 219, 150, 16);
		absent_title.setFont(new Font("Montserrat", Font.BOLD, 15));
		frmStudentReport.getContentPane().add(absent_title);
		
		JLabel absent_field = new JLabel(attNumbers.get(2).toString());
		absent_field.setFont(new Font("Tahoma", Font.PLAIN, 18));
		absent_field.setBounds(283, 219, 103, 16);
		frmStudentReport.getContentPane().add(absent_field);
		
		JLabel percent_title = new JLabel("Percentage :");
		percent_title.setBounds(76, 279, 150, 16);
		percent_title.setFont(new Font("Montserrat", Font.BOLD, 15));
		frmStudentReport.getContentPane().add(percent_title);
		
		JLabel percent_field = new JLabel(String.format("%.2f", attNumbers.get(3))+"%");
		percent_field.setFont(new Font("Tahoma", Font.PLAIN, 18));
		percent_field.setBounds(283, 279, 103, 16);
		frmStudentReport.getContentPane().add(percent_field);
		
		Button btnDates = new Button("Dates List");
		btnDates.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnDates.setForeground(new Color(255, 255, 204));
		btnDates.setBounds(150, 308, 130, 35);
		btnDates.setBackground(new Color(102, 102, 255));
		
		btnDates.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String[] dates_list = getDates(roll,course);
				Dates_List dl = new Dates_List(dates_list);
				
			}  
		});
		
		frmStudentReport.getContentPane().add(btnDates);
		

		
		frmStudentReport.setVisible(true);

	}

	protected String[] getDates(String roll, String course) {
			String[] dates = {"",""};
			try {
				Database db = new Database();
				String getCurr = "SELECT attendance FROM student_ams."+course+" WHERE idUser ='"+roll+"';";
				ResultSet rs = db.executeQuery(getCurr);
				rs.next();
				String currAtt = rs.getString("attendance");
				if(currAtt == null || currAtt.isEmpty()) {
					JOptionPane.showMessageDialog(frmStudentReport, "Student has not been marked any attendance");
					frmStudentReport.dispose();
				}else {
					String[] att_list = currAtt.split(",");
					for(String att : att_list) {
						if(att.startsWith("X")) {
							StringBuffer att1 = new StringBuffer(att);
							att1.deleteCharAt(0);
							dates[1] += att1.toString() + "\r\n";
						}else {
							dates[0] += att+"\r\n";
						}
					}
				}
				return dates;
			}catch(Exception e) {
				e.printStackTrace();
			}

			
		return null;
	}

	private ArrayList<Double> getNumbers(String roll, String course) {
		try {
			Double absent=0.0, present=0.0;
			ArrayList<Double> numbers = new ArrayList<Double>();
			
			Database db = new Database();
			String getCurr = "SELECT attendance FROM student_ams."+course+" WHERE idUser ='"+roll+"';";
			ResultSet rs = db.executeQuery(getCurr);
			rs.next();
			String currAtt = rs.getString("attendance");
			if(currAtt == null || currAtt.isEmpty()) {
				JOptionPane.showMessageDialog(frmStudentReport, "Student has not been marked any attendance");
				frmStudentReport.dispose();
			}else {
				String[] att_list = currAtt.split(",");
				for(String att : att_list) {
					if(att.startsWith("X")) {
						absent++;
					}else {
						present++;
					}
				}
				Double percent = (present/(present+absent))*100;
				
				numbers.add(present + absent);
				numbers.add(present);
				numbers.add(absent);
				numbers.add(percent);
				
				return numbers;
			}

		}catch(Exception e) {
			e.printStackTrace();
		}

		return null;
	}

}
