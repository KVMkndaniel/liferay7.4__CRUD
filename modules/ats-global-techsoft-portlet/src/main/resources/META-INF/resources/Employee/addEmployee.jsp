<%@ include file="../init.jsp"%>

<portlet:actionURL name="CreateEmployee" var="CreateEmployeeURL"></portlet:actionURL>
<aui:form action="<%=CreateEmployeeURL%>" method="POST" name="addfm">
	<aui:input type="text" name="empName" label="Employee Name">
		<aui:validator name="required"></aui:validator>
	</aui:input>
	<aui:input type="file" name="empPhoto" label="Employee Photo"></aui:input>
	<aui:input type="text" name="empKey" label="Employee KEY">
		<aui:validator name="required"></aui:validator>
	</aui:input>
	<aui:input type="number" name="empAge" label="Employee Age">
		<aui:validator name="required"></aui:validator>
	</aui:input>
	<aui:select name="emplRole" label="Employee Role">
		<aui:option>Selected Role</aui:option>
		<aui:option value="Developer">Developer</aui:option>
		<aui:option value="HR">HR</aui:option>
		<aui:option value="MarKeting">Marketing</aui:option>
		<aui:option value="Accounts">Accounts</aui:option>
	</aui:select>
	<aui:input type="text" name="empAddress" label="Employee Address">
		<aui:validator name="required"></aui:validator>
	</aui:input>
	<aui:select name="empGender">
		<aui:option>Selected Gender</aui:option>
		<aui:option value="male">Male</aui:option>
		<aui:option value="female">Female</aui:option>
	</aui:select>
	<aui:button type="submit" class="btn-btn-success">Submit</aui:button>
</aui:form>
