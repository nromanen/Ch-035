package com.crsms.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.crsms.domain.Course;

/**
 * 
 * @author Valerii Motresku, maftey, St. Roman
 *
 */

@Repository("courseDao")
public class CourseDaoImpl extends BaseDaoImpl<Course> implements CourseDao {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	private static Logger logger = LogManager.getLogger(TestDaoImpl.class);

	public CourseDaoImpl() {
		super(Course.class);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Course> getAllInitialized() {
		try {
			List<Course> courses = null;
			courses = (List<Course>) sessionFactory.getCurrentSession()
													.createQuery("FROM Course").list();
			for (Course course : courses) {
				Hibernate.initialize(course.getModules());
			}
			return courses;

		} catch (HibernateException e) {
			logger.error("Error getAllCourse: " + e);
			throw e;
		}
		
	}
	
	@Override
	public Course get(String name) {
		try {
			sessionFactory.getCurrentSession()
				.createQuery("FROM Course c WHERE c.name=:name")
				.setString("name", name).uniqueResult();
		} catch (Exception e) {
			logger.error("Error getCourse: " + e);
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
			Query query = sessionFactory.getCurrentSession()
										.createQuery(hql).setParameter("id", areaId);
			list = query.list();
		} catch (Exception e) {
			logger.error("Error in getting all courses by area id: " + e);
			throw e;
		}
		return list;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Course> getAllByUserId(Long userId) {
		List<Course> list = new ArrayList<Course>();
		try {
			list = sessionFactory.getCurrentSession()
								 .getNamedQuery(Course.GET_BY_USER_ID)
							 	 .setParameter("userId", userId).list();
		} catch (Exception e) {
			logger.error("Error in getting all courses by user id: " + e);
			throw e;
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Course> getAllByUserEmail(String email) {
		List<Course> list = new ArrayList<Course>();
		try {
			list = sessionFactory.getCurrentSession()
								 .getNamedQuery(Course.GET_BY_USER_EMAIL)
							 	 .setParameter("email", email).list();
		} catch (Exception e) {
			logger.error("Error in getting all courses by user email: " + e);
			throw e;
		}
		return list;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Course> getAllByOwnerEmail(String email) {
		List<Course> list = new ArrayList<Course>();
		try {
			list = sessionFactory.getCurrentSession()
								 .getNamedQuery(Course.GET_BY_OWNER_EMAIL)
							 	 .setParameter("email", email).list();
		} catch (Exception e) {
			logger.error("Error in getting all courses by owner email: " + e);
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
			
			sessionFactory.getCurrentSession().createQuery(hqlDelModule)
				.setParameter("id", course.getId()).executeUpdate();
			sessionFactory.getCurrentSession().createQuery(hqlDelTest)
				.setParameter("id", course.getId()).executeUpdate();
			sessionFactory.getCurrentSession().createQuery(hqlDelQuestion)
				.setParameter("id", course.getId()).executeUpdate();
			sessionFactory.getCurrentSession().createQuery(hqlDelAnswer)
				.setParameter("id", course.getId()).executeUpdate();
		} catch (Exception e) {
			logger.error("Error in disable courses: " + e);
		}
		
	}

  @SuppressWarnings("unchecked")
	@Override
  public List<Course> searchCourses(String searchWord) {
    try {
      return (List<Course>) sessionFactory.getCurrentSession()
                        .createQuery("SELECT c FROM Course c WHERE UPPER(c.name) LIKE UPPER(:s) OR "
                        + "UPPER(c.description) LIKE UPPER(:s) ORDER BY c.name, c.description")
                        .setParameter("s", "%" + searchWord + "%").list();
    } catch (HibernateException e) {
      logger.error("Error searchCourses: " + e);
    }
    return null;
  }

}
