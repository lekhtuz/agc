package com.agc.persistence.service;

import org.springframework.stereotype.Repository;

import com.agc.persistence.domain.User;

/**
 * @author Dmitry Lekhtuz
 *
 */
@Repository
public interface UserPersistenceService {
	public User getUser(String username);
}
