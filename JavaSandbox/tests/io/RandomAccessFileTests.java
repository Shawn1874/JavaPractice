package io;

import static org.junit.jupiter.api.Assertions.*;

import java.io.EOFException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

class RandomAccessFileTests {

   @Test
   void testFileNotFound() {
      assertThrows(FileNotFoundException.class, () -> new RandomAccessFile("doesnotexist.txt", "r"));
   }
   
   @Test 
   void testIllegalArgument() {
      assertThrows(IllegalArgumentException.class, () -> new RandomAccessFile("doesnotexist.txt", "r+w"));
   }
   
   @Test
   void testCreateRandomAccessFile() {
      String fileName = "test.dat";
      
      try {
         Files.deleteIfExists(Paths.get(fileName));
      } catch (IOException e) {
         fail(e);
      }
      
      try (RandomAccessFile outStream = new RandomAccessFile(fileName, "rw")) {
         assertEquals(0, outStream.length());
         outStream.writeInt(25);
         assertEquals(4, outStream.length());
         outStream.writeLong(1_000_000_000_000L);
         
         assertEquals(12, outStream.length());
         outStream.seek(0);
         assertEquals(25, outStream.readInt());
         outStream.seek(16);
         assertThrows(EOFException.class, () -> outStream.readByte());
      } catch (IOException e) {
         fail(e);
      }
   }
}
