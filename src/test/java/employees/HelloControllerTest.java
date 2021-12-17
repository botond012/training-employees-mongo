package employees;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class HelloControllerTest {
	@Mock
	HelloService helloService;

	@InjectMocks
	HelloController helloController;
	@Test
	void hello() {
		Mockito.when(helloService.sayHello()).thenReturn("returnmock");
		var message = helloController.hello();
		assertThat(message).isEqualTo("returnmock");
	}
}