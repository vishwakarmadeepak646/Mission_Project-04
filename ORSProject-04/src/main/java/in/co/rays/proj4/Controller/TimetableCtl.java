package in.co.rays.proj4.Controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import in.co.rays.proj4.bean.BaseBean;
import in.co.rays.proj4.bean.TimetableBean;
import in.co.rays.proj4.exception.ApplicationException;
import in.co.rays.proj4.exception.DuplicateRecordException;
import in.co.rays.proj4.model.CourseModel;
import in.co.rays.proj4.model.SubjectModel;
import in.co.rays.proj4.model.TimetableModel;
import in.co.rays.proj4.util.DataUtility;
import in.co.rays.proj4.util.DataValidator;
import in.co.rays.proj4.util.PropertyReader;
import in.co.rays.proj4.util.ServletUtility;

/**
 * TimetableCtl is a Servlet controller that handles the creation, modification, 
 * and viewing of individual Timetable records for examinations.
*  @author Deepak Vishwakarma
 */
@WebServlet(name = "TimetableCtl" , urlPatterns = {"/ctl/TimetableCtl"})
public class TimetableCtl extends BaseCtl{

	/**
	 * Pre-loads the lists of subjects and courses required for the timetable form dropdowns.
	 * * @param request the HTTP servlet request
	 */
	@Override
	protected void pre_loaded(HttpServletRequest request) {
		SubjectModel subjectModel = new SubjectModel();
		CourseModel courseModel = new CourseModel();

		try {
			List subjectList = subjectModel.list();
			request.setAttribute("subjectList", subjectList);

			List courseList = courseModel.list();
			request.setAttribute("courseList", courseList);

		} catch (ApplicationException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Validates input data entered by the user on the timetable form.
	 * Ensures required fields are filled, dates are valid, and exams are not scheduled on Sundays.
	 * * @param request the HTTP servlet request
	 * @return true if validation passes, false otherwise
	 */
	@Override
	protected boolean validate(HttpServletRequest request) {

		boolean pass = true;

		if (DataValidator.isNull(request.getParameter("semester"))) {
			request.setAttribute("semester", PropertyReader.getValue("error.require", "Semester"));
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("examDate"))) {
			request.setAttribute("examDate", PropertyReader.getValue("error.require", "Date of Exam"));
			pass = false;
		} else if (!DataValidator.isDate(request.getParameter("examDate"))) {
			request.setAttribute("examDate", PropertyReader.getValue("error.date", "Date of Exam"));
			pass = false;
		} else if (DataValidator.isSunday(request.getParameter("examDate"))) {
			request.setAttribute("examDate", "Exam should not be on Sunday");
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("examTime"))) {
			request.setAttribute("examTime", PropertyReader.getValue("error.require", "Exam Time"));
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("description"))) {
			request.setAttribute("description", PropertyReader.getValue("error.require", "Description"));
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("courseId"))) {
			request.setAttribute("courseId", PropertyReader.getValue("error.require", "Course Name"));
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("subjectId"))) {
			request.setAttribute("subjectId", PropertyReader.getValue("error.require", "Subject Name"));
			pass = false;
		}

		return pass;
	}

	/**
	 * Populates the TimetableBean from the HTTP request parameters.
	 * * @param request the HTTP servlet request
	 * @return the populated BaseBean object containing timetable data
	 */
	@Override
	protected BaseBean populateBean(HttpServletRequest request) {

		TimetableBean bean = new TimetableBean();

		bean.setId(DataUtility.getLong(request.getParameter("id")));
		bean.setSemester(DataUtility.getString(request.getParameter("semester")));
		bean.setDescription(DataUtility.getString(request.getParameter("description")));
		bean.setExamTime(DataUtility.getString(request.getParameter("examTime")));
		bean.setExamDate(DataUtility.getDate(request.getParameter("examDate")));
		bean.setCourseId(DataUtility.getLong(request.getParameter("courseId")));
		bean.setSubjectId(DataUtility.getLong(request.getParameter("subjectId")));

		populateDTO(bean, request);

		return bean;
	}

	/**
	 * Handles HTTP GET requests to display the timetable form, loading existing 
	 * records if an ID is provided.
	 * * @param request  the HTTP servlet request
	 * @param response the HTTP servlet response
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException      if an I/O error occurs
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		long id = DataUtility.getLong(request.getParameter("id"));

		TimetableModel model = new TimetableModel();

		if (id > 0) {
			try {
				TimetableBean bean = model.findByPk(id);
				ServletUtility.setBean(bean, request);
			} catch (ApplicationException e) {
				e.printStackTrace();
				ServletUtility.handleException(e, request, response);
				return;
			}
		}
		ServletUtility.forward(getView(), request, response);
	}

	/**
	 * Handles HTTP POST requests to save, update, cancel, or reset the timetable form data.
	 * Performs complex validation checks to prevent overlapping exams for the same course, subject, or semester.
	 * * @param request  the HTTP servlet request
	 * @param response the HTTP servlet response
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException      if an I/O error occurs
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String op = DataUtility.getString(request.getParameter("operation"));

		TimetableModel model = new TimetableModel();

		long id = DataUtility.getLong(request.getParameter("id"));

		if (OP_SAVE.equalsIgnoreCase(op)) {

			TimetableBean bean = (TimetableBean) populateBean(request);

			TimetableBean bean1;
			TimetableBean bean2;
			TimetableBean bean3;

			try {
				bean1 = model.checkByCourseName(bean.getCourseId(), bean.getExamDate());

				bean2 = model.checkBySubjectName(bean.getCourseId(), bean.getSubjectId(), bean.getExamDate());

				bean3 = model.checkBySemester(bean.getCourseId(), bean.getSubjectId(), bean.getSemester(),
						bean.getExamDate());

				if (bean1 == null && bean2 == null && bean3 == null) {
					long pk = model.add(bean);
					ServletUtility.setBean(bean, request);
					ServletUtility.setSuccessMessage("Timetable added successfully", request);
				} else {
					bean = (TimetableBean) populateBean(request);
					ServletUtility.setBean(bean, request);
					ServletUtility.setErrorMessage("Timetable already exist!", request);
				}
			} catch (DuplicateRecordException e) {
				ServletUtility.setBean(bean, request);
				ServletUtility.setErrorMessage("Timetable already exist!", request);
			} catch (ApplicationException e) {
				e.printStackTrace();
				ServletUtility.handleException(e, request, response);
				return;
			}

		} else if (OP_UPDATE.equalsIgnoreCase(op)) {

			TimetableBean bean = (TimetableBean) populateBean(request);

			TimetableBean bean4;

			try {

				bean4 = model.checkByExamTime(bean.getCourseId(), bean.getSubjectId(), bean.getSemester(),
						bean.getExamDate(), bean.getExamTime(), bean.getDescription());

				if (id > 0 && bean4 == null) {
					model.update(bean);
					ServletUtility.setBean(bean, request);
					ServletUtility.setSuccessMessage("Timetable updated successfully", request);
				} else {
					bean = (TimetableBean) populateBean(request);
					ServletUtility.setBean(bean, request);
					ServletUtility.setErrorMessage("Timetable already exist!", request);
				}
			} catch (DuplicateRecordException e) {
				ServletUtility.setBean(bean, request);
				ServletUtility.setErrorMessage("Timetable already exist!", request);
			} catch (ApplicationException e) {
				e.printStackTrace();
				ServletUtility.handleException(e, request, response);
				return;
			}
		} else if (OP_CANCEL.equalsIgnoreCase(op)) {
			ServletUtility.redirect(ORSView.TIMETABLE_LIST_CTL, request, response);
			return;
		} else if (OP_RESET.equalsIgnoreCase(op)) {
			ServletUtility.redirect(ORSView.TIMETABLE_CTL, request, response);
			return;
		}
		ServletUtility.forward(getView(), request, response);
	}

	/**
	 * Returns the view page associated with the Timetable controller.
	 * * @return the logical view string
	 */
	@Override
	protected String getView() {
		// TODO Auto-generated method stub
		return ORSView.TIMETABLE_VIEW;
	}

}