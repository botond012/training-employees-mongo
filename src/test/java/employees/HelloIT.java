package employees;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class HelloIT {
	@Autowired
	HelloController helloController;

	@Test
	void sayHello(){
		assertThat(helloController.hello()).startsWith("hello");
	}

}
