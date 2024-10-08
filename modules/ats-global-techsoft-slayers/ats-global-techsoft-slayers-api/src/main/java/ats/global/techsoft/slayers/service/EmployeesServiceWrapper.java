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
 * Provides a wrapper for {@link EmployeesService}.
 *
 * @author Brian Wing Shun Chan
 * @see EmployeesService
 * @generated
 */
public class EmployeesServiceWrapper
	implements EmployeesService, ServiceWrapper<EmployeesService> {

	public EmployeesServiceWrapper() {
		this(null);
	}

	public EmployeesServiceWrapper(EmployeesService employeesService) {
		_employeesService = employeesService;
	}

	@Override
	public ats.global.techsoft.slayers.model.Employees addEmployees(
			long groupId, long companyId, String empName,
			java.io.InputStream empPhotoStream, String empGender, int empAge,
			String emplRole, String empAddress, long empKey,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws java.io.IOException {

		return _employeesService.addEmployees(
			groupId, companyId, empName, empPhotoStream, empGender, empAge,
			emplRole, empAddress, empKey, serviceContext);
	}

	@Override
	public String convertImageToBase64(java.io.InputStream inputStream)
		throws java.io.IOException {

		return _employeesService.convertImageToBase64(inputStream);
	}

	@Override
	public ats.global.techsoft.slayers.model.Employees deleteEmployees(
		ats.global.techsoft.slayers.model.Employees employees) {

		return _employeesService.deleteEmployees(employees);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _employeesService.getOSGiServiceIdentifier();
	}

	@Override
	public ats.global.techsoft.slayers.model.Employees updateEmployees(
			long employeeId, String empName, java.io.InputStream empPhotoStream,
			String empGender, int empAge, String emplRole, String empAddress,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws ats.global.techsoft.slayers.exception.NoSuchEmployeesException,
			   java.io.IOException {

		return _employeesService.updateEmployees(
			employeeId, empName, empPhotoStream, empGender, empAge, emplRole,
			empAddress, serviceContext);
	}

	@Override
	public EmployeesService getWrappedService() {
		return _employeesService;
	}

	@Override
	public void setWrappedService(EmployeesService employeesService) {
		_employeesService = employeesService;
	}

	private EmployeesService _employeesService;

}