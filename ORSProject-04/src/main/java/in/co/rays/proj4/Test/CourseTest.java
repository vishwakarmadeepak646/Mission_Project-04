package in.co.rays.proj4.Test;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import in.co.rays.proj4.bean.CourseBean;
import in.co.rays.proj4.exception.ApplicationException;
import in.co.rays.proj4.exception.DuplicateRecordException;
import in.co.rays.proj4.model.CourseModel;

public class CourseTest {

	public static void main(String[] args) throws ApplicationException {

		 //getAdd();
		// getUpdate();
		// getDelete();
		// findByPK();
		// findByName();
		// search();
	}

	public static void getAdd() throws ApplicationException, DuplicateRecordException {

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

	public static void getUpdate() throws ApplicationException, DuplicateRecordException {

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

	public static void findByPK() throws ApplicationException {
		CourseBean bean = new CourseBean();
		CourseModel m = new CourseModel();

		bean = m.findByPk(1);

		if (bean != null) {
			System.out.println(bean.getId());
			System.out.println(bean.getName());
			System.out.println(bean.getDuration());
			System.out.println(bean.getDescription());
			System.out.println(bean.getCreatedBy());
			System.out.println(bean.getModifiedBy());
			System.out.println(bean.getCreatedDatetime());
			System.out.println(bean.getModifiedDatetime());
			System.out.println("------------------------");
		} else {
			System.out.println("No record found !");
		}
	}

	public static void findByName() throws ApplicationException {
		CourseBean bean = new CourseBean();
		CourseModel m = new CourseModel();

		bean = m.findByName("Bachelor of Technology");

		if (bean != null) {
			System.out.println(bean.getId());
			System.out.println(bean.getName());
			System.out.println(bean.getDuration());
			System.out.println(bean.getDescription());
			System.out.println(bean.getCreatedBy());
			System.out.println(bean.getModifiedBy());
			System.out.println(bean.getCreatedDatetime());
			System.out.println(bean.getModifiedDatetime());
			System.out.println("--------------------------");
		} else {
			System.out.println("No record found !");
		}
	}

	public static void search() throws ApplicationException {
		CourseBean bean = new CourseBean();
		CourseModel m = new CourseModel();

		List<CourseBean> list = new ArrayList<CourseBean>();

//		bean.setId(1);
//		bean.setName("Bachelor");
//		bean.setDescription("e");
//		bean.setDuration("e");

		list = m.search(bean, 1, 5);
		Iterator<CourseBean> it = list.iterator();

		while (it.hasNext()) {
			bean = it.next();
			System.out.println(bean.getId());
			System.out.println(bean.getName());
			System.out.println(bean.getDuration());
			System.out.println(bean.getDescription());
			System.out.println(bean.getCreatedBy());
			System.out.println(bean.getModifiedBy());
			System.out.println(bean.getCreatedDatetime());
			System.out.println(bean.getModifiedDatetime());
			System.out.println("--------------------------");
		}

	}

}
