package lamdas_and_functional;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class RecordTests {

	private Record shawn = new Record(1, "Shawn", "Fox");
	private Record shawnfox = new Record(1, "Shawn", "Fox");
	private Record fox = new Record(1, "Shawn", "Fox");
	private Record clara = new Record(2, "Clara", "Smith");
	private Record heather = new Record(4, "Heather", "Bennett");
	private Record henderson = new Record(4, "Heather", "Henderson");
	private Record harry = new Record(4, "Harry", "Bennett");
	
	/**
	 * Demonstrate how comparable works which a class would implement so that the 
	 * compareTo is implemented on the class itself.
	 */
	@Test
	void testComparable() {
		assertEquals(0, shawn.compareTo(shawn));
		assertEquals(-1, shawn.compareTo(clara));
		assertEquals(1, clara.compareTo(shawn));
		assertEquals(-3, shawn.compareTo(heather));
		assertEquals(3, heather.compareTo(shawn));
		assertEquals(-6, heather.compareTo(henderson));
		assertEquals(6, henderson.compareTo(heather));
		assertEquals(-4, harry.compareTo(heather));
	}
	
	/**
	 * Demonstrate comparator which allows a new class separate from type being compared
	 * so that a binary comparison method can be used.
	 * 
	 * String.compareTo is a bit odd in that it returns the difference between the characters
	 * at the point where there is a difference.  h is 5 characters greater than c in the 
	 * alphabet and c precedes h so comparing clara to heather results in a 5
	 * 
	 */
	@Test
	void testComparator() {
		CompareRecordByFirstName comparatorName = new CompareRecordByFirstName();
		assertEquals(0, comparatorName.compare(shawn, shawnfox));
		assertEquals(-5, comparatorName.compare(clara, heather));
		assertEquals(11, comparatorName.compare(shawn, heather));
		
	}
	
	@Test
	void testEquals() {
		// reflexivity
		assertEquals(shawn, shawn);
		
		// symmetry and transitivity
		assertEquals(shawn, shawnfox);
		assertEquals(shawnfox, shawn);
		assertEquals(shawn, fox);
		assertEquals(shawnfox, fox);
		
		// null test
		assertNotEquals(shawnfox, null);
		
		// not equal test
		assertNotEquals(shawn, clara);
		assertNotEquals(clara, shawn);
		
		// consistency
		assertEquals(shawnfox, fox);
		fox.setFirstName("Bill");
		fox.setIdentifier(7);
		assertNotEquals(shawnfox, fox);
		
	}
	
	/*@Test
	void testTranslator() {
		Record original = new Record(1, "Shawn", "Fox");
		RecordTranslator<Record, RecordWithKeyValues> translator = new RecordTranslator<>();
		RecordWithKeyValues translated = translator.apply(original);
		
		// Verification of result.
		assertEquals(3, translated.entries.size());
		assertTrue(translated.entries.containsKey("Identifier"));
		assertTrue(translated.entries.containsKey("FirstName"));
		assertTrue(translated.entries.containsKey("LastName"));
		
		RecordTranslator<Integer, String> translator2 = new RecordTranslator<>();
		String result = translator2.apply(Integer.valueOf(5));
		assertEquals("3", result);
		
	}*/
}
