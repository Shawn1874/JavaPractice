
public class Record implements Comparable<Record>{
	
	Record(int id, String first, String last) {
		identifier = id;
		firstName = first;
		lastName = last;
	}
	
	int identifier;
	String firstName;
	String lastName;
	
	@Override
	public int compareTo(Record rhs) {
		return (identifier < rhs.identifier ? -1 : identifier > rhs.identifier ? 1 : 0);
	}
}
