import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class OlivesTest {

	@Test
	void testKalamata() {
		Olive kalamata = Olive.KALAMATA;
		assertEquals("Kalamata", kalamata.toString());
		
		Olive picual = Olive.PICUAL;
		assertEquals("Picual", picual.toString());
	}

}
