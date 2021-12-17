package employees;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@EnableConfigurationProperties(value = HelloProperties.class)
public class HelloService {

	private HelloProperties helloProperties;


	public String sayHello() {
		return helloProperties.getMessage() + LocalDateTime.now();
	}
}
