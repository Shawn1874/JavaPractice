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

}
