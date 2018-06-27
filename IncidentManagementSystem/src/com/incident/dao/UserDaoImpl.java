package com.incident.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import com.incident.entity.User;

@Repository
public class UserDaoImpl extends AbstractDao implements UserDao{

	@Override
	public void save(User user) {
		persist(user);
	}

	@SuppressWarnings("unchecked")
	public List<User> findAll() {
		Criteria criteria = getSession().createCriteria(User.class);
		return (List<User>) criteria.list();
	}
	
	@Override
	public void update(User user){
		getSession().update(user);
	}

	@Override
	public User getUserByUserMail(String userMail) {
		Criteria criteria = getSession().createCriteria(User.class);
		criteria.add(Restrictions.eq("email",userMail));
		return (User) criteria.uniqueResult();
	}

}
