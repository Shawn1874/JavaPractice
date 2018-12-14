import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

class WildcardsTests {

	@Test
	void testSumOfList() {
		List<Integer> values = new ArrayList<>();
		values.add(5);
		values.add(20);
		values.add(100);
		
		int sum = (int) WildCards.sumOfList(values);
		assertEquals(125, sum);
	}

	@Test
	void testUnboundedWildcard() {
		List<Double> values = Arrays.asList(1.0, 2.0, 3.0);
		WildCards.printStuff(values);
	}
}
