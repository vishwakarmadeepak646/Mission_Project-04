package in.co.rays.proj4.Test;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import in.co.rays.proj4.bean.SubjectBean;
import in.co.rays.proj4.exception.ApplicationException;
import in.co.rays.proj4.model.SubjectModel;

public class SubjectTest {

	public static void main(String[] args) throws ApplicationException {
		// getAdd();
		// getUpdate();
		// getDelete();
		// findByPk();
		//findByName();
		search();
	}

	public static void getAdd() {

		try {
			SubjectBean bean = new SubjectBean();
			SubjectModel model = new SubjectModel();
			bean.setName("CS");
			bean.setCourseId(1);
			bean.setDescription("Computer science book");
			bean.setCreatedBy("Admin");
			bean.setModifiedBy("Admin");
			bean.setCreatedDatetime(new Timestamp(new Date().getTime()));
			bean.setModifiedDatetime(new Timestamp(new Date().getTime()));
			model.add(bean);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void getUpdate() {

		try {
			SubjectBean bean = new SubjectBean();
			SubjectModel model = new SubjectModel();
			bean.setId(1);
			bean.setName("Data Structure");
			bean.setCourseId(1);
			bean.setDescription("Computer science book");
			bean.setCreatedBy("Admin");
			bean.setModifiedBy("Admin");
			bean.setCreatedDatetime(new Timestamp(new Date().getTime()));
			bean.setModifiedDatetime(new Timestamp(new Date().getTime()));
			model.update(bean);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void getDelete() {

		try {
			SubjectBean bean = new SubjectBean();
			SubjectModel model = new SubjectModel();
			bean.setId(2);

			model.delete(bean);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void findByPk() {

		try {
			SubjectBean bean = new SubjectBean();
			SubjectModel model = new SubjectModel();
			bean = model.findByPk(1);

			if (bean != null) {
				System.out.println("Id: " + bean.getId());
				System.out.println("Name: " + bean.getName());
				System.out.println("Course Id: " + bean.getCourseId());
				System.out.println("Course Name: " + bean.getCourseName());
				System.out.println("Description: " + bean.getDescription());
				System.out.println(bean.getCreatedBy());
				System.out.println(bean.getModifiedBy());
				System.out.println(bean.getCreatedDatetime());
				System.out.println(bean.getModifiedDatetime());
				System.out.println("-------------------------");
			} else {
				System.out.println("No result found");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void findByName() {

		try {
			SubjectBean bean = new SubjectBean();
			SubjectModel model = new SubjectModel();
			bean = model.findByName("Data Structure");

			if (bean != null) {
				System.out.println("Id: " + bean.getId());
				System.out.println("Name: " + bean.getName());
				System.out.println("Course Id: " + bean.getCourseId());
				System.out.println("Course Name: " + bean.getCourseName());
				System.out.println("Description: " + bean.getDescription());
				System.out.println(bean.getCreatedBy());
				System.out.println(bean.getModifiedBy());
				System.out.println(bean.getCreatedDatetime());
				System.out.println(bean.getModifiedDatetime());
				System.out.println("-------------------------");
			} else {
				System.out.println("No result found");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void search() throws ApplicationException {

		SubjectBean bean = new SubjectBean();
		SubjectModel model = new SubjectModel();
		List<SubjectBean> list = new ArrayList<SubjectBean>();
		list = model.search(bean, 1, 5);
		Iterator<SubjectBean> it = list.iterator();
		while (it.hasNext()) {
			bean = it.next();

			System.out.println("Id: " + bean.getId());
			System.out.println("Name: " + bean.getName());
			System.out.println("Course Id: " + bean.getCourseId());
			System.out.println("Course Name: " + bean.getCourseName());
			System.out.println("Description: " + bean.getDescription());
			System.out.println(bean.getCreatedBy());
			System.out.println(bean.getModifiedBy());
			System.out.println(bean.getCreatedDatetime());
			System.out.println(bean.getModifiedDatetime());
			System.out.println("-------------------------");
		}

	}

}
