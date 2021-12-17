package employees;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class HelloServiceTest {

	@Test
	void sayHello() {
		var helloProperties = new HelloProperties();
		helloProperties.setMessage("hello");
		var  helloService = new HelloService(helloProperties);
		var message = helloService.sayHello();
		assertThat(message).startsWith("hello");
	}
}