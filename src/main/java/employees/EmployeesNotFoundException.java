package employees;

import java.net.URI;
import org.zalando.problem.AbstractThrowableProblem;
import org.zalando.problem.Status;

public class EmployeesNotFoundException extends AbstractThrowableProblem {

	public EmployeesNotFoundException(long id) {
		super(URI.create("employees/employees-not-found"),
				"Not Found",
				Status.NOT_FOUND,
				String.format("Employees with id:%s not found", id));
	}
}
