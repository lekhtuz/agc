package com.agc.persistence.service;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.agc.persistence.domain.Employee;
import com.agc.persistence.domain.RecordSet;
import com.agc.persistence.util.Map2BeanTransformer;

/**
 * @author Dmitry Lekhtuz
 *
 */
public class EmployeePersistenceServiceImpl extends AbstractPersistenceService implements EmployeePersistenceService {
	private static final Log LOG = LogFactory.getLog(EmployeePersistenceServiceImpl.class);

	private Map2BeanTransformer<Employee> map2EmployeeTransformer;

	/* (non-Javadoc)
	 * @see com.agc.persistence.service.EmployeePersistenceService#getEmployee(int)
	 */
	@Override
	public Employee getEmployee(int id)
	{
		String _M = "getEmployee(): ";
		LOG.debug(_M + "started. id=" + id);
		
		RecordSet recordSet = getDbAdapter().getRecordSet(getTableName(), "EmEmployeeNo=" + id);
		if (recordSet.size() > 1) {
			// We got more than one employee with the same employee id.
			LOG.debug(_M + "ended. Number of employees returned by db adapter is " + recordSet.size() + ". Should be no more than 1. Is the database corrupted?");
			return(null);
		}

		if (recordSet.size() == 0) {
			// Employee not found
			LOG.debug(_M + "ended. Employee not found");
			return(null);
		}

		Employee employee = getMap2EmployeeTransformer().transform(recordSet.get(0));
		
		LOG.debug(_M + "ended. emloyee=" + employee);
		return(employee);
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

	/**
	 * @return the map2EmployeeTransformer
	 */
	public Map2BeanTransformer<Employee> getMap2EmployeeTransformer()
	{
		return map2EmployeeTransformer;
	}

	/**
	 * @param map2EmployeeTransformer the map2EmployeeTransformer to set
	 */
	public void setMap2EmployeeTransformer(Map2BeanTransformer<Employee> map2EmployeeTransformer)
	{
		this.map2EmployeeTransformer = map2EmployeeTransformer;
	}

}
