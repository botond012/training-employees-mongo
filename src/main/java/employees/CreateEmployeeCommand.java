package employees;

import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateEmployeeCommand {
	@Schema(description = "name of the employee", example = "Béla")
	@Name(message = "Name cannot be empty, should start with Capital letter, and the length of the name should be [3-10]", maxLength = 10)
	private String name;
}
