package com.agc.core.services;

import com.agc.persistence.domain.User;

/**
 * @author lekhdm
 *
 */
public interface UserService {
	public User login(String username, String password);
}
