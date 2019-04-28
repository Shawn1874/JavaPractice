package lamdas_and_functional;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import java.util.function.*;
class FunctionalInterfacesTests {

	@Test
	void testBinaryOperator() {
		BinaryOperator<Integer> add = (x, y) -> x + y;
		assertEquals(5, add.apply(3, 2).intValue());
	}
	
	@Test
	void testPredicate() {
		Predicate<Integer> isEven = (x) -> x % 2 == 0;
		assertTrue(isEven.test(2));
	}
	
	/**
	 * Demonstrates how to assign an existing static method to a functional
	 * interface.  The principal would be the same for non-static methods
	 * and user defined class methods, but this demonstrates the concept.
	 */
	@Test
	void testMethodToLambda() {
		UnaryOperator<Integer> absoluteValue = Math::abs;
		assertEquals(5, absoluteValue.apply(new Integer(-5)).intValue());
	}
}
