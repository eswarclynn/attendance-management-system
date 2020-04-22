import java.awt.Color;
import java.awt.Font;

import javax.swing.*;


public class Dates_List {

	private JFrame frmDatesList;

	public Dates_List(String[] dates) {
		initialize(dates);
	}


	private void initialize(String[] dates) {
		frmDatesList = new JFrame();
		frmDatesList.setTitle("Dates List");
		frmDatesList.setBounds(1100, 100, 450, 489);
		frmDatesList.getContentPane().setBackground(new Color(240, 248, 255));
		frmDatesList.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmDatesList.getContentPane().setLayout(null);
		
		JLabel lblHelloAdmin = new JLabel("Dates List");
		lblHelloAdmin.setForeground(new Color(153, 0, 204));
		lblHelloAdmin.setHorizontalAlignment(SwingConstants.CENTER);
		lblHelloAdmin.setBounds(98, 13, 236, 30);
		lblHelloAdmin.setFont(new Font("Montserrat", Font.BOLD, 18));
		frmDatesList.getContentPane().add(lblHelloAdmin);
		
		JTextPane presentDates = new JTextPane();
		presentDates.setText(dates[0]);
		presentDates.setEditable(false);
		JScrollPane presentSP = new JScrollPane(presentDates);
		presentSP.setBounds(24, 77, 182, 350);
		frmDatesList.getContentPane().add(presentSP);
		
		JTextPane absentDates = new JTextPane();
		absentDates.setText(dates[1]);
		absentDates.setEditable(false);
		JScrollPane absentSP = new JScrollPane(absentDates);
		absentSP.setBounds(227, 77, 182, 350);
		frmDatesList.getContentPane().add(absentSP);
		
		JLabel lblPresentDates = new JLabel("Present Dates");
		lblPresentDates.setHorizontalAlignment(SwingConstants.CENTER);
		lblPresentDates.setForeground(new Color(51, 204, 102));
		lblPresentDates.setFont(new Font("Montserrat", Font.BOLD, 18));
		lblPresentDates.setBounds(40, 45, 151, 30);
		frmDatesList.getContentPane().add(lblPresentDates);
		
		JLabel lblAbsentDates = new JLabel("Absent Dates");
		lblAbsentDates.setHorizontalAlignment(SwingConstants.CENTER);
		lblAbsentDates.setForeground(new Color(255, 51, 51));
		lblAbsentDates.setFont(new Font("Montserrat", Font.BOLD, 18));
		lblAbsentDates.setBounds(244, 45, 151, 30);
		frmDatesList.getContentPane().add(lblAbsentDates);
		
		frmDatesList.setVisible(true);
	}
	
}
