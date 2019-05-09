package class_concepts.innerclassexamples;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class FooTest {

	@Test
	void testFooBarConstructors() {
		foo f = new foo(25, 30);
		assertEquals(25, f.getX());
		assertEquals(30, f.getBar().getY());
		assertEquals(25, f.getBar().getX());
		assertEquals(5, foo.bar.x);
	}
}
