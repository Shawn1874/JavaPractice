Note that eclipse produces a different directory structure compared
to example code from the book.  I started the command from the Lesson8Modules directory.

javac com.horstmann.greet/src/main/java/module-info.java com.horstmann.greet/src/main/java/com/horstmann/greet/Greeter.java com.horstmann.greet/src/main/java/com/horstmann/greet/internal/GreeterImpl.java

Powershell command used to compile the application project
 javac -p com.horstmann.greet/src/main/java ch15.sec05/src/main/java/module-info.java ch15.sec05/src/main/java/com/horstmann/hello/HelloWorld.java
 
 Powershell command used to execute the application project
 java -p "ch15.sec05/src/main/java;com.horstmann.greet/src/main/java" -m ch15.sec05/com.horstmann.hello.HelloWorld