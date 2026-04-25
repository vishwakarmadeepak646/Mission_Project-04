package in.co.rays.proj4.Test;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import in.co.rays.proj4.bean.StudentBean;
import in.co.rays.proj4.exception.ApplicationException;

public class StudentTest {

	public static void main(String[] args) throws Exception {
		// getAdd();
		// getUpdate();
		// getDelete();
		// findByPk();
		// findByEmail();
		getSearch();
	}

	public static void getAdd() throws Exception {
		StudentBean bean = new StudentBean();
		StudentModel model = new StudentModel();
		SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");

		bean.setCollegeId(1);
		bean.setFirstName("Ram");
		bean.setLastName("Sharma");
		bean.setDob(s.parse("2000-01-01"));
		bean.setGender("Male");
		bean.setMobileNo("9989765456");
		bean.setEmail("deepak@gmail.com");
		bean.setCollegeId(1);
		bean.setCreatedBy("Admin");
		bean.setModifiedBy("Admin");
		bean.setCreatedDatetime(new Timestamp(new Date().getTime()));
		bean.setModifiedDatetime(new Timestamp(new Date().getTime()));
		model.add(bean);
	}

	public static void getUpdate() throws Exception {
		StudentBean bean = new StudentBean();
		StudentModel model = new StudentModel();
		SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");

		bean.setId(1);
		bean.setFirstName("Shyam");
		bean.setLastName("Karma");
		bean.setDob(s.parse("2002-01-01"));
		bean.setGender("Male");
		bean.setMobileNo("9989005456");
		bean.setEmail("shyam@gmail.com");
		bean.setCollegeId(1);
		bean.setCreatedBy("Admin");
		bean.setModifiedBy("Admin");
		bean.setCreatedDatetime(new Timestamp(new Date().getTime()));
		bean.setModifiedDatetime(new Timestamp(new Date().getTime()));
		model.update(bean);
	}

	public static void getDelete() throws Exception {
		StudentBean bean = new StudentBean();
		StudentModel model = new StudentModel();

		bean.setId(2);

		model.delete(bean);
	}

	public static void findByPk() throws ApplicationException {
		StudentBean bean = new StudentBean();
		StudentModel model = new StudentModel();
		bean = model.findByPk(1);

		if (bean != null) {
			System.out.println("Id: " + bean.getId());
			System.out.println("First Name: " + bean.getFirstName());
			System.out.println("Last Name: " + bean.getLastName());
			System.out.println("DOB: " + bean.getDob());
			System.out.println("Mobile No: " + bean.getMobileNo());
			System.out.println("Email: " + bean.getEmail());
			System.out.println("College Id: " + bean.getCollegeId());
			System.out.println("College Name: " + bean.getCollegeName());
			System.out.println("Created By: " + bean.getCreatedBy());
			System.out.println("Modified By: " + bean.getModifiedBy());
			System.out.println("Created Datetime: " + bean.getCreatedDatetime());
			System.out.println("Modified Datetime: " + bean.getModifiedDatetime());
		} else {
			System.out.println("No Record found");
		}
	}

	public static void findByEmail() throws ApplicationException {
		StudentBean bean = new StudentBean();
		StudentModel model = new StudentModel();
		bean = model.findByEmailId("shyam@gmail.com");

		if (bean != null) {
			System.out.println("Id: " + bean.getId());
			System.out.println("First Name: " + bean.getFirstName());
			System.out.println("Last Name: " + bean.getLastName());
			System.out.println("DOB: " + bean.getDob());
			System.out.println("Mobile No: " + bean.getMobileNo());
			System.out.println("Email: " + bean.getEmail());
			System.out.println("College Id: " + bean.getCollegeId());
			System.out.println("College Name: " + bean.getCollegeName());
			System.out.println("Created By: " + bean.getCreatedBy());
			System.out.println("Modified By: " + bean.getModifiedBy());
			System.out.println("Created Datetime: " + bean.getCreatedDatetime());
			System.out.println("Modified Datetime: " + bean.getModifiedDatetime());
		} else {
			System.out.println("No Record found");
		}
	}

	public static void getSearch() throws ApplicationException, ParseException {
		SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");

		StudentBean bean = new StudentBean();
		StudentModel model = new StudentModel();
		List<StudentBean> list = new ArrayList<StudentBean>();
//		bean.setId(1);
//		bean.setFirstName("shyam");
//		bean.setLastName("Karma");
//		bean.setDob(s.parse("2002-01-01"));
//		bean.setMobileNo("99");
//		bean.setCollegeName("LNCT");

		list = model.search(bean, 1, 5);

		Iterator<StudentBean> it = list.iterator();
		while (it.hasNext()) {
			bean = it.next();

			System.out.println("Id: " + bean.getId());
			System.out.println("First Name: " + bean.getFirstName());
			System.out.println("Last Name: " + bean.getLastName());
			System.out.println("DOB: " + bean.getDob());
			System.out.println("Mobile No: " + bean.getMobileNo());
			System.out.println("Email: " + bean.getEmail());
			System.out.println("College Id: " + bean.getCollegeId());
			System.out.println("College Name: " + bean.getCollegeName());
			System.out.println("Created By: " + bean.getCreatedBy());
			System.out.println("Modified By: " + bean.getModifiedBy());
			System.out.println("Created Datetime: " + bean.getCreatedDatetime());
			System.out.println("Modified Datetime: " + bean.getModifiedDatetime());

		}
	}
}
