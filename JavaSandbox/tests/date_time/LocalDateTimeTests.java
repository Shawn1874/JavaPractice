package date_time;
import static org.junit.jupiter.api.Assertions.*;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import org.junit.jupiter.api.Test;

class LocalDateTimeTests {

	/**
	 * Use the .of method to construct an immutable LocalDate object, and test a few of 
	 * the  static and instance methods to demonstrate its use.
	 */
	@Test
	void testLocalDateOf() {
		LocalDate date = LocalDate.of(2018,  6,  1);
		
		assertEquals(1, date.getDayOfMonth());
		
		// June 1 is a Friday in 2018
		assertEquals(DayOfWeek.FRIDAY, date.getDayOfWeek());
		
		assertEquals(30, date.lengthOfMonth());
		
		assertEquals(365, date.lengthOfYear());
		
		assertFalse(date.isLeapYear());
		
		// Construct another using an enum
		date = LocalDate.of(2018,  Month.DECEMBER,  25);
		assertEquals(31, date.lengthOfMonth());
		// June 1 is a Friday in 2018
		assertEquals(DayOfWeek.TUESDAY, date.getDayOfWeek());
	}
	
	@Test
	void testLocalDateOfYearDay() {
		LocalDate date = LocalDate.ofYearDay(2018,  32);
		assertEquals(1, date.getDayOfMonth()); // should be feb 1st
		assertEquals(Month.FEBRUARY, date.getMonth()); // should be feb 1st
		
		
		LocalDate date2 = LocalDate.ofYearDay(2018,  33);
		assertTrue(date2.isAfter(date));
	}
	
	@Test
	void testLocalDatePlusAndMinus() {

		LocalDate date = LocalDate.of(2018,  6,  1);
		LocalDate september = date.plusMonths(3);
		assertEquals(Month.SEPTEMBER, september.getMonth());
		
		// minus
		LocalDate may = september.minusMonths(4);
		assertEquals(Month.MAY, may.getMonth());
	}
	
	@Test
	void testLocalDateParse() {
		LocalDate christmas = LocalDate.parse("2018-12-25");
		assertEquals(Month.DECEMBER, christmas.getMonth());
		LocalDate myBirthday = LocalDate.parse("1974-01-07");
		assertEquals(Month.JANUARY, myBirthday.getMonth());
		
		// Must have 4 digit year
		assertThrows(DateTimeParseException.class, () -> LocalDate.parse("52-5-1"));
		
		// Must have 2 digit month and day
		assertThrows(DateTimeParseException.class, () -> LocalDate.parse("1952-5-1"));
	}
	
	@Test
	void testLocalDateTimeParseWithFormatter() {
		LocalDateTime midnightIndependenceDay = LocalDateTime.parse("2018-07-04T00:00:00", DateTimeFormatter.ISO_LOCAL_DATE_TIME);
		assertEquals(7, midnightIndependenceDay.getMonthValue());

		LocalDateTime decemberEighth = LocalDateTime.parse("Sat 12 8 2018 0700", DateTimeFormatter.ofPattern("E M d yyyy kkmm"));
		assertEquals(12, decemberEighth.getMonthValue());
		assertEquals(DayOfWeek.SATURDAY, decemberEighth.getDayOfWeek());
		assertEquals(7, decemberEighth.getHour());
		
	}
}
