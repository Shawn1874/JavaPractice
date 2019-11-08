package concurrency;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.util.concurrent.atomic.*;

public class AtomicClassTests {

   @Test
   public void atomicIntegerGetAndIncrement() {
      var value = new AtomicInteger(0);
      assertEquals(0, value.getAndIncrement());
      assertEquals(1, value.getAndIncrement());
      assertEquals(2, value.get());
      assertEquals(2, value.intValue());
   }
   
   @Test
   public void atomicLongAddAndGet() {
      var value = new AtomicLong(100L);
      assertEquals(150L, value.addAndGet(50L));
      assertEquals(200L, value.addAndGet(50L));
      assertEquals(200L, value.get());
      assertEquals(200L, value.longValue());
   }
}
