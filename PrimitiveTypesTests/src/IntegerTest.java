import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class IntegerTest {

	@Test
	void TestMin() {
		assertEquals(Integer.MIN_VALUE, -2147483648);
	}
	
	@Test
	void TestMax() {
		assertEquals(Integer.MAX_VALUE, 2147483647);
	}
	
	@Test
	void TestSizeInfo() {
		assertEquals(32, Integer.SIZE);
		assertEquals(4, Integer.BYTES);
	}
	
	@Test
	void TestBitCount() {
		assertEquals(32, Integer.bitCount(-1));
		assertEquals(1, Integer.bitCount(1));
		assertEquals(8, Integer.bitCount(255));
	}
	
	@Test 
	void TestDecode() {
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
	void TestNarrowToShort() {
		Integer value = new Integer(70000);
		assertTrue(value.intValue() > (int) Short.MAX_VALUE);
		assertEquals(4464, value.shortValue());
	}
	
	@Test
	void TestToString() {
		String fromInt = Integer.toString(25);
		assertEquals("25", fromInt);
		
		// Interesting that Java allows '_' characters within numeric literals.
		fromInt = Integer.toString(2_500);
		assertEquals("2500", fromInt);
	}
}
