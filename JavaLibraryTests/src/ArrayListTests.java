import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import java.util.*;

class ArrayListTests {

	@Test
	void testSort() {
		// Evidently Arraylist doesn't support primitive types.  It only supports
		// primitive helpers because it can only be created with something that 
		// inherits the object class.
		ArrayList<Integer> list = new ArrayList<>();
		list.add(5);
		list.add(3);
		list.add(7);
		list.sort(null);
		
		// 3 different ways of doing the comparison.
		assertEquals(new Integer(3), list.get(0));
		assertEquals(5, list.get(1).intValue());
		assertEquals(7, (int) list.get(2));
	}

}
