/*
 * The MIT License
 *
 * Copyright 2019 Shawn D. Fox.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package class_concepts.beans;

import javafx.beans.binding.DoubleBinding;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Shawn D. Fox
 */
public class BillPropertyTests
{
   
   public BillPropertyTests()
   {
   }
   
   @Test
   public void testChangedListener() {
      double previous = 185.75;
      double current = 0.0;
      Bill electricBill = new Bill(previous);
      BillChangedListener listener = new BillChangedListener();
      electricBill.amountDueProperty().addListener(listener);
      
      // change the bill and verify that the listener has the previous and current values.
      electricBill.setAmountDue(current);
      assertEquals(previous, listener.getOldValue());
      assertEquals(current, listener.getNewValue());
   }
   
   @Test
   public void testBeanMethods() {
      double value = 185.75;
      Bill electricBill = new Bill(value);
      assertEquals(value, electricBill.getAmountDue());
      
      value = 50.21;
      electricBill.setAmountDue(value);
      assertEquals(value, electricBill.getAmountDue());
      
   }
   
   @Test
   public void testNumberBinding() {
      Bill electric = new Bill(100.0);
      Bill gas = new Bill(75.0);
      
      // Combine the two bills using a binding
      DoubleBinding gasAndElectric = gas.amountDueProperty().add(electric.amountDueProperty());
      assertEquals(175.0, gasAndElectric.getValue());
      assertTrue(gasAndElectric.isValid());
      
      // Verify that the combined value is lazily evaluated
      electric.makePayment(50.0);
      assertTrue(!gasAndElectric.isValid());
      assertEquals(125.0, gasAndElectric.getValue());
      assertTrue(gasAndElectric.isValid());
      
      // Use a change listener to disable lazy evaluation
      BillChangedListener listener = new BillChangedListener();
      gasAndElectric.addListener(listener);
      gas.makePayment(75.0);
      assertEquals(125.0, listener.getOldValue());
      assertEquals(50.0, listener.getNewValue());
      electric.makePayment(50.0);
      assertTrue(gasAndElectric.isValid());
      assertEquals(0.0, gasAndElectric.getValue());
   }
   
   @Test
   public void testThreeBindings() {
      Bill electric = new Bill(100.0);
      Bill gas = new Bill(75.0);
      Bill water = new Bill(50.0);
      DoubleBinding allBills = gas.amountDueProperty().add(electric.amountDueProperty()).add(water.amountDueProperty());
      
      assertEquals(225.0, allBills.getValue());
      gas.makePayment(75.0);
      assertEquals(150.0, allBills.getValue());
      water.makePayment(50.0);
      assertEquals(100.0, allBills.getValue());
      electric.makePayment(100.0);
      assertEquals(0.0, allBills.getValue());
      
   }
}
