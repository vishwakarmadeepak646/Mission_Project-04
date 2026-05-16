package in.co.rays.proj4.Controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import in.co.rays.proj4.bean.BaseBean;
import in.co.rays.proj4.bean.RoleBean;
import in.co.rays.proj4.exception.ApplicationException;
import in.co.rays.proj4.exception.DuplicateRecordException;
import in.co.rays.proj4.model.RoleModel;
import in.co.rays.proj4.util.DataUtility;
import in.co.rays.proj4.util.DataValidator;
import in.co.rays.proj4.util.ServletUtility;

/**
 * RoleCtl is a Servlet controller that handles the addition, modification, and
 * viewing of user Roles.
 * 
 * @author Deepak Vishwakarma
 */
@WebServlet(name = "RoleCtl", urlPatterns = { "/ctl/RoleCtl" })
public class RoleCtl extends BaseCtl {

	/**
	 * Validates input data entered by the user on the role form. * @param request
	 * the HTTP servlet request // * @return true if validation passes, false
	 * otherwise
	 */
	@Override
	protected boolean validate(HttpServletRequest request) {

		boolean pass = true;

		if (DataValidator.isNull(request.getParameter("name"))) {
			request.setAttribute("name", "Name is required");
			pass = false;
		} else if (!DataValidator.isName(request.getParameter("name"))) {

			request.setAttribute("name", "Enter a valid name");
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("description"))) {
			request.setAttribute("description", "Description is required");
			pass = false;
		}
		return pass;
	}

	/**
	 * Populates the RoleBean from the HTTP request parameters. * @param request the
	 * HTTP servlet request
	 * 
	 * @return the populated BaseBean object containing role data
	 */
	@Override
	protected BaseBean populateBean(HttpServletRequest request) {

		RoleBean bean = new RoleBean();

		bean.setId(DataUtility.getLong(request.getParameter("id")));
		bean.setName(DataUtility.getString(request.getParameter("name")));
		bean.setDescription(DataUtility.getString(request.getParameter("description")));

		populateDTO(bean, request);

		return bean;
	}

	/**
	 * Handles HTTP GET requests to display the role form, loading existing records
	 * if an ID is provided. * @param request the HTTP servlet request
	 * 
	 * @param response the HTTP servlet response
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException      if an I/O error occurs
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println("In do get");

		long id = DataUtility.getLong(request.getParameter("id"));

		RoleModel model = new RoleModel();

		if (id > 0) {
			try {
				RoleBean bean = model.findByPk(id);
				ServletUtility.setBean(bean, request);
			} catch (ApplicationException e) {
				e.printStackTrace();
				return;
			}
		}
		ServletUtility.forward(getView(), request, response);

	}

	/**
	 * Handles HTTP POST requests to save, update, cancel, or reset the role form
	 * data. * @param request the HTTP servlet request
	 * 
	 * @param response the HTTP servlet response
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException      if an I/O error occurs
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println("In do Post");

		String op = DataUtility.getString(request.getParameter("operation"));
		System.out.println(op);

		RoleModel model = new RoleModel();

		long id = DataUtility.getLong(request.getParameter("id"));

		if (OP_SAVE.equalsIgnoreCase(op)) {

			RoleBean bean = (RoleBean) populateBean(request);

			try {
				long pk = model.add(bean);
				ServletUtility.setBean(bean, request);
				ServletUtility.setSuccessMessage("Data is successfully saved", request);

			} catch (DuplicateRecordException e) {
				ServletUtility.setBean(bean, request);
				ServletUtility.setErrorMessage("Role already exists", request);
			} catch (ApplicationException e) {
				e.printStackTrace();
				return;
			}
		} else if (OP_UPDATE.equalsIgnoreCase(op)) {
			System.out.println("--------------------");

			RoleBean bean = (RoleBean) populateBean(request);

			try {
				if (id > 0) {
					model.update(bean);
				}
				ServletUtility.setBean(bean, request);
				ServletUtility.setSuccessMessage("Data is successfully updated", request);
			} catch (DuplicateRecordException e) {
				ServletUtility.setBean(bean, request);
				ServletUtility.setErrorMessage("Role already exists", request);
			} catch (ApplicationException e) {
				e.printStackTrace();
				return;
			}

		} else if (OP_CANCEL.equalsIgnoreCase(op)) {
			ServletUtility.redirect(ORSView.ROLE_LIST_CTL, request, response);
			return;
		}

		else if (OP_RESET.equalsIgnoreCase(op)) {
			ServletUtility.redirect(ORSView.ROLE_CTL, request, response);
			return;
		}
		ServletUtility.forward(getView(), request, response);

	}

	/**
	 * Returns the view page associated with the Role controller. * @return the
	 * logical view string
	 */
	@Override
	protected String getView() {
		return ORSView.ROLE_VIEW;
	}
}