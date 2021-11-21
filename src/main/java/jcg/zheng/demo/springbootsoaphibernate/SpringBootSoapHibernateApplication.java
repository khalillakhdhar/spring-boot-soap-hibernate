package jcg.zheng.demo.springbootsoaphibernate;

import java.util.Random;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.EnableAsync;

import jcg.zheng.demo.springbootsoaphibernate.entity.Employee;
import jcg.zheng.demo.springbootsoaphibernate.repository.EmployeeRepository;
import payroll.bestpay.employee.EmployeeType;

@SpringBootApplication
@EnableAsync
public class SpringBootSoapHibernateApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext ctx = SpringApplication.run(SpringBootSoapHibernateApplication.class, args);
		EmployeeRepository empRepo = ctx.getBean(EmployeeRepository.class);
		empRepo.save(buildDummyEmployee("John", "Zhang"));
		empRepo.save(buildDummyEmployee("Dan", "Zhao"));
		empRepo.save(buildDummyEmployee("Tom", "Zheng"));
		empRepo.save(buildDummyEmployee("Mary", "Zheng"));
	}

	private static Employee buildDummyEmployee(String firstName, String lastName) {
		Employee emp = new Employee();
		emp.setType(EmployeeType.HOURLY);
		Random rand = new Random();

		emp.setFirstName(firstName);
		emp.setLastName(lastName);
		emp.setDepartment("dummy dept");
		emp.setManagerId("1");

		emp.setHourlyRate(rand.nextInt(100));

		return emp;
	}

}
