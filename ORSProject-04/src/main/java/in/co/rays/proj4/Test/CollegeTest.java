package in.co.rays.proj4.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.sql.Timestamp;

import in.co.rays.proj4.bean.CollegeBean;
import in.co.rays.proj4.model.CollegeModel;

public class CollegeTest {

	public static void main(String[] args) {
		// getAdd();
		// getUpdate();
		// getDelete();
		// findByPK();
		// findByName();
		search();
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

	public static void getUpdate() {
		try {

			CollegeBean bean = new CollegeBean();
			CollegeModel m = new CollegeModel();
			bean.setId(2);
			bean.setName("LNCT");
			bean.setAddress("Bhopal");
			bean.setState("Madhya Pradesh");
			bean.setCity("Near TCS Bhopal");
			bean.setPhoneNo("9900234576");
			bean.setCreatedBy("Faculty");
			bean.setModifiedBy("Faculty");
			bean.setCreatedDatetime(new Timestamp(new Date().getTime()));
			bean.setModifiedDatetime(new Timestamp(new Date().getTime()));

			m.update(bean);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void getDelete() {
		try {

			CollegeBean bean = new CollegeBean();
			CollegeModel m = new CollegeModel();
			bean.setId(2);

			m.delete(bean);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void findByPK() {

		try {
			CollegeBean bean = new CollegeBean();
			CollegeModel m = new CollegeModel();

			bean = m.findByPk(1);

			if (bean != null) {
				System.out.println(bean.getId());
				System.out.println(bean.getName());
				System.out.println(bean.getAddress());
				System.out.println(bean.getState());
				System.out.println(bean.getCity());
				System.out.println(bean.getPhoneNo());
				System.out.println(bean.getCreatedBy());
				System.out.println(bean.getModifiedBy());
				System.out.println(bean.getCreatedDatetime());
				System.out.println(bean.getModifiedDatetime());
				System.out.println("------------------------");
			} else {
				System.out.println("No record found");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void findByName() {

		try {
			CollegeBean bean = new CollegeBean();
			CollegeModel m = new CollegeModel();

			bean = m.findByName("LNCT");

			if (bean != null) {
				System.out.println(bean.getId());
				System.out.println(bean.getName());
				System.out.println(bean.getAddress());
				System.out.println(bean.getState());
				System.out.println(bean.getCity());
				System.out.println(bean.getPhoneNo());
				System.out.println(bean.getCreatedBy());
				System.out.println(bean.getModifiedBy());
				System.out.println(bean.getCreatedDatetime());
				System.out.println(bean.getModifiedDatetime());
				System.out.println("------------------------");
			} else {
				System.out.println("No record found");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void search() {
		CollegeBean bean = new CollegeBean();
		CollegeModel model = new CollegeModel();
		List<CollegeBean> list = new ArrayList<CollegeBean>();
		int pageNo = 1;
		int pageSize = 5;
		
	//	bean.setId(8);
	//	bean.setName("s");
	//	bean.setAddress("bhopal");
	//	bean.setCity("bh");
	//	bean.setPhoneNo("9876543212");
		
		list = model.search(bean, pageNo, pageSize);
		Iterator<CollegeBean> it = list.iterator();

		while (it.hasNext()) {
			bean = it.next();

			System.out.println(bean.getId());
			System.out.println(bean.getName());
			System.out.println(bean.getAddress());
			System.out.println(bean.getState());
			System.out.println(bean.getCity());
			System.out.println(bean.getPhoneNo());
			System.out.println(bean.getCreatedBy());
			System.out.println(bean.getModifiedBy());
			System.out.println(bean.getCreatedDatetime());
			System.out.println(bean.getModifiedDatetime());
			System.out.println("------------------------");
		}

	}

}
