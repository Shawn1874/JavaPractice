package io;

import static org.junit.jupiter.api.Assertions.*;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

class NIO_ClassTests {

   Path mingw = Paths.get("C:\\Program Files\\Git\\mingw64\\bin");
   
   @Test
   void testGetFileName() {
      assertEquals("bin", mingw.getFileName().toString());
      assertTrue(mingw.endsWith("bin"));
   }
   
   @Test
   void testGetRoot() {
      assertEquals("C:\\", mingw.getRoot().toString());
   }

   /**
    * Root cannot be obtained with Paths.getName.  Index 0 is what is closest to the
    * root.
    */
   @Test
   void testGetName() {
      assertEquals("Program Files", mingw.getName(0).toString());
      assertEquals("mingw64", mingw.getName(2).toString());
      assertThrows(IllegalArgumentException.class, () -> mingw.getName(4));
      assertThrows(IllegalArgumentException.class, () -> mingw.getName(-14));
   }


   /**
    * Root cannot be obtained with Paths.subpath.  Index 0 is what is closest to the
    * root.
    */
   @Test
   void testGetSubPath() {
      assertEquals("Program Files\\Git\\mingw64", mingw.subpath(0, 3).toString());
   }
   
   @Test
   void testGetNameCount() {
      assertEquals(4, mingw.getNameCount());
   }
}
