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

package ats.global.techsoft.slayers.service.impl;

import ats.global.techsoft.slayers.exception.NoSuchEmployeesException;
import ats.global.techsoft.slayers.model.Employees;
import ats.global.techsoft.slayers.service.base.EmployeesServiceBaseImpl;
import ats.global.techsoft.slayers.service.persistence.EmployeesPersistence;

import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.service.ServiceContext;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;
import java.util.Date;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Brian Wing Shun Chan
 */
@Component(property = { "json.web.service.context.name=ats",
		"json.web.service.context.path=Employees" }, service = AopService.class)
public class EmployeesServiceImpl extends EmployeesServiceBaseImpl {

	public Employees addEmployees(long groupId, long companyId, String empName, InputStream empPhotoStream,
			String empGender, int empAge, String emplRole, String empAddress, long empKey,
			ServiceContext serviceContext) throws IOException {

		long employeeId = counterLocalService.increment(Employees.class.getName());
		Employees employee = _employeesPersistence.create(employeeId);

		employee.setGroupId(serviceContext.getScopeGroupId());
		employee.setCompanyId(serviceContext.getCompanyId());
		employee.setEmpName(empName);
		String empPhotoBase64 = convertImageToBase64(empPhotoStream);
		employee.setEmpPhoto(empPhotoBase64);
		employee.setEmpGender(empGender);
		employee.setEmpAge(empAge);
		employee.setEmplRole(emplRole); 
		employee.setEmpAddress(empAddress);
		employee.setEmpKey(empKey);
		employee.setCreateDate(new Date());
		employee.setModifiedDate(new Date());
		employee = _employeesPersistence.update(employee);
		return employee;
	}

	@Override
	public Employees deleteEmployees(Employees employees) {
		return employees;
	}

	@Override
	public Employees updateEmployees(long employeeId, String empName, InputStream empPhotoStream, String empGender,
			int empAge, String emplRole, String empAddress, ServiceContext serviceContext) throws NoSuchEmployeesException, IOException {
		
	    Employees employee = _employeesPersistence.findByPrimaryKey(employeeId);
	    employee.setEmpName(empName);
	    if (empPhotoStream != null) {
	        String empPhotoBase64 = convertImageToBase64(empPhotoStream);
	        employee.setEmpPhoto(empPhotoBase64);
	    }
	    employee.setEmpGender(empGender);
	    employee.setEmpAge(empAge);
	    employee.setEmplRole(emplRole);
	    employee.setEmpAddress(empAddress);
	    employee.setModifiedDate(new Date()); 
	    employee = _employeesPersistence.update(employee);
	    
	   return employee;
	}

	
	public String convertImageToBase64(InputStream inputStream) throws IOException {
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		byte[] buffer = new byte[1024];
		int bytesRead;
		while ((bytesRead = inputStream.read(buffer)) != -1) {
			outputStream.write(buffer, 0, bytesRead);
		}
		byte[] imageBytes = outputStream.toByteArray();
		return Base64.getEncoder().encodeToString(imageBytes);
	}

	
	@Reference
	EmployeesPersistence _employeesPersistence;

}