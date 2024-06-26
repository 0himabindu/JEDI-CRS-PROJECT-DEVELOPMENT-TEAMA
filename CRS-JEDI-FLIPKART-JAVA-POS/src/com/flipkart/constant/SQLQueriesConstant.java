/**
 * 
 */
package com.flipkart.constant;



public class SQLQueriesConstant {
	
		//AdminDao Queries
		public static final String DELETE_COURSE_QUERY = "delete from Course where courseCode = ?";
		public static final String ADD_COURSE_QUERY = "insert into Course(courseCode, courseName, seats, professorId, courseFee) values (?, ?, ?, ?, ?)";
		public static final String VIEW_PENDING_ADMISSION_QUERY = "select userId,name, password, role, gender, address, studentId from student, user where isApproved = 0 and studentId = userId";
		public static final String APPROVE_STUDENT_QUERY = "update Student set isApproved = 1 where studentId = ?";
		public static final String ADD_USER_QUERY = "insert into User(userId, name, password, role, gender, address) values (?, ?, ?, ?, ?, ?)";
		public static final String ADD_PROFESSOR_QUERY = "insert into Professor(professorId, department, designation) values (?, ?, ?)";
		public static final String ASSIGN_COURSE_QUERY = "update Course set professorId = ? where courseCode = ?";
		public static final String VIEW_COURSE_QUERY = "select courseCode, courseName, professorId from Course";
		public static final String VIEW_PROFESSOR_QUERY = "select userId, name, gender, department, designation, address from Professor natural join User where userId = professorId";
		public static final String SET_GENERATED_REPORT_CARD_TRUE = "update student set isReportGenerated = 1 where studentId = ?";
		public static final String GET_GENERATED_REPORT_CARD_TRUE = "select isReportGenerated from student where studentId = ?";
		public static final String ADD_STUDENT="insert into student (studentId,department,gradYear,isApproved,isRegistered,isReportGenerated,isPaid) values (?,?,?,0,0,0,0)";
		public static final String IS_APPROVED="select isApproved from student where studentId = ? ";
		public static final String GET_STUDENT_ID="select studentId from student where userId = ? ";
			
		// StudentDao Queries
		//public static final String GET_COURSES_OF_STUDENT="select * from";
		public static final String VIEW_REGISTERED_COURSES=" select * from course inner join registeredcourse on course.courseCode = registeredcourse.courseCode where registeredcourse.studentId = ?";
		public static final String VIEW_AVAILABLE_COURSES=" select * from course where courseCode not in  (select courseCode  from registeredcourse where studentId = ?) and seats > 0";
		public static final String CHECK_COURSE_AVAILABILITY=" select courseCode from registeredcourse where studentId = ? ";
		public static final String DECREMENT_COURSE_SEATS="update course set seats = seats-1 where courseCode = ? ";
		public static final String ADD_COURSE="insert into registeredcourse (studentId,courseCode,grade) values ( ? , ?, ?)";
		public static final String DROP_COURSE_QUERY = "delete from registeredcourse where courseCode = ? AND studentId = ?;";
		public static final String INCREMENT_SEAT_QUERY  = "update course set seats = seats + 1 where  courseCode = ?;";
		public static final String CALCULATE_FEES  = "select sum(courseFee) from course where courseCode in (select courseCode from registeredcourse where studentId = ?);";
		public static final String UPDATE_REGISTRATION_STATUS = "UPDATE Student SET isRegistered = '1' WHERE (studentId = ?);";
		public static final String VIEW_GRADE = "select course.courseCode,course.courseName,registeredcourse.grade from course inner join registeredcourse on course.courseCode = registeredcourse.courseCode where registeredcourse.studentId = ?;";	
		public static final String GET_SEATS = "select seats from course where courseCode = ?;";
		public static final String INSERT_PAYMENT = "insert into payment(studentId,modeofPayment,referenceId,amount) values(?,?,?,?);";
		public static final String INSERT_NOTIFICATION = "insert into notification(studentId,type,referenceId) values(?,?,?);";
		public static final String GET_NOTIFICATION = "select * from notification where referenceId = ?;";
		public static final String GET_REGISTRATION_STATUS=" select isRegistered from student where studentId = ? ";
		public static final String SET_REGISTRATION_STATUS="update student set isRegistered = true  where studentId=?";
		public static final String GET_PAYMENT_STATUS=" select isPaid from student where studentId = ? ";
		public static final String SET_PAYMENT_STATUS="update student set isPaid = true  where studentId=?";
		public static final String NUMBER_OF_REGISTERED_COURSES=" select studentId from registeredcourse where studentId = ? ";
		public static final String IS_REGISTERED=" select courseCode from registeredcourse where courseCode=? and studentId=? ";
		
		public static final String GET_GRADES = "select grade from registeredcourse where studentId=?";
		
		//ProfessorDao queries
	
		public static final String UPDATE_PASSWORD="update user set password=? where userId = ? ";
		public static final String VERIFY_CREDENTIALS="select password from user where userId = ?";
		public static final String GET_ROLE="select role from user where userId = ?;";
		public static final String GET_NAME="select name from user where userId = ?;";
		public static final String GET_COURSES="select * from course where professorId=?";
		public static final String GET_ENROLLED_STUDENTS="select course.courseCode,course.courseName,registeredcourse.studentId from course inner join registeredcourse on course.courseCode = registeredcourse.courseCode where course.professorId = ? order by course.courseCode";
		public static final String ADD_GRADE="update registeredcourse set grade=? where courseCode=? and studentId=?";
		public static final String GET_PROF_NAME = "select name from user where userId = ?";
		
		
		
}
