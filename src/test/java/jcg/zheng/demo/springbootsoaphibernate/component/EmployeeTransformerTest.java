package jcg.zheng.demo.springbootsoaphibernate.component;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.util.Objects;

import org.junit.Before;
import org.junit.Test;

import jcg.zheng.demo.springbootsoaphibernate.entity.Employee;
import payroll.bestpay.employee.EmployeeInfo;

public class EmployeeTransformerTest {
	
	private EmployeeTransformer testClass;
	
	@Before
	public void setup() {
		testClass = new EmployeeTransformer();
	}

	@Test
	public void convert_happyPath() {
		Employee emp = new Employee();
		emp.setFirstName("Mary");
		emp.setLastName("Zheng");
		emp.setHourlyRate(45);		
		
		EmployeeInfo empInfo = testClass.convert(emp );
		assertEquals("Mary", empInfo.getFirstName());
		assertEquals("Zheng", empInfo.getLastName());
		assertEquals(0, empInfo.getHourlyRate().compareTo(new BigDecimal(45)));
	}

}
