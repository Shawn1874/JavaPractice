import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.InputMismatchException;
import java.util.Scanner;

class DescendingOrderExercise {

	public static String newline = System.getProperty("line.separator");
	
	/**
	 * Build an array with the specified length, and fill it with data from the input
	 * stream.
	 * 
	 * @param numberOfValues
	 * @return
	 */
	Integer[] GetIntegers(Integer numberOfValues) {
		int numExpected = numberOfValues.intValue();
		Integer[] values = new Integer[numberOfValues];

		Scanner scanner = new Scanner(System.in);
		scanner.useDelimiter(System.getProperty("line.separator"));

		int numberEntered = 0;

		while (numberEntered < numExpected) {
			try {
				values[numberEntered] = scanner.nextInt();
				++numberEntered;
			} catch (InputMismatchException e) {
				System.out.println("Invalid input! Constrain your input to whole numbers");
				scanner.nextLine();
			}
		}
		scanner.close();
		return values;

	}

	/**
	 * Print the array on one line followed by a system CRLF
	 * @param values
	 */
	void PrintArray(Integer[] values) {
		for (int value : values) {
			System.out.printf("%d ", value);
		}
		System.out.println();
	}

	/**
	 * Sort an array in descending order.
	 * @param values
	 * @return
	 */
	Integer[] SortDescending(Integer[] values) {
		Integer[] sortedValues = new Integer[values.length];
		System.arraycopy(values, 0, sortedValues, 0, values.length);
		Arrays.sort(sortedValues, Collections.reverseOrder());
		return sortedValues;
	}
	
	public static void main(String[] args) {
		if(args.length == 1) {
			DescendingOrderExercise exercise = new DescendingOrderExercise();
			Integer[] values = exercise.GetIntegers(Integer.parseInt(args[0]));
			Integer[] sortedValues = exercise.SortDescending(values);
			
			exercise.PrintArray(values);
			exercise.PrintArray(sortedValues);
		}
	}

	/**
	 * Demonstrates how to use the input and output stream to perform unit testing.
	 */
	@Test
	void TestGetIntegers() {
		// Capture the output
		final ByteArrayOutputStream capturedOut = new ByteArrayOutputStream();
		System.setOut(new PrintStream(capturedOut));
	
		// Stimulate the input
		String data = "3" + newline + "1" + newline + "4" + newline + "9" + newline;
		System.setIn(new ByteArrayInputStream(data.getBytes()));
		
		String[] arr = {"4"};
		DescendingOrderExercise.main(arr);
		System.err.println(capturedOut);
		
		Scanner sc = new Scanner(capturedOut.toString());
		try {
			/* FIRST LINE
			 */
			
			String currentLine = sc.nextLine();
			assertEquals(currentLine, "3 1 4 9 ");
			
			currentLine = sc.nextLine();
			assertEquals(currentLine, "9 4 3 1 ");
		} catch (Exception e) {
			assertThrowableTestFailure(e);
		}
	}

	@Test
	void TestSortDescending() {
		Integer[] values = { 7, 7, 3, 10, 15, 1, 20 };
		Integer[] sortedValues = SortDescending(values);

		for (int i = 0; i < sortedValues.length - 1; ++i) {
			assertTrue(sortedValues[i] >= sortedValues[i + 1]);
		}

		PrintArray(values);
		PrintArray(sortedValues);
	}
	
	/* method name retrieval code courtesy of: http://dev.kanngard.net/Permalinks/ID_20030114224837.html
	 */
	private void assertThrowableTestFailure(Throwable thrown) {
		StackTraceElement stackTraceElements[] =
            (new Throwable()).getStackTrace();
		fail(thrown.getClass().getName() + " encountered! Unable to successfully execute test: " + stackTraceElements[1].toString());
	}

}
