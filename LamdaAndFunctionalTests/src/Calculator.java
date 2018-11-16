
public class Calculator {
  
    /**
     * Similar to a  delegate in C#.  Defines the functional interface
     * for any type of binary math operation.  The Calculator user must create
     * an instance of something that implements this interface such as a lambda
     * expression or other assignable function implementation.
     *
     */
	@FunctionalInterface
    interface IntegerMath {
        int operation(int a, int b);   
    }
  
    /**
     * Uses the implementation of IntegerMath to perform a calculation, and returns
     * the result.
     * @param a - first operand
     * @param b - second operand
     * @param op - implementation of interface IntegerMath
     * @return - the result of the IntegerMath operation.
     */
    public int operateBinary(int a, int b, IntegerMath op) {
        return op.operation(a, b);
    }
}
