package io;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

import org.junit.jupiter.api.*;

class NIO_ClassTests {

   private Path mingw = Path.of("C:\\Program Files\\Git\\mingw64\\bin");
   private static Path tempDirectory = Path.of("test");
   private static Path src = Path.of("test/src");
   private static Path dest = Path.of("test/dest");
   
   /**
    * Setup some test directories for testing the copying and moving of files.
    * 
    * @throws IOException
    */
   @BeforeAll
   static void createTestDirectories() throws IOException {
      Files.createDirectory(tempDirectory);

      Files.createDirectory(src);
      Files.createDirectory(dest);   }
   
   /**
    * Remove all of the directories that were setup for testing purposes.
    * @throws IOException
    */
   @AfterAll
   static void removeTestDirectories() throws IOException {
      assertTrue(Files.deleteIfExists(src));
      assertTrue(Files.deleteIfExists(dest));
      assertTrue(Files.deleteIfExists(tempDirectory));
   }
   
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
   
   /**
    * Create an empty file that can be moved.  Use the Path.resolve method to join a target directory
    * with the file name, and move the created file to that directory.  Then confirm that it exists
    * in only the target directory and delete it.
    * @throws IOException
    */
   @Test
   void testMoveFile() throws IOException {
      Path file = Path.of(src + "/names.txt");
      Path created = Files.createFile(file);
      assertTrue(created.endsWith("names.txt"));
      Path target = Files.move(created, dest.resolve(created.getFileName()), StandardCopyOption.REPLACE_EXISTING);
      assertFalse(Files.exists(created));
      assertTrue(Files.deleteIfExists(target));
   }

   
   /**
    * Create an empty file that can be moved.  Use the Path.resolve method to join a target directory
    * with the file name, and copy the created file to that directory.  Then confirm that it exists
    * within both directories.  Then delete the files.
    * @throws IOException
    */
   @Test
   void testCopyFile() throws IOException {
      Path file = Path.of(src + "/names.txt");
      Path created = Files.createFile(file);
      assertTrue(created.endsWith("names.txt"));
      Path target = Files.copy(created, dest.resolve(created.getFileName()), StandardCopyOption.REPLACE_EXISTING);
      assertTrue(Files.deleteIfExists(created));
      assertTrue(Files.deleteIfExists(target));
   }
}
