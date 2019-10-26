/**
 * 
 */
package com.shawnfox.java4.concurrency;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import picocli.CommandLine;
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

   @Option(names = { "-rl",
         "--ReentrantLock" }, description = "If present, the test will be performed using a reentrant lock")
   private boolean useReentrantLock = false;

   @Option(names = { "-al", "AtomicLong" }, description = "The number of threads to perform the testing with")
   private boolean useAtomicLong = false;

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
               useReentrantLock ? "true" : "false", useAtomicLong ? "true" : "false"));

           Path dir = Paths.get("."); var visitor = new JavaFileVisitor();
           Files.walkFileTree(dir, visitor);
           System.out.println(visitor.getJavaFiles().toString());
           System.out.println(visitor.getClassFiles().toString());
           
           var counter = new CharacterCounter(visitor.getJavaFiles(),
           visitor.getClassFiles());
           
           var executor = Executors.newFixedThreadPool(1); Future<Long> future =
           executor.submit(counter);
           System.out.println(String.format("Character count future: %d",
           future.get()));
          
      }
      catch (Exception e) {
         e.printStackTrace();
      }

      return null;
   }
}
