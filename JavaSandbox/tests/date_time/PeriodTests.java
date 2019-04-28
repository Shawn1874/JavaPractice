package date_time;
import static org.junit.jupiter.api.Assertions.*;

import java.time.Clock;
import java.time.LocalDate;
import java.time.Month;
import java.time.Period;

import org.junit.jupiter.api.Test;

class PeriodTests {

	@Test
	void testOfWeeks() {
		Period twoWeeks = Period.ofWeeks(2);
		assertEquals(14, twoWeeks.getDays());
		assertEquals(0, twoWeeks.getMonths());
		assertEquals(0, twoWeeks.getYears());
		
		Period oneMonth = twoWeeks.plusDays(14);
		assertEquals(28, oneMonth.getDays());
		assertEquals(0, oneMonth.getMonths());
		assertEquals(0, oneMonth.getYears());
		
		// Interesting.  Can't have days because there is not enough context to determine 
		// if it is a leap year so remember that the period itself is not a date.  
		Period oneYear = Period.ofYears(1);
		assertEquals(0, oneYear.getDays());
		assertEquals(0, oneYear.getMonths());
		assertEquals(1, oneYear.getYears());
		
	}
	
	/**
	 * The between method is probably one of the most useful methods for the period class 
	 * because it works with anything that implements the temporal interface.
	 */
	@Test
	void testBetween() {
		LocalDate januaryFirst = LocalDate.of(2018, Month.JANUARY, 1);
		LocalDate birthday = LocalDate.of(2018, Month.JANUARY, 22);
		Period daysUntilBirthday = Period.between(januaryFirst,  birthday);
		assertEquals(21, daysUntilBirthday.getDays());
		System.out.println(daysUntilBirthday.toString());
	}
	
	/**
	 * Demonstrate the use of Period.of to build a period with all three components
	 * Demonstrate the use of methods such as plusyears, plus to create a new period from an 
	 * existing period.  Demonstrate the use of toTotalMonths to calculate total months on 
	 * a period with both years and months.
	 */
	@Test
	void testOfYearsMonthsDays() {
		Period period = Period.of(2, 11, 15);
		assertEquals(15, period.getDays());
		assertEquals(11, period.getMonths());
		assertEquals(2, period.getYears());
		
		// Notice how adding one year doesn't affect the number of days and months
		// In a period the three components of it are independent.  If I wanted to 
		// subtract some number months and days I'd have to build another  period.
		Period oneMoreYear = period.plusYears(1);
		assertEquals(15, oneMoreYear.getDays());
		assertEquals(11, oneMoreYear.getMonths());
		assertEquals(3, oneMoreYear.getYears());
		
		Period oneMonthOneDay = Period.of(0,  1,  1);
		Period plusOneMonthAndDay = period.plus(oneMonthOneDay);
		assertEquals(16, plusOneMonthAndDay.getDays());
		assertEquals(12, plusOneMonthAndDay.getMonths());
		assertEquals(2, plusOneMonthAndDay.getYears());
		assertEquals(36, plusOneMonthAndDay.toTotalMonths());
		System.out.println(plusOneMonthAndDay.toString());
	}

}
