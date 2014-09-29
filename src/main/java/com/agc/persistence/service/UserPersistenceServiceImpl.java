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
			return null;
		}
		
		User u = new User();
		u.setId(recordSet.getIntValue(0, "UsEmployeeNo"));
		u.setUsername(recordSet.getStringValue(0, "UsUserID"));
		u.setPassword(recordSet.getStringValue(0, "UsUserPassword"));
		u.setUserType(User.UserType.valueOf(recordSet.getStringValue(0, "UsUserType")));

		return(u);
	}
}
