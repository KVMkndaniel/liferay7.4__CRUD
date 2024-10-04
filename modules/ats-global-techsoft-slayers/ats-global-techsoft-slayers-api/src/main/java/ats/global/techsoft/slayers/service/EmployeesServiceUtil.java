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

import ats.global.techsoft.slayers.model.Employees;

import java.io.InputStream;

/**
 * Provides the remote service utility for Employees. This utility wraps
 * <code>ats.global.techsoft.slayers.service.impl.EmployeesServiceImpl</code> and is an
 * access point for service operations in application layer code running on a
 * remote server. Methods of this service are expected to have security checks
 * based on the propagated JAAS credentials because this service can be
 * accessed remotely.
 *
 * @author Brian Wing Shun Chan
 * @see EmployeesService
 * @generated
 */
public class EmployeesServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>ats.global.techsoft.slayers.service.impl.EmployeesServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static Employees addEmployees(
			long groupId, long companyId, String empName,
			InputStream empPhotoStream, String empGender, int empAge,
			String emplRole, String empAddress, long empKey,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws java.io.IOException {

		return getService().addEmployees(
			groupId, companyId, empName, empPhotoStream, empGender, empAge,
			emplRole, empAddress, empKey, serviceContext);
	}

	public static String convertImageToBase64(InputStream inputStream)
		throws java.io.IOException {

		return getService().convertImageToBase64(inputStream);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public static String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static EmployeesService getService() {
		return _service;
	}

	private static volatile EmployeesService _service;

}