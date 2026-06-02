package in.co.rays.proj4.Controller;

/**
 * ORSView is an interface containing constants that map to all view pages
 * (JSPs) and controller paths in the application. It provides a centralized
 * location for navigation routing, preventing hardcoded URL strings throughout
 * the project. * @author Deepak Vishwakarma
 */
public interface ORSView {

	/** Application context path */
	public String APP_CONTEXT = "/ORSProject-04";

	/** Directory folder path for all JSP pages */
	public String PAGE_FOLDER = "/jsp";

	/** Module Page view and controller paths */
	public String MODULE_VIEW = PAGE_FOLDER + "/ModuleView.jsp";
	public String MODULE_CTL = APP_CONTEXT + "/ctl/ModuleCtl";

	/** Path to the JavaDoc index */
	public String JAVA_DOC = APP_CONTEXT + "/doc/index.html";

	/** Welcome Page view and controller paths */
	public String WELCOME_VIEW = PAGE_FOLDER + "/Welcome.jsp";
	public String WELCOME_CTL = APP_CONTEXT + "/WelcomeCtl";

	/** User Registration view and controller paths */
	public String USER_REGISTRATION_VIEW = PAGE_FOLDER + "/UserRegistrationView.jsp";
	public String USER_REGISTRATION_CTL = APP_CONTEXT + "/UserRegistrationCtl";

	/** Login view and controller paths */
	public String LOGIN_VIEW = PAGE_FOLDER + "/LoginView.jsp";
	public String LOGIN_CTL = APP_CONTEXT + "/LoginCtl";

	/** Get Marksheet view and controller paths */
	public String GET_MARKSHEET_VIEW = PAGE_FOLDER + "/GetMarksheetView.jsp";
	public String GET_MARKSHEET_CTL = APP_CONTEXT + "/ctl/GetMarksheetCtl";

	/** Marksheet Merit List view and controller paths */
	public String MARKSHEET_MERIT_LIST_VIEW = PAGE_FOLDER + "/MarksheetMeritListView.jsp";
	public String MARKSHEET_MERIT_LIST_CTL = APP_CONTEXT + "/ctl/MarksheetMeritListCtl";

	/** User view and controller paths */
	public String USER_VIEW = PAGE_FOLDER + "/UserView.jsp";
	public String USER_CTL = APP_CONTEXT + "/ctl/UserCtl";

	/** User List view and controller paths */
	public String USER_LIST_VIEW = PAGE_FOLDER + "/UserListView.jsp";
	public String USER_LIST_CTL = APP_CONTEXT + "/ctl/UserListCtl";

	/** Role view and controller paths */
	public String ROLE_VIEW = PAGE_FOLDER + "/RoleView.jsp";
	public String ROLE_CTL = APP_CONTEXT + "/ctl/RoleCtl";

	/** Role List view and controller paths */
	public String ROLE_LIST_VIEW = PAGE_FOLDER + "/RoleListView.jsp";
	public String ROLE_LIST_CTL = APP_CONTEXT + "/ctl/RoleListCtl";

	/** College view and controller paths */
	public String COLLEGE_VIEW = PAGE_FOLDER + "/CollegeView.jsp";
	public String COLLEGE_CTL = APP_CONTEXT + "/ctl/CollegeCtl";

	/** College List view and controller paths */
	public String COLLEGE_LIST_VIEW = PAGE_FOLDER + "/CollegeListView.jsp";
	public String COLLEGE_LIST_CTL = APP_CONTEXT + "/ctl/CollegeListCtl";

	/** Student view and controller paths */
	public String STUDENT_VIEW = PAGE_FOLDER + "/StudentView.jsp";
	public String STUDENT_CTL = APP_CONTEXT + "/ctl/StudentCtl";

	/** Student List view and controller paths */
	public String STUDENT_LIST_VIEW = PAGE_FOLDER + "/StudentListView.jsp";
	public String STUDENT_LIST_CTL = APP_CONTEXT + "/ctl/StudentListCtl";

	/** Marksheet view and controller paths */
	public String MARKSHEET_VIEW = PAGE_FOLDER + "/MarksheetView.jsp";
	public String MARKSHEET_CTL = APP_CONTEXT + "/ctl/MarksheetCtl";

	/** Marksheet List view and controller paths */
	public String MARKSHEET_LIST_VIEW = PAGE_FOLDER + "/MarksheetListView.jsp";
	public String MARKSHEET_LIST_CTL = APP_CONTEXT + "/ctl/MarksheetListCtl";

	/** Course view and controller paths */
	public String COURSE_VIEW = PAGE_FOLDER + "/CourseView.jsp";
	public String COURSE_CTL = APP_CONTEXT + "/ctl/CourseCtl";

	/** Course List view and controller paths */
	public String COURSE_LIST_VIEW = PAGE_FOLDER + "/CourseListView.jsp";
	public String COURSE_LIST_CTL = APP_CONTEXT + "/ctl/CourseListCtl";

	/** Subject view and controller paths */
	public String SUBJECT_VIEW = PAGE_FOLDER + "/SubjectView.jsp";
	public String SUBJECT_CTL = APP_CONTEXT + "/ctl/SubjectCtl";

	/** Subject List view and controller paths */
	public String SUBJECT_LIST_VIEW = PAGE_FOLDER + "/SubjectListView.jsp";
	public String SUBJECT_LIST_CTL = APP_CONTEXT + "/ctl/SubjectListCtl";

	/** Timetable view and controller paths */
	public String TIMETABLE_VIEW = PAGE_FOLDER + "/TimetableView.jsp";
	public String TIMETABLE_CTL = APP_CONTEXT + "/ctl/TimetableCtl";

	/** Timetable List view and controller paths */
	public String TIMETABLE_LIST_VIEW = PAGE_FOLDER + "/TimetableListView.jsp";
	public String TIMETABLE_LIST_CTL = APP_CONTEXT + "/ctl/TimetableListCtl";

	/** Faculty view and controller paths */
	public String FACULTY_VIEW = PAGE_FOLDER + "/FacultyView.jsp";
	public String FACULTY_CTL = APP_CONTEXT + "/ctl/FacultyCtl";

	/** Faculty List view and controller paths */
	public String FACULTY_LIST_VIEW = PAGE_FOLDER + "/FacultyListView.jsp";
	public String FACULTY_LIST_CTL = APP_CONTEXT + "/ctl/FacultyListCtl";

	/** Forget Password view and controller paths */
	public String FORGET_PASSWORD_VIEW = PAGE_FOLDER + "/ForgetPasswordView.jsp";
	public String FORGET_PASSWORD_CTL = APP_CONTEXT + "/ForgetPasswordCtl";

	/** My Profile view and controller paths */
	public String MY_PROFILE_VIEW = PAGE_FOLDER + "/MyProfileView.jsp";
	public String MY_PROFILE_CTL = APP_CONTEXT + "/ctl/MyProfileCtl";

	/** Change Password view and controller paths */
	public String CHANGE_PASSWORD_VIEW = PAGE_FOLDER + "/ChangePasswordView.jsp";
	public String CHANGE_PASSWORD_CTL = APP_CONTEXT + "/ctl/ChangePasswordCtl";

	/** Error view and controller paths */
	public String ERROR_VIEW = PAGE_FOLDER + "/ErrorView.jsp";
	public String ERROR_CTL = APP_CONTEXT + "/ErrorCtl";

	// -------------------------------Module Paths------------------------------

	public String MEETING_VIEW = PAGE_FOLDER + "/MeetingView.jsp";
	public String MEETING_CTL = APP_CONTEXT + "/ctl/MeetingCtl";

	public String MEETING_LIST_VIEW = PAGE_FOLDER + "/MeetingListView.jsp";
	public String MEETING_LIST_CTL = APP_CONTEXT + "/ctl/MeetingListCtl";

	public String HOSPITAL_VIEW = PAGE_FOLDER + "/HospitalView.jsp";
	public String HOSPITAL_CTL = APP_CONTEXT + "/ctl/HospitalCtl";

	public String HOSPITAL_LIST_VIEW = PAGE_FOLDER + "/HospitalListView.jsp";
	public String HOSPITAL_LIST_CTL = APP_CONTEXT + "/ctl/HospitalListCtl";

	public String INSURANCE_VIEW = PAGE_FOLDER + "/InsuranceView.jsp";
	public String INSURANCE_CTL = APP_CONTEXT + "/ctl/InsuranceCtl";

	public String INSURANCE_LIST_VIEW = PAGE_FOLDER + "/InsuranceListView.jsp";
	public String INSURANCE_LIST_CTL = APP_CONTEXT + "/ctl/InsuranceListCtl";

	public String EVENT_MANAGEMENT_VIEW = PAGE_FOLDER + "/EventManagementView.jsp";
	public String EVENT_MANAGEMENT_CTL = APP_CONTEXT + "/ctl/EventManagementCtl";

	public String EVENT_MANAGEMENT_LIST_VIEW = PAGE_FOLDER + "/EventManagementListView.jsp";
	public String EVENT_MANAGEMENT_LIST_CTL = APP_CONTEXT + "/ctl/EventManagementListCtl";

	public String LIBRARY_VIEW = PAGE_FOLDER + "/LibraryView.jsp";
	public String LIBRARY_CTL = APP_CONTEXT + "/ctl/LibraryCtl";

	public String LIBRARY_LIST_VIEW = PAGE_FOLDER + "/LibraryListView.jsp";
	public String LIBRARY_LIST_CTL = APP_CONTEXT + "/ctl/LibraryListCtl";
	
	public String ATM_VIEW = PAGE_FOLDER + "/ATMView.jsp";
	public String ATM_CTL = APP_CONTEXT + "/ctl/ATMCtl";

	public String ATM_LIST_VIEW = PAGE_FOLDER + "/ATMListView.jsp";
	public String ATM_LIST_CTL = APP_CONTEXT + "/ctl/ATMListCtl";
	
	public String EMI_VIEW = PAGE_FOLDER + "/EMIView.jsp";
	public String EMI_CTL = APP_CONTEXT + "/ctl/EMICtl";

	public String EMI_LIST_VIEW = PAGE_FOLDER + "/EMIListView.jsp";
	public String EMI_LIST_CTL = APP_CONTEXT + "/ctl/EMIListCtl";
}