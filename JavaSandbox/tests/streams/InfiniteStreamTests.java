package streams;

import static org.junit.jupiter.api.Assertions.*;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;

public class InfiniteStreamTests {

   @Test
   void testIterateWithLimit() {
      List<Integer> values = new ArrayList<Integer>();
      
      IntStream.iterate(1, x -> x + 1)
         .limit(10)
         .boxed()
         .forEach(x -> values.add(x));

      assertEquals(10, values.size());
      assertEquals(1, values.get(0));
      assertEquals(2, values.get(1));
      assertEquals(10, values.get(9));
   }
   
   /**
    * Generate is an infinite stream generator so intermediate methods such as limit
    * are used to limit the number of values generated.
    */
   @Test
   void testGenerate() {

      List<Integer> values = new ArrayList<Integer>();
      SecureRandom random = new SecureRandom();
      
      IntStream.generate(() -> random.nextInt())
         .limit(10)
         .forEach(x -> values.add(x));

      assertEquals(10, values.size());
   }
}
