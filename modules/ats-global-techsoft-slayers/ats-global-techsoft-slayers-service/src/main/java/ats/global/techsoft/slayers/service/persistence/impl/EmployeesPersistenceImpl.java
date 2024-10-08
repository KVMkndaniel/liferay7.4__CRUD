/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package ats.global.techsoft.slayers.service.persistence.impl;

import ats.global.techsoft.slayers.exception.NoSuchEmployeesException;
import ats.global.techsoft.slayers.model.Employees;
import ats.global.techsoft.slayers.model.EmployeesTable;
import ats.global.techsoft.slayers.model.impl.EmployeesImpl;
import ats.global.techsoft.slayers.model.impl.EmployeesModelImpl;
import ats.global.techsoft.slayers.service.persistence.EmployeesPersistence;
import ats.global.techsoft.slayers.service.persistence.EmployeesUtil;
import ats.global.techsoft.slayers.service.persistence.impl.constants.ATSPersistenceConstants;

import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.configuration.Configuration;
import com.liferay.portal.kernel.dao.orm.EntityCache;
import com.liferay.portal.kernel.dao.orm.FinderCache;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.dao.orm.SessionFactory;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.security.auth.CompanyThreadLocal;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextThreadLocal;
import com.liferay.portal.kernel.service.persistence.impl.BasePersistenceImpl;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.SetUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.uuid.PortalUUID;

import java.io.Serializable;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import javax.sql.DataSource;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

/**
 * The persistence implementation for the employees service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@Component(service = EmployeesPersistence.class)
public class EmployeesPersistenceImpl
	extends BasePersistenceImpl<Employees> implements EmployeesPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>EmployeesUtil</code> to access the employees persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		EmployeesImpl.class.getName();

	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List1";

	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List2";

	private FinderPath _finderPathWithPaginationFindAll;
	private FinderPath _finderPathWithoutPaginationFindAll;
	private FinderPath _finderPathCountAll;
	private FinderPath _finderPathWithPaginationFindByUuid;
	private FinderPath _finderPathWithoutPaginationFindByUuid;
	private FinderPath _finderPathCountByUuid;

	/**
	 * Returns all the employeeses where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching employeeses
	 */
	@Override
	public List<Employees> findByUuid(String uuid) {
		return findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the employeeses where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>EmployeesModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of employeeses
	 * @param end the upper bound of the range of employeeses (not inclusive)
	 * @return the range of matching employeeses
	 */
	@Override
	public List<Employees> findByUuid(String uuid, int start, int end) {
		return findByUuid(uuid, start, end, null);
	}

	/**
	 * Returns an ordered range of all the employeeses where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>EmployeesModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of employeeses
	 * @param end the upper bound of the range of employeeses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching employeeses
	 */
	@Override
	public List<Employees> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<Employees> orderByComparator) {

		return findByUuid(uuid, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the employeeses where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>EmployeesModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of employeeses
	 * @param end the upper bound of the range of employeeses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching employeeses
	 */
	@Override
	public List<Employees> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<Employees> orderByComparator,
		boolean useFinderCache) {

		uuid = Objects.toString(uuid, "");

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindByUuid;
				finderArgs = new Object[] {uuid};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByUuid;
			finderArgs = new Object[] {uuid, start, end, orderByComparator};
		}

		List<Employees> list = null;

		if (useFinderCache) {
			list = (List<Employees>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (Employees employees : list) {
					if (!uuid.equals(employees.getUuid())) {
						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler sb = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					3 + (orderByComparator.getOrderByFields().length * 2));
			}
			else {
				sb = new StringBundler(3);
			}

			sb.append(_SQL_SELECT_EMPLOYEES_WHERE);

			boolean bindUuid = false;

			if (uuid.isEmpty()) {
				sb.append(_FINDER_COLUMN_UUID_UUID_3);
			}
			else {
				bindUuid = true;

				sb.append(_FINDER_COLUMN_UUID_UUID_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(EmployeesModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				if (bindUuid) {
					queryPos.add(uuid);
				}

				list = (List<Employees>)QueryUtil.list(
					query, getDialect(), start, end);

				cacheResult(list);

				if (useFinderCache) {
					finderCache.putResult(finderPath, finderArgs, list);
				}
			}
			catch (Exception exception) {
				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first employees in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching employees
	 * @throws NoSuchEmployeesException if a matching employees could not be found
	 */
	@Override
	public Employees findByUuid_First(
			String uuid, OrderByComparator<Employees> orderByComparator)
		throws NoSuchEmployeesException {

		Employees employees = fetchByUuid_First(uuid, orderByComparator);

		if (employees != null) {
			return employees;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("uuid=");
		sb.append(uuid);

		sb.append("}");

		throw new NoSuchEmployeesException(sb.toString());
	}

	/**
	 * Returns the first employees in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching employees, or <code>null</code> if a matching employees could not be found
	 */
	@Override
	public Employees fetchByUuid_First(
		String uuid, OrderByComparator<Employees> orderByComparator) {

		List<Employees> list = findByUuid(uuid, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last employees in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching employees
	 * @throws NoSuchEmployeesException if a matching employees could not be found
	 */
	@Override
	public Employees findByUuid_Last(
			String uuid, OrderByComparator<Employees> orderByComparator)
		throws NoSuchEmployeesException {

		Employees employees = fetchByUuid_Last(uuid, orderByComparator);

		if (employees != null) {
			return employees;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("uuid=");
		sb.append(uuid);

		sb.append("}");

		throw new NoSuchEmployeesException(sb.toString());
	}

	/**
	 * Returns the last employees in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching employees, or <code>null</code> if a matching employees could not be found
	 */
	@Override
	public Employees fetchByUuid_Last(
		String uuid, OrderByComparator<Employees> orderByComparator) {

		int count = countByUuid(uuid);

		if (count == 0) {
			return null;
		}

		List<Employees> list = findByUuid(
			uuid, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the employeeses before and after the current employees in the ordered set where uuid = &#63;.
	 *
	 * @param EmployeeId the primary key of the current employees
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next employees
	 * @throws NoSuchEmployeesException if a employees with the primary key could not be found
	 */
	@Override
	public Employees[] findByUuid_PrevAndNext(
			long EmployeeId, String uuid,
			OrderByComparator<Employees> orderByComparator)
		throws NoSuchEmployeesException {

		uuid = Objects.toString(uuid, "");

		Employees employees = findByPrimaryKey(EmployeeId);

		Session session = null;

		try {
			session = openSession();

			Employees[] array = new EmployeesImpl[3];

			array[0] = getByUuid_PrevAndNext(
				session, employees, uuid, orderByComparator, true);

			array[1] = employees;

			array[2] = getByUuid_PrevAndNext(
				session, employees, uuid, orderByComparator, false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected Employees getByUuid_PrevAndNext(
		Session session, Employees employees, String uuid,
		OrderByComparator<Employees> orderByComparator, boolean previous) {

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				4 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			sb = new StringBundler(3);
		}

		sb.append(_SQL_SELECT_EMPLOYEES_WHERE);

		boolean bindUuid = false;

		if (uuid.isEmpty()) {
			sb.append(_FINDER_COLUMN_UUID_UUID_3);
		}
		else {
			bindUuid = true;

			sb.append(_FINDER_COLUMN_UUID_UUID_2);
		}

		if (orderByComparator != null) {
			String[] orderByConditionFields =
				orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				sb.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				sb.append(_ORDER_BY_ENTITY_ALIAS);
				sb.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						sb.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(WHERE_GREATER_THAN);
					}
					else {
						sb.append(WHERE_LESSER_THAN);
					}
				}
			}

			sb.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				sb.append(_ORDER_BY_ENTITY_ALIAS);
				sb.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						sb.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(ORDER_BY_ASC);
					}
					else {
						sb.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			sb.append(EmployeesModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		if (bindUuid) {
			queryPos.add(uuid);
		}

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(employees)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<Employees> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the employeeses where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	@Override
	public void removeByUuid(String uuid) {
		for (Employees employees :
				findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(employees);
		}
	}

	/**
	 * Returns the number of employeeses where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching employeeses
	 */
	@Override
	public int countByUuid(String uuid) {
		uuid = Objects.toString(uuid, "");

		FinderPath finderPath = _finderPathCountByUuid;

		Object[] finderArgs = new Object[] {uuid};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_EMPLOYEES_WHERE);

			boolean bindUuid = false;

			if (uuid.isEmpty()) {
				sb.append(_FINDER_COLUMN_UUID_UUID_3);
			}
			else {
				bindUuid = true;

				sb.append(_FINDER_COLUMN_UUID_UUID_2);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				if (bindUuid) {
					queryPos.add(uuid);
				}

				count = (Long)query.uniqueResult();

				finderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception exception) {
				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_UUID_UUID_2 =
		"employees.uuid = ?";

	private static final String _FINDER_COLUMN_UUID_UUID_3 =
		"(employees.uuid IS NULL OR employees.uuid = '')";

	private FinderPath _finderPathFetchByUUID_G;
	private FinderPath _finderPathCountByUUID_G;

	/**
	 * Returns the employees where uuid = &#63; and groupId = &#63; or throws a <code>NoSuchEmployeesException</code> if it could not be found.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching employees
	 * @throws NoSuchEmployeesException if a matching employees could not be found
	 */
	@Override
	public Employees findByUUID_G(String uuid, long groupId)
		throws NoSuchEmployeesException {

		Employees employees = fetchByUUID_G(uuid, groupId);

		if (employees == null) {
			StringBundler sb = new StringBundler(6);

			sb.append(_NO_SUCH_ENTITY_WITH_KEY);

			sb.append("uuid=");
			sb.append(uuid);

			sb.append(", groupId=");
			sb.append(groupId);

			sb.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(sb.toString());
			}

			throw new NoSuchEmployeesException(sb.toString());
		}

		return employees;
	}

	/**
	 * Returns the employees where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching employees, or <code>null</code> if a matching employees could not be found
	 */
	@Override
	public Employees fetchByUUID_G(String uuid, long groupId) {
		return fetchByUUID_G(uuid, groupId, true);
	}

	/**
	 * Returns the employees where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching employees, or <code>null</code> if a matching employees could not be found
	 */
	@Override
	public Employees fetchByUUID_G(
		String uuid, long groupId, boolean useFinderCache) {

		uuid = Objects.toString(uuid, "");

		Object[] finderArgs = null;

		if (useFinderCache) {
			finderArgs = new Object[] {uuid, groupId};
		}

		Object result = null;

		if (useFinderCache) {
			result = finderCache.getResult(
				_finderPathFetchByUUID_G, finderArgs, this);
		}

		if (result instanceof Employees) {
			Employees employees = (Employees)result;

			if (!Objects.equals(uuid, employees.getUuid()) ||
				(groupId != employees.getGroupId())) {

				result = null;
			}
		}

		if (result == null) {
			StringBundler sb = new StringBundler(4);

			sb.append(_SQL_SELECT_EMPLOYEES_WHERE);

			boolean bindUuid = false;

			if (uuid.isEmpty()) {
				sb.append(_FINDER_COLUMN_UUID_G_UUID_3);
			}
			else {
				bindUuid = true;

				sb.append(_FINDER_COLUMN_UUID_G_UUID_2);
			}

			sb.append(_FINDER_COLUMN_UUID_G_GROUPID_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				if (bindUuid) {
					queryPos.add(uuid);
				}

				queryPos.add(groupId);

				List<Employees> list = query.list();

				if (list.isEmpty()) {
					if (useFinderCache) {
						finderCache.putResult(
							_finderPathFetchByUUID_G, finderArgs, list);
					}
				}
				else {
					Employees employees = list.get(0);

					result = employees;

					cacheResult(employees);
				}
			}
			catch (Exception exception) {
				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		if (result instanceof List<?>) {
			return null;
		}
		else {
			return (Employees)result;
		}
	}

	/**
	 * Removes the employees where uuid = &#63; and groupId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the employees that was removed
	 */
	@Override
	public Employees removeByUUID_G(String uuid, long groupId)
		throws NoSuchEmployeesException {

		Employees employees = findByUUID_G(uuid, groupId);

		return remove(employees);
	}

	/**
	 * Returns the number of employeeses where uuid = &#63; and groupId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the number of matching employeeses
	 */
	@Override
	public int countByUUID_G(String uuid, long groupId) {
		uuid = Objects.toString(uuid, "");

		FinderPath finderPath = _finderPathCountByUUID_G;

		Object[] finderArgs = new Object[] {uuid, groupId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(3);

			sb.append(_SQL_COUNT_EMPLOYEES_WHERE);

			boolean bindUuid = false;

			if (uuid.isEmpty()) {
				sb.append(_FINDER_COLUMN_UUID_G_UUID_3);
			}
			else {
				bindUuid = true;

				sb.append(_FINDER_COLUMN_UUID_G_UUID_2);
			}

			sb.append(_FINDER_COLUMN_UUID_G_GROUPID_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				if (bindUuid) {
					queryPos.add(uuid);
				}

				queryPos.add(groupId);

				count = (Long)query.uniqueResult();

				finderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception exception) {
				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_UUID_G_UUID_2 =
		"employees.uuid = ? AND ";

	private static final String _FINDER_COLUMN_UUID_G_UUID_3 =
		"(employees.uuid IS NULL OR employees.uuid = '') AND ";

	private static final String _FINDER_COLUMN_UUID_G_GROUPID_2 =
		"employees.groupId = ?";

	private FinderPath _finderPathWithPaginationFindByUuid_C;
	private FinderPath _finderPathWithoutPaginationFindByUuid_C;
	private FinderPath _finderPathCountByUuid_C;

	/**
	 * Returns all the employeeses where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching employeeses
	 */
	@Override
	public List<Employees> findByUuid_C(String uuid, long companyId) {
		return findByUuid_C(
			uuid, companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the employeeses where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>EmployeesModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of employeeses
	 * @param end the upper bound of the range of employeeses (not inclusive)
	 * @return the range of matching employeeses
	 */
	@Override
	public List<Employees> findByUuid_C(
		String uuid, long companyId, int start, int end) {

		return findByUuid_C(uuid, companyId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the employeeses where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>EmployeesModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of employeeses
	 * @param end the upper bound of the range of employeeses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching employeeses
	 */
	@Override
	public List<Employees> findByUuid_C(
		String uuid, long companyId, int start, int end,
		OrderByComparator<Employees> orderByComparator) {

		return findByUuid_C(
			uuid, companyId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the employeeses where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>EmployeesModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of employeeses
	 * @param end the upper bound of the range of employeeses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching employeeses
	 */
	@Override
	public List<Employees> findByUuid_C(
		String uuid, long companyId, int start, int end,
		OrderByComparator<Employees> orderByComparator,
		boolean useFinderCache) {

		uuid = Objects.toString(uuid, "");

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindByUuid_C;
				finderArgs = new Object[] {uuid, companyId};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByUuid_C;
			finderArgs = new Object[] {
				uuid, companyId, start, end, orderByComparator
			};
		}

		List<Employees> list = null;

		if (useFinderCache) {
			list = (List<Employees>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (Employees employees : list) {
					if (!uuid.equals(employees.getUuid()) ||
						(companyId != employees.getCompanyId())) {

						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler sb = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					4 + (orderByComparator.getOrderByFields().length * 2));
			}
			else {
				sb = new StringBundler(4);
			}

			sb.append(_SQL_SELECT_EMPLOYEES_WHERE);

			boolean bindUuid = false;

			if (uuid.isEmpty()) {
				sb.append(_FINDER_COLUMN_UUID_C_UUID_3);
			}
			else {
				bindUuid = true;

				sb.append(_FINDER_COLUMN_UUID_C_UUID_2);
			}

			sb.append(_FINDER_COLUMN_UUID_C_COMPANYID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(EmployeesModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				if (bindUuid) {
					queryPos.add(uuid);
				}

				queryPos.add(companyId);

				list = (List<Employees>)QueryUtil.list(
					query, getDialect(), start, end);

				cacheResult(list);

				if (useFinderCache) {
					finderCache.putResult(finderPath, finderArgs, list);
				}
			}
			catch (Exception exception) {
				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first employees in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching employees
	 * @throws NoSuchEmployeesException if a matching employees could not be found
	 */
	@Override
	public Employees findByUuid_C_First(
			String uuid, long companyId,
			OrderByComparator<Employees> orderByComparator)
		throws NoSuchEmployeesException {

		Employees employees = fetchByUuid_C_First(
			uuid, companyId, orderByComparator);

		if (employees != null) {
			return employees;
		}

		StringBundler sb = new StringBundler(6);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("uuid=");
		sb.append(uuid);

		sb.append(", companyId=");
		sb.append(companyId);

		sb.append("}");

		throw new NoSuchEmployeesException(sb.toString());
	}

	/**
	 * Returns the first employees in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching employees, or <code>null</code> if a matching employees could not be found
	 */
	@Override
	public Employees fetchByUuid_C_First(
		String uuid, long companyId,
		OrderByComparator<Employees> orderByComparator) {

		List<Employees> list = findByUuid_C(
			uuid, companyId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last employees in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching employees
	 * @throws NoSuchEmployeesException if a matching employees could not be found
	 */
	@Override
	public Employees findByUuid_C_Last(
			String uuid, long companyId,
			OrderByComparator<Employees> orderByComparator)
		throws NoSuchEmployeesException {

		Employees employees = fetchByUuid_C_Last(
			uuid, companyId, orderByComparator);

		if (employees != null) {
			return employees;
		}

		StringBundler sb = new StringBundler(6);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("uuid=");
		sb.append(uuid);

		sb.append(", companyId=");
		sb.append(companyId);

		sb.append("}");

		throw new NoSuchEmployeesException(sb.toString());
	}

	/**
	 * Returns the last employees in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching employees, or <code>null</code> if a matching employees could not be found
	 */
	@Override
	public Employees fetchByUuid_C_Last(
		String uuid, long companyId,
		OrderByComparator<Employees> orderByComparator) {

		int count = countByUuid_C(uuid, companyId);

		if (count == 0) {
			return null;
		}

		List<Employees> list = findByUuid_C(
			uuid, companyId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the employeeses before and after the current employees in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param EmployeeId the primary key of the current employees
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next employees
	 * @throws NoSuchEmployeesException if a employees with the primary key could not be found
	 */
	@Override
	public Employees[] findByUuid_C_PrevAndNext(
			long EmployeeId, String uuid, long companyId,
			OrderByComparator<Employees> orderByComparator)
		throws NoSuchEmployeesException {

		uuid = Objects.toString(uuid, "");

		Employees employees = findByPrimaryKey(EmployeeId);

		Session session = null;

		try {
			session = openSession();

			Employees[] array = new EmployeesImpl[3];

			array[0] = getByUuid_C_PrevAndNext(
				session, employees, uuid, companyId, orderByComparator, true);

			array[1] = employees;

			array[2] = getByUuid_C_PrevAndNext(
				session, employees, uuid, companyId, orderByComparator, false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected Employees getByUuid_C_PrevAndNext(
		Session session, Employees employees, String uuid, long companyId,
		OrderByComparator<Employees> orderByComparator, boolean previous) {

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				5 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			sb = new StringBundler(4);
		}

		sb.append(_SQL_SELECT_EMPLOYEES_WHERE);

		boolean bindUuid = false;

		if (uuid.isEmpty()) {
			sb.append(_FINDER_COLUMN_UUID_C_UUID_3);
		}
		else {
			bindUuid = true;

			sb.append(_FINDER_COLUMN_UUID_C_UUID_2);
		}

		sb.append(_FINDER_COLUMN_UUID_C_COMPANYID_2);

		if (orderByComparator != null) {
			String[] orderByConditionFields =
				orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				sb.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				sb.append(_ORDER_BY_ENTITY_ALIAS);
				sb.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						sb.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(WHERE_GREATER_THAN);
					}
					else {
						sb.append(WHERE_LESSER_THAN);
					}
				}
			}

			sb.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				sb.append(_ORDER_BY_ENTITY_ALIAS);
				sb.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						sb.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(ORDER_BY_ASC);
					}
					else {
						sb.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			sb.append(EmployeesModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		if (bindUuid) {
			queryPos.add(uuid);
		}

		queryPos.add(companyId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(employees)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<Employees> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the employeeses where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	@Override
	public void removeByUuid_C(String uuid, long companyId) {
		for (Employees employees :
				findByUuid_C(
					uuid, companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
					null)) {

			remove(employees);
		}
	}

	/**
	 * Returns the number of employeeses where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching employeeses
	 */
	@Override
	public int countByUuid_C(String uuid, long companyId) {
		uuid = Objects.toString(uuid, "");

		FinderPath finderPath = _finderPathCountByUuid_C;

		Object[] finderArgs = new Object[] {uuid, companyId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(3);

			sb.append(_SQL_COUNT_EMPLOYEES_WHERE);

			boolean bindUuid = false;

			if (uuid.isEmpty()) {
				sb.append(_FINDER_COLUMN_UUID_C_UUID_3);
			}
			else {
				bindUuid = true;

				sb.append(_FINDER_COLUMN_UUID_C_UUID_2);
			}

			sb.append(_FINDER_COLUMN_UUID_C_COMPANYID_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				if (bindUuid) {
					queryPos.add(uuid);
				}

				queryPos.add(companyId);

				count = (Long)query.uniqueResult();

				finderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception exception) {
				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_UUID_C_UUID_2 =
		"employees.uuid = ? AND ";

	private static final String _FINDER_COLUMN_UUID_C_UUID_3 =
		"(employees.uuid IS NULL OR employees.uuid = '') AND ";

	private static final String _FINDER_COLUMN_UUID_C_COMPANYID_2 =
		"employees.companyId = ?";

	private FinderPath _finderPathWithPaginationFindByEmployeeId;
	private FinderPath _finderPathWithoutPaginationFindByEmployeeId;
	private FinderPath _finderPathCountByEmployeeId;

	/**
	 * Returns all the employeeses where EmployeeId = &#63;.
	 *
	 * @param EmployeeId the employee ID
	 * @return the matching employeeses
	 */
	@Override
	public List<Employees> findByEmployeeId(long EmployeeId) {
		return findByEmployeeId(
			EmployeeId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the employeeses where EmployeeId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>EmployeesModelImpl</code>.
	 * </p>
	 *
	 * @param EmployeeId the employee ID
	 * @param start the lower bound of the range of employeeses
	 * @param end the upper bound of the range of employeeses (not inclusive)
	 * @return the range of matching employeeses
	 */
	@Override
	public List<Employees> findByEmployeeId(
		long EmployeeId, int start, int end) {

		return findByEmployeeId(EmployeeId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the employeeses where EmployeeId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>EmployeesModelImpl</code>.
	 * </p>
	 *
	 * @param EmployeeId the employee ID
	 * @param start the lower bound of the range of employeeses
	 * @param end the upper bound of the range of employeeses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching employeeses
	 */
	@Override
	public List<Employees> findByEmployeeId(
		long EmployeeId, int start, int end,
		OrderByComparator<Employees> orderByComparator) {

		return findByEmployeeId(
			EmployeeId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the employeeses where EmployeeId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>EmployeesModelImpl</code>.
	 * </p>
	 *
	 * @param EmployeeId the employee ID
	 * @param start the lower bound of the range of employeeses
	 * @param end the upper bound of the range of employeeses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching employeeses
	 */
	@Override
	public List<Employees> findByEmployeeId(
		long EmployeeId, int start, int end,
		OrderByComparator<Employees> orderByComparator,
		boolean useFinderCache) {

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindByEmployeeId;
				finderArgs = new Object[] {EmployeeId};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByEmployeeId;
			finderArgs = new Object[] {
				EmployeeId, start, end, orderByComparator
			};
		}

		List<Employees> list = null;

		if (useFinderCache) {
			list = (List<Employees>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (Employees employees : list) {
					if (EmployeeId != employees.getEmployeeId()) {
						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler sb = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					3 + (orderByComparator.getOrderByFields().length * 2));
			}
			else {
				sb = new StringBundler(3);
			}

			sb.append(_SQL_SELECT_EMPLOYEES_WHERE);

			sb.append(_FINDER_COLUMN_EMPLOYEEID_EMPLOYEEID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(EmployeesModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(EmployeeId);

				list = (List<Employees>)QueryUtil.list(
					query, getDialect(), start, end);

				cacheResult(list);

				if (useFinderCache) {
					finderCache.putResult(finderPath, finderArgs, list);
				}
			}
			catch (Exception exception) {
				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first employees in the ordered set where EmployeeId = &#63;.
	 *
	 * @param EmployeeId the employee ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching employees
	 * @throws NoSuchEmployeesException if a matching employees could not be found
	 */
	@Override
	public Employees findByEmployeeId_First(
			long EmployeeId, OrderByComparator<Employees> orderByComparator)
		throws NoSuchEmployeesException {

		Employees employees = fetchByEmployeeId_First(
			EmployeeId, orderByComparator);

		if (employees != null) {
			return employees;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("EmployeeId=");
		sb.append(EmployeeId);

		sb.append("}");

		throw new NoSuchEmployeesException(sb.toString());
	}

	/**
	 * Returns the first employees in the ordered set where EmployeeId = &#63;.
	 *
	 * @param EmployeeId the employee ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching employees, or <code>null</code> if a matching employees could not be found
	 */
	@Override
	public Employees fetchByEmployeeId_First(
		long EmployeeId, OrderByComparator<Employees> orderByComparator) {

		List<Employees> list = findByEmployeeId(
			EmployeeId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last employees in the ordered set where EmployeeId = &#63;.
	 *
	 * @param EmployeeId the employee ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching employees
	 * @throws NoSuchEmployeesException if a matching employees could not be found
	 */
	@Override
	public Employees findByEmployeeId_Last(
			long EmployeeId, OrderByComparator<Employees> orderByComparator)
		throws NoSuchEmployeesException {

		Employees employees = fetchByEmployeeId_Last(
			EmployeeId, orderByComparator);

		if (employees != null) {
			return employees;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("EmployeeId=");
		sb.append(EmployeeId);

		sb.append("}");

		throw new NoSuchEmployeesException(sb.toString());
	}

	/**
	 * Returns the last employees in the ordered set where EmployeeId = &#63;.
	 *
	 * @param EmployeeId the employee ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching employees, or <code>null</code> if a matching employees could not be found
	 */
	@Override
	public Employees fetchByEmployeeId_Last(
		long EmployeeId, OrderByComparator<Employees> orderByComparator) {

		int count = countByEmployeeId(EmployeeId);

		if (count == 0) {
			return null;
		}

		List<Employees> list = findByEmployeeId(
			EmployeeId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Removes all the employeeses where EmployeeId = &#63; from the database.
	 *
	 * @param EmployeeId the employee ID
	 */
	@Override
	public void removeByEmployeeId(long EmployeeId) {
		for (Employees employees :
				findByEmployeeId(
					EmployeeId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(employees);
		}
	}

	/**
	 * Returns the number of employeeses where EmployeeId = &#63;.
	 *
	 * @param EmployeeId the employee ID
	 * @return the number of matching employeeses
	 */
	@Override
	public int countByEmployeeId(long EmployeeId) {
		FinderPath finderPath = _finderPathCountByEmployeeId;

		Object[] finderArgs = new Object[] {EmployeeId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_EMPLOYEES_WHERE);

			sb.append(_FINDER_COLUMN_EMPLOYEEID_EMPLOYEEID_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(EmployeeId);

				count = (Long)query.uniqueResult();

				finderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception exception) {
				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_EMPLOYEEID_EMPLOYEEID_2 =
		"employees.EmployeeId = ?";

	private FinderPath _finderPathWithPaginationFindByEmpName;
	private FinderPath _finderPathWithoutPaginationFindByEmpName;
	private FinderPath _finderPathCountByEmpName;

	/**
	 * Returns all the employeeses where EmpName = &#63;.
	 *
	 * @param EmpName the emp name
	 * @return the matching employeeses
	 */
	@Override
	public List<Employees> findByEmpName(String EmpName) {
		return findByEmpName(
			EmpName, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the employeeses where EmpName = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>EmployeesModelImpl</code>.
	 * </p>
	 *
	 * @param EmpName the emp name
	 * @param start the lower bound of the range of employeeses
	 * @param end the upper bound of the range of employeeses (not inclusive)
	 * @return the range of matching employeeses
	 */
	@Override
	public List<Employees> findByEmpName(String EmpName, int start, int end) {
		return findByEmpName(EmpName, start, end, null);
	}

	/**
	 * Returns an ordered range of all the employeeses where EmpName = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>EmployeesModelImpl</code>.
	 * </p>
	 *
	 * @param EmpName the emp name
	 * @param start the lower bound of the range of employeeses
	 * @param end the upper bound of the range of employeeses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching employeeses
	 */
	@Override
	public List<Employees> findByEmpName(
		String EmpName, int start, int end,
		OrderByComparator<Employees> orderByComparator) {

		return findByEmpName(EmpName, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the employeeses where EmpName = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>EmployeesModelImpl</code>.
	 * </p>
	 *
	 * @param EmpName the emp name
	 * @param start the lower bound of the range of employeeses
	 * @param end the upper bound of the range of employeeses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching employeeses
	 */
	@Override
	public List<Employees> findByEmpName(
		String EmpName, int start, int end,
		OrderByComparator<Employees> orderByComparator,
		boolean useFinderCache) {

		EmpName = Objects.toString(EmpName, "");

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindByEmpName;
				finderArgs = new Object[] {EmpName};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByEmpName;
			finderArgs = new Object[] {EmpName, start, end, orderByComparator};
		}

		List<Employees> list = null;

		if (useFinderCache) {
			list = (List<Employees>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (Employees employees : list) {
					if (!EmpName.equals(employees.getEmpName())) {
						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler sb = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					3 + (orderByComparator.getOrderByFields().length * 2));
			}
			else {
				sb = new StringBundler(3);
			}

			sb.append(_SQL_SELECT_EMPLOYEES_WHERE);

			boolean bindEmpName = false;

			if (EmpName.isEmpty()) {
				sb.append(_FINDER_COLUMN_EMPNAME_EMPNAME_3);
			}
			else {
				bindEmpName = true;

				sb.append(_FINDER_COLUMN_EMPNAME_EMPNAME_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(EmployeesModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				if (bindEmpName) {
					queryPos.add(EmpName);
				}

				list = (List<Employees>)QueryUtil.list(
					query, getDialect(), start, end);

				cacheResult(list);

				if (useFinderCache) {
					finderCache.putResult(finderPath, finderArgs, list);
				}
			}
			catch (Exception exception) {
				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first employees in the ordered set where EmpName = &#63;.
	 *
	 * @param EmpName the emp name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching employees
	 * @throws NoSuchEmployeesException if a matching employees could not be found
	 */
	@Override
	public Employees findByEmpName_First(
			String EmpName, OrderByComparator<Employees> orderByComparator)
		throws NoSuchEmployeesException {

		Employees employees = fetchByEmpName_First(EmpName, orderByComparator);

		if (employees != null) {
			return employees;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("EmpName=");
		sb.append(EmpName);

		sb.append("}");

		throw new NoSuchEmployeesException(sb.toString());
	}

	/**
	 * Returns the first employees in the ordered set where EmpName = &#63;.
	 *
	 * @param EmpName the emp name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching employees, or <code>null</code> if a matching employees could not be found
	 */
	@Override
	public Employees fetchByEmpName_First(
		String EmpName, OrderByComparator<Employees> orderByComparator) {

		List<Employees> list = findByEmpName(EmpName, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last employees in the ordered set where EmpName = &#63;.
	 *
	 * @param EmpName the emp name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching employees
	 * @throws NoSuchEmployeesException if a matching employees could not be found
	 */
	@Override
	public Employees findByEmpName_Last(
			String EmpName, OrderByComparator<Employees> orderByComparator)
		throws NoSuchEmployeesException {

		Employees employees = fetchByEmpName_Last(EmpName, orderByComparator);

		if (employees != null) {
			return employees;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("EmpName=");
		sb.append(EmpName);

		sb.append("}");

		throw new NoSuchEmployeesException(sb.toString());
	}

	/**
	 * Returns the last employees in the ordered set where EmpName = &#63;.
	 *
	 * @param EmpName the emp name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching employees, or <code>null</code> if a matching employees could not be found
	 */
	@Override
	public Employees fetchByEmpName_Last(
		String EmpName, OrderByComparator<Employees> orderByComparator) {

		int count = countByEmpName(EmpName);

		if (count == 0) {
			return null;
		}

		List<Employees> list = findByEmpName(
			EmpName, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the employeeses before and after the current employees in the ordered set where EmpName = &#63;.
	 *
	 * @param EmployeeId the primary key of the current employees
	 * @param EmpName the emp name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next employees
	 * @throws NoSuchEmployeesException if a employees with the primary key could not be found
	 */
	@Override
	public Employees[] findByEmpName_PrevAndNext(
			long EmployeeId, String EmpName,
			OrderByComparator<Employees> orderByComparator)
		throws NoSuchEmployeesException {

		EmpName = Objects.toString(EmpName, "");

		Employees employees = findByPrimaryKey(EmployeeId);

		Session session = null;

		try {
			session = openSession();

			Employees[] array = new EmployeesImpl[3];

			array[0] = getByEmpName_PrevAndNext(
				session, employees, EmpName, orderByComparator, true);

			array[1] = employees;

			array[2] = getByEmpName_PrevAndNext(
				session, employees, EmpName, orderByComparator, false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected Employees getByEmpName_PrevAndNext(
		Session session, Employees employees, String EmpName,
		OrderByComparator<Employees> orderByComparator, boolean previous) {

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				4 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			sb = new StringBundler(3);
		}

		sb.append(_SQL_SELECT_EMPLOYEES_WHERE);

		boolean bindEmpName = false;

		if (EmpName.isEmpty()) {
			sb.append(_FINDER_COLUMN_EMPNAME_EMPNAME_3);
		}
		else {
			bindEmpName = true;

			sb.append(_FINDER_COLUMN_EMPNAME_EMPNAME_2);
		}

		if (orderByComparator != null) {
			String[] orderByConditionFields =
				orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				sb.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				sb.append(_ORDER_BY_ENTITY_ALIAS);
				sb.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						sb.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(WHERE_GREATER_THAN);
					}
					else {
						sb.append(WHERE_LESSER_THAN);
					}
				}
			}

			sb.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				sb.append(_ORDER_BY_ENTITY_ALIAS);
				sb.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						sb.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(ORDER_BY_ASC);
					}
					else {
						sb.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			sb.append(EmployeesModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		if (bindEmpName) {
			queryPos.add(EmpName);
		}

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(employees)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<Employees> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the employeeses where EmpName = &#63; from the database.
	 *
	 * @param EmpName the emp name
	 */
	@Override
	public void removeByEmpName(String EmpName) {
		for (Employees employees :
				findByEmpName(
					EmpName, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(employees);
		}
	}

	/**
	 * Returns the number of employeeses where EmpName = &#63;.
	 *
	 * @param EmpName the emp name
	 * @return the number of matching employeeses
	 */
	@Override
	public int countByEmpName(String EmpName) {
		EmpName = Objects.toString(EmpName, "");

		FinderPath finderPath = _finderPathCountByEmpName;

		Object[] finderArgs = new Object[] {EmpName};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_EMPLOYEES_WHERE);

			boolean bindEmpName = false;

			if (EmpName.isEmpty()) {
				sb.append(_FINDER_COLUMN_EMPNAME_EMPNAME_3);
			}
			else {
				bindEmpName = true;

				sb.append(_FINDER_COLUMN_EMPNAME_EMPNAME_2);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				if (bindEmpName) {
					queryPos.add(EmpName);
				}

				count = (Long)query.uniqueResult();

				finderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception exception) {
				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_EMPNAME_EMPNAME_2 =
		"employees.EmpName = ?";

	private static final String _FINDER_COLUMN_EMPNAME_EMPNAME_3 =
		"(employees.EmpName IS NULL OR employees.EmpName = '')";

	private FinderPath _finderPathWithPaginationFindByEmplRole;
	private FinderPath _finderPathWithoutPaginationFindByEmplRole;
	private FinderPath _finderPathCountByEmplRole;

	/**
	 * Returns all the employeeses where EmplRole = &#63;.
	 *
	 * @param EmplRole the empl role
	 * @return the matching employeeses
	 */
	@Override
	public List<Employees> findByEmplRole(String EmplRole) {
		return findByEmplRole(
			EmplRole, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the employeeses where EmplRole = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>EmployeesModelImpl</code>.
	 * </p>
	 *
	 * @param EmplRole the empl role
	 * @param start the lower bound of the range of employeeses
	 * @param end the upper bound of the range of employeeses (not inclusive)
	 * @return the range of matching employeeses
	 */
	@Override
	public List<Employees> findByEmplRole(String EmplRole, int start, int end) {
		return findByEmplRole(EmplRole, start, end, null);
	}

	/**
	 * Returns an ordered range of all the employeeses where EmplRole = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>EmployeesModelImpl</code>.
	 * </p>
	 *
	 * @param EmplRole the empl role
	 * @param start the lower bound of the range of employeeses
	 * @param end the upper bound of the range of employeeses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching employeeses
	 */
	@Override
	public List<Employees> findByEmplRole(
		String EmplRole, int start, int end,
		OrderByComparator<Employees> orderByComparator) {

		return findByEmplRole(EmplRole, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the employeeses where EmplRole = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>EmployeesModelImpl</code>.
	 * </p>
	 *
	 * @param EmplRole the empl role
	 * @param start the lower bound of the range of employeeses
	 * @param end the upper bound of the range of employeeses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching employeeses
	 */
	@Override
	public List<Employees> findByEmplRole(
		String EmplRole, int start, int end,
		OrderByComparator<Employees> orderByComparator,
		boolean useFinderCache) {

		EmplRole = Objects.toString(EmplRole, "");

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindByEmplRole;
				finderArgs = new Object[] {EmplRole};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByEmplRole;
			finderArgs = new Object[] {EmplRole, start, end, orderByComparator};
		}

		List<Employees> list = null;

		if (useFinderCache) {
			list = (List<Employees>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (Employees employees : list) {
					if (!EmplRole.equals(employees.getEmplRole())) {
						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler sb = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					3 + (orderByComparator.getOrderByFields().length * 2));
			}
			else {
				sb = new StringBundler(3);
			}

			sb.append(_SQL_SELECT_EMPLOYEES_WHERE);

			boolean bindEmplRole = false;

			if (EmplRole.isEmpty()) {
				sb.append(_FINDER_COLUMN_EMPLROLE_EMPLROLE_3);
			}
			else {
				bindEmplRole = true;

				sb.append(_FINDER_COLUMN_EMPLROLE_EMPLROLE_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(EmployeesModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				if (bindEmplRole) {
					queryPos.add(EmplRole);
				}

				list = (List<Employees>)QueryUtil.list(
					query, getDialect(), start, end);

				cacheResult(list);

				if (useFinderCache) {
					finderCache.putResult(finderPath, finderArgs, list);
				}
			}
			catch (Exception exception) {
				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first employees in the ordered set where EmplRole = &#63;.
	 *
	 * @param EmplRole the empl role
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching employees
	 * @throws NoSuchEmployeesException if a matching employees could not be found
	 */
	@Override
	public Employees findByEmplRole_First(
			String EmplRole, OrderByComparator<Employees> orderByComparator)
		throws NoSuchEmployeesException {

		Employees employees = fetchByEmplRole_First(
			EmplRole, orderByComparator);

		if (employees != null) {
			return employees;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("EmplRole=");
		sb.append(EmplRole);

		sb.append("}");

		throw new NoSuchEmployeesException(sb.toString());
	}

	/**
	 * Returns the first employees in the ordered set where EmplRole = &#63;.
	 *
	 * @param EmplRole the empl role
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching employees, or <code>null</code> if a matching employees could not be found
	 */
	@Override
	public Employees fetchByEmplRole_First(
		String EmplRole, OrderByComparator<Employees> orderByComparator) {

		List<Employees> list = findByEmplRole(
			EmplRole, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last employees in the ordered set where EmplRole = &#63;.
	 *
	 * @param EmplRole the empl role
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching employees
	 * @throws NoSuchEmployeesException if a matching employees could not be found
	 */
	@Override
	public Employees findByEmplRole_Last(
			String EmplRole, OrderByComparator<Employees> orderByComparator)
		throws NoSuchEmployeesException {

		Employees employees = fetchByEmplRole_Last(EmplRole, orderByComparator);

		if (employees != null) {
			return employees;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("EmplRole=");
		sb.append(EmplRole);

		sb.append("}");

		throw new NoSuchEmployeesException(sb.toString());
	}

	/**
	 * Returns the last employees in the ordered set where EmplRole = &#63;.
	 *
	 * @param EmplRole the empl role
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching employees, or <code>null</code> if a matching employees could not be found
	 */
	@Override
	public Employees fetchByEmplRole_Last(
		String EmplRole, OrderByComparator<Employees> orderByComparator) {

		int count = countByEmplRole(EmplRole);

		if (count == 0) {
			return null;
		}

		List<Employees> list = findByEmplRole(
			EmplRole, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the employeeses before and after the current employees in the ordered set where EmplRole = &#63;.
	 *
	 * @param EmployeeId the primary key of the current employees
	 * @param EmplRole the empl role
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next employees
	 * @throws NoSuchEmployeesException if a employees with the primary key could not be found
	 */
	@Override
	public Employees[] findByEmplRole_PrevAndNext(
			long EmployeeId, String EmplRole,
			OrderByComparator<Employees> orderByComparator)
		throws NoSuchEmployeesException {

		EmplRole = Objects.toString(EmplRole, "");

		Employees employees = findByPrimaryKey(EmployeeId);

		Session session = null;

		try {
			session = openSession();

			Employees[] array = new EmployeesImpl[3];

			array[0] = getByEmplRole_PrevAndNext(
				session, employees, EmplRole, orderByComparator, true);

			array[1] = employees;

			array[2] = getByEmplRole_PrevAndNext(
				session, employees, EmplRole, orderByComparator, false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected Employees getByEmplRole_PrevAndNext(
		Session session, Employees employees, String EmplRole,
		OrderByComparator<Employees> orderByComparator, boolean previous) {

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				4 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			sb = new StringBundler(3);
		}

		sb.append(_SQL_SELECT_EMPLOYEES_WHERE);

		boolean bindEmplRole = false;

		if (EmplRole.isEmpty()) {
			sb.append(_FINDER_COLUMN_EMPLROLE_EMPLROLE_3);
		}
		else {
			bindEmplRole = true;

			sb.append(_FINDER_COLUMN_EMPLROLE_EMPLROLE_2);
		}

		if (orderByComparator != null) {
			String[] orderByConditionFields =
				orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				sb.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				sb.append(_ORDER_BY_ENTITY_ALIAS);
				sb.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						sb.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(WHERE_GREATER_THAN);
					}
					else {
						sb.append(WHERE_LESSER_THAN);
					}
				}
			}

			sb.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				sb.append(_ORDER_BY_ENTITY_ALIAS);
				sb.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						sb.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(ORDER_BY_ASC);
					}
					else {
						sb.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			sb.append(EmployeesModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		if (bindEmplRole) {
			queryPos.add(EmplRole);
		}

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(employees)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<Employees> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the employeeses where EmplRole = &#63; from the database.
	 *
	 * @param EmplRole the empl role
	 */
	@Override
	public void removeByEmplRole(String EmplRole) {
		for (Employees employees :
				findByEmplRole(
					EmplRole, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(employees);
		}
	}

	/**
	 * Returns the number of employeeses where EmplRole = &#63;.
	 *
	 * @param EmplRole the empl role
	 * @return the number of matching employeeses
	 */
	@Override
	public int countByEmplRole(String EmplRole) {
		EmplRole = Objects.toString(EmplRole, "");

		FinderPath finderPath = _finderPathCountByEmplRole;

		Object[] finderArgs = new Object[] {EmplRole};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_EMPLOYEES_WHERE);

			boolean bindEmplRole = false;

			if (EmplRole.isEmpty()) {
				sb.append(_FINDER_COLUMN_EMPLROLE_EMPLROLE_3);
			}
			else {
				bindEmplRole = true;

				sb.append(_FINDER_COLUMN_EMPLROLE_EMPLROLE_2);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				if (bindEmplRole) {
					queryPos.add(EmplRole);
				}

				count = (Long)query.uniqueResult();

				finderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception exception) {
				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_EMPLROLE_EMPLROLE_2 =
		"employees.EmplRole = ?";

	private static final String _FINDER_COLUMN_EMPLROLE_EMPLROLE_3 =
		"(employees.EmplRole IS NULL OR employees.EmplRole = '')";

	public EmployeesPersistenceImpl() {
		Map<String, String> dbColumnNames = new HashMap<String, String>();

		dbColumnNames.put("uuid", "uuid_");

		setDBColumnNames(dbColumnNames);

		setModelClass(Employees.class);

		setModelImplClass(EmployeesImpl.class);
		setModelPKClass(long.class);

		setTable(EmployeesTable.INSTANCE);
	}

	/**
	 * Caches the employees in the entity cache if it is enabled.
	 *
	 * @param employees the employees
	 */
	@Override
	public void cacheResult(Employees employees) {
		entityCache.putResult(
			EmployeesImpl.class, employees.getPrimaryKey(), employees);

		finderCache.putResult(
			_finderPathFetchByUUID_G,
			new Object[] {employees.getUuid(), employees.getGroupId()},
			employees);
	}

	private int _valueObjectFinderCacheListThreshold;

	/**
	 * Caches the employeeses in the entity cache if it is enabled.
	 *
	 * @param employeeses the employeeses
	 */
	@Override
	public void cacheResult(List<Employees> employeeses) {
		if ((_valueObjectFinderCacheListThreshold == 0) ||
			((_valueObjectFinderCacheListThreshold > 0) &&
			 (employeeses.size() > _valueObjectFinderCacheListThreshold))) {

			return;
		}

		for (Employees employees : employeeses) {
			if (entityCache.getResult(
					EmployeesImpl.class, employees.getPrimaryKey()) == null) {

				cacheResult(employees);
			}
		}
	}

	/**
	 * Clears the cache for all employeeses.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(EmployeesImpl.class);

		finderCache.clearCache(EmployeesImpl.class);
	}

	/**
	 * Clears the cache for the employees.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(Employees employees) {
		entityCache.removeResult(EmployeesImpl.class, employees);
	}

	@Override
	public void clearCache(List<Employees> employeeses) {
		for (Employees employees : employeeses) {
			entityCache.removeResult(EmployeesImpl.class, employees);
		}
	}

	@Override
	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(EmployeesImpl.class);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(EmployeesImpl.class, primaryKey);
		}
	}

	protected void cacheUniqueFindersCache(
		EmployeesModelImpl employeesModelImpl) {

		Object[] args = new Object[] {
			employeesModelImpl.getUuid(), employeesModelImpl.getGroupId()
		};

		finderCache.putResult(_finderPathCountByUUID_G, args, Long.valueOf(1));
		finderCache.putResult(
			_finderPathFetchByUUID_G, args, employeesModelImpl);
	}

	/**
	 * Creates a new employees with the primary key. Does not add the employees to the database.
	 *
	 * @param EmployeeId the primary key for the new employees
	 * @return the new employees
	 */
	@Override
	public Employees create(long EmployeeId) {
		Employees employees = new EmployeesImpl();

		employees.setNew(true);
		employees.setPrimaryKey(EmployeeId);

		String uuid = _portalUUID.generate();

		employees.setUuid(uuid);

		employees.setCompanyId(CompanyThreadLocal.getCompanyId());

		return employees;
	}

	/**
	 * Removes the employees with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param EmployeeId the primary key of the employees
	 * @return the employees that was removed
	 * @throws NoSuchEmployeesException if a employees with the primary key could not be found
	 */
	@Override
	public Employees remove(long EmployeeId) throws NoSuchEmployeesException {
		return remove((Serializable)EmployeeId);
	}

	/**
	 * Removes the employees with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the employees
	 * @return the employees that was removed
	 * @throws NoSuchEmployeesException if a employees with the primary key could not be found
	 */
	@Override
	public Employees remove(Serializable primaryKey)
		throws NoSuchEmployeesException {

		Session session = null;

		try {
			session = openSession();

			Employees employees = (Employees)session.get(
				EmployeesImpl.class, primaryKey);

			if (employees == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchEmployeesException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(employees);
		}
		catch (NoSuchEmployeesException noSuchEntityException) {
			throw noSuchEntityException;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	@Override
	protected Employees removeImpl(Employees employees) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(employees)) {
				employees = (Employees)session.get(
					EmployeesImpl.class, employees.getPrimaryKeyObj());
			}

			if (employees != null) {
				session.delete(employees);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (employees != null) {
			clearCache(employees);
		}

		return employees;
	}

	@Override
	public Employees updateImpl(Employees employees) {
		boolean isNew = employees.isNew();

		if (!(employees instanceof EmployeesModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(employees.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(employees);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in employees proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom Employees implementation " +
					employees.getClass());
		}

		EmployeesModelImpl employeesModelImpl = (EmployeesModelImpl)employees;

		if (Validator.isNull(employees.getUuid())) {
			String uuid = _portalUUID.generate();

			employees.setUuid(uuid);
		}

		ServiceContext serviceContext =
			ServiceContextThreadLocal.getServiceContext();

		Date date = new Date();

		if (isNew && (employees.getCreateDate() == null)) {
			if (serviceContext == null) {
				employees.setCreateDate(date);
			}
			else {
				employees.setCreateDate(serviceContext.getCreateDate(date));
			}
		}

		if (!employeesModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				employees.setModifiedDate(date);
			}
			else {
				employees.setModifiedDate(serviceContext.getModifiedDate(date));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (isNew) {
				session.save(employees);
			}
			else {
				employees = (Employees)session.merge(employees);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		entityCache.putResult(
			EmployeesImpl.class, employeesModelImpl, false, true);

		cacheUniqueFindersCache(employeesModelImpl);

		if (isNew) {
			employees.setNew(false);
		}

		employees.resetOriginalValues();

		return employees;
	}

	/**
	 * Returns the employees with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the employees
	 * @return the employees
	 * @throws NoSuchEmployeesException if a employees with the primary key could not be found
	 */
	@Override
	public Employees findByPrimaryKey(Serializable primaryKey)
		throws NoSuchEmployeesException {

		Employees employees = fetchByPrimaryKey(primaryKey);

		if (employees == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchEmployeesException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return employees;
	}

	/**
	 * Returns the employees with the primary key or throws a <code>NoSuchEmployeesException</code> if it could not be found.
	 *
	 * @param EmployeeId the primary key of the employees
	 * @return the employees
	 * @throws NoSuchEmployeesException if a employees with the primary key could not be found
	 */
	@Override
	public Employees findByPrimaryKey(long EmployeeId)
		throws NoSuchEmployeesException {

		return findByPrimaryKey((Serializable)EmployeeId);
	}

	/**
	 * Returns the employees with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param EmployeeId the primary key of the employees
	 * @return the employees, or <code>null</code> if a employees with the primary key could not be found
	 */
	@Override
	public Employees fetchByPrimaryKey(long EmployeeId) {
		return fetchByPrimaryKey((Serializable)EmployeeId);
	}

	/**
	 * Returns all the employeeses.
	 *
	 * @return the employeeses
	 */
	@Override
	public List<Employees> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the employeeses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>EmployeesModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of employeeses
	 * @param end the upper bound of the range of employeeses (not inclusive)
	 * @return the range of employeeses
	 */
	@Override
	public List<Employees> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the employeeses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>EmployeesModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of employeeses
	 * @param end the upper bound of the range of employeeses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of employeeses
	 */
	@Override
	public List<Employees> findAll(
		int start, int end, OrderByComparator<Employees> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the employeeses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>EmployeesModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of employeeses
	 * @param end the upper bound of the range of employeeses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of employeeses
	 */
	@Override
	public List<Employees> findAll(
		int start, int end, OrderByComparator<Employees> orderByComparator,
		boolean useFinderCache) {

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindAll;
				finderArgs = FINDER_ARGS_EMPTY;
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindAll;
			finderArgs = new Object[] {start, end, orderByComparator};
		}

		List<Employees> list = null;

		if (useFinderCache) {
			list = (List<Employees>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_EMPLOYEES);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_EMPLOYEES;

				sql = sql.concat(EmployeesModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<Employees>)QueryUtil.list(
					query, getDialect(), start, end);

				cacheResult(list);

				if (useFinderCache) {
					finderCache.putResult(finderPath, finderArgs, list);
				}
			}
			catch (Exception exception) {
				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Removes all the employeeses from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (Employees employees : findAll()) {
			remove(employees);
		}
	}

	/**
	 * Returns the number of employeeses.
	 *
	 * @return the number of employeeses
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(_SQL_COUNT_EMPLOYEES);

				count = (Long)query.uniqueResult();

				finderCache.putResult(
					_finderPathCountAll, FINDER_ARGS_EMPTY, count);
			}
			catch (Exception exception) {
				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	@Override
	public Set<String> getBadColumnNames() {
		return _badColumnNames;
	}

	@Override
	protected EntityCache getEntityCache() {
		return entityCache;
	}

	@Override
	protected String getPKDBName() {
		return "EmployeeId";
	}

	@Override
	protected String getSelectSQL() {
		return _SQL_SELECT_EMPLOYEES;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return EmployeesModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the employees persistence.
	 */
	@Activate
	public void activate() {
		_valueObjectFinderCacheListThreshold = GetterUtil.getInteger(
			PropsUtil.get(PropsKeys.VALUE_OBJECT_FINDER_CACHE_LIST_THRESHOLD));

		_finderPathWithPaginationFindAll = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0],
			new String[0], true);

		_finderPathWithoutPaginationFindAll = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0],
			new String[0], true);

		_finderPathCountAll = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll",
			new String[0], new String[0], false);

		_finderPathWithPaginationFindByUuid = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid",
			new String[] {
				String.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			},
			new String[] {"uuid_"}, true);

		_finderPathWithoutPaginationFindByUuid = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid",
			new String[] {String.class.getName()}, new String[] {"uuid_"},
			true);

		_finderPathCountByUuid = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid",
			new String[] {String.class.getName()}, new String[] {"uuid_"},
			false);

		_finderPathFetchByUUID_G = new FinderPath(
			FINDER_CLASS_NAME_ENTITY, "fetchByUUID_G",
			new String[] {String.class.getName(), Long.class.getName()},
			new String[] {"uuid_", "groupId"}, true);

		_finderPathCountByUUID_G = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUUID_G",
			new String[] {String.class.getName(), Long.class.getName()},
			new String[] {"uuid_", "groupId"}, false);

		_finderPathWithPaginationFindByUuid_C = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid_C",
			new String[] {
				String.class.getName(), Long.class.getName(),
				Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			},
			new String[] {"uuid_", "companyId"}, true);

		_finderPathWithoutPaginationFindByUuid_C = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid_C",
			new String[] {String.class.getName(), Long.class.getName()},
			new String[] {"uuid_", "companyId"}, true);

		_finderPathCountByUuid_C = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid_C",
			new String[] {String.class.getName(), Long.class.getName()},
			new String[] {"uuid_", "companyId"}, false);

		_finderPathWithPaginationFindByEmployeeId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByEmployeeId",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			},
			new String[] {"EmployeeId"}, true);

		_finderPathWithoutPaginationFindByEmployeeId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByEmployeeId",
			new String[] {Long.class.getName()}, new String[] {"EmployeeId"},
			true);

		_finderPathCountByEmployeeId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByEmployeeId",
			new String[] {Long.class.getName()}, new String[] {"EmployeeId"},
			false);

		_finderPathWithPaginationFindByEmpName = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByEmpName",
			new String[] {
				String.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			},
			new String[] {"EmpName"}, true);

		_finderPathWithoutPaginationFindByEmpName = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByEmpName",
			new String[] {String.class.getName()}, new String[] {"EmpName"},
			true);

		_finderPathCountByEmpName = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByEmpName",
			new String[] {String.class.getName()}, new String[] {"EmpName"},
			false);

		_finderPathWithPaginationFindByEmplRole = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByEmplRole",
			new String[] {
				String.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			},
			new String[] {"EmplRole"}, true);

		_finderPathWithoutPaginationFindByEmplRole = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByEmplRole",
			new String[] {String.class.getName()}, new String[] {"EmplRole"},
			true);

		_finderPathCountByEmplRole = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByEmplRole",
			new String[] {String.class.getName()}, new String[] {"EmplRole"},
			false);

		_setEmployeesUtilPersistence(this);
	}

	@Deactivate
	public void deactivate() {
		_setEmployeesUtilPersistence(null);

		entityCache.removeCache(EmployeesImpl.class.getName());
	}

	private void _setEmployeesUtilPersistence(
		EmployeesPersistence employeesPersistence) {

		try {
			Field field = EmployeesUtil.class.getDeclaredField("_persistence");

			field.setAccessible(true);

			field.set(null, employeesPersistence);
		}
		catch (ReflectiveOperationException reflectiveOperationException) {
			throw new RuntimeException(reflectiveOperationException);
		}
	}

	@Override
	@Reference(
		target = ATSPersistenceConstants.SERVICE_CONFIGURATION_FILTER,
		unbind = "-"
	)
	public void setConfiguration(Configuration configuration) {
	}

	@Override
	@Reference(
		target = ATSPersistenceConstants.ORIGIN_BUNDLE_SYMBOLIC_NAME_FILTER,
		unbind = "-"
	)
	public void setDataSource(DataSource dataSource) {
		super.setDataSource(dataSource);
	}

	@Override
	@Reference(
		target = ATSPersistenceConstants.ORIGIN_BUNDLE_SYMBOLIC_NAME_FILTER,
		unbind = "-"
	)
	public void setSessionFactory(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}

	@Reference
	protected EntityCache entityCache;

	@Reference
	protected FinderCache finderCache;

	private static final String _SQL_SELECT_EMPLOYEES =
		"SELECT employees FROM Employees employees";

	private static final String _SQL_SELECT_EMPLOYEES_WHERE =
		"SELECT employees FROM Employees employees WHERE ";

	private static final String _SQL_COUNT_EMPLOYEES =
		"SELECT COUNT(employees) FROM Employees employees";

	private static final String _SQL_COUNT_EMPLOYEES_WHERE =
		"SELECT COUNT(employees) FROM Employees employees WHERE ";

	private static final String _ORDER_BY_ENTITY_ALIAS = "employees.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No Employees exists with the primary key ";

	private static final String _NO_SUCH_ENTITY_WITH_KEY =
		"No Employees exists with the key {";

	private static final Log _log = LogFactoryUtil.getLog(
		EmployeesPersistenceImpl.class);

	private static final Set<String> _badColumnNames = SetUtil.fromArray(
		new String[] {"uuid"});

	@Override
	protected FinderCache getFinderCache() {
		return finderCache;
	}

	@Reference
	private PortalUUID _portalUUID;

}