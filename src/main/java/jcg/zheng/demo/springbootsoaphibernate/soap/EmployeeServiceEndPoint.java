package jcg.zheng.demo.springbootsoaphibernate.soap;

import javax.xml.bind.JAXBElement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import jcg.zheng.demo.springbootsoaphibernate.aop.LoggableService;
import jcg.zheng.demo.springbootsoaphibernate.component.EmployeeService;
import payroll.bestpay.employee.EmployeeIdWrapper;
import payroll.bestpay.employee.EmployeeInfo;
import payroll.bestpay.employee.EmployeeInfoWrapper;
import payroll.bestpay.employee.ObjectFactory;

@Endpoint
public class EmployeeServiceEndPoint {

	private static final String NAMESPACE_URI = "http://bestpay.payroll/employee";

	@Autowired
	private EmployeeService empService;

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "employeeLookupRequest")
	@ResponsePayload
	@LoggableService
	public JAXBElement<EmployeeInfoWrapper> employeeLookup(@RequestPayload JAXBElement<EmployeeIdWrapper> request) {
		ObjectFactory factory = new ObjectFactory();
		EmployeeInfoWrapper response = factory.createEmployeeInfoWrapper();
		for (String empId : request.getValue().getEid()) {
			EmployeeInfo found = empService.employeeLookup(empId);
			if( found != null) {
				response.getEmployeeInfo().add(found);
			} 
		}

		return factory.createEmployeeServiceResponse(response);
	}
}
