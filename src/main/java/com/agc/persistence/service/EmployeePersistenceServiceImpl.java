/**
 * 
 */
package com.agc.persistence.service;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.agc.persistence.domain.Employee;
import com.agc.persistence.domain.RecordSet;

/**
 * @author lekhdm
 *
 */
public class EmployeePersistenceServiceImpl extends AbstractPersistentService implements EmployeePersistenceService {
	private static final Log LOG = LogFactory.getLog(EmployeePersistenceServiceImpl.class);

	/* (non-Javadoc)
	 * @see com.agc.persistence.service.EmployeePersistenceService#getEmployee(int)
	 */
	@Override
	public Employee getEmployee(int id)
	{
		String _M = "getEmployee(): ";
		LOG.debug(_M + "started. id=" + id);
		
		RecordSet recordSet = getDbAdapter().getRecordSet(getTableName(), "EmEmployeeNo=" + id);
		if (recordSet.size() != 1) {
			return(null);
		}
		
		Employee e = new Employee();
		
		return(e);
	}

	/* (non-Javadoc)
	 * @see com.agc.persistence.service.EmployeePersistenceService#getEmployee(java.lang.String)
	 */
	@Override
	public Employee getEmployee(String username)
	{
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.agc.persistence.service.EmployeePersistenceService#getAllEmployees()
	 */
	@Override
	public List<Employee> getAllEmployees()
	{
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.agc.persistence.service.EmployeePersistenceService#createEmployee(com.agc.persistence.domain.Employee)
	 */
	@Override
	public int createEmployee(Employee employee)
	{
		// TODO Auto-generated method stub
		return 0;
	}

}
