
public class Album {
	public Album(String artistName, String name, int numSold, String[] tracks) {
		this.tracks = tracks;
		this.artistName = artistName;
		this.albumName = name;
		this.numSold = numSold;
	}
	
	public int getNumSold() {
		return numSold;
	}
	public void setNumSold(int numSold) {
		this.numSold = numSold;
	}
	public String getArtistName() {
		return artistName;
	}
	public String getAlbumName() {
		return albumName;
	}
	public String[] getTracks() {
		return tracks;
	}

	// in place, default initialization of fields
	private String artistName = "";
	private String albumName = "";
	private String[] tracks;
	private int numSold = 0;
	
	// could also initialize this way, and it would be copied int
	// every constructor.
	//	{
	//		artistName = "";
	//		albumName = "";
	//	}
}
