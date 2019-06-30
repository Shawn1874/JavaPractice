/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package concurrency.BasicExecutor;

import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;

/**
 *
 * @author Shawn Fox
 */
public class TaskExecutor {
   public static void main(String[] args) {
      PrintTask task1 = new PrintTask("Task 1");
      PrintTask task2 = new PrintTask("Task 2");
      PrintTask task3 = new PrintTask("Task 3");
      
      System.out.println("Starting Executor");
      
      ExecutorService executorService = Executors.newCachedThreadPool();
      
      executorService.execute(task1);
      executorService.execute(task2);
      executorService.execute(task3);
      
      executorService.shutdown();
      
      System.out.printf("Task started, main ends.%n%n");
   }
}
