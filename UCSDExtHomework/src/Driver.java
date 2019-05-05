
import java.time.LocalDate;

/**
 * This test driver will exercise the Movie class by constructing numerous
 * objects, adding those to a loop, and displaying the object information to the
 * console.
 * 
 * @author Shawn D. Fox
 *
 */
public class Driver {

   /**
    * This is the entry point of the application
    * 
    * @param args - an array of strings that can be supplied from the command line
    */
   public static void main(String args[]) {
      String newline = System.getProperty("line.separator");
      
      Movie[] movies = { 
            new Movie(),
            new Movie(), 
            new Movie("Spaceballs", "Mel Brooks", 96, 22.7, LocalDate.of(1987, 6, 24), 17),
            new Movie("Ghostbusters", "Ivan Reitman", 105, 30.0, LocalDate.of(1984,  6,  7), 55),
            new ScienceFictionMovie("Guardians of the Galaxy", "James Gunn", 122, 232.3, 
                  LocalDate.of(2014,  8,  1), 12, ScienceFictionMovie.SubGenre.SpaceOpera),
            new ScienceFictionMovie()
      };
      
      movies[5].setName("The Martian");
      movies[5].setDirector("Ridley Scott");
      movies[5].setRunningTime(142);
      movies[5].setBudget(108.0);
      movies[5].setReleaseDate(LocalDate.of(2015, 10, 2));
      movies[5].setWatched(2);
      ((ScienceFictionMovie) movies[5]).setSubGenre(ScienceFictionMovie.SubGenre.HardSciFi);

      // Demonstrate the equals method by copy constructing a movie
      Movie spaceBalls = new Movie(movies[2]);

      System.out.println(newline + "Demonstrate copy construction, and the equals method");
      if(spaceBalls.equals(movies[2]) && spaceBalls.equals(spaceBalls) && movies[2].equals(spaceBalls) &&
            !spaceBalls.equals(null)) {
         System.out.println("Copy constructor test passed!");
      }
      else {
         System.out.println("Copy constructor test failed!");
      }

      // Demonstrate that the negative test case works when comparing unequal objects
      if(!spaceBalls.equals(movies[0]) && !movies[0].equals(spaceBalls)) {
         System.out.println("not equals test passed!");
      }
      else {
         System.out.println("not equals test failed!");
      }

      movies[0].setName("The Shawshank Redemption");
      movies[0].setDirector("Frank Darrabont");
      movies[0].setRunningTime(142);
      movies[0].setBudget(25);
      movies[0].setWatched(15);
      movies[0].setReleaseDate(LocalDate.of(1994, 9, 10));

      movies[1].setName("Robin Hood");
      movies[1].setDirector("Ridley Scott");
      movies[1].setRunningTime(140);
      movies[1].setBudget(200);
      movies[1].setWatched(7);
      movies[1].setReleaseDate(LocalDate.of(2010, 5, 12));

      movies[1].watchedItAgain();
      

      System.out.println(newline + "Demonstrate polymorphism via the exection of the display method for the" +
                         " two ScienceFictionMovie objects");
      movies[4].display();
      movies[5].display();

      // For assignment 2, demonstrating the toString method and the display method.
      // I chose to implement display in terms of toString so both are demonstrated
      // even though display is directly called.
      System.out.println(newline + "Demonstrate polymorphism via the exection of the display method on each object");
      for(Movie current : movies )  {
         current.display();
      }

      // Demonstrate toString method for one of the objects just to show that an object 
      // can be used where a string is expected
      System.out.println(newline + "Demonstration of toString method for two of the objects");
      System.out.println(movies[1]);
      System.out.println(movies[4]);

      System.out.println(newline + "Demonstrate the reference count.  The total count includes the copy constructed object");
      Movie.displayReferenceCounter();
   }
}
