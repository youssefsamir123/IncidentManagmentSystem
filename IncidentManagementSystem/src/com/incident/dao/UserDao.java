package com.incident.dao;

import com.incident.entity.User;


public interface UserDao extends GenericDAO<User>{
	
	public User getUserByUserMail(String userMail);
}
