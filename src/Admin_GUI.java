import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Admin_GUI {

	private JFrame frmAdminHome;


	public Admin_GUI(String user) {
		initialize(user);
	}

	private void initialize(String user) {
		frmAdminHome = new JFrame();
		frmAdminHome.setTitle("Admin Home");
		frmAdminHome.setBounds(100, 100, 450, 300);
		frmAdminHome.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmAdminHome.getContentPane().setLayout(null);
		frmAdminHome.getContentPane().setBackground(new Color(240, 248, 255));
		
		Button addStud = new Button("Add Student");
		addStud.setBounds(40, 60, 146, 55);
		addStud.setBackground(new Color(30, 144, 255));
		
		addStud.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				AddStudent as = new AddStudent();
			}  
		});
		frmAdminHome.getContentPane().add(addStud);
		
		Button addTea = new Button("Add Teacher");
		addTea.setBounds(245, 60, 146, 55);
		addTea.setBackground(new Color(30, 144, 255));
		
		addTea.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				AddTeacher at = new AddTeacher();
			}  
		});
		frmAdminHome.getContentPane().add(addTea);
		
		Button addCour = new Button("Add Course");
		addCour.setBounds(40, 150, 146, 60);
		addCour.setBackground(new Color(30, 144, 255));
		
		addCour.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				AddCourse ac = new AddCourse();
			}  
		});
		frmAdminHome.getContentPane().add(addCour);
		
		Button studCour = new Button("Add Student to Course");
		studCour.setBounds(245, 150, 146, 60);
		studCour.setBackground(new Color(30, 144, 255));
		
		studCour.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				StudentCourse sc = new StudentCourse();
			}  
		});
		frmAdminHome.getContentPane().add(studCour);
		
		JLabel lblHelloAdmin = new JLabel("Hello Admin!");
		lblHelloAdmin.setForeground(new Color(153, 0, 204));
		lblHelloAdmin.setHorizontalAlignment(SwingConstants.CENTER);
		lblHelloAdmin.setBounds(143, 13, 146, 30);
		lblHelloAdmin.setFont(new Font("Montserrat", Font.BOLD, 15));
		frmAdminHome.getContentPane().add(lblHelloAdmin);
		
		
		frmAdminHome.setVisible(true);
	}

}
