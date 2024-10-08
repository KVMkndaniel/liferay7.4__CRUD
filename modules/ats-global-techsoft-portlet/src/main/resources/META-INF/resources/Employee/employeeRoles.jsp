<%@page import="ats.global.techsoft.slayers.model.Employees"%>
<%@page import="java.util.List"%>
<%@ include file="../init.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<% 
List<Employees> employees = (List<Employees>) request.getAttribute("employees");
%>

<table border="1">
	<thead>
		<tr>
			<th>Employee Name</th>
			<th>Role</th>
			<th>Gender</th>
			<th>Age</th>
			<th>Role</th>
			<th>Address</th>
			<th>Photo</th>
		</tr>
	</thead>
	<tbody>
		<%
                if (employees != null && !employees.isEmpty()) {
                    for (Employees employee : employees) {
            %>
		<tr>
			<td><%=employee.getEmpName() %></td>
			<td><%=employee.getEmplRole() %></td>
			<td><%= employee.getEmpGender() %></td>
                <td><%= employee.getEmpAge() %></td>
                <td><%= employee.getEmplRole() %></td>
                <td><%= employee.getEmpAddress() %></td>
                <td>
                    <%
                        String empPhotoBase64 = employee.getEmpPhoto();
                        if (empPhotoBase64 != null && !empPhotoBase64.isEmpty()) {
                    %>
                        <img src="data:image/png;base64,<%= empPhotoBase64 %>" alt="Employee Photo" width="50" height="50"/>
                    <%
                        } else {
                    %>
                        No Photo
                    <%
                        }
                    %>
                </td>
		</tr>
		<%} }%>
	</tbody>
</table>
