package ats.global.techsoft.slayers.service.persistence.impl;


import com.liferay.portal.dao.orm.custom.sql.CustomSQL;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.SQLQuery;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.service.persistence.impl.BasePersistenceImpl;

import java.util.Collections;
import java.util.List;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import ats.global.techsoft.slayers.model.Employees;
import ats.global.techsoft.slayers.model.impl.EmployeesImpl;
import ats.global.techsoft.slayers.service.persistence.EmployeesFinder;

@Component(service = EmployeesFinder.class)
public class EmployeesFinderImpl extends EmployeesFinderBaseImpl implements EmployeesFinder {
	@Reference
	CustomSQL customSQL;

	public List<Employees> getResultByGenderAndAge(String EmplRole){
		Session session= null;
		List<Employees> Employeelist = Collections.EMPTY_LIST;
		try {
			session = openSession();
			String sql = customSQL.get(getClass(), "getResultByGenderAndAge");
			System.out.println("sql ======>"+sql);
			SQLQuery sqlQuery = session.createSQLQuery(sql);
			sqlQuery.setCacheable(false);
			sqlQuery.addEntity("Employees", EmployeesImpl.class);
			QueryPos queryPos = QueryPos.getInstance(sqlQuery);
			queryPos.add(EmplRole);
			Employeelist =  (List<Employees>)sqlQuery.list();
			return Employeelist;
		} catch (Exception e) {
			
		}
		finally {
			closeSession(session);
		}
		return Employeelist;
		
	}
}
