package in.co.rays.proj4.Test;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

import in.co.rays.proj4.bean.StudentBean;

public class StudentTest {

	public static void main(String[] args) throws Exception {
		// getAdd();
		getUpdate();
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
}
