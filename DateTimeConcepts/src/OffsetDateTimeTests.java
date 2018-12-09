import static org.junit.jupiter.api.Assertions.*;

import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;

import org.junit.jupiter.api.Test;

class OffsetDateTimeTests {

	@Test
	void testOfOffsetDateTime() {
		OffsetDateTime odt = OffsetDateTime.of(2018, 12,  25,  8,  0,  0,  0,  ZoneOffset.of("-08:00"));//ZoneId.SHORT_IDS.get("PST"));
		System.out.println(odt.toString());
		assertEquals(ZoneOffset.ofHours(-8), odt.getOffset());
	}

}
