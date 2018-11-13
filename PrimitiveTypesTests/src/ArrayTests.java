import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import java.util.Arrays;

class ArrayTests {

	@Test
	void TestBinarySearch() {
		int[] values = {1, 10, 3, 4, 20, 15, 17, 18, 2 };
		assertEquals(9, values.length);
		Arrays.sort(values);
		assertEquals(1, values[0]);
		assertEquals(20, values[values.length - 1]);
		
		// Find 1 and 2 which is in the array.  If found the return value is the 0 
		// based index
		assertEquals(1, Arrays.binarySearch(values, 2));
		assertEquals(0, Arrays.binarySearch(values, 1));
		
		// find the insertion point for 30 which is not within the array.  Since it
		// is not found the return value is the negative of the 1 based index.  It's
		// very interesting that the return value is 1 based for this case.
		assertEquals(-10, Arrays.binarySearch(values, 30));
		assertEquals(-7, Arrays.binarySearch(values, 16));
		
	}

}
