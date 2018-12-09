import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.time.Month;
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
	
	@Test
	void testCombineLocalDateTimeAndZone() {
		// Leaving from San Francisco on July 20, 2013, at 7:30 p.m.
		LocalDateTime leaving = LocalDateTime.of(2013, Month.JULY, 20, 19, 30);
		ZoneId leavingZone = ZoneId.of("America/Los_Angeles"); 
		ZonedDateTime departure = ZonedDateTime.of(leaving, leavingZone);
		System.out.println(departure.toString());
		
		// Create  new object with same time but CST.  Notice that this really does just change the zone
		// and doesn't adjust the time to the new zone.
		ZonedDateTime chicago = leaving.atZone(ZoneId.of("America/Chicago"));
		assertEquals(19, chicago.getHour());
		
		// The withZoneSameInstant actually causes the time to change.  Instead of simply changing the zone itself
		// it is adjusting the instant of time based on temporal between the zones.
		ZoneId arrivingZone = ZoneId.of("Asia/Tokyo"); 
		ZonedDateTime arrival = departure.withZoneSameInstant(arrivingZone);
		System.out.println(arrival.toString());
		assertEquals(11, arrival.getHour());
	}

}
