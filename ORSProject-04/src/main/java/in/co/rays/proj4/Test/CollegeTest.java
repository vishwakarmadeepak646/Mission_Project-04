package in.co.rays.proj4.Test;

import java.util.Date;
import java.sql.Timestamp;

import in.co.rays.proj4.bean.CollegeBean;
import in.co.rays.proj4.model.CollegeModel;

public class CollegeTest {

	public static void main(String[] args) {
		getAdd();
	}

	public static void getAdd() {

		try {
			CollegeBean bean = new CollegeBean();
			CollegeModel m = new CollegeModel();
			bean.setName("Dr Hari Singh Gour");
			bean.setAddress("Sagar");
			bean.setState("Madhya Pradesh");
			bean.setCity("GopalGanj Sagar");
			bean.setPhoneNo("9981234576");
			bean.setCreatedBy("Admin");
			bean.setModifiedBy("Admin");
			bean.setCreatedDatetime(new Timestamp(new Date().getTime()));
			bean.setModifiedDatetime(new Timestamp(new Date().getTime()));
			m.add(bean);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
