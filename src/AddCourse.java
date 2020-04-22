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


public class AddCourse {

	public JFrame frmAddCourse;
	private JTextField user_field;
	private JTextField name_field;
	private JTextField branch_field;
	private JTextField teacher_field;

	public AddCourse() {
		initialize();
	}

	private void initialize() {
		frmAddCourse = new JFrame();
		frmAddCourse.setTitle("Add Course");
		frmAddCourse.setBounds(100, 100, 600, 450);
		frmAddCourse.getContentPane().setBackground(new Color(240, 248, 255));
		frmAddCourse.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmAddCourse.getContentPane().setLayout(null);
		
		JLabel lblHelloAdmin = new JLabel("Enter New Course Details");
		lblHelloAdmin.setForeground(new Color(153, 0, 204));
		lblHelloAdmin.setHorizontalAlignment(SwingConstants.CENTER);
		lblHelloAdmin.setBounds(145, 13, 292, 30);
		lblHelloAdmin.setFont(new Font("Montserrat", Font.BOLD, 18));
		frmAddCourse.getContentPane().add(lblHelloAdmin);
		
		JLabel user_title = new JLabel("Course ID :");
		user_title.setBounds(59, 70, 174, 42);
		user_title.setFont(new Font("Montserrat", Font.BOLD, 15));
		user_title.setHorizontalAlignment(SwingConstants.CENTER);
		frmAddCourse.getContentPane().add(user_title);
		
		
		JLabel name_title = new JLabel("Name :");
		name_title.setBounds(59, 120, 174, 42);
		name_title.setFont(new Font("Montserrat", Font.BOLD, 15));
		name_title.setHorizontalAlignment(SwingConstants.CENTER);
		frmAddCourse.getContentPane().add(name_title);
		
		JLabel branch_title = new JLabel("Branch :");
		branch_title.setBounds(59, 170, 174, 42);
		branch_title.setFont(new Font("Montserrat", Font.BOLD, 15));
		branch_title.setHorizontalAlignment(SwingConstants.CENTER);
		frmAddCourse.getContentPane().add(branch_title);
		
		JLabel teacher_title = new JLabel("Teacher ID :");
		teacher_title.setBounds(59, 220, 174, 42);
		teacher_title.setFont(new Font("Montserrat", Font.BOLD, 15));
		teacher_title.setHorizontalAlignment(SwingConstants.CENTER);
		frmAddCourse.getContentPane().add(teacher_title);
		
		user_field = new JTextField();
		user_field.setToolTipText("Enter Course ID...");
		user_field.setColumns(10);
		user_field.setBounds(304, 75, 180, 30);
		frmAddCourse.getContentPane().add(user_field);
		
		name_field = new JTextField();
		name_field.setToolTipText("Enter Name...");
		name_field.setColumns(10);
		name_field.setBounds(304, 125, 180, 30);
		frmAddCourse.getContentPane().add(name_field);
		
		branch_field = new JTextField();
		branch_field.setToolTipText("Enter Branch...");
		branch_field.setColumns(10);
		branch_field.setBounds(304, 175, 180, 30);
		frmAddCourse.getContentPane().add(branch_field);
		
		teacher_field = new JTextField();
		teacher_field.setToolTipText("Enter Teacher ID...");
		teacher_field.setColumns(10);
		teacher_field.setBounds(304, 225, 180, 30);
		frmAddCourse.getContentPane().add(teacher_field);
		
		Button submit = new Button("Submit");
		submit.setBackground(new Color(50, 205, 50));
		submit.setBounds(169, 290, 100, 45);
		
		submit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				addNew();
			}  
		});
		
		frmAddCourse.getContentPane().add(submit);
		
		Button cancel = new Button("Cancel");
		cancel.setBackground(new Color(255, 69, 0));
		cancel.setBounds(304, 290, 100, 45);
		
		cancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				frmAddCourse.dispose();
			}  
		});
		
		frmAddCourse.getContentPane().add(cancel);
		
		frmAddCourse.setVisible(true);
	}

	protected void addNew() {
			String user = user_field.getText();
			String name = name_field.getText();
			String branch = branch_field.getText();
			String teach = teacher_field.getText();
			
			if(user.isEmpty() || name.isEmpty() || branch.isEmpty() || teach.isEmpty()) {
				JOptionPane.showMessageDialog(null, "Enter every value to submit.");
			}
			
			Database db = new Database();
			try {
				ResultSet rs = db.executeQuery("SELECT * FROM courses_list WHERE idcourse=\""+user+"\";");	
				if(rs.next() == true) {
					JOptionPane.showMessageDialog(null, "Course ID already exists.\r\n If you want to create new course for already existing subject(course), suffix Course ID with 'A,B,..'");
				}
				else {
					String teaSQL = "SELECT iduser FROM student_ams.teacher_details WHERE iduser='"+teach+"';";
					ResultSet rs1 = db.executeQuery(teaSQL);
					if(rs1 == null) {
						JOptionPane.showMessageDialog(null, "Teacher ID doesn't exist!");

					}else {
						String pattern = "^([A-Z]{2})([0-9]{3})([A-z]?)";;
						if(user.matches(pattern)) {
							try{
								db.executeQuery("INSERT INTO `student_ams`.`courses_list` (`idcourse`, `name`, `branch`, `idteacher`) VALUES ('"+user+"', '"+name+"', '"+branch+"', '"+teach+"');");
								
								String createTable = "CREATE TABLE `student_ams`.`"+user+"` (\r\n" + 
										"  `iduser` VARCHAR(6) NOT NULL,\r\n" + 
										"  `attendance` VARCHAR(2000) NULL DEFAULT '',\r\n" + 
										"  PRIMARY KEY (`iduser`),\r\n" +  
										"    FOREIGN KEY (`iduser`)\r\n" + 
										"    REFERENCES `student_ams`.`student_details` (`iduser`)\r\n" + 
										"    ON DELETE NO ACTION\r\n" + 
										"    ON UPDATE NO ACTION);";
								db.executeQuery(createTable);
								
							
								
								JOptionPane.showMessageDialog(null, "New Course Added Successfully");
								frmAddCourse.dispose();
							}catch(ArithmeticException e) {
								JOptionPane.showMessageDialog(null, "Invalid Teacher.");
								
							}

							
						}else {
							JOptionPane.showMessageDialog(null, "Invalid Course ID");
						}
					}
				}
			} catch (SQLException e) {

				e.printStackTrace();
			}
		
	}
}
