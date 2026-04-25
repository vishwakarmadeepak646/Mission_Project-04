package in.co.rays.proj4.Test;

import java.sql.Timestamp;
import java.util.Date;

import in.co.rays.proj4.bean.SubjectBean;
import in.co.rays.proj4.model.SubjectModel;

public class SubjectTest {

	public static void main(String[] args) {
		// getAdd();
		// getUpdate();
		// getDelete();
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

}
