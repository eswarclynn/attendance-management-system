import javax.swing.*;
import java.awt.Font;
import java.sql.ResultSet;
import java.awt.Color;

public class DateReport {

	private JFrame frmAttendanceDateReport;

	public DateReport(String date, String course) {
		initialize(date, course);
	}

	private void initialize(String date, String course) {
		frmAttendanceDateReport = new JFrame();
		frmAttendanceDateReport.getContentPane().setBackground(new Color(240, 248, 255));
		frmAttendanceDateReport.setTitle("Attendance Date Report");
		frmAttendanceDateReport.setBounds(1100, 100, 450, 521);
		frmAttendanceDateReport.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmAttendanceDateReport.getContentPane().setLayout(null);
		frmAttendanceDateReport.setVisible(true);
		
		JLabel head_title = new JLabel("Attendance Report ");
		head_title.setBounds(0, 0, 432, 37);
		head_title.setHorizontalAlignment(SwingConstants.CENTER);
		head_title.setForeground(new Color(153, 0, 204));
		head_title.setFont(new Font("Montserrat", Font.BOLD, 18));
		frmAttendanceDateReport.getContentPane().add(head_title);
		
		JLabel subhead_title = new JLabel("for "+course+" on "+date);
		subhead_title.setBounds(11, 29, 410, 30);
		subhead_title.setHorizontalAlignment(SwingConstants.CENTER);
		subhead_title.setForeground(new Color(153, 0, 204));
		subhead_title.setFont(new Font("Montserrat", Font.BOLD, 18));
		frmAttendanceDateReport.getContentPane().add(subhead_title);
		
		String[] stud_list = getStudents(date,course);
		
		JTextPane presentDates = new JTextPane();
		presentDates.setText(stud_list[0]);
		presentDates.setEditable(false);
		JScrollPane presentSP = new JScrollPane(presentDates);
		presentSP.setBounds(24, 90, 182, 350);
		frmAttendanceDateReport.getContentPane().add(presentSP);
		
		JTextPane absentDates = new JTextPane();
		absentDates.setText(stud_list[1]);
		absentDates.setEditable(false);
		JScrollPane absentSP = new JScrollPane(absentDates);
		absentSP.setBounds(227, 90, 182, 350);
		frmAttendanceDateReport.getContentPane().add(absentSP);
		
		JLabel lblPresentDates = new JLabel("Present Students");
		lblPresentDates.setHorizontalAlignment(SwingConstants.CENTER);
		lblPresentDates.setForeground(new Color(51, 204, 102));
		lblPresentDates.setFont(new Font("Montserrat", Font.BOLD, 18));
		lblPresentDates.setBounds(24, 57, 182, 30);
		frmAttendanceDateReport.getContentPane().add(lblPresentDates);
		
		JLabel lblAbsentDates = new JLabel("Absent Students");
		lblAbsentDates.setHorizontalAlignment(SwingConstants.CENTER);
		lblAbsentDates.setForeground(new Color(255, 51, 51));
		lblAbsentDates.setFont(new Font("Montserrat", Font.BOLD, 18));
		lblAbsentDates.setBounds(227, 57, 182, 30);
		frmAttendanceDateReport.getContentPane().add(lblAbsentDates);
		
		
	}

	private String[] getStudents(String date, String course) {
		String[] studs = {"",""};
		try {
			Database db = new Database();
			String getSQL = "SELECT idUser,attendance FROM student_ams."+course;
			ResultSet rs = db.executeQuery(getSQL);
			if(rs == null) {
				JOptionPane.showMessageDialog(null, "No students enrolled in this course!");
				frmAttendanceDateReport.dispose();
			} else {
				int f=0,g=0;
				while(rs.next()) {
					f++;
					int pos = rs.getString("attendance").indexOf(date);
					if(pos == -1) {
						g++;

					}else {
						try {
							if(rs.getString("attendance").charAt(pos-1) == 'X') {
								studs[1] += rs.getString("idUser") + "\r\n";
							}else {
								studs[0] += rs.getString("idUser") + "\r\n";
							}
						}catch(StringIndexOutOfBoundsException e) {
							studs[0] += rs.getString("idUser") + "\r\n";
						}

					}
				}
				
				if(f==g) {
					JOptionPane.showMessageDialog(null, "Attendance has not been taken for given date yet!");
					frmAttendanceDateReport.dispose();
				}
			}
			
			return studs;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
