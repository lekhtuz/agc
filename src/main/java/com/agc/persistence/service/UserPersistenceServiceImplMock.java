package com.agc.persistence.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanPropertyValueEqualsPredicate;
import org.apache.commons.collections.CollectionUtils;

import com.agc.persistence.domain.User;

/**
 * @author Dmitry Lekhtuz
 *
 */
public class UserPersistenceServiceImplMock implements UserPersistenceService {
	private static List<User> storage = new ArrayList<User>();

	static {
		User u = new User();
		
		u.setId(1);
		u.setUsername("BobW");
		u.setPassword("Production");
		storage.add(u);
	}

	@Override
	public User getUser(String username)
	{
		BeanPropertyValueEqualsPredicate predicate = new BeanPropertyValueEqualsPredicate("username", username);
		User u = (User)CollectionUtils.find(storage, predicate);
		return(u);
	}
}
