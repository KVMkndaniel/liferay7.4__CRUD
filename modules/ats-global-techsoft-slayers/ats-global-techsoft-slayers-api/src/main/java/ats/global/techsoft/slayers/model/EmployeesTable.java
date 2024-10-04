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

import com.liferay.petra.sql.dsl.Column;
import com.liferay.petra.sql.dsl.base.BaseTable;

import java.sql.Types;

import java.util.Date;

/**
 * The table class for the &quot;ATS_Employees&quot; database table.
 *
 * @author Brian Wing Shun Chan
 * @see Employees
 * @generated
 */
public class EmployeesTable extends BaseTable<EmployeesTable> {

	public static final EmployeesTable INSTANCE = new EmployeesTable();

	public final Column<EmployeesTable, String> uuid = createColumn(
		"uuid_", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<EmployeesTable, Long> EmployeeId = createColumn(
		"EmployeeId", Long.class, Types.BIGINT, Column.FLAG_PRIMARY);
	public final Column<EmployeesTable, Long> groupId = createColumn(
		"groupId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<EmployeesTable, Long> companyId = createColumn(
		"companyId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<EmployeesTable, Long> EmpKey = createColumn(
		"EmpKey", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<EmployeesTable, String> EmpName = createColumn(
		"EmpName", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<EmployeesTable, Date> createDate = createColumn(
		"createDate", Date.class, Types.TIMESTAMP, Column.FLAG_DEFAULT);
	public final Column<EmployeesTable, Date> modifiedDate = createColumn(
		"modifiedDate", Date.class, Types.TIMESTAMP, Column.FLAG_DEFAULT);
	public final Column<EmployeesTable, String> EmpPhoto = createColumn(
		"EmpPhoto", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<EmployeesTable, String> EmpGender = createColumn(
		"EmpGender", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<EmployeesTable, Integer> EmpAge = createColumn(
		"EmpAge", Integer.class, Types.INTEGER, Column.FLAG_DEFAULT);
	public final Column<EmployeesTable, String> EmplRole = createColumn(
		"EmplRole", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<EmployeesTable, String> EmpAddress = createColumn(
		"EmpAddress", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);

	private EmployeesTable() {
		super("ATS_Employees", EmployeesTable::new);
	}

}