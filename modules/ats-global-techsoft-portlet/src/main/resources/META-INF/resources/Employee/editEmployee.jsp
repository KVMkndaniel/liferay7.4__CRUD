<%@ include file="../init.jsp"%>
<%@ taglib uri="http://liferay.com/tld/aui" prefix="aui" %>
<%@ taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>
<%@ page import="com.liferay.portal.kernel.util.ParamUtil" %>
<%@ page import="ats.global.techsoft.slayers.model.Employees" %>
<%@ page import="ats.global.techsoft.slayers.service.EmployeesLocalServiceUtil" %>

<portlet:actionURL name="UpdateEmployee" var="updateEmployeesURL" />

<%
    long employeeId = ParamUtil.getLong(request, "employeeId");
    Employees employees = null;

    if (employeeId > 0) {
        employees = EmployeesLocalServiceUtil.fetchEmployees(employeeId);
    }
%>

<h3>Update Employee</h3>

<aui:form action="<%= updateEmployeesURL %>" method="post" enctype="multipart/form-data">
    
    <!-- Hidden field for Employee ID -->
    <aui:input name="employeeId" type="hidden" value="<%= employees.getEmployeeId() %>" />
    
    <!-- Employee Name -->
    <aui:input name="empName" label="Employee Name" type="text" value="<%= employees.getEmpName() %>" />
    
    <!-- Gender Select -->
   
    <aui:select name="empGender" label="Gender" id="genderSelected" class="form-control">
		<aui:option value="<%=employees.getEmpGender()%>"><%=employees.getEmpGender()%></aui:option>
		<aui:option value="Male">Male</aui:option>
		<aui:option value="Female">Female</aui:option>
	</aui:select>
    <!-- Employee Age -->
    <aui:input name="empAge" label="Employee Age" type="number" value="<%= employees.getEmpAge() %>" />
    
    <!-- Employee Role -->
    <aui:select name="emplRole" label="Employee Role">
        <aui:option value="<%= employees.getEmplRole() %>">Selected Role: <%= employees.getEmplRole() %></aui:option>
        <aui:option value="Developer">Developer</aui:option>
        <aui:option value="HR">HR</aui:option>
        <aui:option value="Marketing">Marketing</aui:option>
        <aui:option value="Accounts">Accounts</aui:option>
    </aui:select>
    
    <!-- Employee Address -->
    <aui:input name="empAddress" label="Employee Address" type="text" value="<%= employees.getEmpAddress() %>" />
    
    <!-- Submit Button -->
    <aui:button type="submit" value="Update" />

</aui:form>
