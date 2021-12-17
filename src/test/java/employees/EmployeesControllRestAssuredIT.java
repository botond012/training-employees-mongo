package employees;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static io.restassured.module.mockmvc.RestAssuredMockMvc.*;
import static org.hamcrest.core.IsEqual.equalTo;

import io.restassured.http.ContentType;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class EmployeesControllRestAssuredIT {
	@Autowired
	MockMvc mockMvc;

	@Autowired
	EmployeesService employeesService;

	@BeforeEach
	void init() {
		RestAssuredMockMvc.mockMvc(mockMvc);
		RestAssuredMockMvc.requestSpecification = given()
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON);

		employeesService.deleteAllEmployees();

	}

	@Test
	void testListEmployees() {
		with()
				.body(new CreateEmployeeCommand("Zoli"))
				.post("/api/employees")
				.then()
				.statusCode(201)
				.body("name", equalTo("Zoli"))
				.log();
		with()
				.get("/api/employees")
				.then()
				.statusCode(200)
				.body("[0].name", equalTo("Zoli"))
				.body("size()", equalTo(1))
				.log();
	}

	@Test
	void validate() {
		with()
				.body(new CreateEmployeeCommand("Zoli"))
				.post("/api/employees")
				.then()
				.body(matchesJsonSchemaInClasspath("employee-dto.json"));

	}
}
