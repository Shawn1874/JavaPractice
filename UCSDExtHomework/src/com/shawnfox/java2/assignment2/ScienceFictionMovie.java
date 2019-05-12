package com.shawnfox.java2.assignment2;
import java.time.LocalDate;
import java.util.Objects;

/**
 * 
 */

/**
 * ScienceFictionMovie extends the concept of a Movie by providing attributes specific 
 * to this particular genre.  One could argue that genre would be better as a part of
 * a movie using a has-a relationship, but the purpose of this exercise is to 
 * demonstrate an understanding of the coding syntax required to create sub-class 
 * types.
 * 
 * @author Shawn D. Fox
 *
 */
public class ScienceFictionMovie extends Movie {


   public enum SubGenre { 
		HardSciFi ("The science portrayed is well researched, and considered plausible."), 
		SoftSciFi ("The science portrayed is not detailed and well researched"), 
		SpaceOpera("Stories characterized by extent of space travel, and amount of time that protagonist spends in a space faring lifestyle"), 
		Punk ("A story with themes typically about fighting the corruption of government");
		
		private String description;
		
		SubGenre(String description) {
			this.description = description;
		}
		
		/**
		 * Return the description of the SubGenre
		 * 
		 */
		String getDescription() {
			return description;
		}
	}
	
	SubGenre subGenre;

	/**
	 * The default constructor
	 */
	public ScienceFictionMovie() {
		super();
		subGenre = SubGenre.HardSciFi;
	}

	/**
	 * @param name the title of the movie (e.g., Star Wars, Star Trek)
	 * @param director the name of the person who directed the filming of the movie.
	 * @param runningTime - the length of the movie
	 * @param budget - the operating budget for the making of the movie.
	 * @param releaseDate - the date when the movie was first released in America.
	 * @param timesWatched - the approximate number of times that I have watched the movie.
	 */
	public ScienceFictionMovie(String name, String director, int runningTime, double budget, LocalDate releaseDate,
			int timesWatched, SubGenre subGenre) {
		super(name, director, runningTime, budget, releaseDate, timesWatched);
		this.subGenre = subGenre;
	}

	/**
	 * Copy Constructor
	 * 
	 * @param rhs
	 */
	public ScienceFictionMovie(ScienceFictionMovie rhs) {
		super(rhs.getName(), rhs.getDirector(), rhs.getRunningTime(), rhs.getBudget(), rhs.getReleaseDate(), rhs.getWatched());
		this.subGenre = rhs.subGenre;
	}
	
	/**
	 * Construct a string representation of an object.
	 * 
	 * @return String
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(super.toString());
		builder.append(String.format("%n\tIt is a science fiction film with subgenre of %s", subGenre));
		
		return builder.toString();
	}
	
	/**
	 * Send a string representation of this object to standard out.  The construction of the string
	 * is done by the toString method.
	 */
	@Override
	public void display() {
		System.out.println(toString());
	}
	
	/**
	 * @return the subGenre
	 */
	public SubGenre getSubGenre() {
		return subGenre;
	}

	/**
	 * Setter for the subGenre field
	 * @param subGenre - the new value
	 */
	public void setSubGenre(SubGenre subGenre) {
		this.subGenre = subGenre;
	}

   /**
    * Generates a hashCode for an object
    */
   @Override
   public int hashCode() {
      final int prime = 31;
      int result = super.hashCode();
      result = prime * result + Objects.hash(subGenre);
      return result;
   }

   /**
    * Compares an instance of a ScienceFictionMovie against another
    */
   @Override
   public boolean equals(Object obj) {
      if (this == obj) {
         return true;
      }
      
      if (!super.equals(obj)) {
         return false;
      }
      
      if (!(obj instanceof ScienceFictionMovie)) {
         return false;
      }
      
      // This deals with other objects where Movie is further sub-typed
      // Since two objects of different types and different data can't generate
      // the same hashcode, this will prevent false positives. It is safe since
      // only the != operator is used.  
      if(hashCode() != obj.hashCode()) {
         return false;
      }
      
      ScienceFictionMovie other = (ScienceFictionMovie) obj;
      return subGenre == other.subGenre;
   }
}
