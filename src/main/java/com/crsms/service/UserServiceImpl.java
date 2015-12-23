
package com.crsms.service;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.mail.MessagingException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.crsms.dao.UserDao;
import com.crsms.domain.Role;
import com.crsms.domain.User;
import com.crsms.domain.UserInfo;
import com.crsms.util.Invocable;

/**
 * 
 * @author Roman Romaniuk
 *
 */
@Service("userService")
@Transactional(propagation = Propagation.REQUIRED)
public class UserServiceImpl extends BaseServiceImpl<User> implements UserService {
	
	private static final long STUDENT_ROLE_ID = 2;
	
	private final Logger logger = LogManager.getLogger(UserServiceImpl.class);

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private MailService mailService;

	@Autowired
	private UserDao userDao;
	
	@Autowired
	private UserInfoService userInfoService;
	
	@Autowired
	private TeacherRequestService teacherRequestService;

	private Role studentRole;
	
	@PostConstruct
	private void postConstruct() {
		this.studentRole = this.roleService.getRoleById(STUDENT_ROLE_ID);
	}
	
	@Override
	@Transactional
	public User saveUser(User user) {
			user.setEmail(user.getEmail().toLowerCase());
			user.setPassword(passwordEncoder.encode(user.getPassword()));
			userDao.save(user);
			UserInfo userInfo = new UserInfo();
			userInfoService.save(userInfo);
			userInfo.setUser(user);
			userInfoService.update(userInfo);
			
		return user;
	}
	
	@Override
	@Transactional
	public User saveUser(User user, boolean teacherRequest) {
		this.saveUser(user);
		user.setIsEnabled(false);
		user.setRole(this.studentRole);
		this.update(user);
		
		if (teacherRequest) {
			teacherRequestService.createRequest(user);
		}
		
		return user;
	}
	
	@Override
	public User createAndSaveStudent(String email, String password, String url) {
		User user = new User();
		user.setEmail(email);
		user.setPassword(password);
		user = saveUser(user, false);
		try {
			mailService.sendConfirmation(email, user.getId(), url);
		} catch (MessagingException e) {
			logger.error("Error in sending email to " + email + ": " + e);
		}
		return user;
	}
	
	@Override
	public boolean changePassword(String email, String currentPassword, String newPassword) {
		User user = getUserByEmail(email);
		
		if (!this.passwordEncoder.matches(currentPassword, user.getPassword())) {
			return false;
		}
		
		user.setPassword(this.passwordEncoder.encode(newPassword));
		userDao.update(user);
		
		return true;
	}
	
	@Override
	public User activateUser(User user) {
		user.setIsEnabled(true);
		this.update(user);
		return user;
	};
	
	@Override
	public User getUserByEmail(String email) {
		return userDao.getUserByEmail(email);
	}
	
	@Override
	public User getUserByEmail(String email, List<Invocable<User>> initializers) {
		User user = userDao.getUserByEmail(email);
		for (Invocable<User> initalizer : initializers) {
			initalizer.invoke(user);
		}
		return user;
	}

	@Override
	public boolean isEmailExists(String email) {
		return userDao.getUserByEmail(email.toLowerCase()) != null;
	}

	public long getRowsCount(String keyWord) {
		return userDao.getRowsCount(keyWord);
	}

	@Override
	public List<User> getPagingUsers(int offSet, int itemsPerPage, 
									String sortingField, String order, String keyWord) {
		return userDao.getPagingUsers(offSet, itemsPerPage, 
									sortingField, order, keyWord);
	}

	@Override
	public long getUsersToApproveCount() {
		return userDao.getUsersToApproveCount();
	}

	@Override
	public List<User> getUsersToApprove() {
		return userDao.getUsersToApprove();
	}

	@Override
	@Transactional
	public User updateUserParameters(User user, Long roleId, Boolean isEnabled) {
		
		user.setRole(roleService.getRoleById(roleId));
		user.setIsEnabled(isEnabled);
		userDao.update(user);
		return user;
	}

	
}
