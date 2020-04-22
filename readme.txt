Student Attendance Management System

By Eswar Prasad Clinton. A, 411818, II year CSE, NIT Andhra Pradesh.

----------------------------***********************----------------------------

*******IMPORTANT*******

The SQL file needed to initialize the database and tables required is attached with the name 'SQL_AMS.sql'. THIS NEEDS TO BE DONE BEFORE THE ACTIVATION OF THE SYSTEM.

The System can be activated in any of the two ways:
    1. By executing the runnable JAR file 'Attendance_Management_System.jar'.
    2. Importing the project into Eclipse(preferred IDE) and running it.

The Default Admin credentials are as follows:
    Username: 011011
    Password: passadmin

Out of the box, the DEFAULT ADMIN IS THE ONE AND ONLY USER. Log in using the Default Admin credentials to create new teachers, students, courses and use other functionalities.  

Please refer the class diagram 'ClassDiagram.jpg' for better understanding.

***********************

The system is built using Java Swing for the Graphical User Interface(GUI) and JDBC(Java Database Connectivity) with MySQL as the Database.

The SQL file needed to initialize the database and tables required is attached with the name 'SQL_AMS.sql'. THIS NEEDS TO BE DONE BEFORE THE ACTIVATION OF THE SYSTEM.
The SQL file shall preferably be opened and executed in MySQL Workbench to have a clear understanding of the database. The SQL file is created using 'Data Export' feature of MySQL Workbench. 

The System can be activated in any of the two ways:
    1. By executing the runnable JAR file 'Attendance_Management_System.jar'.
    2. Importing the project into Eclipse(preferred IDE) and running it.

The Source Code is available at 'src' folder. 

The Attendance Management System can be used by 3 types of users :
    1. Admin
    2. Teacher
    3. Student

The Admin users are expected to have some command over Java, MySQL and MySQL Workbench while the other users(Teacher and Student) are expected to have minimum computer user knowledge.

Although MOST OF THE EXCEPTIONS ARE ALREADY FOUND AND HANDLED by appropriate message dialogs, users are expected to follow along the system and not enter absurd data.

Following are the functionalities of different types of users:
    1. Admin:
        i. Add Student.
        ii. Add Teacher.
        iii. Add Course.
        iv. Add Students to Course.

    2. Teacher:
        i. Take Attendance.
        ii. Get Attendance Report.
        iii. Get Details of him/herself.
        iv. Change Password.

    3. Student:
        i. Check Attendance.
        ii. Change Password.

NOTE:   Deleting(also Updating) of records such as credentials, teachers, students, courses, etc. have to be done in the database using proper MySQL queries or MySQL Workbench.
        Deleting has not been included in the application or GUI considering the scope of the system(Attendance Management).

If at any point in the system, a bug is encountered or a feature fails or something out of the blue occurs, please run the system using Eclipse(preferred IDE) or mail the issue to 'esuclintblazer@gmail.com'.

For further use/testing of the system, please use the DEFAULT ADMIN to create new teachers and students and test the functionalities. Please use MySQL Queries or MySQL workbench to create new Admin.
