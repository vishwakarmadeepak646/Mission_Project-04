package in.co.rays.proj4.Test;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import in.co.rays.proj4.bean.TimetableBean;
import in.co.rays.proj4.exception.ApplicationException;
import in.co.rays.proj4.model.TimetableModel;

public class TimetableTest {

	public static void main(String[] args) throws Exception {
		// getAdd();
		// getUpdate();
		// getDelete();
		// findByPK();
		// checkByCourseName();
		// testcheckBySubjectName();
		testCheckBySemester();
		// testCheckByExamTime();
		// search();
	}

	public static void getAdd() {

		try {
			SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
			TimetableBean bean = new TimetableBean();
			TimetableModel model = new TimetableModel();

			bean.setSemester("First Semester");
			bean.setDescription("Study related to CS");
			bean.setExamDate(s.parse("2026-04-06"));
			bean.setExamTime("12:00 PM");
			bean.setCourseId(1);
			bean.setSubjectId(1);
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
			SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
			TimetableBean bean = new TimetableBean();
			TimetableModel model = new TimetableModel();
			bean.setId(2);
			bean.setSemester("Second Semester");
			bean.setDescription("Study related to Java");
			bean.setExamDate(s.parse("2026-04-09"));
			bean.setExamTime("12:00 PM");
			bean.setCourseId(1);
			bean.setSubjectId(1);
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
			SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
			TimetableBean bean = new TimetableBean();
			TimetableModel model = new TimetableModel();
			bean.setId(2);

			model.delete(bean);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void findByPK() throws ApplicationException {

		TimetableBean bean = new TimetableBean();
		TimetableModel model = new TimetableModel();
		bean = model.findByPk(1);

		if (bean != null) {
			System.out.println(bean.getId());
			System.out.println(bean.getSemester());
			System.out.println(bean.getDescription());
			System.out.println(bean.getExamDate());
			System.out.println(bean.getExamTime());
			System.out.println(bean.getCourseId());
			System.out.println(bean.getCourseName());
			System.out.println(bean.getSubjectId());
			System.out.println(bean.getSubjectName());
			System.out.println(bean.getCreatedBy());
			System.out.println(bean.getModifiedBy());
			System.out.println(bean.getCreatedDatetime());
			System.out.println(bean.getModifiedDatetime());
		} else {
			System.out.println("Record not found");
		}
	}

	public static void checkByCourseName() throws ApplicationException, ParseException {
		SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
		TimetableBean bean = new TimetableBean();
		TimetableModel model = new TimetableModel();

		bean = model.checkByCourseName(1L, new java.sql.Date(s.parse("2026-04-06").getTime()));

		print(bean);
	}

	public static void search() {

		try {
			TimetableBean bean = new TimetableBean();
			TimetableModel model = new TimetableModel();
			List<TimetableBean> list = new ArrayList<TimetableBean>();

			list = model.search(bean, 1, 5);
			Iterator<TimetableBean> it = list.iterator();

			while (it.hasNext()) {

				bean = it.next();
				print(bean);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void testcheckBySubjectName() {
		SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
		try {
			TimetableBean bean = new TimetableBean();
			TimetableModel model = new TimetableModel();

			bean = model.checkBySemester(1L, 1L, "First Semester", new java.sql.Date(s.parse("2026-04-06").getTime()));

			print(bean);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void testCheckByExamTime() throws Exception, ParseException {
		SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
		TimetableBean bean = new TimetableBean();
		TimetableModel model = new TimetableModel();
		bean = model.checkByExamTime(1L, 1L, "First Semester", new java.sql.Date(s.parse("2026-04-06").getTime()),
				"12:00 PM", "Study related to CS");
		print(bean);
	}

	public static void testCheckBySemester() throws ApplicationException, ParseException {
		SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
		TimetableBean bean = new TimetableBean();
		TimetableModel model = new TimetableModel();
		bean = model.checkBySemester(1L, 1L, "First Semester", new java.sql.Date(s.parse("2026-04-06").getTime()));
		print(bean);
	}

	public static void print(TimetableBean bean) {

		if (bean != null) {
			System.out.println(bean.getId());
			System.out.println(bean.getSemester());
			System.out.println(bean.getDescription());
			System.out.println(bean.getExamDate());
			System.out.println(bean.getExamTime());
			System.out.println(bean.getCourseId());
			System.out.println(bean.getCourseName());
			System.out.println(bean.getSubjectId());
			System.out.println(bean.getSubjectName());
			System.out.println(bean.getCreatedBy());
			System.out.println(bean.getModifiedBy());
			System.out.println(bean.getCreatedDatetime());
			System.out.println(bean.getModifiedDatetime());
			System.out.println("----------------------");
		} else {
			System.out.println("Record not found");
		}

	}

}
