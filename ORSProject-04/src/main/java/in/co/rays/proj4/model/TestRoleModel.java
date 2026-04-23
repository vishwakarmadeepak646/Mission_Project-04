package in.co.rays.proj4.model;

import java.sql.Timestamp;
import java.util.Date;

import in.co.rays.proj4.bean.RoleBean;
import in.co.rays.proj4.exception.ApplicationException;

public class TestRoleModel {

	public static void main(String[] args) throws ApplicationException {

		// getAdd();
		// getUpdate();
		getDelete();
	}

	public static void getAdd() throws ApplicationException {
		RoleBean bean = new RoleBean();
		RoleModel m = new RoleModel();

		bean.setName("User5");
		bean.setDescription("Normal user role");
		bean.setCreatedBy("System");
		bean.setModifiedBy("System");
		bean.setCreatedDatetime(new Timestamp(new Date().getTime()));
		bean.setModifiedDatetime(new Timestamp(new Date().getTime()));

		long i = m.add(bean);
		System.out.println(i + " Record added");

	}

	public static void getUpdate() throws ApplicationException {
		RoleBean bean = new RoleBean();
		RoleModel m = new RoleModel();

		bean.setName("L2 User");
		bean.setDescription("L2 User role");
		bean.setCreatedBy("User");
		bean.setModifiedBy("System");
		bean.setCreatedDatetime(new Timestamp(new Date().getTime()));
		bean.setModifiedDatetime(new Timestamp(new Date().getTime()));
		bean.setId(1);

		m.update(bean);

	}

	public static void getDelete() throws ApplicationException {

		RoleBean bean = new RoleBean();
		RoleModel m = new RoleModel();

		bean.setId(4);

		m.delete(bean);
		System.out.println("Records Deleted successfully ...");
	}

}
