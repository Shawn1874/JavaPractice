package com.shawnfox.java4.concurrency;

import static org.junit.jupiter.api.Assertions.*;

import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.junit.jupiter.api.Test;

class Fibonacci implements Callable<Long> {

   private long nth = 5;

   @Override
   public Long call() throws Exception {
      return fib(nth);
   }

   long fib(long n) {
      
      if (n <= 1)
         return n;
      return fib(n - 1) + fib(n - 2);
   }

   public void setNth(long value) {
      nth = value <= 0 ? 0 : value;
   }

}

class ThreadTests {

   @Test
   void testNoThread() throws Exception {
      var callable = new Fibonacci();
      assertEquals(5, callable.call()); // 5th
      callable.setNth(0);
      assertEquals(0, callable.call());
      callable.setNth(1);
      assertEquals(1, callable.call());
      callable.setNth(9);
      assertEquals(34, callable.call());
   }
   
   @Test
   void testFuture() throws Exception {
      var callable = new Fibonacci();
      callable.setNth(9);
      var executor = Executors.newFixedThreadPool(1);
      Future<Long> result = executor.submit(callable);
      assertEquals(34, result.get());
   }
}
