/**
 * 
 */
package com.shawnfox.java2.assignment3;

/**
 * @author Shawn D. Fox
 *
 */
@FunctionalInterface
public interface IsValid<T> {
   abstract boolean test(T value);
}
