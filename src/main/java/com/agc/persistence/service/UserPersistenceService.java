package com.agc.persistence.service;

import org.springframework.stereotype.Repository;

import com.agc.persistence.domain.User;

/**
 * @author lekhdm
 *
 */
@Repository
public interface UserPersistenceService {
	public User getUser(String username);
}
