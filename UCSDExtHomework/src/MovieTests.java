import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MovieTests {
	
	Movie ghostbusters = new Movie("Ghostbusters", "Ivan Reitman", 105, 30.0, LocalDate.of(1984,  6,  7), 55);
	Movie spaceballs = new Movie("Spaceballs", "Mel Brooks", 96, 22.7, LocalDate.of(1987, 6, 24), 17);
	ScienceFictionMovie guardians = new ScienceFictionMovie("Guardians of the Galaxy", 
			"James Gunn", 
			122, 
			232.3, 
			LocalDate.of(2014,  8,  1), 
			12, 
			ScienceFictionMovie.SubGenre.SoftSciFi);
	
	@BeforeEach
	void initialize() {
		Movie.resetMovieCounter();
	}

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
	
	@Test
	void testHashCode() {

		String name = "Spaceballs";
		String director = "Mel Brooks";
		int runTime = 96;
		double budget = 22.7;
		LocalDate releaseDate = LocalDate.of(1987, 6, 24);
		int watched = 150;
		
		Movie Spaceballs = new Movie(name, director, runTime, budget, releaseDate, watched);
		
		Movie Ghostbusters = new Movie("Ghostbusters", "Ivan Reitman", 105, 30.0, LocalDate.of(1984,  6,  7), 55);
		Movie gb = new Movie("Ghostbusters", "Ivan Reitman", 105, 30.0, LocalDate.of(1984,  6,  7), 55);
				
	    assertNotEquals(Spaceballs.hashCode(), Ghostbusters.hashCode());
	    assertEquals(gb.hashCode(), Ghostbusters.hashCode());
	}
	
	@Test
	void testCopyConstructor()  {
		Movie gb = new Movie("Ghostbusters", "Ivan Reitman", 105, 30.0, LocalDate.of(1984,  6,  7), 55);
		Movie gb2 = new Movie(gb);
		assertEquals(2, Movie.getMovieCount());
		assertEquals(gb.hashCode(), gb2.hashCode());
	}
	
	@Test
	void testSciFiMovieConstructors() {
		ScienceFictionMovie sciFimovie = new ScienceFictionMovie();
		
		assertEquals(0.0, sciFimovie.getBudget());
		assertEquals(0, sciFimovie.getRunningTime());
		assertEquals("", sciFimovie.getName());
		assertEquals("", sciFimovie.getDirector());
		assertEquals(null, sciFimovie.getReleaseDate());
		assertEquals(ScienceFictionMovie.SubGenre.HardSciFi, sciFimovie.getSubGenre());
	}
	
	@Test
	void testSciFiMovieSetters() {
		this.guardians.setSubGenre(ScienceFictionMovie.SubGenre.SpaceOpera);
		assertEquals(ScienceFictionMovie.SubGenre.SpaceOpera, this.guardians.getSubGenre());
	}
	
	@Test
	void testEqualsContract() {
		
		// Test Reflexiveness (x == x)
		Movie spaceballs = new Movie("Spaceballs", "Mel Brooks", 96, 22.7, LocalDate.of(1987, 6, 24), 17);
		assertTrue(spaceballs.equals(spaceballs));
		
		// Test Symmetry (if x == y then y == x)
		assertTrue(spaceballs.equals(this.spaceballs));
		assertTrue(this.spaceballs.equals(spaceballs));
		
		// Test Transitivity (if x == y && y == z then x == z
		Movie spaceballsTheMovie = new Movie("Spaceballs", "Mel Brooks", 96, 22.7, LocalDate.of(1987, 6, 24), 17);
		assertTrue(spaceballs.equals(this.spaceballs));
		assertTrue(spaceballsTheMovie.equals(this.spaceballs));
		assertTrue(spaceballs.equals(spaceballsTheMovie));
		
		// Test Consistency
		assertTrue(spaceballs.equals(this.spaceballs));
		assertTrue(this.spaceballs.equals(spaceballs));
	}
	
	@Test
	void testEqualsContractWithInheritance() {
		
	}
}
