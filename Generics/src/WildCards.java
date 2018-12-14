import java.util.List;

public class WildCards {
	public static double sumOfList(List<? extends Number> list) {
	    double s = 0.0;
	    for (Number n : list)
	        s += n.doubleValue();
	    return s;
	}
	
	/**
	 * Curiously, this seems to work the same as 'public void printStuff(Iterable<?> stuff)'
	 * Perhaps using an unbounded wildcard is just a shorter syntax. 
	 * @param stuff
	 */
	public static <T> void printStuff(Iterable<T> stuff) {
	  for (Object item : stuff) {
	    System.out.println(item);
	  }
	}
}
