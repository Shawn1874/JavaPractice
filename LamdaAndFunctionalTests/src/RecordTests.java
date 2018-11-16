import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class RecordTests {

	/*
	 * Demonstrate how comparable works which a class would implement so that the 
	 * compareTo is implemented on the class itself.
	 */
	@Test
	void testComparable() {
		Record shawn = new Record(1, "Shawn", "Fox");
		Record steve = new Record(1, "Steve", "Henderson");
		assertEquals(0, shawn.compareTo(steve));
		
		Record clara = new Record(2, "Clara", "Smith");
		Record heather = new Record(0, "Heather", "Bennett");
		assertEquals(-1, shawn.compareTo(clara));
		assertEquals(1, shawn.compareTo(heather));
	}
	
	/**
	 * Demonstrate comparator which allows a new class separate from type being compared
	 * so that a binary comparison method can be used.
	 * 
	 */
	@Test
	void testComparator() {
		Record shawn = new Record(1, "Shawn", "Fox");
		Record steve = new Record(5, "Shawn", "Henderson");
		CompareRecordByFirstName comparatorName = new CompareRecordByFirstName();
		assertEquals(0, comparatorName.compare(shawn, steve));
		
		Record clara = new Record(2, "Clara", "Smith");
		Record heather = new Record(0, "Heather", "Bennett");
		assertEquals(-5, comparatorName.compare(clara, heather));
		assertEquals(11, comparatorName.compare(shawn, heather));
		
	}
}