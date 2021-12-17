package employees;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class EmployeesRepositoryIT {

	@Autowired
	private EmployeesRepository repository;

	@Test
	void testPersist() {
		var employee = new Employee("Béla");
		repository.save(employee);
		var employees = repository.findAll();
		assertThat(employees).extracting(Employee::getName).containsExactly("Béla");
	}
}
