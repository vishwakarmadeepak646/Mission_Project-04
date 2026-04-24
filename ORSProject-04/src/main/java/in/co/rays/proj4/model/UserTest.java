package in.co.rays.proj4.model;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import in.co.rays.proj4.bean.UserBean;
import in.co.rays.proj4.exception.ApplicationException;

public class UserTest {

	public static void main(String[] args) throws ParseException, ApplicationException {
		// getAdd();
		// getUpdate();
		// getDelete();
		// FindByPK();
		// findbyLogin();
		// authenticate();
		search();
	}

	public static void getAdd() throws ParseException, ApplicationException {
		SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
		UserBean bean = new UserBean();
		UserModel m = new UserModel();

		bean.setFirstName("Kapil");
		bean.setLastName("Vishwakarma");
		bean.setLogin("kapil@gamil.com");
		bean.setPassword("deepak");
		bean.setDob(s.parse("2002-01-01"));
		bean.setMobileNo("99877865679");
		bean.setRoleId(1);
		bean.setGender("Male");
		bean.setCreatedBy("User");
		bean.setModifiedBy("User");
		bean.setCreatedDatetime(new Timestamp(new Date().getTime()));
		bean.setModifiedDatetime(new Timestamp(new Date().getTime()));
		m.add(bean);
	}

	public static void getUpdate() throws ParseException, ApplicationException {

		SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
		UserBean bean = new UserBean();
		UserModel m = new UserModel();

		bean.setId(1);
		bean.setFirstName("Kapil");
		bean.setLastName("Vishwakarma");
		bean.setLogin("kapil@gamail.com");
		bean.setPassword("deepak");
		bean.setDob(s.parse("2000-01-01"));
		bean.setMobileNo("99877865679");
		bean.setRoleId(1);
		bean.setGender("Male");
		bean.setCreatedBy("User");
		bean.setModifiedBy("User");
		bean.setCreatedDatetime(new Timestamp(new Date().getTime()));
		bean.setModifiedDatetime(new Timestamp(new Date().getTime()));
		m.update(bean);
	}

	public static void getDelete() throws ParseException, ApplicationException {

		UserBean bean = new UserBean();
		UserModel m = new UserModel();

		bean.setId(1);

		m.delete(bean);
	}

	public static void FindByPK() throws ApplicationException {
		UserBean bean = new UserBean();
		UserModel m = new UserModel();

		bean = m.findByPk(1);

		if (bean != null) {
			System.out.println(bean.getId());
			System.out.println(bean.getFirstName());
			System.out.println(bean.getLastName());
			System.out.println(bean.getLogin());
			System.out.println(bean.getPassword());
			System.out.println(bean.getDob());
			System.out.println(bean.getMobileNo());
			System.out.println(bean.getRoleId());
			System.out.println(bean.getGender());
			System.out.println(bean.getCreatedBy());
			System.out.println(bean.getModifiedBy());
			System.out.println(bean.getCreatedDatetime());
			System.out.println(bean.getModifiedDatetime());
		}

	}

	public static void findbyLogin() throws ApplicationException {
		UserBean bean = new UserBean();
		UserModel m = new UserModel();

		bean = m.findByLogin("deepak@gamil.com");

		if (bean != null) {
			System.out.println(bean.getId());
			System.out.println(bean.getFirstName());
			System.out.println(bean.getLastName());
			System.out.println(bean.getLogin());
			System.out.println(bean.getPassword());
			System.out.println(bean.getDob());
			System.out.println(bean.getMobileNo());
			System.out.println(bean.getRoleId());
			System.out.println(bean.getGender());
			System.out.println(bean.getCreatedBy());
			System.out.println(bean.getModifiedBy());
			System.out.println(bean.getCreatedDatetime());
			System.out.println(bean.getModifiedDatetime());
		} else {
			System.out.println("No record found");
		}

	}

	public static void authenticate() throws ApplicationException {
		UserBean bean = new UserBean();
		UserModel m = new UserModel();

		bean = m.authenticate("deepak@gamil.com", "deepak");

		if (bean != null) {
			System.out.println(bean.getId());
			System.out.println(bean.getFirstName());
			System.out.println(bean.getLastName());
			System.out.println(bean.getLogin());
			System.out.println(bean.getPassword());
			System.out.println(bean.getDob());
			System.out.println(bean.getMobileNo());
			System.out.println(bean.getRoleId());
			System.out.println(bean.getGender());
			System.out.println(bean.getCreatedBy());
			System.out.println(bean.getModifiedBy());
			System.out.println(bean.getCreatedDatetime());
			System.out.println(bean.getModifiedDatetime());
		} else {
			System.out.println("No record found");
		}

	}

	public static void search() throws ApplicationException, ParseException {
		SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
		UserBean bean = new UserBean();
		UserModel m = new UserModel();
		List<UserBean> list = new ArrayList<UserBean>();

		// bean.setId(1);
		// bean.setFirstName("dee");
		// bean.setLastName("vish");
		// bean.setLogin("deepak");
		bean.setDob(s.parse("2000-01-01"));

		list = m.search(bean, 1, 5);

		Iterator<UserBean> it = list.iterator();

		while (it.hasNext()) {
			bean = it.next();
			System.out.println(bean.getId());
			System.out.println(bean.getFirstName());
			System.out.println(bean.getLastName());
			System.out.println(bean.getLogin());
			System.out.println(bean.getPassword());
			System.out.println(bean.getDob());
			System.out.println(bean.getMobileNo());
			System.out.println(bean.getRoleId());
			System.out.println(bean.getGender());
			System.out.println(bean.getCreatedBy());
			System.out.println(bean.getModifiedBy());
			System.out.println(bean.getCreatedDatetime());
			System.out.println(bean.getModifiedDatetime());
			System.out.println("-----------------------------");
		}

	}
}
