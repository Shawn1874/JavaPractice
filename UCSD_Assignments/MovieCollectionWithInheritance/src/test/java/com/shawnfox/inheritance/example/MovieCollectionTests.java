package com.shawnfox.inheritance.example;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.shawnfox.inheritance.example.MovieCollection;

class MovieCollectionTests {

	@Test
	void testDefaultConstructor() {
		MovieCollection myMovies = new MovieCollection();
		assertNotEquals(null, myMovies.getMovies());
	}
}
