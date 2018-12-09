import static org.junit.jupiter.api.Assertions.*;

import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;

import org.junit.jupiter.api.Test;

class ZonedDateTimeTests {

	// Demonstrate how to construct a ZonedDateTime, specify a ZoneId, and verify the Zone Offset
	// This class has many of the same get and of methods as other date time classes
	@Test
	void testOfYMDHMS() {
		ZonedDateTime zdt = ZonedDateTime.of(2018, 12,  25,  8,  0,  0,  0,  ZoneId.of("America/Los_Angeles"));//ZoneId.SHORT_IDS.get("PST"));
		System.out.println(zdt.toString());
		assertEquals(ZoneOffset.ofHours(-8), zdt.getOffset());
	}

}
