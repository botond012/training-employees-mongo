package employees;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.zalando.problem.Problem;
import org.zalando.problem.Status;

@RestController
@RequestMapping("/api/employees")
@Tag(name = "operations on employee")
public class EmployeesController {

	private EmployeesService employeesService;

	public EmployeesController(EmployeesService employeesService) {
		this.employeesService = employeesService;
	}


	@GetMapping(produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public List<EmployeeDto> listEmployees(@RequestParam Optional<String> prefix) {
		return employeesService.listEmployees(prefix);
	}

	@GetMapping(value = "/{id}", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public EmployeeDto findEmployeeById(@PathVariable("id") long id) {
		return employeesService.listEmployeeById(id);
	}

	//	@GetMapping("/{id}")
	//	public ResponseEntity findEmployeeById(@PathVariable("id") long id) {
	//		try {
	//			return ResponseEntity.ok(employeesService.listEmployeeById(id));
	//		} catch (IllegalArgumentException e) {
	//			return ResponseEntity.notFound().build();
	//		}
	//
	//	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	@Operation(summary = "creates an employee")
	@ApiResponse(responseCode = "201", description = "employee has been created")
	public EmployeeDto createEmployee(@Valid @RequestBody CreateEmployeeCommand command) {
		return employeesService.createEmployee(command);
	}

	@PutMapping("/{id}")
	public EmployeeDto createEmployee(@PathVariable("id") long id, @RequestBody UpdateEmployeeCommand command) {
		return employeesService.updateEmployee(id, command);
	}


	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteEmployee(@PathVariable("id") long id) {
		employeesService.deleteEmployee(id);
	}



}
