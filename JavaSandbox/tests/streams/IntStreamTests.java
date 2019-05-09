package streams;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import java.security.SecureRandom;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class IntStreamTests {

   @Test
   void testRangeClosed() {
      int sum = IntStream.rangeClosed(1, 5).sum();
      assertEquals(15, sum);
   }
   
   @Test
   void testRange() {
      int sum = IntStream.range(1, 5).sum();
      assertEquals(10, sum);
   }
   
   @Test
   void testMap() {
      int sum = IntStream.rangeClosed(1, 10)
                         .map(x -> x * 2)
                         .sum();
      
      assertEquals(110, sum);
   }
   
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
   
   @Test
   void testMapToObject() {
      SecureRandom randomNumbers = new SecureRandom();
      String numbers = randomNumbers.ints(10, 1, 7)  // ints returns an IntStream
            .mapToObj(String::valueOf)   // x -> return String.valueOf(x);
            .collect(Collectors.joining(" ")); // concatenates numbers separated with a space
      assertEquals(19, numbers.length());
      System.out.println(numbers);
   }
}
