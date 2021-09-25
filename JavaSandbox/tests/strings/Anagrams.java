package strings;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import Strings.AnagramSolver;

import org.junit.jupiter.api.Test;

class Anagrams {
	
	private AnagramSolver solver = new AnagramSolver();

	@Test
	void testIsAnagram() {
		char [] first = { 't', 'e', 'a' };
		char [] second = { 'a', 't', 'e' };
		
		
		boolean result = solver.isAnagram(first, second);
		assertTrue(result);
		
		second[1] = 'a';
		result = solver.isAnagram(first, second);
		assertFalse(result);
	}
	
	
	@Test
	void testConstructAnagramMap() {
		
		HashMap<String, List<String>> result;
		var anagrams = Arrays.asList("ate", "tea", "note", "tone");
		result = solver.buildAnagramMap(anagrams);
		assertEquals(2, result.size());
		System.out.println(result.toString());
		
		anagrams = Arrays.asList("pan", "pear", "reap", "stressed", "desserts", "pare", "nap");
		result = solver.buildAnagramMap(anagrams);
		assertEquals(3, result.size());
		System.out.println(result.toString());
		
	}
	
	@Test
	void testConstructAnagramList() {
		
		var anagrams = new ArrayList<String> (Arrays.asList("ate", "tea", "note", "tone"));
		var result = solver.buildAnagramList(anagrams);
		assertEquals(2, result.size());
		System.out.println(result.toString());
		
		anagrams.addAll(Arrays.asList("pan", "pear", "reap", "stressed", "desserts", "pare", "nap"));
		result = solver.buildAnagramList(anagrams);
		assertEquals(5, result.size());
		System.out.println(result.toString());
	}

}
