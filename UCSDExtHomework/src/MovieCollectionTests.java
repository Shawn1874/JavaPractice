import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class MovieCollectionTests {

	@Test
	void testDefaultConstructor() {
		MovieCollection myMovies = new MovieCollection();
		assertNotEquals(null, myMovies.getMovies());
	}
}
