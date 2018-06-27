package com.incident.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.incident.entity.User;

@Service
@Transactional
public interface UserService extends GenericService<User>{

	public User getUserByUserMail(String userMail);
	
}
