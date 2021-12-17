package employees;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@AllArgsConstructor
public class EmployeesService {

	private ModelMapper modelMapper;
	private EmployeesRepository repository;


	public List<EmployeeDto> listEmployees(Optional<String> prefix) {
		var targetListType = new TypeToken<List<EmployeeDto>>() {
		}.getType();
		var filteredEmployees = repository.findAll().stream()
				.filter(employee -> prefix.isEmpty() || employee.getName().toLowerCase().startsWith(prefix.get().toLowerCase()))
				.collect(Collectors.toList());
		return modelMapper.map(filteredEmployees, targetListType);
	}

	public EmployeeDto listEmployeeById(String id) {
		return modelMapper.map(repository.findById(id)
						.orElseThrow(() -> new IllegalArgumentException("employee not found"))
				, EmployeeDto.class);
	}

	public EmployeeDto createEmployee(CreateEmployeeCommand command) {
		var employee = new Employee(command.getName());
		repository.save(employee);
		log.info("Employee has been created");
		log.debug(String.format("Employee has been created, with name: %s id: %s", employee.getName(), employee.getId()));
		return modelMapper.map(employee, EmployeeDto.class);
	}

	@Transactional
	public EmployeeDto updateEmployee(String id, UpdateEmployeeCommand command) {
		var employee = repository.findById(id).orElseThrow(() -> new IllegalArgumentException("employee not found"));
		employee.setName(command.getName());
		repository.save(employee);
		return modelMapper.map(employee, EmployeeDto.class);
	}

	public void deleteEmployee(String id) {
		repository.deleteById(id);
	}

	public void deleteAllEmployees() {
		repository.deleteAll();
	}


}
