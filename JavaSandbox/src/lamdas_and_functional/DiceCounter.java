package lamdas_and_functional;

import java.security.SecureRandom;
import java.util.function.Function;
import java.util.stream.Collectors;

public class DiceCounter {
   
   /**
    * Roll a die 60 million times, and summarize the results.  Box each result
    * into an Integer.  Group by identity which is the value of each roll (1-6)
    * creating a Map<Integer, Long>.  Then Print the result.
    * @param args
    */
   public static void main(String[] args) {
      System.out.printf("%-6s%s%n", "Face", "Frequency");
      
      SecureRandom generator = new SecureRandom();
      
      generator.ints(60_000_000, 1, 7)
         .boxed()  
         .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
         .forEach((face, frequency) ->
            System.out.printf("%-6s%s%n", face, frequency));
   }
}
