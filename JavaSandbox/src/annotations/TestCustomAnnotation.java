package annotations;

import java.lang.reflect.Method;

public class TestCustomAnnotation {

   public static void main(String[] args) throws Exception {

      // Test a single-value annoation.
      var h = new HelloWorld();
      Method m = h.getClass().getMethod("sayHello");

      MyAnnotation manno = m.getAnnotation(MyAnnotation.class);
      int numTimes = manno.value();
      for(int i = 0; i < numTimes; ++i) {
         h.sayHello();
      }
      
      var greeting = new Hello();
      m = greeting.getClass().getMethod("sayHelloOnce");
      manno = m.getAnnotation(MyAnnotation.class);
      numTimes = manno.value();
      for(int i = 0; i < numTimes; ++i) {
         greeting.sayHelloOnce();
      }
      
      // Test the CanRun annotation, and invoke the runnable methods.  This is only a marker
      // annotation that indicates whether something can be done.
      AnnotationRunner runner = new AnnotationRunner();
      Method[] methods = runner.getClass().getMethods();

      for (Method method : methods) {
          CanRun annos = method.getAnnotation(CanRun.class);
          if (annos != null) {
              try {
                  method.invoke(runner);
              } catch (Exception e) {
                  e.printStackTrace();
              }
          }
      }
  }
}

// @MyAnnotation(value = 1) : Error since the class is not a defined target!
class HelloWorld {
   /**
    * Demonstrate the use of a custom annotation allowed for a method.
    */
   @MyAnnotation(value = 5)
   public void sayHello() {
      System.out.println("Hello Annotations!");
   }
}

class Hello {
   /**
    * Demonstrate the use of a custom annotation allowed for a method.
    */
   @MyAnnotation
   public void sayHelloOnce() {
      System.out.println("Hello Shawn");
   }
}
