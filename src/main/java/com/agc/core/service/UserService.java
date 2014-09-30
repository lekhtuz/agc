package com.agc.core.service;

import com.agc.persistence.domain.User;

/**
 * @author Dmitry Lekhtuz
 *
 */
public interface UserService {
	public User login(String username, String password);
}
