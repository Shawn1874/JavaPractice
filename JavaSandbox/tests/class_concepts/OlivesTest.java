package class_concepts;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import class_concepts.Olive;

class OlivesTest {

	@Test
	void testKalamata() {
		Olive kalamata = Olive.KALAMATA;
		assertEquals("Kalamata", kalamata.toString());
		
		Olive picual = Olive.PICUAL;
		assertEquals("Picual", picual.toString());
	}

}
