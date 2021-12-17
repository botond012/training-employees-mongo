package employees;

import static org.hamcrest.Matchers.equalTo;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;


@WebMvcTest(controllers = EmployeesController.class)
public class EmployeesControllerWebMvcIT {
	@MockBean
	EmployeesService employeesService;
	@Autowired
	MockMvc mockMvc;

	@Test
	void testListEmployees() throws Exception {
		when(employeesService.listEmployees(any()))
				.thenReturn(List.of(
						new EmployeeDto(1l, "Béla"),
						new EmployeeDto(2l, "Zoli")));
		mockMvc.perform(MockMvcRequestBuilders.get("/api/employees"))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(jsonPath("$[0].name",equalTo("Béla")));

	}
}
