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

package ats.global.techsoft.slayers.service.http;

import ats.global.techsoft.slayers.service.EmployeesServiceUtil;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.security.auth.HttpPrincipal;
import com.liferay.portal.kernel.service.http.TunnelUtil;
import com.liferay.portal.kernel.util.MethodHandler;
import com.liferay.portal.kernel.util.MethodKey;

/**
 * Provides the HTTP utility for the
 * <code>EmployeesServiceUtil</code> service
 * utility. The
 * static methods of this class calls the same methods of the service utility.
 * However, the signatures are different because it requires an additional
 * <code>HttpPrincipal</code> parameter.
 *
 * <p>
 * The benefits of using the HTTP utility is that it is fast and allows for
 * tunneling without the cost of serializing to text. The drawback is that it
 * only works with Java.
 * </p>
 *
 * <p>
 * Set the property <b>tunnel.servlet.hosts.allowed</b> in portal.properties to
 * configure security.
 * </p>
 *
 * <p>
 * The HTTP utility is only generated for remote services.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class EmployeesServiceHttp {

	public static ats.global.techsoft.slayers.model.Employees addEmployees(
			HttpPrincipal httpPrincipal, long groupId, long companyId,
			String empName, java.io.InputStream empPhotoStream,
			String empGender, int empAge, String emplRole, String empAddress,
			long empKey,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws java.io.IOException {

		try {
			MethodKey methodKey = new MethodKey(
				EmployeesServiceUtil.class, "addEmployees",
				_addEmployeesParameterTypes0);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, groupId, companyId, empName, empPhotoStream,
				empGender, empAge, emplRole, empAddress, empKey,
				serviceContext);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
				if (exception instanceof java.io.IOException) {
					throw (java.io.IOException)exception;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(
					exception);
			}

			return (ats.global.techsoft.slayers.model.Employees)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static String convertImageToBase64(
			HttpPrincipal httpPrincipal, java.io.InputStream inputStream)
		throws java.io.IOException {

		try {
			MethodKey methodKey = new MethodKey(
				EmployeesServiceUtil.class, "convertImageToBase64",
				_convertImageToBase64ParameterTypes1);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, inputStream);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
				if (exception instanceof java.io.IOException) {
					throw (java.io.IOException)exception;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(
					exception);
			}

			return (String)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	private static Log _log = LogFactoryUtil.getLog(EmployeesServiceHttp.class);

	private static final Class<?>[] _addEmployeesParameterTypes0 = new Class[] {
		long.class, long.class, String.class, java.io.InputStream.class,
		String.class, int.class, String.class, String.class, long.class,
		com.liferay.portal.kernel.service.ServiceContext.class
	};
	private static final Class<?>[] _convertImageToBase64ParameterTypes1 =
		new Class[] {java.io.InputStream.class};

}