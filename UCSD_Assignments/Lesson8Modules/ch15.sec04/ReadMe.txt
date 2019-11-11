# Used these two commands to perform command line testing.  However it is a modular maven project
# that also produces an executable JAR.
javac ch15.sec04/src/main/java/module-info.java ch15.sec04/src/main/java/com/horstmann/hello/HelloWorld.java
java -p ch15.sec04/src/main/java -m ch15.sec04/com.horstmann.hello.HelloWorld