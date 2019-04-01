import java.util.Objects;

public class Record implements Comparable<Record>{

	Record(int id, String first, String last) {
		identifier = id;
		firstName = first;
		lastName = last;
	}
	
	/* Build a new hashcode
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return Objects.hash(firstName, identifier, lastName);
	}

	/* Checks whether this is logically equivalent to another instance
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		
		if (!(obj instanceof Record)) {
			return false;
		}
		Record other = (Record) obj;
		return Objects.equals(firstName, other.firstName) && identifier == other.identifier
				&& Objects.equals(lastName, other.lastName);
	}
	
	@Override
	/**
	 * Implemented in terms of equals to ensure that compareTo is consistent with equals.
	 * If objects are not equal then the difference returned is either the difference
	 * between identifiers or the difference between last then first name.
	 */
	public int compareTo(Record rhs) {
		int result;
		
		if(this.equals(rhs)) {
			result = 0;
		}
		else{
			if(this.identifier != rhs.identifier) {
				result = this.identifier - rhs.identifier;
			}
			else if(!this.lastName.equals(rhs.lastName)){
				result = this.lastName.compareTo(rhs.lastName);
			}
			else{
				result = this.firstName.compareTo(rhs.firstName);
			}
		}
		
		return result;
	}
	
	private int identifier;
	/**
	 * @return the identifier
	 */
	public int getIdentifier() {
		return identifier;
	}

	/**
	 * @param identifier the identifier to set
	 */
	public void setIdentifier(int identifier) {
		this.identifier = identifier;
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	@Override
	public String toString() {
		return String.format("%s %s : %d", firstName, lastName, identifier);
	}

	private String firstName;
	private String lastName;
	
}
