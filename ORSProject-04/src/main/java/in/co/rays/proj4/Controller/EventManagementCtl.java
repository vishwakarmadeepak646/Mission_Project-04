package in.co.rays.proj4.Controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import in.co.rays.proj4.bean.BaseBean;
import in.co.rays.proj4.bean.EventManagementBean;
import in.co.rays.proj4.exception.ApplicationException;
import in.co.rays.proj4.exception.DuplicateRecordException;
import in.co.rays.proj4.model.EventManagementModel;
import in.co.rays.proj4.util.DataUtility;
import in.co.rays.proj4.util.DataValidator;
import in.co.rays.proj4.util.PropertyReader;
import in.co.rays.proj4.util.ServletUtility;

@WebServlet(name = "EventManagementCtl", urlPatterns = {"/ctl/EventManagementCtl"})
public class EventManagementCtl extends BaseCtl {

    private static final Logger log = Logger.getLogger(EventManagementCtl.class);

    @Override
    protected boolean validate(HttpServletRequest request) {
        boolean pass = true;

        if (DataValidator.isNull(request.getParameter("eventName"))) {
            request.setAttribute("eventName", PropertyReader.getValue("error.require", "Event Name"));
            pass = false;
        }

        if (DataValidator.isNull(request.getParameter("organizerName"))) {
            request.setAttribute("organizerName", PropertyReader.getValue("error.require", "Organizer Name"));
            pass = false;
        }

        if (DataValidator.isNull(request.getParameter("venue"))) {
            request.setAttribute("venue", PropertyReader.getValue("error.require", "Venue"));
            pass = false;
        }

        if (DataValidator.isNull(request.getParameter("budget"))) {
            request.setAttribute("budget", PropertyReader.getValue("error.require", "Budget"));
            pass = false;
        } else if (!request.getParameter("budget").matches("\\d+(\\.\\d+)?")) {
            request.setAttribute("budget", "Budget must be a valid number");
            pass = false;
        }

        return pass;
    }

    @Override
    protected BaseBean populateBean(HttpServletRequest request) {
        EventManagementBean bean = new EventManagementBean();

        bean.setId(DataUtility.getLong(request.getParameter("id")));
        bean.setEventName(DataUtility.getString(request.getParameter("eventName")));
        bean.setOrganizerName(DataUtility.getString(request.getParameter("organizerName")));
        bean.setVenue(DataUtility.getString(request.getParameter("venue")));
        bean.setBudget(DataUtility.getLong(request.getParameter("budget")));

        return bean; // Note: populateDTO is removed as audit columns are omitted
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        long id = DataUtility.getLong(request.getParameter("id"));
        EventManagementModel model = new EventManagementModel();

        if (id > 0) {
            try {
                EventManagementBean bean = model.findByPk(id);
                ServletUtility.setBean(bean, request);
            } catch (ApplicationException e) {
                e.printStackTrace();
                return;
            }
        }
        ServletUtility.forward(getView(), request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String op = DataUtility.getString(request.getParameter("operation"));
        EventManagementModel model = new EventManagementModel();
        long id = DataUtility.getLong(request.getParameter("id"));

        if (OP_SAVE.equalsIgnoreCase(op)) {
            EventManagementBean bean = (EventManagementBean) populateBean(request);
            try {
                long pk = model.add(bean);
                ServletUtility.setBean(bean, request);
                ServletUtility.setSuccessMessage("Event added successfully", request);
            } catch (DuplicateRecordException e) {
                ServletUtility.setBean(bean, request);
                ServletUtility.setErrorMessage("Event Name " + bean.getEventName() + " already exists", request);
            } catch (ApplicationException e) {
                e.printStackTrace();
                return;
            }
        } else if (OP_UPDATE.equalsIgnoreCase(op)) {
            EventManagementBean bean = (EventManagementBean) populateBean(request);
            try {
                if (id > 0) {
                    model.update(bean);
                }
                ServletUtility.setBean(bean, request);
                ServletUtility.setSuccessMessage("Event updated successfully", request);
            } catch (DuplicateRecordException e) {
                ServletUtility.setBean(bean, request);
                ServletUtility.setErrorMessage("Event Name " + bean.getEventName() + " already exists", request);
            } catch (ApplicationException e) {
                e.printStackTrace();
                return;
            }
        } else if (OP_CANCEL.equalsIgnoreCase(op)) {
            ServletUtility.redirect(ORSView.EVENT_MANAGEMENT_LIST_CTL, request, response);
            return;
        } else if (OP_RESET.equalsIgnoreCase(op)) {
            ServletUtility.redirect(ORSView.EVENT_MANAGEMENT_CTL, request, response);
            return;
        }
        ServletUtility.forward(getView(), request, response);
    }

    @Override
    protected String getView() {
        return ORSView.EVENT_MANAGEMENT_VIEW;
    }
}