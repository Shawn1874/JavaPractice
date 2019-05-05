package class_concepts;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import class_concepts.Planet;

class PlanetTest {

	private static double THRESHOLD = .001d;
	
	@Test
	void testMars() {
		Planet mars = Planet.MARS;
		double expected = 3.712d;
		double diff = mars.surfaceGravity() - expected;
		assertTrue(diff < THRESHOLD);
	}
	
	@Test
	void testOrdinal() {
		assertEquals(0, Planet.MERCURY.ordinal());
		assertEquals(3, Planet.MARS.ordinal());
	}
	
	@Test
	void testToString() {
		assertTrue(Planet.VENUS.toString().equals("VENUS"));
	}
	
	/**
	 * The result of the compareTo for an enum is the difference bewteen the items
	 * being compared.
	 */
	@Test
	void testCompareTo() {
		assertEquals(-1, Planet.VENUS.compareTo(Planet.EARTH));
		assertEquals(2, Planet.JUPITER.compareTo(Planet.EARTH));
	}

}
