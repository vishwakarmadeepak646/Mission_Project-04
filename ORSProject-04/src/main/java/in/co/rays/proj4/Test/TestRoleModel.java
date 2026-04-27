package in.co.rays.proj4.Test;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import com.mchange.v2.sql.filter.SynchronizedFilterDataSource;

import in.co.rays.proj4.bean.RoleBean;
import in.co.rays.proj4.exception.ApplicationException;
import in.co.rays.proj4.model.RoleModel;

public class TestRoleModel {

	public static void main(String[] args) throws ApplicationException {

		 //getAdd();
		// getUpdate();
		// getDelete();
		// getfindByPk();
		// getSearch();
		//getName();
	}

	public static void getAdd() throws ApplicationException {
		RoleBean bean = new RoleBean();
		RoleModel m = new RoleModel();

		bean.setName("Admin");
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

	public static void getfindByPk() throws ApplicationException {

		RoleBean bean = new RoleBean();
		RoleModel m = new RoleModel();

		bean = m.findByPk(2);

		if (bean != null) {
			System.out.println(bean.getId());
			System.out.println(bean.getName());
			System.out.println(bean.getDescription());
			System.out.println(bean.getCreatedBy());
			System.out.println(bean.getModifiedBy());
			System.out.println(bean.getCreatedDatetime());
			System.out.println(bean.getModifiedDatetime());
			System.out.println("--------------------------");
		}
	}

	public static void getName() throws ApplicationException {

		RoleBean bean = new RoleBean();
		RoleModel m = new RoleModel();

		bean = m.findByName("User");

		if (bean != null) {
			System.out.println(bean.getId());
			System.out.println(bean.getName());
			System.out.println(bean.getDescription());
			System.out.println(bean.getCreatedBy());
			System.out.println(bean.getModifiedBy());
			System.out.println(bean.getCreatedDatetime());
			System.out.println(bean.getModifiedDatetime());
			System.out.println("--------------------------");
		} else {
			System.out.println("No record found");
		}
	}

	public static void getSearch() throws ApplicationException {
		RoleBean bean = new RoleBean();
		RoleModel m = new RoleModel();
		List<RoleBean> list = new ArrayList<RoleBean>();

		// bean.setName("User");
		// bean.setId(2);
		// bean.setDescription("nor");

		list = m.search(bean, 1, 5);
		Iterator<RoleBean> it = list.iterator();

		while (it.hasNext()) {
			bean = it.next();

			System.out.println(bean.getId());
			System.out.println(bean.getName());
			System.out.println(bean.getDescription());
			System.out.println(bean.getCreatedBy());
			System.out.println(bean.getModifiedBy());
			System.out.println(bean.getCreatedDatetime());
			System.out.println(bean.getModifiedDatetime());
			System.out.println("--------------------------");
		}

	}

}
