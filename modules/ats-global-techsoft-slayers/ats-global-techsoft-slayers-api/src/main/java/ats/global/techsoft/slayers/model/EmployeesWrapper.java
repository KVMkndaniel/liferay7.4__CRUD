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

package ats.global.techsoft.slayers.model;

import com.liferay.exportimport.kernel.lar.StagedModelType;
import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.wrapper.BaseModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link Employees}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see Employees
 * @generated
 */
public class EmployeesWrapper
	extends BaseModelWrapper<Employees>
	implements Employees, ModelWrapper<Employees> {

	public EmployeesWrapper(Employees employees) {
		super(employees);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("EmployeeId", getEmployeeId());
		attributes.put("groupId", getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("EmpKey", getEmpKey());
		attributes.put("EmpName", getEmpName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("EmpPhoto", getEmpPhoto());
		attributes.put("EmpGender", getEmpGender());
		attributes.put("EmpAge", getEmpAge());
		attributes.put("EmplRole", getEmplRole());
		attributes.put("EmpAddress", getEmpAddress());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long EmployeeId = (Long)attributes.get("EmployeeId");

		if (EmployeeId != null) {
			setEmployeeId(EmployeeId);
		}

		Long groupId = (Long)attributes.get("groupId");

		if (groupId != null) {
			setGroupId(groupId);
		}

		Long companyId = (Long)attributes.get("companyId");

		if (companyId != null) {
			setCompanyId(companyId);
		}

		Long EmpKey = (Long)attributes.get("EmpKey");

		if (EmpKey != null) {
			setEmpKey(EmpKey);
		}

		String EmpName = (String)attributes.get("EmpName");

		if (EmpName != null) {
			setEmpName(EmpName);
		}

		Date createDate = (Date)attributes.get("createDate");

		if (createDate != null) {
			setCreateDate(createDate);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}

		String EmpPhoto = (String)attributes.get("EmpPhoto");

		if (EmpPhoto != null) {
			setEmpPhoto(EmpPhoto);
		}

		String EmpGender = (String)attributes.get("EmpGender");

		if (EmpGender != null) {
			setEmpGender(EmpGender);
		}

		Integer EmpAge = (Integer)attributes.get("EmpAge");

		if (EmpAge != null) {
			setEmpAge(EmpAge);
		}

		String EmplRole = (String)attributes.get("EmplRole");

		if (EmplRole != null) {
			setEmplRole(EmplRole);
		}

		String EmpAddress = (String)attributes.get("EmpAddress");

		if (EmpAddress != null) {
			setEmpAddress(EmpAddress);
		}
	}

	@Override
	public Employees cloneWithOriginalValues() {
		return wrap(model.cloneWithOriginalValues());
	}

	/**
	 * Returns the company ID of this employees.
	 *
	 * @return the company ID of this employees
	 */
	@Override
	public long getCompanyId() {
		return model.getCompanyId();
	}

	/**
	 * Returns the create date of this employees.
	 *
	 * @return the create date of this employees
	 */
	@Override
	public Date getCreateDate() {
		return model.getCreateDate();
	}

	/**
	 * Returns the emp address of this employees.
	 *
	 * @return the emp address of this employees
	 */
	@Override
	public String getEmpAddress() {
		return model.getEmpAddress();
	}

	/**
	 * Returns the emp age of this employees.
	 *
	 * @return the emp age of this employees
	 */
	@Override
	public int getEmpAge() {
		return model.getEmpAge();
	}

	/**
	 * Returns the emp gender of this employees.
	 *
	 * @return the emp gender of this employees
	 */
	@Override
	public String getEmpGender() {
		return model.getEmpGender();
	}

	/**
	 * Returns the emp key of this employees.
	 *
	 * @return the emp key of this employees
	 */
	@Override
	public long getEmpKey() {
		return model.getEmpKey();
	}

	/**
	 * Returns the employee ID of this employees.
	 *
	 * @return the employee ID of this employees
	 */
	@Override
	public long getEmployeeId() {
		return model.getEmployeeId();
	}

	/**
	 * Returns the empl role of this employees.
	 *
	 * @return the empl role of this employees
	 */
	@Override
	public String getEmplRole() {
		return model.getEmplRole();
	}

	/**
	 * Returns the emp name of this employees.
	 *
	 * @return the emp name of this employees
	 */
	@Override
	public String getEmpName() {
		return model.getEmpName();
	}

	/**
	 * Returns the emp photo of this employees.
	 *
	 * @return the emp photo of this employees
	 */
	@Override
	public String getEmpPhoto() {
		return model.getEmpPhoto();
	}

	/**
	 * Returns the group ID of this employees.
	 *
	 * @return the group ID of this employees
	 */
	@Override
	public long getGroupId() {
		return model.getGroupId();
	}

	/**
	 * Returns the modified date of this employees.
	 *
	 * @return the modified date of this employees
	 */
	@Override
	public Date getModifiedDate() {
		return model.getModifiedDate();
	}

	/**
	 * Returns the primary key of this employees.
	 *
	 * @return the primary key of this employees
	 */
	@Override
	public long getPrimaryKey() {
		return model.getPrimaryKey();
	}

	/**
	 * Returns the uuid of this employees.
	 *
	 * @return the uuid of this employees
	 */
	@Override
	public String getUuid() {
		return model.getUuid();
	}

	@Override
	public void persist() {
		model.persist();
	}

	/**
	 * Sets the company ID of this employees.
	 *
	 * @param companyId the company ID of this employees
	 */
	@Override
	public void setCompanyId(long companyId) {
		model.setCompanyId(companyId);
	}

	/**
	 * Sets the create date of this employees.
	 *
	 * @param createDate the create date of this employees
	 */
	@Override
	public void setCreateDate(Date createDate) {
		model.setCreateDate(createDate);
	}

	/**
	 * Sets the emp address of this employees.
	 *
	 * @param EmpAddress the emp address of this employees
	 */
	@Override
	public void setEmpAddress(String EmpAddress) {
		model.setEmpAddress(EmpAddress);
	}

	/**
	 * Sets the emp age of this employees.
	 *
	 * @param EmpAge the emp age of this employees
	 */
	@Override
	public void setEmpAge(int EmpAge) {
		model.setEmpAge(EmpAge);
	}

	/**
	 * Sets the emp gender of this employees.
	 *
	 * @param EmpGender the emp gender of this employees
	 */
	@Override
	public void setEmpGender(String EmpGender) {
		model.setEmpGender(EmpGender);
	}

	/**
	 * Sets the emp key of this employees.
	 *
	 * @param EmpKey the emp key of this employees
	 */
	@Override
	public void setEmpKey(long EmpKey) {
		model.setEmpKey(EmpKey);
	}

	/**
	 * Sets the employee ID of this employees.
	 *
	 * @param EmployeeId the employee ID of this employees
	 */
	@Override
	public void setEmployeeId(long EmployeeId) {
		model.setEmployeeId(EmployeeId);
	}

	/**
	 * Sets the empl role of this employees.
	 *
	 * @param EmplRole the empl role of this employees
	 */
	@Override
	public void setEmplRole(String EmplRole) {
		model.setEmplRole(EmplRole);
	}

	/**
	 * Sets the emp name of this employees.
	 *
	 * @param EmpName the emp name of this employees
	 */
	@Override
	public void setEmpName(String EmpName) {
		model.setEmpName(EmpName);
	}

	/**
	 * Sets the emp photo of this employees.
	 *
	 * @param EmpPhoto the emp photo of this employees
	 */
	@Override
	public void setEmpPhoto(String EmpPhoto) {
		model.setEmpPhoto(EmpPhoto);
	}

	/**
	 * Sets the group ID of this employees.
	 *
	 * @param groupId the group ID of this employees
	 */
	@Override
	public void setGroupId(long groupId) {
		model.setGroupId(groupId);
	}

	/**
	 * Sets the modified date of this employees.
	 *
	 * @param modifiedDate the modified date of this employees
	 */
	@Override
	public void setModifiedDate(Date modifiedDate) {
		model.setModifiedDate(modifiedDate);
	}

	/**
	 * Sets the primary key of this employees.
	 *
	 * @param primaryKey the primary key of this employees
	 */
	@Override
	public void setPrimaryKey(long primaryKey) {
		model.setPrimaryKey(primaryKey);
	}

	/**
	 * Sets the uuid of this employees.
	 *
	 * @param uuid the uuid of this employees
	 */
	@Override
	public void setUuid(String uuid) {
		model.setUuid(uuid);
	}

	@Override
	public String toXmlString() {
		return model.toXmlString();
	}

	@Override
	public StagedModelType getStagedModelType() {
		return model.getStagedModelType();
	}

	@Override
	protected EmployeesWrapper wrap(Employees employees) {
		return new EmployeesWrapper(employees);
	}

}