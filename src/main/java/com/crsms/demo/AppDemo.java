package com.crsms.demo;


import java.util.LinkedHashSet;
import java.util.Set;

import org.joda.time.DateTime;
import org.joda.time.Duration;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;


import com.crsms.domain.Answer;
import com.crsms.domain.Course;
import com.crsms.domain.Direction;
import com.crsms.domain.Group;
import com.crsms.domain.GroupType;
import com.crsms.domain.Module;
import com.crsms.domain.Question;
import com.crsms.domain.Resource;
import com.crsms.domain.Role;
import com.crsms.domain.Test;
import com.crsms.domain.User;
import com.crsms.domain.UserInfo;
import com.crsms.service.CourseService;
import com.crsms.service.TestService;
import com.crsms.service.UserService;

/**
 * 
 * @author Valerii Motresku
 *
 */

public class AppDemo {

	public static void main(String[] args) {
		
		AbstractApplicationContext context = new AnnotationConfigApplicationContext(AppDemoConfig.class);
		/*
		AnnotationConfigApplicationContext  context = new AnnotationConfigApplicationContext();
		context.scan("com.crsms.config"); 
		context.refresh();
		*/
		
		TestService testService = (TestService) context.getBean("testService");
		 
        /*
         * Create test1
         */
        Test test1 = new Test();
        test1.setName("test1");
        test1.setAvailable(false);
        test1.setModule(new Module());
       
        Set<Question> tempQuestions = new LinkedHashSet<Question>();
        
        Question tempQuestion = new Question();
        tempQuestion.setText("How are you?");
        tempQuestion.setTest(test1);
        tempQuestions.add(tempQuestion);
        
        tempQuestion = new Question();
        tempQuestion.setText("What are you?");
        tempQuestion.setTest(test1);
        
        Answer answer1 = new Answer();
        answer1.setQuestion(tempQuestion);
        answer1.setCorrect(false);
        answer1.setText("Red");
        
        Answer answer2 = new Answer();
        answer2.setQuestion(tempQuestion);
        answer2.setCorrect(true);
        answer2.setText("Balck");
        
        Set<Answer> answers = new LinkedHashSet<Answer>();
        answers.add(answer1);
        answers.add(answer2);
        
        // assign answers
        tempQuestion.setAnswers(answers);
        
        tempQuestions.add(tempQuestion);
        
        // assign questions
        test1.setQuestions(tempQuestions);
 
        /*
         * Create test2
         */
        Test test2 = new Test();
        test2.setName("test2");
        test2.setAvailable(false);
        test2.setModule(new Module());
        test2.setQuestions(new LinkedHashSet<Question>());
 
        /*
         * Persist both tests
         */

        test1.setModule(null);
        test2.setModule(null);
        testService.saveTest(test1);
        testService.saveTest(test2);
 
        //get persisted test
        Test test3 = testService.getTestById(1l);
        System.out.println(test3.getQuestions().iterator().next().getText());

        
        //create course
        Course course = new Course();
        course.setName("Course 1");
        
        Direction direction = new Direction();
        direction.setName("Java");
        course.setDirection(direction);
        
        course.setOpen(true);
        course.setStartDate(new DateTime());
        course.setDuration(new Duration(10000000l));
        
        //create modules
        Module module = new Module();
        module.setAvailable(false);
        module.setDescription("DESC");
        module.setName("Module 1");
        module.setOrderPosition(1l);
        
        //connect test to module
        test1.setModule(module);
        Set<Test> tests = new LinkedHashSet<>();
        tests.add(test1);
        module.setTests(tests);
        
        //create resources
        Resource res = new Resource();
        res.setUrl("https://google.com.ua");
        Set<Resource> resources = new LinkedHashSet<Resource>();
        resources.add(res);
        
        res = new Resource();
        res.setUrl("https://softserve.ua");
        resources.add(res);
        
        module.setResources(resources);
       
        // connect module to course
        module.setCourse(course);
        
        Set<Module> modules = new LinkedHashSet<>();
       
        modules.add(module);
        
        course.setModules(modules);
        
        // persist course
        CourseService courseService = (CourseService) context.getBean("courseService");
        courseService.saveCourse(course);
        
        // course2
        Course course2 = new Course();
       
        Direction direction2 = new Direction();
        direction2.setName("C++");
        course2.setDirection(direction2);
        
        course2.setDuration(null);
        course2.setModules(null);
        course2.setName("Course 2");
        course2.setStartDate(null);
        
        // persist course2
        courseService.saveCourse(course2);
        
        // course3
        Course course3 = new Course();
        course3.setDirection(direction2);        
        course3.setDuration(null);
        course3.setModules(null);
        course3.setName("Course 3");
        course3.setStartDate(null);
        
        // persist course3
        courseService.saveCourse(course3);
        
        // create user
        User user = new User();
        user.setEmail("amberu@gmail.com");
        user.setPasssword("root");
        
        //create role
        Role role = new Role();
        role.setName("admin");	        
        //assign role
        user.setRole(role);
        
        //create userInfo
        UserInfo userInfo = new UserInfo();
        userInfo.setFirstName("Valerii");
        userInfo.setSecondName("Motresku");
        userInfo.setGroups(null);
        //assign userInfo to user
        userInfo.setUser(user);
        user.setUserInfo(userInfo);
        
        //persist user
        UserService userService = (UserService) context.getBean("userService");
        userService.saveUser(user);
        
        //user2
        User user2 = new User();
        user2.setEmail("user2@gmail.com");
        user2.setRole(role);
        user2.setPasssword("root");
        
        //userInfo based on existing
        //userInfo.setUser(user2);
        //user2.setUserInfo(userInfo);
        
       
        //create userInfo2
        UserInfo userInfo2 = new UserInfo();
        userInfo2.setFirstName("user2");
        userInfo2.setSecondName("user2");
        userInfo2.setGroups(null);
        //assign userInfo to user
        userInfo2.setUser(user2);
        user2.setUserInfo(userInfo2);
         
        // persist user2
        userService.saveUser(user2);
        
        
        //create group
        Group group1 = new Group();
        
        Set<Course> courses = new LinkedHashSet<>();
        courses.add(course);
        group1.setCourses(courses);
        
        group1.setMaxUserCount(10L);
        group1.setName("Ch-035");
        //group1.setRecruited(false);
        
        //create groupType
        GroupType groupType = new  GroupType();
        groupType.setName("Morning");
        //assign groupType
        group1.setGroupType(groupType);
        
        Set<UserInfo> users = new LinkedHashSet<>();
        users.add(user.getUserInfo());
        users.add(user2.getUserInfo());
        group1.setUsers(users);
        
        // I'm tired :(
        
        
        context.close();
	}

}
