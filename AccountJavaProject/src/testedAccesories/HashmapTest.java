package testedAccesories;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

public class HashmapTest {

	public static void main(String[] args) {
		 HashMap<Character, Integer> hashMap = new HashMap<>();
		
		 String character = "character";
		 
		 for (int i = 0; i < character.toCharArray().length; i++) {
			      hashMap	.put(character.toCharArray()[i], hashMap.containsKey(character.toCharArray()[i])
			    		   ? hashMap.get(character.toCharArray()[i]) +1: 1);	
		}
		 System.out.println(hashMap.get('c'));
		 for (Entry<Character, Integer> e : hashMap.entrySet())
	            System.out.println("Key: " + e.getKey()
	                               + " Value: " + e.getValue());
			
		}
}

