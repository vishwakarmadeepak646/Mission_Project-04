package in.co.rays.proj4.Controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import in.co.rays.proj4.bean.BaseBean;
import in.co.rays.proj4.bean.UserBean;
import in.co.rays.proj4.util.DataUtility;
import in.co.rays.proj4.util.DataValidator;
import in.co.rays.proj4.util.ServletUtility;

/**
 * Base Controller class used to provide common functionality
 * for all controller classes in the application.
 * 
 * This class handles:
 * - Common operation constants
 * - Validation handling
 * - DTO population
 * - Preloading data
 * - Request processing
 * 
 * @author Deepak Vishwakarma
 */
public abstract class BaseCtl extends HttpServlet {

	public static final String OP_SAVE = "Save";
	public static final String OP_UPDATE = "Update";
	public static final String OP_CANCEL = "Cancel";
	public static final String OP_DELETE = "Delete";
	public static final String OP_LIST = "List";
	public static final String OP_SEARCH = "Search";
	public static final String OP_VIEW = "View";
	public static final String OP_NEXT = "Next";
	public static final String OP_PREVIOUS = "Previous";
	public static final String OP_NEW = "New";
	public static final String OP_GO = "Go";
	public static final String OP_BACK = "Back";
	public static final String OP_RESET = "Reset";
	public static final String OP_LOG_OUT = "Logout";

	public static final String MSG_SUCCESS = "success";

	public static final String MSG_ERROR = "error";

	/**
	 * Validates input data entered by the user.
	 * 
	 * @param request HttpServletRequest object
	 * @return true if validation passes otherwise false
	 */
	
	// check input data enter by user
	protected boolean validate(HttpServletRequest request) {
		return true;
	}

	/**
	 * Preloads data such as dropdown lists before request processing.
	 * 
	 * @param request HttpServletRequest object
	 */
	
	// search pre_loaded data like drop_down list
	protected void pre_loaded(HttpServletRequest request) {
	}

	/**
	 * Populates bean object using request parameters.
	 * 
	 * @param request HttpServletRequest object
	 * @return populated BaseBean object
	 */
	// get data from view and set into bean object
	protected BaseBean populateBean(HttpServletRequest request) {

		return null;
	}

	/**
	 * Populates common DTO attributes like createdBy,
	 * modifiedBy and timestamps.
	 * 
	 * @param dto BaseBean object
	 * @param request HttpServletRequest object
	 * @return populated BaseBean object
	 */
	
	// Track the changes done by Admin/Student/College etc
	protected BaseBean populateDTO(BaseBean dto, HttpServletRequest request) {

		String createdBy = request.getParameter("createdBy");
		String modifiedBy = null;

		UserBean userbean = (UserBean) request.getSession().getAttribute("user");

		if (userbean == null) {
			createdBy = "root";
			modifiedBy = "root";
		} else {
			modifiedBy = userbean.getLogin();
			if ("null".equalsIgnoreCase(createdBy) || DataValidator.isNull(createdBy)) {
				createdBy = modifiedBy;
			}
		}

		dto.setCreatedBy(createdBy);
		dto.setModifiedBy(modifiedBy);

		long cdt = DataUtility.getLong(request.getParameter("createdDatetime"));

		if (cdt > 0) {
			dto.setCreatedDatetime(DataUtility.getTimestamp(cdt));
		} else {
			dto.setCreatedDatetime(DataUtility.getCurrentTimestamp());
		}

		dto.setModifiedDatetime(DataUtility.getCurrentTimestamp());

		return dto;
	}

	/**
	 * Service method executed before every request.
	 * Performs preloading and validation.
	 * 
	 * @param request HttpServletRequest object
	 * @param response HttpServletResponse object
	 * @throws ServletException if servlet error occurs
	 * @throws IOException if input/output error occurs
	 */
	
	// It will run first every time, whenever user send request
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		pre_loaded(request);

		String op = DataUtility.getString(request.getParameter("operation"));

		System.out.println("op === " + op);

		if (DataValidator.isNotNull(op) && !OP_CANCEL.equalsIgnoreCase(op) && !OP_RESET.equalsIgnoreCase(op)
				&& !OP_VIEW.equalsIgnoreCase(op) && !OP_DELETE.equalsIgnoreCase(op)) {
			if (validate(request) == false) {
				BaseBean bean = populateBean(request);
				ServletUtility.setBean(bean, request);
				ServletUtility.forward(getView(), request, response);
				return;
			}
		}
		super.service(request, response);
	}

	/**
	 * Returns view page path.
	 * 
	 * @return JSP view path
	 */
	protected abstract String getView();

}
