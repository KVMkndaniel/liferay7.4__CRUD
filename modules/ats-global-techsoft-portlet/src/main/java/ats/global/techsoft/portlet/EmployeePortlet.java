package ats.global.techsoft.portlet;

import ats.global.techsoft.portlet.constants.EmployeePortletKeys;
import ats.global.techsoft.slayers.model.Employees;
import ats.global.techsoft.slayers.service.EmployeesLocalService;
import ats.global.techsoft.slayers.service.EmployeesLocalServiceUtil;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextFactory;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.upload.UploadPortletRequest;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.ProcessAction;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(immediate = true, property = { "com.liferay.portlet.display-category=ATS",
		"com.liferay.portlet.header-portlet-css=/css/main.css", "com.liferay.portlet.instanceable=true",
		"javax.portlet.display-name=EmployeeModule", "javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp", "javax.portlet.name=" + EmployeePortletKeys.EMPLOYEE,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user" }, service = Portlet.class)
public class EmployeePortlet extends MVCPortlet {

	@Override
	public void doView(RenderRequest renderRequest, RenderResponse renderResponse)
			throws IOException, PortletException {
		ThemeDisplay themeDisplay = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
		DynamicQuery dynamicQuery = EmployeesLocalServiceUtil.dynamicQuery();
		List<Employees> datalist = EmployeesLocalServiceUtil.dynamicQuery(dynamicQuery);
		renderRequest.setAttribute("datalist", datalist);
		super.doView(renderRequest, renderResponse);
	}

	public void CreateEmployee(ActionRequest request, ActionResponse response) throws PortalException, IOException {
		_log.info("Started Method :::::::");
		ServiceContext serviceContext = ServiceContextFactory.getInstance(Employees.class.getName(), request);
		long groupId = serviceContext.getScopeGroupId();
		long companyId = serviceContext.getCompanyId();
		String empName = ParamUtil.getString(request, "empName");
		_log.info("Employee Name :::::::" + empName);
		String empGender = ParamUtil.getString(request, "empGender");
		int empAge = ParamUtil.getInteger(request, "empAge");
		String emplRole = ParamUtil.getString(request, "emplRole");
		String empAddress = ParamUtil.getString(request, "empAddress");
		long empKey = ParamUtil.getLong(request, "empKey");
		UploadPortletRequest uploadRequest = PortalUtil.getUploadPortletRequest(request);
		InputStream empPhotoStream = uploadRequest.getFileAsStream("empPhoto");
		_employeeLocalService.addEmployees(groupId, companyId, empName, empPhotoStream, empGender, empAge, emplRole,
				empAddress, empKey, serviceContext);
	}

	public void UpdateEmployee(ActionRequest request, ActionResponse response) throws PortalException, IOException {
		_log.info("Started UpdateEmployee Method :::::::");

		ServiceContext serviceContext = ServiceContextFactory.getInstance(Employees.class.getName(), request);
		long employeeId = ParamUtil.getLong(request, "employeeId");

		if (employeeId <= 0) {
			_log.error("Validation failed: Invalid employee ID.");
			throw new PortalException("Employee ID is required for update.");
		}
		String empName = ParamUtil.getString(request, "empName");
		String empGender = ParamUtil.getString(request, "empGender");
		int empAge = ParamUtil.getInteger(request, "empAge", 0);
		String emplRole = ParamUtil.getString(request, "emplRole");
		String empAddress = ParamUtil.getString(request, "empAddress");
		_log.info("Updating Employee ID: " + employeeId + ", Name: " + empName);
		if (Validator.isNull(empName) || Validator.isNull(empGender) || empAge <= 0 || Validator.isNull(emplRole)) {
			_log.error("Validation failed: Missing required employee details.");
			throw new PortalException("Employee name, gender, age, and role are required.");
		}
		UploadPortletRequest uploadRequest = PortalUtil.getUploadPortletRequest(request);
		InputStream empPhotoStream = uploadRequest.getFileAsStream("empPhoto");
		_employeeLocalService.updateEmployees(employeeId, empName, empPhotoStream, empGender, empAge, emplRole,
				empAddress, serviceContext);
		_log.info("Employee updated successfully.");
	}

	@ProcessAction(name = "deleteEmployee")
    public void deleteEmployee(ActionRequest actionRequest, ActionResponse actionResponse){
		
        long employeeId = ParamUtil.getLong(actionRequest, "employeeId", GetterUtil.DEFAULT_LONG);
        try {
        	_employeeLocalService.deleteEmployees(employeeId);
        } catch (Exception e) {
            _log.error(e.getMessage(), e);
        }
    }
	@Reference
	EmployeesLocalService _employeeLocalService;

	private Log _log = LogFactoryUtil.getLog(EmployeePortlet.class);
}