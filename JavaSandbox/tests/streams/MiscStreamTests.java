package streams;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;

import org.junit.jupiter.api.Test;

import class_concepts.Album;

class MiscStreamTests {

   /**
    * Demonstrate static methods to create streams from Arrays class. Additionally
    * demonstrate that streams are not usable after a terminal operation.
    */
   @Test
   void testMatching() {
      DoubleStream doubleStream = Arrays.stream(new double[] { 1.0, 2.0, 3.5, 4.5 });

      boolean result = doubleStream.allMatch(x -> x > 0.0);
      assertTrue(result);

      doubleStream = Arrays.stream(new double[] { 1.0, 2.0, 3.5, 4.5 });
      assertTrue(doubleStream.anyMatch(x -> x >= 4.0));

      doubleStream = Arrays.stream(new double[] { 1.0, 2.0, 3.5, 4.5 });
      assertTrue(doubleStream.noneMatch(x -> x <= 0.0));

      doubleStream = Arrays.stream(new double[] { 1.0, 2.0, 3.5, 4.5 });
      assertFalse(doubleStream.allMatch(x -> x >= 4.0));
   }

   @Test
   void testStringStream() {
      String[] strings = { "Red", "orange", "Yellow", "green", "Blue", "indigo", "Violet" };
      List<String> results = Arrays.stream(strings).filter(s -> s.compareToIgnoreCase("n") < 0).sorted()
            .collect(Collectors.toList());

      assertEquals(3, results.size());
   }

   /**
    * Demonstrate using a stream of a user defined type Album. Demonstrate using an
    * instance of a Predicate<T> as part of a filter, and the use of
    * Collectors.toList() to put the results into a new List object.
    */
   @Test
   void testStreamOfUserDefinedType() {
      Album pieceOfMind = new Album("Iron Maiden", "Piece Of Mind", 5_000_000,
            new String[] { "Flight of Icarus", "The Trooper", "Revalations" });
      Album powerslave = new Album("Iron Maiden", "Powerslave", 4_500_000,
            new String[] { "Powerslave", "Aces High", "Two minutes to midnight" });
      Album diaryOfaMadman = new Album("Ozzy Osbourne", "Diary of a Madman", 4_100_000,
            new String[] { "Over the Mountain", "Flying High Again" });

      List<Album> albums = Arrays.asList(pieceOfMind, powerslave, diaryOfaMadman);

      Predicate<Album> ironMaidenOnly = (a -> a.getArtistName().equals("Iron Maiden"));

      List<Album> ironMaidenAlbums = albums.stream().filter(ironMaidenOnly).collect(Collectors.toList());
      assertEquals(2, ironMaidenAlbums.size());
   }

   @Test
   void testCounter() {
      List<String> names = List.of("Shawn David Fox", "Joe Montanna", "George Herbet Walker Bush");
      long count = names.stream().filter(n -> n.length() > 13).count();
      assertEquals(2, count);

      count = names.parallelStream().filter(n -> n.length() > 13).count();
      assertEquals(2, count);
   }

   /**
    * This test performs a comparison of map vs flatmap.
    */
   @Test
   void testFlatMap() {
      Optional<String> opt = Optional.of("STRING");
      assertEquals("STRING", opt.get());

      assertEquals(
            opt, 
            Optional.of("string").flatMap(s -> Optional.of("STRING")));

      assertEquals(
            Optional.of(opt), 
            Optional.of("string").map(s -> Optional.of("STRING")));
   }
}
