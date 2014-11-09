package com.agc.persistence.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.agc.persistence.domain.RecordSet;
import com.agc.persistence.domain.User;
import com.agc.persistence.util.Map2BeanTransformer;

/**
 * @author Dmitry Lekhtuz
 *
 */
public class UserPersistenceServiceImpl extends AbstractPersistenceService implements UserPersistenceService {
	private static final Log LOG = LogFactory.getLog(UserPersistenceServiceImpl.class);

	private Map2BeanTransformer<User> map2UserTransformer;

	/* (non-Javadoc)
	 * @see com.agc.persistence.service.UserPersistenceService#getUser(java.lang.String)
	 */
	@Override
	public User getUser(String username)
	{
		String _M = "getUser(): ";
		LOG.debug(_M + "started. username=" + username);
		
		RecordSet recordSet = getDbAdapter().getRecordSet(getTableName(), "UsUserID=" + "'" + username + "'");
		if (recordSet.size() > 1) {
			// We got more than one user with the same username.
			LOG.debug(_M + "ended. Number of users returned by db adapter is " + recordSet.size() + ". Should be no more than 1. Is the database corrupted?");
			return(null);
		}

		if (recordSet.size() == 0) {
			// User not found
			return(null);
		}

		User user = getMap2UserTransformer().transform(recordSet.get(0));
		return(user);
	}

	/**
	 * @return the map2UserTransformer
	 */
	public Map2BeanTransformer<User> getMap2UserTransformer()
	{
		return map2UserTransformer;
	}

	/**
	 * @param map2UserTransformer the map2UserTransformer to set
	 */
	public void setMap2UserTransformer(Map2BeanTransformer<User> map2UserTransformer)
	{
		this.map2UserTransformer = map2UserTransformer;
	}
}
