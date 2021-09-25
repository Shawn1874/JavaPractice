package Strings;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class AnagramSolver {
	
	/**
	 * Finds anagrams and builds a multidimensional array list.  Size of the first dimension
	 * is the number of sets of anagrams found, and 2nd dimension 1 row each for each set of anagrams.
	 * 
	 * Approach:
	 * Foreach element of the input array, compare to the first element of each row to determine if 
	 * it is an anagram of something already within the solution array. The first string of the input 
	 * will be added to the first arraylist since there is nothing yet to compare to.  From that point
	 * loop over all rows and check if the next string is an anagram of the first word in the row. 
	 * Either append the string to an existing row or add it as the first string to a new row.
	 * 
	 * @param possibleAnagrams Arraylist of strings such as "ate", "tea", "note", "tone"
	 * @return multi-dimensional ArrayList<ArrayList<String>> where each row is a set of anagrams
	 */
	public ArrayList<ArrayList<String>> buildAnagramList(ArrayList<String> possibleAnagrams) {
		var solution = new ArrayList<ArrayList<String>>();
		
		// 1st iteration the list will be empty so first string will be added
		// 2nd iteration - if not anagram of any of the first elements of each list then add a new array list with s
		//    as the first element
		for (String a : possibleAnagrams) {
			boolean inserted = false;
			
			for(ArrayList<String> s : solution) {
			
				if(isAnagram(s.get(0).toCharArray(), a.toCharArray())) {
					inserted = true;
					s.add(a);
				}
			}
			
			if(!inserted) {
				var list = new ArrayList<String>();
				list.add(a);
				solution.add(list);
			}
		}
		return solution;
	}
	
	/**
	 * Finds anagrams and builds a HashMap<String, ArrayList<String>>.  
	 * 
	 * Approach:
	 * If the HashMap is empty then the first string will be inserted with an empty ArrayList as the value.
	 * Subsequent strings will be compared to the keys.  If it is an anagram then add it to the ArrayList of
	 * values.
	 * 
	 * @param possibleAnagrams ArrayList of strings such as "ate", "tea", "note", "tone"
	 * @return
	 */
	public HashMap<String, List<String>> buildAnagramMap(List<String> possibleAnagrams) {
		var solution = new HashMap<String, List<String>>();
		
		// 1st iteration the list will be empty so first string will be added
		// 2nd iteration - if not anagram of any of the first elements of each list then add a new array list with s
		//    as the first element
		for (String a : possibleAnagrams) {
			boolean inserted = false;
			
			for(String key : solution.keySet()) {
				
				if(isAnagram(key.toCharArray(), a.toCharArray())) {
					solution.get(key).add(a);
					inserted = true;
				}
			}
			
			if(!inserted) {
				var list = new ArrayList<String>();
				solution.put(a, list);
			}
		}
		return solution;
	}
	
	/**
	 * Tests whether the parameters are anagrams of one another.  An anagram of a word is one that uses the same characters
	 * to form another word.  For instance pear is an anagram of pare, and reap.
	 * 
	 * Approach:
	 * First a short circuit evaluation is performed since the arrays must be the same length to be anagrams.  If the length
	 * matches then sort the two 
	 * @param first char[]
	 * @param second char[]
	 * @return true if the the two words are anagrams and false if these are not anagrams.
	 */
	public boolean isAnagram(char[] first, char[] second) {
		
		if(first.length != second.length) {
			return false;
		}
		else {
			// in order for one to be an anagram of another, each character must occur the same number of times 
			// in the other string
			Arrays.sort(second);
			Arrays.sort(first);
			return (Arrays.compare(first, second) == 0);
		}
	}

}
