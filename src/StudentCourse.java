import java.awt.Button;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;


public class StudentCourse {

	public JFrame frmStudentCourse;
	private JTextField user_field;
	private JTextField stud_field;

	public StudentCourse() {
		initialize();
	}

	private void initialize() {
		frmStudentCourse = new JFrame();
		frmStudentCourse.setTitle("Add Course");
		frmStudentCourse.setBounds(100, 100, 600, 300);
		frmStudentCourse.getContentPane().setBackground(new Color(240, 248, 255));
		frmStudentCourse.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmStudentCourse.getContentPane().setLayout(null);
		
		JLabel lblHelloAdmin = new JLabel("Add Students to Course");
		lblHelloAdmin.setForeground(new Color(153, 0, 204));
		lblHelloAdmin.setHorizontalAlignment(SwingConstants.CENTER);
		lblHelloAdmin.setBounds(145, 13, 292, 30);
		lblHelloAdmin.setFont(new Font("Montserrat", Font.BOLD, 18));
		frmStudentCourse.getContentPane().add(lblHelloAdmin);
		
		JLabel user_title = new JLabel("Course ID :");
		user_title.setBounds(59, 70, 174, 42);
		user_title.setFont(new Font("Montserrat", Font.BOLD, 15));
		user_title.setHorizontalAlignment(SwingConstants.CENTER);
		frmStudentCourse.getContentPane().add(user_title);
		
		JLabel stud_title = new JLabel("Enter Student ID's :");
		stud_title.setBounds(59, 120, 174, 42);
		stud_title.setFont(new Font("Montserrat", Font.BOLD, 15));
		stud_title.setHorizontalAlignment(SwingConstants.CENTER);
		frmStudentCourse.getContentPane().add(stud_title);
		
		user_field = new JTextField();
		user_field.setToolTipText("Enter Course ID...");
		user_field.setColumns(10);
		user_field.setBounds(304, 75, 180, 30);
		frmStudentCourse.getContentPane().add(user_field);
		
		stud_field = new JTextField();
		stud_field.setToolTipText("Enter attending Student ID's");
		stud_field.setColumns(10);
		stud_field.setBounds(304, 125, 180, 30);
		frmStudentCourse.getContentPane().add(stud_field);
		
		Button submit = new Button("Submit");
		submit.setBackground(new Color(50, 205, 50));
		submit.setBounds(169, 180, 100, 45);
		
		submit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				addNew();
			}  
		});
		
		frmStudentCourse.getContentPane().add(submit);
		
		Button cancel = new Button("Cancel");
		cancel.setBackground(new Color(255, 69, 0));
		cancel.setBounds(304, 180, 100, 45);
		
		cancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				frmStudentCourse.dispose();
			}  
		});
		
		frmStudentCourse.getContentPane().add(cancel);
		
		JLabel label = new JLabel("Eg. 981101,981102,981245");
		label.setBounds(304, 158, 180, 16);
		frmStudentCourse.getContentPane().add(label);
		
		frmStudentCourse.setVisible(true);
	}

	protected void addNew() {
			String user = user_field.getText();
			String stud = stud_field.getText();
			
			if(user.isEmpty() || stud.isEmpty()) {
				JOptionPane.showMessageDialog(null, "Enter every value to submit.");
			}
			
			Database db = new Database();
			try {
				
				String pattern = "^([A-Z]{2})([0-9]{3})([A-z]?)";
				if(user.matches(pattern)) {
					ResultSet rs = db.executeQuery("SELECT * FROM courses_list WHERE idcourse=\""+user+"\";");	
					if(rs.next() == false) {
						JOptionPane.showMessageDialog(null, "Invalid Course ID.\nCourse doesn't exist.");
					}
					else {
						String broke = null;
						try {
							String[] stud_list = stud.split(",");
							
							for(String idUser : stud_list) {
								broke = idUser;
								String insertStud = "INSERT INTO `student_ams`.`"+user+"` (`idUser`) VALUES ('"+idUser+"');";
								db.executeQuery(insertStud);
							}
							
							JOptionPane.showMessageDialog(null, "New Students Added Successfully");
							frmStudentCourse.dispose();
						}catch(ArithmeticException e) {
							JOptionPane.showMessageDialog(null, "Invalid Student IDs.\r\nRecords from '"+broke+"' are not inserted!");
							
						}

					}
				
				}else {
					JOptionPane.showMessageDialog(null, "Invalid Course ID.");
				}

			} catch (SQLException e) {

				e.printStackTrace();
			}
		
	}

}
