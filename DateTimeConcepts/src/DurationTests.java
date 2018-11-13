import static org.junit.jupiter.api.Assertions.*;

import java.time.Duration;

import org.junit.jupiter.api.Test;

class DurationTests {

	@Test
	void testToDays() {
		Duration duration = Duration.ofHours(48);
		assertEquals(2, duration.toDays());
	}
	
	@Test
	void testToMinutes() {
		Duration duration = Duration.ofHours(48);
		assertEquals(60*48, duration.toMinutes());
	}
	
	@Test
	void testToHours() {
		Duration duration = Duration.ofDays(4);
		assertEquals(4*24, duration.toHours());
	}
	
	@Test
	void testWithSeconds() {
		Duration duration = Duration.ofSeconds(24);
		Duration withNanos = duration.plusNanos(5);
		
		// At this point withNanos is 24 seconds, 5 nano seconds
		assertEquals(5, withNanos.getNano());
		assertEquals(24, withNanos.getSeconds());
		
		// Let's see how the withSeconds function works.  Curiously I can specify that I want to keep
		// only N seconds from the original duration but leave the nano seconds portion unchanged.
		// The reverse is possible using Duration.withNanos().
		Duration seconds = withNanos.withSeconds(5);
		assertEquals(5, seconds.getNano());
		assertEquals(5, seconds.getSeconds());
		
	}
	
	@Test
	void testEquals() {
		Duration first = Duration.ofDays(2);
		Duration second = Duration.ofDays(2);
		assertEquals(first, second);
		
		Duration third = Duration.ofHours(48);
		assertEquals(first, third);
		
	}
}
