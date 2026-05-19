package in.co.rays.proj4.Controller;

import java.io.IOException;
import java.sql.SQLException;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.crypto.Data;

import in.co.rays.proj4.bean.BaseBean;
import in.co.rays.proj4.bean.RoleBean;
import in.co.rays.proj4.bean.UserBean;
import in.co.rays.proj4.exception.ApplicationException;
import in.co.rays.proj4.exception.DuplicateRecordException;
import in.co.rays.proj4.model.UserModel;
import in.co.rays.proj4.util.DataUtility;
import in.co.rays.proj4.util.DataValidator;
import in.co.rays.proj4.util.ServletUtility;

/**
 * UserRegistrationCtl is a Servlet controller that manages new user sign-ups.
 * New users are typically registered with the default 'Student' role.
 * 
 * @author Deepak Vishwakarma
 */
@WebServlet(name = "UserRegistrationCtl", urlPatterns = { "/UserRegistrationCtl" })
public class UserRegistrationCtl extends BaseCtl {

	/** Operation constant for Sign Up */
	public static final String OP_SIGN_UP = "Sign Up";

	/** Log4j Logger */
	private static final Logger log = Logger.getLogger(UserRegistrationCtl.class);

	/**
	 * Validates input data entered by the new user on the registration form. Checks
	 * for correct email format, password strength, and confirms password matches.
	 * * @param request the HTTP servlet request
	 * 
	 * @return true if validation passes, false otherwise
	 */
	@Override
	protected boolean validate(HttpServletRequest request) {
		log.debug("UserRegistrationCtl validate() called");

		boolean pass = true;

		if (DataValidator.isNull(request.getParameter("firstName"))) {
			request.setAttribute("firstName", "First Name is required");
			pass = false;
			log.warn("Validation failed: firstName is null");
		} else if (!DataValidator.isName(request.getParameter("firstName"))) {
			request.setAttribute("firstName", "First Name is invalid");
			pass = false;
			  log.warn("Validation failed: firstName invalid");
		}

		if (DataValidator.isNull(request.getParameter("lastName"))) {
			request.setAttribute("lastName", "Last Name is required");
			pass = false;
			 log.warn("Validation failed: lastName is null");
		} else if (!DataValidator.isName(request.getParameter("lastName"))) {
			request.setAttribute("lastName", "Invalid Last Name");
			pass = false;
			log.warn("Validation failed: lastName invalid");
		}

		if (DataValidator.isNull(request.getParameter("login"))) {
			request.setAttribute("login", "Login id is required");
			pass = false;
			 log.warn("Validation failed: login is null");
		} else if (!DataValidator.isEmail(request.getParameter("login"))) {
			request.setAttribute("login", "Invalid login id");
			pass = false;
			  log.warn("Validation failed: login not a valid email");
		}

		if (DataValidator.isNull(request.getParameter("password"))) {
			request.setAttribute("password", "Password is required");
			pass = false;
			 log.warn("Validation failed: password is null");
		} else if (!DataValidator.isPasswordLength(request.getParameter("password"))) {
			request.setAttribute("password", "Password should be 8 to 12 characters");
			pass = false;
			 log.warn("Validation failed: password length invalid");
		} else if (!DataValidator.isPassword(request.getParameter("password"))) {
			request.setAttribute("password", "Must contain uppercase, lowercase, digit & special character");
			pass = false;
			  log.warn("Validation failed: password strength invalid");
		}

		if (DataValidator.isNull(request.getParameter("confirmPassword"))) {
			request.setAttribute("confirmPassword", "confirmPassword is required");
			pass = false;
			 log.warn("Validation failed: confirmPassword is null");
		}

		if (DataValidator.isNull(request.getParameter("gender"))) {
			request.setAttribute("gender", "gender is required");
			pass = false;
			log.warn("Validation failed: gender is null");
		}

		if (DataValidator.isNull(request.getParameter("dob"))) {
			request.setAttribute("dob", "dob is required");
			pass = false;
			 log.warn("Validation failed: dob is null");
		} else if (!DataValidator.isDate(request.getParameter("dob"))) {
			request.setAttribute("dob", "Invalid date of birth");
			pass = false;
			 log.warn("Validation failed: dob invalid date");
        
			
		}

		if (!request.getParameter("password").equals(request.getParameter("confirmPassword"))) {
			request.setAttribute("confirmPassword", "Password and Confirm Password must be Same!");
			pass = false;
			log.warn("Validation failed: password and confirmPassword mismatch");
		}

		if (DataValidator.isNull(request.getParameter("mobileNo"))) {
			request.setAttribute("mobileNo", "mobileNo is required");
			pass = false;
			log.warn("Validation failed: mobileNo is null");
		} else if (!DataValidator.isPhoneLength(request.getParameter("mobileNo"))) {
			request.setAttribute("mobileNo", "Mobile No must have 10 digits");
			pass = false;
            log.warn("Validation failed: mobileNo length invalid");
		} else if (!DataValidator.isPhoneNo(request.getParameter("mobileNo"))) {
			request.setAttribute("mobileNo", "Invalid Mobile No");
			pass = false;
			 log.warn("Validation failed: mobileNo invalid");
		}

		return pass;
	}

	/**
	 * Populates the UserBean from the HTTP request parameters. Automatically sets
	 * the default RoleId to STUDENT. * @param request the HTTP servlet request
	 * 
	 * @return the populated BaseBean object containing new user registration data
	 */
	@Override // get data from view and set into bean object
	protected BaseBean populateBean(HttpServletRequest request) {
		log.debug("UserRegistrationCtl populateBean() called");

		UserBean bean = new UserBean();

		bean.setFirstName(DataUtility.getString(request.getParameter("firstName")));
		bean.setLastName(DataUtility.getString(request.getParameter("lastName")));
		bean.setLogin(DataUtility.getString(request.getParameter("login")));
		bean.setPassword(DataUtility.getString(request.getParameter("password")));
		bean.setConfirmPassword(DataUtility.getString(request.getParameter("confirmPassword")));
		bean.setGender(DataUtility.getString(request.getParameter("gender")));
		bean.setDob(DataUtility.getDate(request.getParameter("dob")));
		bean.setMobileNo(DataUtility.getString(request.getParameter("mobileNo")));

		// In this method it will return Basebean | It is important to remember
		bean.setRoleId(RoleBean.STUDENT);
		 log.info("Populated UserBean for registration: " + bean.getLogin());
		return bean;
	}

	/**
	 * Handles HTTP GET requests to display the user registration form. * @param
	 * request the HTTP servlet request
	 * 
	 * @param response the HTTP servlet response
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException      if an I/O error occurs
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		log.debug("UserRegistrationCtl doGet() called, forwarding to view");
		ServletUtility.forward(getView(), request, response);
	}

	/**
	 * Handles HTTP POST requests to register the new user into the system. * @param
	 * request the HTTP servlet request
	 * 
	 * @param response the HTTP servlet response
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException      if an I/O error occurs
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		log.debug("UserRegistrationCtl doPost() called");
		String op = DataUtility.getString(request.getParameter("operation"));
		System.out.println("Inside Reset" + op);
		UserModel model = new UserModel();

		if (OP_SIGN_UP.equalsIgnoreCase(op)) {

			UserBean bean = (UserBean) populateBean(request);

			try {
				long pk = model.registerUser(bean);
				ServletUtility.setBean(bean, request);
				// request.setAttribute("success", "Registration successfully");
				ServletUtility.setSuccessMessage("Registration successful!", request);
				log.info("User registered successfully: " + bean.getLogin());
			} catch (DuplicateRecordException e) {
				ServletUtility.setBean(bean, request);
				// request.setAttribute("error", Login id already exists");
				ServletUtility.setErrorMessage("Login id already exists", request);
				
			} catch (ApplicationException e) {
				e.printStackTrace();
				 log.warn("Duplicate login during registration: " + bean.getLogin());
			}
			ServletUtility.forward(getView(), request, response);
		} else if (OP_RESET.equalsIgnoreCase(op)) {
			 log.info("Reset operation triggered, redirecting to registration page");
			System.out.println("Inside Reset");
			ServletUtility.redirect(ORSView.USER_REGISTRATION_CTL, request, response);
			return;
		}

	}

	/**
	 * Returns the view page associated with the User Registration controller.
	 * * @return the logical view string
	 */
	@Override
	protected String getView() {
		log.debug("Returning UserRegistration view page");
		return ORSView.USER_REGISTRATION_VIEW;
	}
}