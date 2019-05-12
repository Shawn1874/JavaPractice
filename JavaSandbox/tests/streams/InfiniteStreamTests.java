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
   }
   
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
