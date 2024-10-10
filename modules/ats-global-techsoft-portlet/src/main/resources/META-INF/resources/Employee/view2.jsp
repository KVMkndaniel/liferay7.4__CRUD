<%@page import="com.liferay.portal.kernel.util.Validator"%>
<%@page import="javax.portlet.PortletRequest"%>
<%@page import="com.liferay.portal.kernel.portlet.PortletURLFactoryUtil"%>
<%@page import="javax.portlet.PortletURL"%>
<%@page import="ats.global.techsoft.slayers.model.Employees"%>
<%@page import="java.util.List"%>
<%@ include file="../init.jsp" %>
<%
List<Employees> datalist = (List<Employees>) request.getAttribute("datalist");
%>

<h3>Employee List</h3>

<h3>Select Gender</h3>
<select id="genderSelect">
    <option value="">Select Gender</option>
    <option value="male">Male</option>
    <option value="female">Female</option>
</select>

<h3>Select Role</h3>
<select id="roleSelect">
    <option value="">Select Role</option>
    <option value="manager">Manager</option>
    <option value="developer">Developer</option>
    <option value="analyst">Analyst</option>
</select>

<button id="filterButton">Filter</button>

<table class="table table-bordered table-hover">
    <thead>
        <tr>
            <th>Employee ID</th>
            <th>Employee Name</th>
            <th>Gender</th>
            <th>Role</th>
            <th>Address</th>
            <th>Actions</th>
        </tr>
    </thead>
    <tbody id="userList">
        <%
        if (datalist != null && !datalist.isEmpty()) {
            for (Employees employee : datalist) {
        %>
        <tr>
            <td><%= employee.getEmployeeId() %></td>
            <td><%= employee.getEmpName() %></td>
            <td><%= employee.getEmpGender() %></td>
            <td><%= employee.getEmplRole() %></td>
            <td><%= employee.getEmpAddress() %></td>
            <td>
                <a href="<%= renderResponse.createRenderURL() + "?employeeId=" + employee.getEmployeeId() %>" class="btn btn-primary">Update</a>
                <a href="<%= renderResponse.createActionURL() + "?action=delete&employeeId=" + employee.getEmployeeId() %>" class="btn btn-danger" onclick="return confirm('Are you sure?');">Delete</a>
            </td>
        </tr>
        <%
            }
        } else {
        %>
        <tr>
            <td colspan="6">No employees found.</td>
        </tr>
        <%
        }
        %>
    </tbody>
</table>

<aui:script>
    A.one('#filterButton').on('click', function() {
        var gender = document.getElementById("genderSelect").value;
        var role = document.getElementById("roleSelect").value;
        var params = gender + '|' + role;

        var resourceURL = '<portlet:resourceURL id="filterdata" />';
        A.io.request(resourceURL, {
            dataType: 'json',
            method: 'GET',
            data: { filters: params },
            on: {
                success: function() {
                    var response = this.get('responseData');
                    var filteredEmployees = response.datalist;
                    var userListHtml = '';

                    // Build the HTML for the filtered employees
                    A.Array.each(filteredEmployees, function(employee) {
                        userListHtml += '<tr>' +
                            '<td>' + employee.employeeId + '</td>' +
                            '<td>' + employee.empName + '</td>' +
                            '<td>' + employee.empGender + '</td>' +
                            '<td>' + employee.emplRole + '</td>' +
                            '<td>' + employee.empAddress + '</td>' +
                            '<td>' +
                               <%--  '<a href="<%= renderResponse.createRenderURL() + "?employeeId=" + employee.employeeId %>" class="btn btn-primary">Update</a>' +
                                '<a href="<%= renderResponse.createActionURL() + "?action=delete&employeeId=" + employee.employeeId %>" class="btn btn-danger" onclick="return confirm(\'Are you sure?\');">Delete</a>' +
                            --%> '</td>' +
                        '</tr>';
                    });

                    // Update the user list in the DOM
                    A.one('#userList').setHTML(userListHtml);
                },
                failure: function() {
                    console.error('Error fetching filtered users');
                }
            }
        });
    });
</aui:script>
