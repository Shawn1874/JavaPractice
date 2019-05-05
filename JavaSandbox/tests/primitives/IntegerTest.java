package primitives;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class IntegerTest {

	@Test
	void testMin() {
		assertEquals(Integer.MIN_VALUE, -2147483648);
	}
	
	@Test
	void testMax() {
		assertEquals(Integer.MAX_VALUE, 2147483647);
	}
	
	@Test
	void testSizeInfo() {
		assertEquals(32, Integer.SIZE);
		assertEquals(4, Integer.BYTES);
	}
	
	@Test
	void testBitCount() {
		assertEquals(32, Integer.bitCount(-1));
		assertEquals(1, Integer.bitCount(1));
		assertEquals(8, Integer.bitCount(255));
	}
	
	@Test 
	void testDecode() {
		Integer result = Integer.decode("-1");
		assertEquals(-1, result.intValue());
		
		result = Integer.decode("025");
		assertEquals(21, result.intValue());
		
		result = Integer.decode("0xff");
		assertEquals(255, result.intValue());
	}
	
	/**
	 * Narrowing doesn't throw.  It simply truncates bits.
	 * 70000 = 0x11170 so narrowing to a short results in 0x1170 or 4464 decimal.
	 * If using these instance methods it is important to use the MAX_VALUE
	 * constant first to ensure that it is possible.
	 */
	@Test
	void testNarrowToShort() {
		Integer value = Integer.valueOf(70000);
		assertTrue(value.intValue() > (int) Short.MAX_VALUE);
		assertEquals(4464, value.shortValue());
	}
	
	/**
	 * A cast from float to int doesn't result in exception.  The value is simply truncated.
	 */
	@Test
	void testFloatToInt() {
		float value = 3.21f;
		assertEquals(3, (int) value);
	}
	
	@Test
	void testToString() {
		String fromInt = Integer.toString(25);
		assertEquals("25", fromInt);
		
		// Interesting that Java allows '_' characters within numeric literals.
		fromInt = Integer.toString(2_500);
		assertEquals("2500", fromInt);
	}
}
