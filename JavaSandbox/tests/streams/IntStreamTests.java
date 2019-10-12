package streams;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import java.security.SecureRandom;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class IntStreamTests {
   public static Optional<Double> inverse(Double n) {
      return n == 0 ? Optional.empty() : Optional.of(1 / n);
   }
   
   @Test
   void testInverse() {
      var inverted = inverse(0.0);
      assertTrue(inverted.isEmpty());
      inverted = inverse(0.1);
      assertTrue(inverted.isPresent());
      assertEquals(10, inverted.get());
   }

   /**
    * Range closed is an inclusive range
    */
   @Test
   void testRangeClosed() {
      int sum = IntStream.rangeClosed(1, 5).sum();
      assertEquals(15, sum);
   }

   /**
    * Range is an exclusive range
    */
   @Test
   void testRange() {
      int sum = IntStream.range(1, 5).sum();
      assertEquals(10, sum);
   }
   
   /**
    * take while stops taking when the predicate returns true.
    */
   @Test
   void testTakeWhile() {
      assertEquals(10, IntStream.rangeClosed(1, 25).takeWhile(no -> no <= 10).count());
   }
   
   /**
    * drop while starts taking when the predicate returns true.
    */
   @Test
   void testDropWhile() {
      assertEquals(15, IntStream.rangeClosed(1, 25).dropWhile(no -> no <= 10).count());
   }
   
   /**
    * Map  transforms each input
    */
   @Test
   void testMap() {
      int sum = IntStream.rangeClosed(1, 10)
                         .map(x -> x * 2)
                         .sum();
      
      assertEquals(110, sum);
   }
   
   /**
    * Multiple even numbers identified by the filter by 3, and sum the results of the mapped
    * numbers.
    * sum = 2*3 + 4*3 + 6*3 + 8*3 + 10*3 = 6 + 12 + 18 + 24 + 30 = 90
    */
   @Test
   void testFilter() {
      int sum = IntStream.rangeClosed(1,  10)
            .filter(x -> x % 2 == 0)
            .map(x -> x * 3)
            .sum();
      
      assertEquals(90, sum);
      
      // Accomplish the same thing without the filter
      sum = IntStream.rangeClosed(1,  10)
            .map(x -> x % 2 != 0 ? 0 : x * 3)
            .sum();
      
      assertEquals(90, sum);
   }
   
   /**
    * mapToObj is another form of transformation but instead of dealing with primitives,
    * it is able to use a functional interface type to build a reference type from the 
    * input.
    */
   @Test
   void testMapToObject() {
      SecureRandom randomNumbers = new SecureRandom();
      String numbers = randomNumbers.ints(10, 1, 7)  // ints returns an IntStream
            .mapToObj(String::valueOf)   // x -> return String.valueOf(x);
            .collect(Collectors.joining(" ")); // Collects numbers converted to strings joined with a space
      assertEquals(19, numbers.length());
      System.out.println(numbers);
   }
   
   /**
    * min finds the minimum value in an input stream but is not required
    * to return a result.
    */
   @Test
   void testMin() {
      int [] values = { 7, 10, 3, 6, 15 };
      OptionalInt minvalue = IntStream.of(values)
         .min();
      
      assertTrue(minvalue.isPresent());
      assertEquals(3, minvalue.getAsInt());
   }
   
   /** 
    * Demonstrate how reduce can do the same thing as the min function
    * 
    */
   @Test
   void testReduceToMin() {
      int [] values = { 7, 10, 3, 6, 15 };
      OptionalInt minvalue = IntStream.of(values).reduce(Integer::min);
      
      assertTrue(minvalue.isPresent());
      assertEquals(3, minvalue.getAsInt());
      
      // Demonstrate some other methods of OptionalInt and what happens if
      // reduce is given only one element?
      int [] oneElement = { 5 };
      minvalue = OptionalInt.empty();
      assertEquals(0, minvalue.orElse(0));
      
      // Results in 5 since it is the only value in the stream.
      minvalue = IntStream.of(oneElement).reduce(Integer::min);
      assertEquals(5, minvalue.orElse(0));
   }
}
