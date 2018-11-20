import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

class LamdaTests {

	@Test
	void testIntegerMath() {
		Calculator calc = new Calculator();
		Calculator.IntegerMath addition = (a, b) -> a + b;
		Calculator.IntegerMath subtraction = (a, b) -> a - b;
		Calculator.IntegerMath division = (a, b) -> a / b;
		Calculator.IntegerMath multiplication = (a, b) -> a * b;
		Calculator.IntegerMath modulus = (a, b) -> a % b;
		
		assertEquals(300, calc.operateBinary(100, 200, addition));
		assertEquals(-100, calc.operateBinary(100, 200, subtraction));
		assertEquals(0, calc.operateBinary(100, 200, division));
		assertEquals(20000, calc.operateBinary(100, 200, multiplication));
		assertEquals(2, calc.operateBinary(5,  3,  modulus));
		
		// Instead of a lambda, try an existing math operation.  Any existing
		// binary math operation should work.
		Calculator.IntegerMath min = Integer::min;
		assertEquals(1525, calc.operateBinary(2500,  1525,  min));

		Calculator.IntegerMath rotateLeft = Integer::rotateLeft;
		assertEquals(0xf000000f, calc.operateBinary(0x000000ff,  28,  rotateLeft));
		
		// Instead of assigning the lamba to a temporary, use it directly. As long as the 
		// lamba meets the criteria of the functional interface required, it can be used 
		// in place.
		assertEquals(4, calc.operateBinary(2,  1,  (a, b) -> a << b ));

	}
	
	@Test
	void testAccumulate() {
		assertEquals(15, VarArgExamples.accumulate((a, b) -> a + b, 1, 2, 3, 4, 5).intValue());
		
		Double result = VarArgExamples.accumulate((a, b) -> a + b, 5.1, 6.2, 3.0, 4.0, 2.4);
		double diff = result.doubleValue() - 20.7;
		assertTrue(diff < 0.1);
		
	}
}
