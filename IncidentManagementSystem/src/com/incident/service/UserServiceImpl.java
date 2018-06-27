package com.incident.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.incident.dao.UserDao;
import com.incident.entity.User;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao dao;

	public boolean save(User user) {
		try {
			dao.save(user);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public List<User> findAll() {
		return dao.findAll();
	}

	public boolean update(User user) {
		try {
			dao.update(user);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public User getUserByUserMail(String userMail) {
		return dao.getUserByUserMail(userMail);
	}
}
