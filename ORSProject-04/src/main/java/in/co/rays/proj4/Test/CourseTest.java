package in.co.rays.proj4.Test;

import java.sql.Timestamp;
import java.util.Date;

import in.co.rays.proj4.bean.CourseBean;
import in.co.rays.proj4.exception.ApplicationException;
import in.co.rays.proj4.model.CourseModel;

public class CourseTest {

	public static void main(String[] args) throws ApplicationException {

		// getAdd();
		// getUpdate();
		getDelete();
	}

	public static void getAdd() throws ApplicationException {

		CourseBean bean = new CourseBean();
		CourseModel model = new CourseModel();
		bean.setName("Bachelor of Technology");
		bean.setDuration("4 Years");
		bean.setDescription("Engineering Course");
		bean.setCreatedBy("Admin");
		bean.setModifiedBy("Admin");
		bean.setCreatedDatetime(new Timestamp(new Date().getTime()));
		bean.setModifiedDatetime(new Timestamp(new Date().getTime()));
		model.add(bean);

	}

	public static void getUpdate() throws ApplicationException {

		CourseBean bean = new CourseBean();
		CourseModel model = new CourseModel();
		bean.setId(1);
		bean.setName("Master of Technology'");
		bean.setDuration("2 Years");
		bean.setDescription("PG Course");
		bean.setCreatedBy("Admin");
		bean.setModifiedBy("Admin");
		bean.setCreatedDatetime(new Timestamp(new Date().getTime()));
		bean.setModifiedDatetime(new Timestamp(new Date().getTime()));
		model.update(bean);

	}

	public static void getDelete() throws ApplicationException {
		CourseBean bean = new CourseBean();
		CourseModel m = new CourseModel();
		bean.setId(1);
		m.delete(bean);
	}

}
