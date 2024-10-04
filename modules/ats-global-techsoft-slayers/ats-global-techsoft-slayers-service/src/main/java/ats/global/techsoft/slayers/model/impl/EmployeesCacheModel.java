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

package ats.global.techsoft.slayers.model.impl;

import ats.global.techsoft.slayers.model.Employees;

import com.liferay.petra.lang.HashUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing Employees in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class EmployeesCacheModel
	implements CacheModel<Employees>, Externalizable {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof EmployeesCacheModel)) {
			return false;
		}

		EmployeesCacheModel employeesCacheModel = (EmployeesCacheModel)object;

		if (EmployeeId == employeesCacheModel.EmployeeId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, EmployeeId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(27);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", EmployeeId=");
		sb.append(EmployeeId);
		sb.append(", groupId=");
		sb.append(groupId);
		sb.append(", companyId=");
		sb.append(companyId);
		sb.append(", EmpKey=");
		sb.append(EmpKey);
		sb.append(", EmpName=");
		sb.append(EmpName);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", EmpPhoto=");
		sb.append(EmpPhoto);
		sb.append(", EmpGender=");
		sb.append(EmpGender);
		sb.append(", EmpAge=");
		sb.append(EmpAge);
		sb.append(", EmplRole=");
		sb.append(EmplRole);
		sb.append(", EmpAddress=");
		sb.append(EmpAddress);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public Employees toEntityModel() {
		EmployeesImpl employeesImpl = new EmployeesImpl();

		if (uuid == null) {
			employeesImpl.setUuid("");
		}
		else {
			employeesImpl.setUuid(uuid);
		}

		employeesImpl.setEmployeeId(EmployeeId);
		employeesImpl.setGroupId(groupId);
		employeesImpl.setCompanyId(companyId);
		employeesImpl.setEmpKey(EmpKey);

		if (EmpName == null) {
			employeesImpl.setEmpName("");
		}
		else {
			employeesImpl.setEmpName(EmpName);
		}

		if (createDate == Long.MIN_VALUE) {
			employeesImpl.setCreateDate(null);
		}
		else {
			employeesImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			employeesImpl.setModifiedDate(null);
		}
		else {
			employeesImpl.setModifiedDate(new Date(modifiedDate));
		}

		if (EmpPhoto == null) {
			employeesImpl.setEmpPhoto("");
		}
		else {
			employeesImpl.setEmpPhoto(EmpPhoto);
		}

		if (EmpGender == null) {
			employeesImpl.setEmpGender("");
		}
		else {
			employeesImpl.setEmpGender(EmpGender);
		}

		employeesImpl.setEmpAge(EmpAge);

		if (EmplRole == null) {
			employeesImpl.setEmplRole("");
		}
		else {
			employeesImpl.setEmplRole(EmplRole);
		}

		if (EmpAddress == null) {
			employeesImpl.setEmpAddress("");
		}
		else {
			employeesImpl.setEmpAddress(EmpAddress);
		}

		employeesImpl.resetOriginalValues();

		return employeesImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		uuid = objectInput.readUTF();

		EmployeeId = objectInput.readLong();

		groupId = objectInput.readLong();

		companyId = objectInput.readLong();

		EmpKey = objectInput.readLong();
		EmpName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();
		EmpPhoto = objectInput.readUTF();
		EmpGender = objectInput.readUTF();

		EmpAge = objectInput.readInt();
		EmplRole = objectInput.readUTF();
		EmpAddress = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		if (uuid == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(uuid);
		}

		objectOutput.writeLong(EmployeeId);

		objectOutput.writeLong(groupId);

		objectOutput.writeLong(companyId);

		objectOutput.writeLong(EmpKey);

		if (EmpName == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(EmpName);
		}

		objectOutput.writeLong(createDate);
		objectOutput.writeLong(modifiedDate);

		if (EmpPhoto == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(EmpPhoto);
		}

		if (EmpGender == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(EmpGender);
		}

		objectOutput.writeInt(EmpAge);

		if (EmplRole == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(EmplRole);
		}

		if (EmpAddress == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(EmpAddress);
		}
	}

	public String uuid;
	public long EmployeeId;
	public long groupId;
	public long companyId;
	public long EmpKey;
	public String EmpName;
	public long createDate;
	public long modifiedDate;
	public String EmpPhoto;
	public String EmpGender;
	public int EmpAge;
	public String EmplRole;
	public String EmpAddress;

}