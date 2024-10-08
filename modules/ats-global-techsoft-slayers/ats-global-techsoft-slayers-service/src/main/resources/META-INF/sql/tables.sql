create table ATS_Employees (
	uuid_ VARCHAR(75) null,
	EmployeeId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	EmpKey LONG,
	EmpName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	EmpPhoto VARCHAR(75) null,
	EmpGender VARCHAR(75) null,
	EmpAge INTEGER,
	EmplRole VARCHAR(75) null,
	EmpAddress VARCHAR(75) null
);

create table ATS_student (
	uuid_ VARCHAR(75) null,
	id_ LONG not null primary key,
	name VARCHAR(75) null,
	standard VARCHAR(75) null
);