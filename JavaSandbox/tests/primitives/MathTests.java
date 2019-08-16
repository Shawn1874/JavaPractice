package primitives;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import java.math.*;

class MathTests {

	@Test
	void TestInstanceOf() {
		BigDecimal value = new BigDecimal(5);
		assertTrue(value instanceof Number);
	}
	
	@Test
	void testRint() {
		double result = Math.rint(3.2);
		assertEquals(3, result);
		
		// The result is always even if the value is equidistant from the high and low
		// integer.
		result = Math.rint(3.5);
		assertEquals(4, result);
	}

	@Test
	void testAddExact() {
		assertThrows(ArithmeticException.class, () -> Math.addExact(Integer.MAX_VALUE, 1));
		assertEquals(2, Math.addExact(1, 1));
	}
	
	@Test
	void testFloorDivision() {
		assertThrows(ArithmeticException.class, () -> Math.floorDiv(1000, 0));
		assertEquals(111, Math.floorDiv(1000, 9));
		
	}
        
        @Test
        void testCeiling() {
           assertEquals(Math.ceil(6.4), 7.0);
           assertEquals(Math.ceil(-6.4), -6.0);
        
        }
           
        @Test
        void testFloor() {
           assertEquals(Math.floor(6.4), 6.0);
           assertEquals(Math.floor(-6.4), -7.0);
        }
        
}
