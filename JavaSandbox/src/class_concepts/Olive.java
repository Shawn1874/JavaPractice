package class_concepts;

// Interesting way of setting up an enumeration and control how the string conversion works.
// Since the possible values are typically named as constants, but the string representation 
// often needs to be something else we can give the enum a construction and  an overridden
// toString method.
public enum Olive {
	KALAMATA("Kalamata"), PICUAL("Picual"), LIGURIAN("Ligurian");
	
	private String name;
	
	Olive(String name)
	{
		this.name = name;
	}
	
	@Override
	public String toString() {
		return name;
	}
}
