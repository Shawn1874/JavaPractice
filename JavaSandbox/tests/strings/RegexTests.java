package strings;

import static org.junit.jupiter.api.Assertions.*;

import java.util.regex.Pattern;

import org.junit.jupiter.api.Test;

class RegexTests {

   private final String regex = "0[Xx][0-9A-Fa-f]+";
   
   @Test
   void testPatternMatches() {
      assertTrue(Pattern.matches(regex, "0xa"));
      assertTrue(Pattern.matches(regex, "0XABCDEF90"));
      assertTrue(Pattern.matches(regex, "0x0123abf0"));
      assertFalse(Pattern.matches(regex, "x0123abf0"));
      assertFalse(Pattern.matches(regex, "0123abf0"));
      assertFalse(Pattern.matches(regex, "abcd"));
   }
   
   @Test
   void testCompiledPattern() {
      var p = Pattern.compile(regex);
      var m = p.matcher("0x0123abf0");
      assertTrue(m.matches());
   }

}
