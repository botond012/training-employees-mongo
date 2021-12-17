package employees;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document("employees")
public class Employee {

	@org.springframework.data.annotation.Id
	private String Id;
	private String name;

	public Employee(String name) {
		this.name = name;
	}
}
