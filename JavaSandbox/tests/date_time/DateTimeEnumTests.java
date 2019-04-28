package date_time;
import static org.junit.jupiter.api.Assertions.*;

import java.time.DayOfWeek;
import java.time.Month;

import org.junit.jupiter.api.Test;

class DateTimeEnumTests {
	
	/**
	 * Demonstrates the use of the DayOfWeek enum which is immutable.
	 */
	@Test
	void testDayOfWeekEnum() {
		DayOfWeek monday = DayOfWeek.MONDAY;
		assertEquals(DayOfWeek.THURSDAY, monday.plus(3));
		assertEquals(1, monday.getValue());  // monday wasn't changed by use of .plus(3)
		
		// Convert from int
		DayOfWeek dow = DayOfWeek.of(7);
		assertEquals(DayOfWeek.SUNDAY, dow);

		// Convert from string.  String must be all caps which is odd.  When converted into
		// a string, it would be "Monday".
		dow = DayOfWeek.valueOf("MONDAY");
		assertEquals(DayOfWeek.MONDAY, dow);
		
	}
	
	@Test
	void testMonthEnum() {
		// For most months the min and max length ought to be the same.
		assertEquals(Month.JANUARY.maxLength(), Month.JANUARY.minLength());
		
		// min and max length methods were made for the leap year.
		assertEquals(28, Month.FEBRUARY.minLength());
		assertEquals(29, Month.FEBRUARY.maxLength());
		
		assertEquals(Month.OCTOBER, Month.DECEMBER.firstMonthOfQuarter());
		assertEquals(Month.JULY, Month.AUGUST.firstMonthOfQuarter());
		
		assertEquals(Month.JUNE, Month.of(6));
		assertEquals(Month.APRIL, Month.valueOf("APRIL"));
		
		Month[] months = Month.values();
		assertEquals(Month.JANUARY, months[0]);
		assertEquals(Month.DECEMBER, months[11]);
	}
}
