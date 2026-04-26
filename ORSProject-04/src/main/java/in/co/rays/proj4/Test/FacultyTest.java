package in.co.rays.proj4.Test;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import in.co.rays.proj4.bean.FacultyBean;
import in.co.rays.proj4.exception.ApplicationException;
import in.co.rays.proj4.exception.DuplicateRecordException;
import in.co.rays.proj4.model.FacultyModel;

public class FacultyTest {

	public static void main(String[] args) throws ApplicationException, ParseException {
		// getAdd();
		// getUpdate();
		// getDelete();
		// findByPK();
		// findByEmail();
		search();
	}

	public static void getAdd() throws ApplicationException, ParseException, DuplicateRecordException {
		SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
		FacultyModel fac = new FacultyModel();
		FacultyBean bean = new FacultyBean();
		bean.setFirstName("Suresh");
		bean.setLastName("Rajpoot");
		bean.setDob(s.parse("1991-08-08"));
		bean.setGender("Male");
		bean.setMobileNo("9988776655");
		bean.setEmail("kamlesh@gamail.com");
		bean.setCollegeId(1);
		bean.setCourseId(1);
		bean.setSubjectId(1);
		bean.setCreatedBy("Admin");
		bean.setModifiedBy("Admin");
		bean.setCreatedDatetime(new Timestamp(new Date().getTime()));
		bean.setModifiedDatetime(new Timestamp(new Date().getTime()));
		fac.add(bean);
	}

	public static void getUpdate() throws ApplicationException, ParseException {
		SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
		FacultyModel fac = new FacultyModel();
		FacultyBean bean = new FacultyBean();
		bean.setId(1);
		bean.setFirstName("Kamlesh");
		bean.setLastName("Rajpoot");
		bean.setDob(s.parse("1991-08-08"));
		bean.setGender("Male");
		bean.setMobileNo("9988776655");
		bean.setEmail("suresh@gamail.com");
		bean.setCollegeId(1);
		bean.setCourseId(1);
		bean.setSubjectId(1);
		bean.setCreatedBy("Admin");
		bean.setModifiedBy("Admin");
		bean.setCreatedDatetime(new Timestamp(new Date().getTime()));
		bean.setModifiedDatetime(new Timestamp(new Date().getTime()));
		fac.update(bean);
	}

	public static void getDelete() throws ApplicationException, ParseException {
		FacultyModel fac = new FacultyModel();
		FacultyBean bean = new FacultyBean();
		bean.setId(2);

		fac.delete(bean);
	}

	public static void findByPK() throws ApplicationException {
		try {
			FacultyModel facModel = new FacultyModel();
			FacultyBean bean = new FacultyBean();
			bean = facModel.findByPk(1);

			if (bean != null) {

				System.out.println(bean.getId());
				System.out.println(bean.getFirstName());
				System.out.println(bean.getLastName());
				System.out.println(bean.getDob());
				System.out.println(bean.getGender());
				System.out.println(bean.getMobileNo());
				System.out.println(bean.getEmail());
				System.out.println(bean.getCollegeId());
				System.out.println(bean.getCollegeName());
				System.out.println(bean.getCollegeId());
				System.out.println(bean.getCollegeName());
				System.out.println(bean.getSubjectId());
				System.out.println(bean.getSubjectName());
				System.out.println(bean.getCreatedBy());
				System.out.println(bean.getModifiedBy());
				System.out.println(bean.getCreatedDatetime());
				System.out.println(bean.getModifiedDatetime());
			} else {
				System.out.println("No Record found");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void findByEmail() throws ApplicationException {
		try {
			FacultyModel facModel = new FacultyModel();
			FacultyBean bean = new FacultyBean();
			bean = facModel.findByName("suresh@gamail.com");

			if (bean != null) {

				System.out.println(bean.getId());
				System.out.println(bean.getFirstName());
				System.out.println(bean.getLastName());
				System.out.println(bean.getDob());
				System.out.println(bean.getGender());
				System.out.println(bean.getMobileNo());
				System.out.println(bean.getEmail());
				System.out.println(bean.getCollegeId());
				System.out.println(bean.getCollegeName());
				System.out.println(bean.getCollegeId());
				System.out.println(bean.getCollegeName());
				System.out.println(bean.getSubjectId());
				System.out.println(bean.getSubjectName());
				System.out.println(bean.getCreatedBy());
				System.out.println(bean.getModifiedBy());
				System.out.println(bean.getCreatedDatetime());
				System.out.println(bean.getModifiedDatetime());
			} else {
				System.out.println("No Record found");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	public static void search() throws ApplicationException {
		FacultyModel model = new FacultyModel();
		FacultyBean bean = new FacultyBean();
		List<FacultyBean> list = new ArrayList<FacultyBean>();
		list = model.search(bean, 1, 5);
		Iterator<FacultyBean> it = list.iterator();
		
		while(it.hasNext()) {
			bean = it.next();
			System.out.println(bean.getId());
			System.out.println(bean.getFirstName());
			System.out.println(bean.getLastName());
			System.out.println(bean.getDob());
			System.out.println(bean.getGender());
			System.out.println(bean.getMobileNo());
			System.out.println(bean.getEmail());
			System.out.println(bean.getCollegeId());
			System.out.println(bean.getCollegeName());
			System.out.println(bean.getCollegeId());
			System.out.println(bean.getCollegeName());
			System.out.println(bean.getSubjectId());
			System.out.println(bean.getSubjectName());
			System.out.println(bean.getCreatedBy());
			System.out.println(bean.getModifiedBy());
			System.out.println(bean.getCreatedDatetime());
			System.out.println(bean.getModifiedDatetime());
			System.out.println("-----------------------------");
		}
	}
}
