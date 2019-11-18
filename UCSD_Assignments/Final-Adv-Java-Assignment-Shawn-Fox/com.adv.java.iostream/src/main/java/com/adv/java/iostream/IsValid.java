/**
 * 
 */
package com.adv.java.iostream;

/**
 * A Functional Interface that defines a single method capable of determining
 * the validity of a value.  This can be the target of a lambda expression.
 * 
 * @author Shawn D. Fox
 *
 */
@FunctionalInterface
public interface IsValid<T> {
   
   /**
    * Applies a test to the argument.
    * 
    * @param value - the value to test
    * @return the boolean result of the test where true = passed and false = failed
    */
   abstract boolean test(T value);
}
