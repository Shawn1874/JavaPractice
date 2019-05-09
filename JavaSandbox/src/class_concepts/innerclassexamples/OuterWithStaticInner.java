package class_concepts.innerclassexamples;

public class OuterWithStaticInner {
	private static int x = 1;
	private static int y = 2;
	
	static class NestedStaticDemo {
		public static int z = 3;
		
		public static int getX() {
			return x;
		}
		
		public static int getY() {
			return y;
		}
	}
}
