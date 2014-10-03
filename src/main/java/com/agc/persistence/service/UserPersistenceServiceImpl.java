package com.agc.persistence.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.agc.persistence.domain.RecordSet;
import com.agc.persistence.domain.User;

/**
 * @author Dmitry Lekhtuz
 *
 */
public class UserPersistenceServiceImpl extends AbstractPersistentService implements UserPersistenceService {
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
		if (recordSet.size() != 1) {
			// We got either no users or more than one user with the same username.
			LOG.debug(_M + "ended. Number of users returned by db adapter is " + recordSet.size() + ". Should be 1.");
			return null;
		}
		
		User u = getMap2UserTransformer().transform(recordSet.get(0));

		return(u);
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
