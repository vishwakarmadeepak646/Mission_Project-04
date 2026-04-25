package in.co.rays.proj4.Test;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import in.co.rays.proj4.bean.MarksheetBean;
import in.co.rays.proj4.exception.ApplicationException;
import in.co.rays.proj4.model.MarksheetModel;

public class MarksheetTest {

	public static void main(String[] args) throws ApplicationException {
		// getAdd();
		// getUpdate();
		// getDelete();
		// findByPK();
		// findByRollNo();
		search();
	}

	public static void getAdd() throws ApplicationException {
		try {
			MarksheetBean bean = new MarksheetBean();
			MarksheetModel model = new MarksheetModel();

			bean.setId(1);
			bean.setRollNo("101");
			bean.setStudentId(2);
			bean.setPhysics(87);
			bean.setChemistry(89);
			bean.setMaths(98);
			bean.setCreatedBy("Admin");
			bean.setModifiedBy("Admin");
			bean.setCreatedDatetime(new Timestamp(new Date().getTime()));
			bean.setModifiedDatetime(new Timestamp(new Date().getTime()));
			System.out.println("------------------");
			model.add(bean);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void getUpdate() throws ApplicationException {
		try {
			MarksheetBean bean = new MarksheetBean();
			MarksheetModel model = new MarksheetModel();

			bean.setId(2);
			bean.setRollNo("102");
			bean.setStudentId(1);
			bean.setPhysics(77);
			bean.setChemistry(59);
			bean.setMaths(88);
			bean.setCreatedBy("Admin");
			bean.setModifiedBy("Admin");
			bean.setCreatedDatetime(new Timestamp(new Date().getTime()));
			bean.setModifiedDatetime(new Timestamp(new Date().getTime()));
			System.out.println("------------------");
			model.update(bean);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void getDelete() throws ApplicationException {
		try {
			MarksheetBean bean = new MarksheetBean();
			MarksheetModel model = new MarksheetModel();

			bean.setId(4);
			model.delete(bean);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void findByPK() throws ApplicationException {

		MarksheetBean bean = new MarksheetBean();
		MarksheetModel model = new MarksheetModel();
		bean = model.findByPk(1);

		if (bean != null) {
			System.out.println(bean.getId());
			System.out.println(bean.getRollNo());
			System.out.println(bean.getStudentId());
			System.out.println(bean.getName());
			System.out.println(bean.getPhysics());
			System.out.println(bean.getChemistry());
			System.out.println(bean.getMaths());
			System.out.println(bean.getCreatedBy());
			System.out.println(bean.getModifiedBy());
			System.out.println(bean.getCreatedDatetime());
			System.out.println(bean.getModifiedDatetime());
			System.out.println("-------------------------");
		} else {
			System.out.println("No Record found");
		}
	}

	public static void findByRollNo() throws ApplicationException {

		MarksheetBean bean = new MarksheetBean();
		MarksheetModel model = new MarksheetModel();
		bean = model.findByRollNo("101");

		if (bean != null) {
			System.out.println(bean.getId());
			System.out.println(bean.getRollNo());
			System.out.println(bean.getStudentId());
			System.out.println(bean.getName());
			System.out.println(bean.getPhysics());
			System.out.println(bean.getChemistry());
			System.out.println(bean.getMaths());
			System.out.println(bean.getCreatedBy());
			System.out.println(bean.getModifiedBy());
			System.out.println(bean.getCreatedDatetime());
			System.out.println(bean.getModifiedDatetime());
			System.out.println("-------------------------");
		} else {
			System.out.println("No Record found");
		}
	}

	public static void search() throws ApplicationException {

		MarksheetBean bean = new MarksheetBean();
		MarksheetModel model = new MarksheetModel();
		List<MarksheetBean> list = new ArrayList<>();
		// bean.setId(1);
		// bean.setRollNo("101");
		list = model.search(bean, 1, 5);
		Iterator<MarksheetBean> it = list.iterator();

		while (it.hasNext()) {
			bean = it.next();
			System.out.println(bean.getId());
			System.out.println(bean.getRollNo());
			System.out.println(bean.getStudentId());
			System.out.println(bean.getName());
			System.out.println(bean.getPhysics());
			System.out.println(bean.getChemistry());
			System.out.println(bean.getMaths());
			System.out.println(bean.getCreatedBy());
			System.out.println(bean.getModifiedBy());
			System.out.println(bean.getCreatedDatetime());
			System.out.println(bean.getModifiedDatetime());
			System.out.println("-------------------------");
		}

	}
}
