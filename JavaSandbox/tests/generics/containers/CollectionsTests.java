package generics.containers;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import class_concepts.Planet;

import org.junit.jupiter.api.Test;

class CollectionsTests {

   @Test
   void testMin() {
      Integer values [] = { 5, 10, 100, 15, 20, 25, 3 };
      ArrayDeque<Integer> container = new ArrayDeque<Integer>(Arrays.asList(values));
      
      assertEquals(3, Collections.min(container));
   }
   
   /**
    * Demonstrate using lambdas and the Comparator function interface while testing the 
    * Collections.Max method
    */
   @Test
   void testMax() {
      
      // Build an array of planets
      Planet[] planets = { Planet.EARTH, Planet.JUPITER, Planet.VENUS, Planet.URANUS };
      
      ArrayList<Planet> thePlanets = new ArrayList<Planet>( Arrays.asList(planets) );
      
      Comparator<Planet> compareMass = (Planet p1, Planet p2) -> 
      { 
         return Double.compare(p1.mass(), p2.mass());
      };
      
      Planet largestMass = Collections.max(thePlanets, compareMass);
      
      assertEquals(Planet.JUPITER, largestMass);
      
      Comparator<Planet> compareRadius = (Planet p1, Planet p2) -> 
      { 
         return Double.compare(p1.radius(), p2.radius());
      };
      
      thePlanets.remove(Planet.JUPITER);
      Planet largestRadius = Collections.max(thePlanets, compareRadius);
      
      assertEquals(Planet.URANUS, largestRadius);
   }
   
   /**
    * Demonstrate that the unmodifiable collection wrappers will not support methods that 
    * would modify the container.  Instead exceptions will be thrown.
    */
   @Test
   void testUnmodifiableList() {
      Planet[] planets = { Planet.EARTH, Planet.JUPITER, Planet.VENUS, Planet.URANUS };
      
      ArrayList<Planet> thePlanets = new ArrayList<Planet>( Arrays.asList(planets) );
      
      List<Planet> roPlanets = Collections.unmodifiableList(thePlanets);
      Planet test = roPlanets.get(0);
      assertEquals(Planet.EARTH, test);

      assertThrows(UnsupportedOperationException.class, () -> roPlanets.add(Planet.MERCURY));
      assertThrows(UnsupportedOperationException.class, () -> roPlanets.add(2, Planet.MERCURY));
      assertThrows(UnsupportedOperationException.class, () -> roPlanets.clear());
      assertThrows(UnsupportedOperationException.class, () -> roPlanets.remove(Planet.JUPITER));
      assertThrows(UnsupportedOperationException.class, () -> roPlanets.set(1, Planet.MERCURY));
   }
}
