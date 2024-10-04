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

package ats.global.techsoft.slayers.service;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link EmployeesLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see EmployeesLocalService
 * @generated
 */
public class EmployeesLocalServiceWrapper
	implements EmployeesLocalService, ServiceWrapper<EmployeesLocalService> {

	public EmployeesLocalServiceWrapper() {
		this(null);
	}

	public EmployeesLocalServiceWrapper(
		EmployeesLocalService employeesLocalService) {

		_employeesLocalService = employeesLocalService;
	}

	/**
	 * Adds the employees to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect EmployeesLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param employees the employees
	 * @return the employees that was added
	 */
	@Override
	public ats.global.techsoft.slayers.model.Employees addEmployees(
		ats.global.techsoft.slayers.model.Employees employees) {

		return _employeesLocalService.addEmployees(employees);
	}

	@Override
	public ats.global.techsoft.slayers.model.Employees addEmployees(
			long groupId, long companyId, String empName,
			java.io.InputStream empPhotoStream, String empGender, int empAge,
			String emplRole, String empAddress, long empKey,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws java.io.IOException {

		return _employeesLocalService.addEmployees(
			groupId, companyId, empName, empPhotoStream, empGender, empAge,
			emplRole, empAddress, empKey, serviceContext);
	}

	@Override
	public String convertImageToBase64(java.io.InputStream inputStream)
		throws java.io.IOException {

		return _employeesLocalService.convertImageToBase64(inputStream);
	}

	/**
	 * Creates a new employees with the primary key. Does not add the employees to the database.
	 *
	 * @param EmployeeId the primary key for the new employees
	 * @return the new employees
	 */
	@Override
	public ats.global.techsoft.slayers.model.Employees createEmployees(
		long EmployeeId) {

		return _employeesLocalService.createEmployees(EmployeeId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel createPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _employeesLocalService.createPersistedModel(primaryKeyObj);
	}

	/**
	 * Deletes the employees from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect EmployeesLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param employees the employees
	 * @return the employees that was removed
	 */
	@Override
	public ats.global.techsoft.slayers.model.Employees deleteEmployees(
		ats.global.techsoft.slayers.model.Employees employees) {

		return _employeesLocalService.deleteEmployees(employees);
	}

	/**
	 * Deletes the employees with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect EmployeesLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param EmployeeId the primary key of the employees
	 * @return the employees that was removed
	 * @throws PortalException if a employees with the primary key could not be found
	 */
	@Override
	public ats.global.techsoft.slayers.model.Employees deleteEmployees(
			long EmployeeId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _employeesLocalService.deleteEmployees(EmployeeId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _employeesLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public <T> T dslQuery(com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {
		return _employeesLocalService.dslQuery(dslQuery);
	}

	@Override
	public int dslQueryCount(
		com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {

		return _employeesLocalService.dslQueryCount(dslQuery);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _employeesLocalService.dynamicQuery();
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {

		return _employeesLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ats.global.techsoft.slayers.model.impl.EmployeesModelImpl</code>.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @return the range of matching rows
	 */
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {

		return _employeesLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ats.global.techsoft.slayers.model.impl.EmployeesModelImpl</code>.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching rows
	 */
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {

		return _employeesLocalService.dynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the number of rows matching the dynamic query
	 */
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {

		return _employeesLocalService.dynamicQueryCount(dynamicQuery);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @param projection the projection to apply to the query
	 * @return the number of rows matching the dynamic query
	 */
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {

		return _employeesLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public ats.global.techsoft.slayers.model.Employees fetchEmployees(
		long EmployeeId) {

		return _employeesLocalService.fetchEmployees(EmployeeId);
	}

	/**
	 * Returns the employees matching the UUID and group.
	 *
	 * @param uuid the employees's UUID
	 * @param groupId the primary key of the group
	 * @return the matching employees, or <code>null</code> if a matching employees could not be found
	 */
	@Override
	public ats.global.techsoft.slayers.model.Employees
		fetchEmployeesByUuidAndGroupId(String uuid, long groupId) {

		return _employeesLocalService.fetchEmployeesByUuidAndGroupId(
			uuid, groupId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _employeesLocalService.getActionableDynamicQuery();
	}

	/**
	 * Returns the employees with the primary key.
	 *
	 * @param EmployeeId the primary key of the employees
	 * @return the employees
	 * @throws PortalException if a employees with the primary key could not be found
	 */
	@Override
	public ats.global.techsoft.slayers.model.Employees getEmployees(
			long EmployeeId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _employeesLocalService.getEmployees(EmployeeId);
	}

	/**
	 * Returns the employees matching the UUID and group.
	 *
	 * @param uuid the employees's UUID
	 * @param groupId the primary key of the group
	 * @return the matching employees
	 * @throws PortalException if a matching employees could not be found
	 */
	@Override
	public ats.global.techsoft.slayers.model.Employees
			getEmployeesByUuidAndGroupId(String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _employeesLocalService.getEmployeesByUuidAndGroupId(
			uuid, groupId);
	}

	/**
	 * Returns a range of all the employeeses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ats.global.techsoft.slayers.model.impl.EmployeesModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of employeeses
	 * @param end the upper bound of the range of employeeses (not inclusive)
	 * @return the range of employeeses
	 */
	@Override
	public java.util.List<ats.global.techsoft.slayers.model.Employees>
		getEmployeeses(int start, int end) {

		return _employeesLocalService.getEmployeeses(start, end);
	}

	/**
	 * Returns all the employeeses matching the UUID and company.
	 *
	 * @param uuid the UUID of the employeeses
	 * @param companyId the primary key of the company
	 * @return the matching employeeses, or an empty list if no matches were found
	 */
	@Override
	public java.util.List<ats.global.techsoft.slayers.model.Employees>
		getEmployeesesByUuidAndCompanyId(String uuid, long companyId) {

		return _employeesLocalService.getEmployeesesByUuidAndCompanyId(
			uuid, companyId);
	}

	/**
	 * Returns a range of employeeses matching the UUID and company.
	 *
	 * @param uuid the UUID of the employeeses
	 * @param companyId the primary key of the company
	 * @param start the lower bound of the range of employeeses
	 * @param end the upper bound of the range of employeeses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the range of matching employeeses, or an empty list if no matches were found
	 */
	@Override
	public java.util.List<ats.global.techsoft.slayers.model.Employees>
		getEmployeesesByUuidAndCompanyId(
			String uuid, long companyId, int start, int end,
			com.liferay.portal.kernel.util.OrderByComparator
				<ats.global.techsoft.slayers.model.Employees>
					orderByComparator) {

		return _employeesLocalService.getEmployeesesByUuidAndCompanyId(
			uuid, companyId, start, end, orderByComparator);
	}

	/**
	 * Returns the number of employeeses.
	 *
	 * @return the number of employeeses
	 */
	@Override
	public int getEmployeesesCount() {
		return _employeesLocalService.getEmployeesesCount();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery
		getExportActionableDynamicQuery(
			com.liferay.exportimport.kernel.lar.PortletDataContext
				portletDataContext) {

		return _employeesLocalService.getExportActionableDynamicQuery(
			portletDataContext);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _employeesLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _employeesLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _employeesLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	 * Updates the employees in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect EmployeesLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param employees the employees
	 * @return the employees that was updated
	 */
	@Override
	public ats.global.techsoft.slayers.model.Employees updateEmployees(
		ats.global.techsoft.slayers.model.Employees employees) {

		return _employeesLocalService.updateEmployees(employees);
	}

	@Override
	public ats.global.techsoft.slayers.model.Employees updateEmployees(
			long employeeId, String empName, java.io.InputStream empPhotoStream,
			String empGender, int empAge, String emplRole, String empAddress,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws ats.global.techsoft.slayers.exception.NoSuchEmployeesException,
			   java.io.IOException {

		return _employeesLocalService.updateEmployees(
			employeeId, empName, empPhotoStream, empGender, empAge, emplRole,
			empAddress, serviceContext);
	}

	@Override
	public EmployeesLocalService getWrappedService() {
		return _employeesLocalService;
	}

	@Override
	public void setWrappedService(EmployeesLocalService employeesLocalService) {
		_employeesLocalService = employeesLocalService;
	}

	private EmployeesLocalService _employeesLocalService;

}