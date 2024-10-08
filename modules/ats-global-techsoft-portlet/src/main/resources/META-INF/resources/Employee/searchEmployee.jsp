<%@page import="ats.global.techsoft.slayers.service.EmployeesLocalServiceUtil"%>
<%@page import="ats.global.techsoft.slayers.model.Employees"%>
<%@page import="java.util.List"%>
<%@page import="com.liferay.portal.kernel.util.ParamUtil"%>
<%@ include file="../init.jsp"%>

<%
    // Retrieve the employee name parameter from the request
    String empName = ParamUtil.getString(renderRequest, "EmpName");

    // Fetch the list of employees matching the given name
    List<Employees> employees = EmployeesLocalServiceUtil.findByEmployeeName(empName);
%>

	<h3>Employee Name Finder Method Call</h3>
	<h2>
		
		<%= empName %></h2>

	<ul>
		<%
            
            if (employees.isEmpty()) {
        %>
		<li>No employees found.</li>
		<%
            } else {
               
                for (Employees employee : employees) {
        %>
		<li><%= employee.getEmpName() %></li>
		<%
                }
            }
        %>
	</ul>

