package in.co.rays.proj4.Controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import in.co.rays.proj4.bean.BaseBean;
import in.co.rays.proj4.bean.HospitalBean;
import in.co.rays.proj4.exception.ApplicationException;
import in.co.rays.proj4.model.HospitalModel;
import in.co.rays.proj4.util.DataUtility;
import in.co.rays.proj4.util.DataValidator;
import in.co.rays.proj4.util.PropertyReader;
import in.co.rays.proj4.util.ServletUtility;

@WebServlet(name = "HospitalCtl", urlPatterns = {"/ctl/HospitalCtl"})
public class HospitalCtl extends BaseCtl {

    private static final Logger log = Logger.getLogger(HospitalCtl.class);

    @Override
    protected boolean validate(HttpServletRequest request) {
        boolean pass = true;

        if (DataValidator.isNull(request.getParameter("patientId"))) {
            request.setAttribute("patientId", PropertyReader.getValue("error.require", "Patient ID"));
            pass = false;
        } else if (!DataValidator.isInteger(request.getParameter("patientId"))) {
            request.setAttribute("patientId", "Patient ID must be a number");
            pass = false;
        }

        if (DataValidator.isNull(request.getParameter("patientName"))) {
            request.setAttribute("patientName", PropertyReader.getValue("error.require", "Patient Name"));
            pass = false;
        }

        if (DataValidator.isNull(request.getParameter("doctorName"))) {
            request.setAttribute("doctorName", PropertyReader.getValue("error.require", "Doctor Name"));
            pass = false;
        }

        if (DataValidator.isNull(request.getParameter("disease"))) {
            request.setAttribute("disease", PropertyReader.getValue("error.require", "Disease"));
            pass = false;
        }

        if (DataValidator.isNull(request.getParameter("roomNumber"))) {
            request.setAttribute("roomNumber", PropertyReader.getValue("error.require", "Room Number"));
            pass = false;
        } else if (!DataValidator.isInteger(request.getParameter("roomNumber"))) {
            request.setAttribute("roomNumber", "Room Number must be a number");
            pass = false;
        }

        return pass;
    }

    @Override
    protected BaseBean populateBean(HttpServletRequest request) {
        HospitalBean bean = new HospitalBean();

        bean.setId(DataUtility.getLong(request.getParameter("id")));
        bean.setPatientId(DataUtility.getInt(request.getParameter("patientId")));
        bean.setPatientName(DataUtility.getString(request.getParameter("patientName")));
        bean.setDoctorName(DataUtility.getString(request.getParameter("doctorName")));
        bean.setDisease(DataUtility.getString(request.getParameter("disease")));
        bean.setRoomNumber(DataUtility.getInt(request.getParameter("roomNumber")));

        populateDTO(bean, request);
        return bean;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        long id = DataUtility.getLong(request.getParameter("id"));
        HospitalModel model = new HospitalModel();

        if (id > 0) {
            try {
                HospitalBean bean = model.findByPk(id);
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
        HospitalModel model = new HospitalModel();
        long id = DataUtility.getLong(request.getParameter("id"));

        if (OP_SAVE.equalsIgnoreCase(op)) {
            HospitalBean bean = (HospitalBean) populateBean(request);
            try {
                long pk = model.add(bean);
                ServletUtility.setBean(bean, request);
                ServletUtility.setSuccessMessage("Record added successfully", request);
            } catch (ApplicationException e) {
                e.printStackTrace();
                return;
            }
        } else if (OP_UPDATE.equalsIgnoreCase(op)) {
            HospitalBean bean = (HospitalBean) populateBean(request);
            try {
                if (id > 0) {
                    model.update(bean);
                }
                ServletUtility.setBean(bean, request);
                ServletUtility.setSuccessMessage("Record updated successfully", request);
            } catch (ApplicationException e) {
                e.printStackTrace();
                return;
            }
        } else if (OP_CANCEL.equalsIgnoreCase(op)) {
            ServletUtility.redirect(ORSView.HOSPITAL_LIST_CTL, request, response);
            return;
        } else if (OP_RESET.equalsIgnoreCase(op)) {
            ServletUtility.redirect(ORSView.HOSPITAL_CTL, request, response);
            return;
        }
        ServletUtility.forward(getView(), request, response);
    }

    @Override
    protected String getView() {
        return ORSView.HOSPITAL_VIEW;
    }
}