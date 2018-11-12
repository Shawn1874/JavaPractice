import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class AlbumTest {

	@Test
	void testAlbum() {
		Album pieceOfMind = new Album("Iron Maiden", "Piece Of Mind", 1_555_232_322, new String[] { "Flight of Icarus", "The Trooper", "Revalations" });
		assertEquals("Piece Of Mind", pieceOfMind.getAlbumName());
		assertEquals("Iron Maiden", pieceOfMind.getArtistName());
		assertEquals(1_555_232_322, pieceOfMind.getNumSold());
		assertEquals("Piece Of Mind", pieceOfMind.getAlbumName());
		assertEquals(3, pieceOfMind.getTracks().length);
		
		pieceOfMind.setNumSold(2_000_005);
		assertEquals(2_000_005, pieceOfMind.getNumSold());
	}
}
