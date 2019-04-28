package class_concepts;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class LocalClassExampleTest {

	@Test
	void testValidatePhoneNumber() {
		assertTrue(LocalClassExample.validatePhoneNumber("858-332-2499"));
		assertTrue(LocalClassExample.validatePhoneNumber("619.205.3311"));
		assertFalse(LocalClassExample.validatePhoneNumber("368-1312"));
	}

}
