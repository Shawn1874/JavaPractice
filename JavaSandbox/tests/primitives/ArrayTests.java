package primitives;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

class ArrayTests {

	@Test
	void TestBinarySearch() {
		int[] values = {1, 10, 3, 4, 20, 15, 17, 18, 2 };
		// sorted { 1, 2, 3, 4, 10, 15, 17, 18, 20 }
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
	
	/**
	 * Demonstrate how to use the System.Array copy method.
	 * Demonstrate how to make a string from a char array or vice versa.
	 */
	@Test
	void TestArrayCopy() {
		char [] words = "Unfortunately".toCharArray();
		char[] word = new char[9];
		System.arraycopy(words, 2, word, 0, 9);
		String copy = new String(word);
		assertTrue("fortunate".equals(copy));
	}
	
	@SuppressWarnings("unused")
	@Test
	void TestArrayException() {
		int[] values = new int[5];
		assertEquals(0, values[0]);
		assertThrows(IndexOutOfBoundsException.class, () -> { int value = values[17]; });
	}
	
	/**
	 * Since arrays can't be resized copyOf can build a new copy with the new length
	 *  truncating or increasing the size of the array with default values.
	 */
	@SuppressWarnings("unused")
	@Test
	void TestCopyOf() {
		int [] values = {1, 2, 3, 4, 5};
		
		values = Arrays.copyOf(values, 7);
		assertEquals(values.length, 7);
		assertEquals(values[5], 0);
		assertEquals(values[6], 0);
		
		int [] shortendedValues = Arrays.copyOf(values, 2);
		assertEquals(shortendedValues.length, 2);
		assertEquals(shortendedValues[0], 1);
		assertEquals(shortendedValues[1], 2);
		assertThrows(IndexOutOfBoundsException.class, () -> { int value = shortendedValues[3]; });
	}
	
	/**
	 * Evidently it is possible to create an array with zero elements.
	 */
	@Test
	void TestZeroElementArray() {
	    int navigationalDeflector[] = {};
	    assertEquals(0, navigationalDeflector.length);
	}
	
	/**
	 * Demonstrate initialization of a multi-dimensional array with same number of
	 * rows and columns, and a reassignment of one row to a new array with a different
	 * number of columns.
	 */
	@Test
	void TestTwoDimensionalArrays() {
		// First test.  Replace the second row with a 4 element array
		int [][] progressions = {{1, 4, 5}, {2, 5, 1}};
	    progressions[1] = new int[]{1, 4, 6, 5};
	    assertEquals(progressions[1][3], 5);
	    	    
	    String multiPass[][] = {{"Leeloo", "Korbin"},{"Zorg", "Ruby"}};
	    String answer = multiPass[1][0];
	    assertEquals("Zorg", answer);
	    
	    for(int i = 0; i < progressions.length; ++i) 
	    	for(int j = 0; j < progressions[i].length; ++j)
	    		progressions[i][j] = i + j;

	    assertEquals(1, progressions[0][1]);
	    assertEquals(2, progressions[1][1]);
	    assertEquals(3, progressions[1][2]);
	}
	
	/**
	 * This test demonstrates that each row of a multi-dimensional array 
	 * can be any number of elements.  
	 */
	@Test
	void TestJaggedArrayExample() {
		int[][] b = new int[2][]; // create 2 rows
		b[0] = new int[5]; // create 5 columns for row 0
		b[1] = new int[3]; // create 3 columns for row 1
		assertEquals(5, b[0].length);
		assertEquals(3, b[1].length);
		assertEquals(0, b[1][2]);
	}
	
	@Test
	void TestArrayHelperSum() {
		assertEquals(10, ArrayHelper.sum(5, 5));
		assertEquals(100, ArrayHelper.sum(5, 50, 25, 7, 13));
	}
}
