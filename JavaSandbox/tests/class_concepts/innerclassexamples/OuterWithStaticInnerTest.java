package class_concepts.innerclassexamples;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class OuterWithStaticInnerTest {

	@Test
	void test() {
		assertEquals(1, OuterWithStaticInner.NestedStaticDemo.getX());
		assertEquals(2, OuterWithStaticInner.NestedStaticDemo.getY());
		assertEquals(3, OuterWithStaticInner.NestedStaticDemo.z);
	}

}
