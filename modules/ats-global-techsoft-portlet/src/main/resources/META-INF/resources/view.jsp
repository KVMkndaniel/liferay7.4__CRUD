<%@page import="javax.portlet.PortletRequest"%>
<%@page import="com.liferay.portal.kernel.portlet.PortletURLFactoryUtil"%>
<%@page import="javax.portlet.PortletURL"%>
<%@page import="ats.global.techsoft.slayers.model.Employees"%>
<%@page import="java.util.List"%>
<%@ include file="/init.jsp" %>

<liferay-portlet:renderURL varImpl="formURL">
	<portlet:param name="mvcPath" value="/Employee/addEmployee.jsp" />
</liferay-portlet:renderURL>
<liferay-portlet:renderURL varImpl="SearchURL">
	<portlet:param name="mvcPath" value="/Employee/searchEmployee.jsp" />
</liferay-portlet:renderURL>

	<aui:a href="${SearchURL}" cssClass="btn btn-primary"
	label="FinderMethod" />
	<aui:a href="${formURL}" cssClass="btn btn-prim adddata" >
    <img src="http://localhost:8080/documents/d/guest/user" alt="personIcon" style="width:40px; height:40px; margin-right:5px;" />
    AddEmployee
</aui:a>
		
<style>
a.btn.btn-prim.adddata {
    float: right;
    margin-right: 227px;
   Background-color:#4154f1 ;
   color:white
}
</style>
<%
    
    List<Employees> datalist = (List<Employees>) request.getAttribute("datalist");
    PortletURL informationRenderURL = renderResponse.createRenderURL();
    informationRenderURL.setParameter("jspPage", "/Employee/editEmployee.jsp");

%>

<h3>Employee List </h3>

<aui:form method="post">

    <table class="table table-bordered table-hover">
        <thead>
            <tr>
                <th>Employee ID</th>
                <th>Employee Name</th>
                <th>Gender</th>
                <th>Age</th>
                <th>Role</th>
                <th>Address</th>
                <th>Photo</th>
            </tr>
        </thead>
        <tbody>
            <%
                if (datalist != null && !datalist.isEmpty()) {
                    for (Employees employee : datalist) {
            %>
            <tr>
                <td><%= employee.getEmployeeId() %></td>
                <td><%= employee.getEmpName() %></td>
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
                <td>
                <%
				informationRenderURL.setParameter("employeeId", String.valueOf(employee.getEmployeeId()));
			  %> <a href="<%=informationRenderURL %>" class="btn btn" style="Background-color:#4154f1 ;color:white"
			role="button">Update </a>
                </td>
                <td>
                <portlet:actionURL
				name="deleteEmployee" var="deleteEnquiryActionURL">
				<portlet:param name="employeeId"
					value="<%=String.valueOf(employee.getEmployeeId())%>" />
			</portlet:actionURL> <a href="${deleteEnquiryActionURL}" class="btn btn-warning "
			onclick="return confirm('Are you sure you want to delete this item?');">
				Delete </a>
                </td>
            </tr>
            <%
                    }
                } else {
            %>
            <tr>
                <td colspan="7">No employees found.</td>
            </tr>
            <%
                }
            %>
        </tbody>
    </table>
</aui:form>
<br>
<br>
<% 
List<Employees> employees = (List<Employees>) request.getAttribute("employees");
%>
<h3>Employee Custom-SQL </h3>
<p>( SELECT * FROM ats_employees empRole where empRole.EmplRole = "Developer" )</p>
<table class="table table-bordered table-hover">
	<thead>
		<tr>
			<th>Employee Name</th>
			<th>Role</th>
			<th>Gender</th>
			<th>Age</th>
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