package strings;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import class_concepts.tree;

class StringFormatting {

	@Test
	void testFloatPointFormat() {
		
		// %width.precision with code specifying fill with leading zeros at the front
		// 10 total digits with a precision of 5 and leading zeros if necessary.
		String pi = String.format("%010.5f", Math.PI);
		assertEquals(0, pi.compareTo("0003.14159"));
	}
	
	@Test 
	void testEnumToString() {
	   assertTrue(tree.oak.toString().equals("oak"));
	}

}
