package employees;

import javax.validation.constraints.NotBlank;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "employee")
public class HelloProperties {
	@NotBlank
	String message;
}
