package com.crsms.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.crsms.domain.Course;

/**
 * 
 * @author Valerii Motresku, maftey, St. Roman
 *
 */

@Repository
public class CourseDaoImpl extends BaseDaoImpl<Course> implements CourseDao {

	public CourseDaoImpl() {
		super(Course.class);
	}
	
	@Override
	public Course get(String name) {
		try {
			this.getSessionFactory().getCurrentSession()
				.createQuery("FROM Course c WHERE c.name=:name")
				.setString("name", name).uniqueResult();
		} catch (Exception e) {
			this.getLogger().error("Error getCourse: " + e);
			throw e;
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Course> getAllByAreaId(Long areaId) {
		List<Course> list = new ArrayList<Course>();
		try {
			String hql = "from Course where area_id = :id order by id asc";
			Query query = this.getSessionFactory().getCurrentSession()
										.createQuery(hql).setParameter("id", areaId);
			list = query.list();
		} catch (Exception e) {
			this.getLogger().error("Error in getting all courses by area id: " + e);
			throw e;
		}
		return list;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Course> getAllByUserId(Long userId) {
		List<Course> list = new ArrayList<Course>();
		try {
			list = this.getSessionFactory().getCurrentSession()
								 .getNamedQuery(Course.GET_BY_USER_ID)
							 	 .setParameter("userId", userId).list();
		} catch (Exception e) {
			this.getLogger().error("Error in getting all courses by user id: " + e);
			throw e;
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Course> getAllByUserEmail(String email) {
		List<Course> list = new ArrayList<Course>();
		try {
			list = this.getSessionFactory().getCurrentSession()
								 .getNamedQuery(Course.GET_BY_USER_EMAIL)
							 	 .setParameter("email", email).list();
		} catch (Exception e) {
			this.getLogger().error("Error in getting all courses by user email: " + e);
			throw e;
		}
		return list;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Course> getAllByOwnerEmail(String email) {
		List<Course> list = new ArrayList<Course>();
		try {
			list = this.getSessionFactory().getCurrentSession()
								 .getNamedQuery(Course.GET_BY_OWNER_EMAIL)
							 	 .setParameter("email", email).list();
		} catch (Exception e) {
			this.getLogger().error("Error in getting all courses by owner email: " + e);
		}
		return list;
	}

	@Override
	public void disable(Course course) {
		course.setDisable(true);
		this.update(course);
		try { //TODO: this is piece of shit, maybe rewrite?
			String hqlDelModule = ""
					+ "UPDATE Module module SET module.disable=true WHERE module IN "
					+ "(SELECT moduleList "
					+ "FROM Course course "
					+ "JOIN course.modules moduleList "
					+ "WHERE course.id = :id)";
			
			String hqlDelTest = "UPDATE Test test SET test.disable=true WHERE test IN "
					+ "(SELECT testList "
					+ "FROM Course course "
					+ "JOIN course.modules moduleList "
					+ "JOIN moduleList.tests testList "
					+ "WHERE course.id = :id)";
			
			String hqlDelQuestion = ""
					+ "UPDATE Question question SET question.disable=true WHERE question IN "
					+ "(SELECT questionList "
					+ "FROM Course course "
					+ "JOIN course.modules moduleList "
					+ "JOIN moduleList.tests testList "
					+ "JOIN testList.questions questionList "
					+ "WHERE course.id = :id)";
			
			String hqlDelAnswer = "UPDATE Answer answer SET answer.disable=true WHERE answer IN "
					+ "(SELECT answerList "
					+ "FROM Course course "
					+ "JOIN course.modules moduleList "
					+ "JOIN moduleList.tests testList "
					+ "JOIN testList.questions questionList "
					+ "JOIN questionList.answers answerList "
					+ "WHERE course.id = :id)";
			
			this.getSessionFactory().getCurrentSession().createQuery(hqlDelModule)
				.setParameter("id", course.getId()).executeUpdate();
			this.getSessionFactory().getCurrentSession().createQuery(hqlDelTest)
				.setParameter("id", course.getId()).executeUpdate();
			this.getSessionFactory().getCurrentSession().createQuery(hqlDelQuestion)
				.setParameter("id", course.getId()).executeUpdate();
			this.getSessionFactory().getCurrentSession().createQuery(hqlDelAnswer)
				.setParameter("id", course.getId()).executeUpdate();
		} catch (Exception e) {
			this.getLogger().error("Error in disable courses: " + e);
		}
		
	}

  @SuppressWarnings("unchecked")
	@Override
  public List<Course> searchCourses(String searchWord) {
    try {
      return (List<Course>) this.getSessionFactory().getCurrentSession()
                        .getNamedQuery(Course.SEARCH)
                        .setParameter("s", "%" + searchWord + "%").list();
    } catch (HibernateException e) {
      this.getLogger().error("Error searchCourses: " + e);
    }
    return null;
  }

}
