The projects were compiled as Maven modules with the following structure
Final-Adv-Java-Assignment-Shawn-Fox
   -- com.adv.java.application
   -- com.adv.java.iostream
   -- com.adv.java.streams
   -- com.adv.java.xml
   
com.adv.java.application generates and executable JAR that depends on the other modules, so
the best way to execute the program is as follows.
1) build the parent POM within Final-Adv-Java-Assignment-Shawn-Fox which builds all modules
2) Run the executable within Final-Adv-Java-Assignment-Shawn-Fox\com.adv.java.application\target
3) java -jar AdvJavaFinalProject-jar-with-dependencies.jar
4) It will work with or without an existing .xml file in the same location of the JAR.
5) Feel free to use the employees.xml checked into om.adv.java.application which I produced during my testing
6) If you start the program with no input xml, then you can enter employees which generates the .xml file, then
   run the program again to see that the data is persisted.