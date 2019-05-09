package class_concepts.innerclassexamples;

/**
 *  This class demonstrates that an inner class is not constructed automatically if the outer
 *  class is constructed.  Notice that foo has explicitly constructed bar.  If that had not 
 *  been done, then an instance of foo does not automatically contain an instance of bar.
 *  It also demonstrates shadowing.   Notice that getX() explicitly refers to foo's 
 *  x and not the static variable.
 * @author kempo_000
 *
 */
public class foo {
	private int x;
	private bar b;
	
	foo(int x, int y) {
		this.x = x;
		b = new bar(y);
	}
	
	public int getX() {
		return x;
	}
	
	public bar getBar() {
		return b;
	}
	
	public class bar {
		static public final int x = 5;   // allowable because it is final
		int y;
		
		int getY() {
			return y;
		}
		int getX() {
			return foo.this.x;
		}
		
		bar(int value) {
			y = value;
		}
	}
}
