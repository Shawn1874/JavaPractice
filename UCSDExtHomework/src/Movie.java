import java.time.*;

/**
 * Movie - a class that contains information about a movie.
 * 
 * @author Shawn D. Fox
 *
 */
public class Movie {

	private String name;
	private String director;
	private int runningTime;
	private double budget;
	private LocalDate releaseDate;
	private int watched;

	/**
	 * The default constructor.
	 */
	public Movie() {
		name = "";
		director = "";
		runningTime = 0;
		budget = 0.0;
		releaseDate = null;
		watched = 0;
	}
	
	/**
	 * This is a constructor that accepts arguments.
	 * @param name the title of the movie (e.g., Ghostbusters, Groundhog Day)
	 * @param director the name of the person who directed the filming of the movie.
	 * @param runningTime - the length of the movie
	 * @param budget - the operating budget for the making of the movie.
	 * @param releaseDate - the date when the movie was first released in America.
	 * @param timesWatched - the approximate number of times that I have watched the movie.
	 */
	public Movie(String name, String director, int runningTime, double budget, LocalDate releaseDate, int timesWatched) {
		this.name = name;
		this.director = director;
		this.runningTime = runningTime;
		this.budget = budget;
		this.releaseDate = releaseDate;
		this.watched = timesWatched;
	}
	
	/**
	 * Increments watched to track the number of times that I've watched the movie.
	 */
	public void watchedItAgain() {
		watched++;
	}
	
	/**
	 * Display information about this object.
	 */
	public void display() {
		StringBuilder toDisplay = new StringBuilder();
		
		toDisplay.append(String.format("%s was directed by %s, and first released %s.%n",
				name, 
				director, 
				releaseDate));
		
		toDisplay.append(String.format("\tIt was produced with a budget of %.1f million dollars,%n\tis %d minutes long, and I've watched it %d times.", 
				budget, 
				runningTime, 
				watched));
		
		System.out.println(toDisplay);
	}
	
	/**
	 * @return the name of the movie
	 */
	public String getName() {
		return name;
	}
	/**
	 * Sets the movie name
	 * @param name - the name of the movie
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * @return the name of the person who directed the movie
	 */
	public String getDirector() {
		return director;
	}
	
	/**
	 * Sets the director field
	 * @param director - the name of the person who directed the movie
	 */
	public void setDirector(String director) {
		this.director = director;
	}
	
	/**
	 * @return the length of the movie in minutes
	 */
	public int getRunningTime() {
		return runningTime;
	}
	
	/**
	 * Sets the running time
	 * @param runningTime - the length of the movie in minutes
	 */
	public void setRunningTime(int runningTime) {
		this.runningTime = runningTime;
	}
	/**
	 * @return the budget in dollars.cents
	 */
	public double getBudget() {
		return budget;
	}
	/**
	 * Sets the budget for making the movie
	 * @param budget - the budget in dollars.cents
	 */
	public void setBudget(double budget) {
		this.budget = budget;
	}
	
	/**
	 * @return the releaseDate as a LocalDate object
	 */
	public LocalDate getReleaseDate() {
		return releaseDate;
	}
	
	/**
	 * Sets the release date of the movie.
	 * @param releaseDate - the releaseDate to set as a LocalDate object
	 */
	public void setReleaseDate(LocalDate releaseDate) {
		this.releaseDate = releaseDate;
	}
	
	/**
	 * @return the number of times that  I've watched it
	 */
	public int getWatched() {
		return watched;
	}
	
	/**
	 * Sets the watched counter
	 * @param watched - the number of times that  I've watched it
	 */
	public void setWatched(int watched) {
		this.watched = watched;
	}
	
}
