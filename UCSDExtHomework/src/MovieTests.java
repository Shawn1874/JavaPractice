import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

class MovieTests {

	@Test
	void testDefaultConstruction() {
		Movie emptyMovie = new Movie();
		
		assertEquals(0.0, emptyMovie.getBudget());
		assertEquals(0, emptyMovie.getRunningTime());
		assertEquals("", emptyMovie.getName());
		assertEquals("", emptyMovie.getDirector());
		assertEquals(null, emptyMovie.getReleaseDate());
	}
	
	@Test
	void testArgumentConstructor() {
		String name = "Spaceballs";
		String director = "Mel Brooks";
		int runTime = 96;
		double budget = 22.7;
		LocalDate releaseDate = LocalDate.of(1987, 6, 24);
		int watched = 150;
		
		Movie Spaceballs = new Movie(name, director, runTime, budget, releaseDate, watched);
		
		assertEquals(budget, Spaceballs.getBudget());
		assertEquals(runTime, Spaceballs.getRunningTime());
		assertEquals(name, Spaceballs.getName());
		assertEquals(director, Spaceballs.getDirector());
		assertEquals(releaseDate, Spaceballs.getReleaseDate());
		assertEquals(watched, Spaceballs.getWatched());
		
		Spaceballs.watchedItAgain();
		assertEquals(watched + 1, Spaceballs.getWatched());
	}

}
