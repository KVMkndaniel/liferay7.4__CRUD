<%@page import="javax.portlet.PortletURL"%>
<%@page import="ats.global.techsoft.slayers.model.Employees"%>
<%@page import="java.util.List"%>
<%@ include file="/init.jsp" %>

<liferay-portlet:renderURL varImpl="formURL">
	<portlet:param name="mvcPath" value="/Employee/addEmployee.jsp" />
</liferay-portlet:renderURL>

<%-- <aui:a href="${formURL}" cssClass="btn btn-primary adddata"
	label="AddData" /> --%>
	<aui:a href="${formURL}" cssClass="btn btn-prim adddata">
    <img src="http://www.atsglobal.com:8080/documents/d/guest/add-friend" alt="Add Icon" style="width:40px; height:40px; margin-right:5px;" />
    AddEmployee
</aui:a>
	

<style>
a.btn.btn-prim.adddata {
    float: right;
    margin-right: 227px;
   
}
</style>
<%
    
    List<Employees> datalist = (List<Employees>) request.getAttribute("datalist");
    PortletURL informationRenderURL = renderResponse.createRenderURL();
    informationRenderURL.setParameter("jspPage", "/Employee/editEmployee.jsp");

%>

<h3>Employee List</h3>

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
			  %> <a href="<%=informationRenderURL %>" class="btn btn-info"
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
