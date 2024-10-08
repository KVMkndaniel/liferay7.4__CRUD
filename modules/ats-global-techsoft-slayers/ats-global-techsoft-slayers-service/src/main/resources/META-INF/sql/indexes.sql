create index IX_4124E897 on ATS_Employees (EmpName[$COLUMN_LENGTH:75$]);
create index IX_FCFAA77A on ATS_Employees (EmplRole[$COLUMN_LENGTH:75$]);
create index IX_B1F719A0 on ATS_Employees (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_F3B98822 on ATS_Employees (uuid_[$COLUMN_LENGTH:75$], groupId);

create index IX_DB097F32 on ATS_student (uuid_[$COLUMN_LENGTH:75$]);