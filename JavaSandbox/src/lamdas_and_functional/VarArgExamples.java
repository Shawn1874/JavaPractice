package lamdas_and_functional;
import java.util.function.*;

/**
 * Demonstrate methods that consume a variable number of arguments.
 */
public class VarArgExamples {
	@SafeVarargs
	public static <E> E accumulate(BinaryOperator<E> operator, E... elements) {
		E total = elements[0];
		
		for(int i = 1; i < elements.length; ++i) {
			total = operator.apply(total,  elements[i]);
		}
		
		return total;
	}
}
