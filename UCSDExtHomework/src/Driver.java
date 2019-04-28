
import java.time.LocalDate;

/**
 * This test driver will exercise the Movie class by constructing numerous objects, 
 * adding those to a loop, and displaying the object information to the console.
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
	       Movie[] movies = { 
	    		   new Movie(),
	    		   new Movie(), 
	    		   new Movie("Spaceballs", "Mel Brooks", 96, 22.7, LocalDate.of(1987, 6, 24), 17),
	    		   new Movie("Ghostbusters", "Ivan Reitman", 105, 30.0, LocalDate.of(1984,  6,  7), 55)
	       };
	       
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
	       
	       for(Movie current : movies )  {
	    	   current.display();
	       }
	       
	       int value = (int) 5553322331023003L;
	   }
}
