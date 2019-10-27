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
 * @author Shawn D. Fox
 *
 */
@Command(name = "Lesson5Concurrency", mixinStandardHelpOptions = true, version = "1.0")
public class Driver implements Callable<Void> {

   @Option(names = { "-n", "--num-threads" }, description = "The number of threads to perform the testing with")
   private int numThreads = 8;

   @ArgGroup(exclusive = true)
   SynchronizationOptions synchOptions;
   
   static class SynchronizationOptions {
      @Option(names = { "-rl", "--ReentrantLock" }, 
              description = "If present, the test will be performed using a reentrant lock",
              required = false)
      boolean useReentrantLock = false;

      @Option(names = { "-al", "AtomicLong" }, 
            description = "The number of threads to perform the testing with",
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
         System.out.println(String.format("numThreads: %d, useReentrantLock: %s, useAtomicLong: %s,", numThreads,
               synchOptions.useReentrantLock ? "true" : "false", synchOptions.useAtomicLong ? "true" : "false"));

         // Walk the file tree to generate a list of files to process within each thread.
         Path dir = Paths.get(".");
         var visitor = new JavaFileVisitor();
         Files.walkFileTree(dir, visitor);
         System.out.println(visitor.getJavaFiles().toString());
         System.out.println(visitor.getClassFiles().toString());

         List<CharacterCounter> counters = new ArrayList<>();
         
         for(int i = 0; i < numThreads; ++i) {
            if(synchOptions.useReentrantLock)  {
               counters.add(new LockingCounter(visitor.getJavaFiles(), visitor.getClassFiles()));
            }
            else if(synchOptions.useAtomicLong) {
               counters.add(new AtomicCharacterCounter(visitor.getJavaFiles(), visitor.getClassFiles()));
            }
            else {
               counters.add(new UnsynchronizedCharacterCounter(visitor.getJavaFiles(), visitor.getClassFiles()));
            }
         }

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
         
         System.out.println(String.format("Character count summed from Callable instances: %d", summedCount));
         System.out.println(String.format("Character Count from static field: %d", 
               counters.get(0).getTotalCount()));
      }
      catch (Exception e) {
         e.printStackTrace();
      }

      return null;
   }
}
