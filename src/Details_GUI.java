import java.awt.Button;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.sql.ResultSet;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@SuppressWarnings("unused")
public class Details_GUI {

	private JFrame frmStudentDetails;

	public Details_GUI(String username, String mode) {
		if(mode.equals("Student")) {
			initialize_student(username);
		}else if(mode.equals("Teacher")) {
			initialize_teacher(username);
		}

	}

	private void initialize_teacher(String username) {
		String user = null;
		String name = null;
		String branch = null;
		String phone = null;
		
		Database db = new Database();
		String sql;
		sql = "SELECT iduser,name,branch,phone FROM teacher_details WHERE iduser="+username+";";
		ResultSet rs = db.executeQuery(sql);
		try {
			if(rs.next()) {
				user = rs.getString("iduser");
				name = rs.getString("name");
				branch = rs.getString("branch");
				phone = rs.getString("phone");
			}

		}catch(Exception e) {
			e.printStackTrace();
		}
		final String userName = user;
		
		frmStudentDetails = new JFrame();
		frmStudentDetails.setTitle("Teacher Details");
		frmStudentDetails.setBounds(650, 100, 600, 400);
		frmStudentDetails.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmStudentDetails.getContentPane().setBackground(new Color(240, 248, 255));
		frmStudentDetails.getContentPane().setLayout(null);
		
		JLabel lblWelcome = new JLabel("Welcome, PROF."+name+"!");
		lblWelcome.setBounds(0, 0, 582, 75);
		lblWelcome.setHorizontalAlignment(SwingConstants.CENTER);
		lblWelcome.setFont(new Font("Bebas", Font.BOLD, 20));
		frmStudentDetails.getContentPane().add(lblWelcome);
		
		JLabel reg_title = new JLabel("Registration No.");
		reg_title.setBounds(76, 99, 150, 16);
		reg_title.setFont(new Font("Montserrat", Font.BOLD, 15));
		frmStudentDetails.getContentPane().add(reg_title);
		
		JLabel reg_field = new JLabel(user);
		reg_field.setBounds(378, 99, 180, 16);
		frmStudentDetails.getContentPane().add(reg_field);
		
		JLabel name_title = new JLabel("Name");
		name_title.setBounds(76, 159, 150, 16);
		name_title.setFont(new Font("Montserrat", Font.BOLD, 15));
		frmStudentDetails.getContentPane().add(name_title);
		
		JLabel name_field = new JLabel(name);
		name_field.setBounds(378, 159, 180, 16);
		frmStudentDetails.getContentPane().add(name_field);
		
		JLabel branch_title = new JLabel("Branch");
		branch_title.setBounds(76, 219, 150, 16);
		branch_title.setFont(new Font("Montserrat", Font.BOLD, 15));
		frmStudentDetails.getContentPane().add(branch_title);
		
		JLabel branch_field = new JLabel(branch);
		branch_field.setBounds(378, 219, 180, 16);
		frmStudentDetails.getContentPane().add(branch_field);
		
		JLabel phone_title = new JLabel("Phone No.");
		phone_title.setBounds(76, 279, 150, 16);
		phone_title.setFont(new Font("Montserrat", Font.BOLD, 15));
		frmStudentDetails.getContentPane().add(phone_title);
		
		JLabel phone_field = new JLabel(phone);
		phone_field.setBounds(378, 279, 180, 16);
		frmStudentDetails.getContentPane().add(phone_field);
		
		frmStudentDetails.setVisible(true);

		
	}

	private void initialize_student(String username) {
		
		String user = null;
		String name = null;
		int year = 0;
		String branch = null;
		String phone = null;
		
		Database db = new Database();
		String sql;
		sql = "SELECT iduser,name,year,branch,phone FROM student_details WHERE iduser="+username+";";
		ResultSet rs = db.executeQuery(sql);
		try {
			if(rs.next()) {
				user = rs.getString("iduser");
				name = rs.getString("name");
				year = rs.getInt("year");
				branch = rs.getString("branch");
				phone = rs.getString("phone");
			}

		}catch(Exception e) {
			e.printStackTrace();
		}
		final String userName = user;
		
		frmStudentDetails = new JFrame();
		frmStudentDetails.setTitle("Student Details");
		frmStudentDetails.setBounds(650, 100, 600, 430);
		frmStudentDetails.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmStudentDetails.getContentPane().setBackground(new Color(240, 248, 255));
		frmStudentDetails.getContentPane().setLayout(null);
		
		JLabel lblWelcome = new JLabel("Welcome, "+name+"!");
		lblWelcome.setBounds(0, 0, 582, 75);
		lblWelcome.setHorizontalAlignment(SwingConstants.CENTER);
		lblWelcome.setFont(new Font("Bebas", Font.BOLD, 20));
		frmStudentDetails.getContentPane().add(lblWelcome);
		
		JLabel reg_title = new JLabel("Registration No.");
		reg_title.setBounds(76, 99, 150, 16);
		reg_title.setFont(new Font("Montserrat", Font.BOLD, 15));
		frmStudentDetails.getContentPane().add(reg_title);
		
		JLabel reg_field = new JLabel(user);
		reg_field.setBounds(378, 99, 180, 16);
		frmStudentDetails.getContentPane().add(reg_field);
		
		JLabel name_title = new JLabel("Name");
		name_title.setBounds(76, 159, 150, 16);
		name_title.setFont(new Font("Montserrat", Font.BOLD, 15));
		frmStudentDetails.getContentPane().add(name_title);
		
		JLabel name_field = new JLabel(name);
		name_field.setBounds(378, 159, 180, 16);
		frmStudentDetails.getContentPane().add(name_field);
		
		JLabel year_title = new JLabel("Year");
		year_title.setBounds(76, 219, 150, 16);
		year_title.setFont(new Font("Montserrat", Font.BOLD, 15));
		frmStudentDetails.getContentPane().add(year_title);
		
		JLabel year_field = new JLabel(new String().valueOf(year));
		year_field.setBounds(378, 219, 56, 16);
		frmStudentDetails.getContentPane().add(year_field);
		
		JLabel branch_title = new JLabel("Branch");
		branch_title.setBounds(76, 279, 150, 16);
		branch_title.setFont(new Font("Montserrat", Font.BOLD, 15));
		frmStudentDetails.getContentPane().add(branch_title);
		
		JLabel branch_field = new JLabel(branch);
		branch_field.setBounds(378, 279, 180, 16);
		frmStudentDetails.getContentPane().add(branch_field);
		
		JLabel phone_title = new JLabel("Phone No.");
		phone_title.setBounds(76, 333, 150, 16);
		phone_title.setFont(new Font("Montserrat", Font.BOLD, 15));
		frmStudentDetails.getContentPane().add(phone_title);
		
		JLabel phone_field = new JLabel(phone);
		phone_field.setBounds(378, 336, 180, 16);
		frmStudentDetails.getContentPane().add(phone_field);
		
		
		frmStudentDetails.setVisible(true);
	}
}
