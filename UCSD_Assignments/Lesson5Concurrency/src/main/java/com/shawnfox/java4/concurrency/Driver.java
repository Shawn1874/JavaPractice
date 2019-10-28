/**
 * 
 */
package com.shawnfox.java4.concurrency;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import picocli.CommandLine;
import picocli.CommandLine.ArgGroup;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

/**
 * This class is the driver of the application which contains the entry point.  The purpose of the
 * application is to demonstrate 2 options for correctly writing to a variable shared by threads, 
 * and the result of unsynchronized writes.  Specify -h or --help on the command line to learn all
 * of the options for executing the program.
 * 
 * @author Shawn D. Fox
 *
 */
@Command(name = "Lesson5Concurrency", mixinStandardHelpOptions = true, version = "1.0")
public class Driver implements Callable<Void> {

   @Option(names = { "-n", "--num-threads" }, required = false, description = "The number of threads to perform the testing with")
   private int numThreads = 8;
   
   @Option(names = { "-i", "--num-iterations" }, required = false, description = "The number of times to repeat the test")
   private int iterationsRequested = 1;

   @ArgGroup(exclusive = true)
   SynchronizationOptions synchOptions = new SynchronizationOptions();
   
   static class SynchronizationOptions {
      @Option(names = { "-l", "--ReentrantLock" }, 
              description = "If present, the test will be performed using a reentrant lock to manage the shared variable",
              required = false)
      boolean useReentrantLock = false;

      @Option(names = { "-a", "--AtomicLong" }, 
            description = "If present, the test will be performed using an AtomicLong to store the shared variable",
            required = false)
      boolean useAtomicLong = false;
   }

   /**
    * @param args
    */
   public static void main(String[] args) {
      System.exit(new CommandLine(new Driver()).execute(args));
   }

   @Override
   public Void call() throws Exception {
      try {
         
         // print arguments received
         System.out.println("Received the following arguments from the command line");
         System.out.println(String.format("numThreads: %d, useReentrantLock: %s, useAtomicLong: %s,", numThreads,
               synchOptions.useReentrantLock ? "true" : "false", synchOptions.useAtomicLong ? "true" : "false"));

         // Walk the file tree to generate a list of files to process within each thread.
         Path dir = Paths.get(".");
         var visitor = new JavaFileVisitor();
         Files.walkFileTree(dir, visitor);
         System.out.println(visitor.getJavaFiles().toString());
         List<CharacterCounter> counters = new ArrayList<>();
         CountingStrategy strategy;
         
         if(synchOptions.useReentrantLock)  {
            strategy = new LockingCounter();
         }
         else if(synchOptions.useAtomicLong) {
            strategy = new AtomicCharacterCounter();
         }
         else {
            strategy = new UnsynchronizedCharacterCounter();
         }
         
         for(int i = 0; i < numThreads; ++i) {
            
            counters.add(new CharacterCounter(visitor.getJavaFiles(), strategy));
         }

         int iteration = 1;
         
         do {
            var executor = Executors.newFixedThreadPool(numThreads);
            List<Future<Long>> futures = executor.invokeAll(counters);
            long summedCount = futures.stream().mapToLong(f -> {
               try {
                  return f.get();
               }
               catch (InterruptedException | ExecutionException e) {
                  e.printStackTrace();
               }
               return 0;
            }).sum();
            
            System.out.println(String.format("Iteration %d", iteration));
            System.out.println(String.format("Character count summed from Callable instances: %d", summedCount));
            System.out.println(
                  String.format("Character Count from static field: %d", strategy.getTotalCount()));
            strategy.resetTotalCount();
            
         } while (++iteration <= iterationsRequested);
      }
      catch (Exception e) {
         e.printStackTrace();
      }

      return null;
   }
}
