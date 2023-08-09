package map;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class HashMapDemo {
	public static void main(String args[]) {
		Map<Integer,String> map = new HashMap<Integer,String>();
		map.put(1, "madhuri");
		map.put(2, "jaya");         //adding elements into treemap
		map.put(3, "madhu");
		System.out.println("fetch name at key:1 "+map.get(1));  //get element by key
		System.out.println("remove data of key:1 "+map.remove(1)); //remove data by key
		System.out.println("after removing key 1 data.. and updating data at key 1 and 2");
		map.put(1, "kaki");  
		map.put(2, "mad");          //updating data at key 1 and key 2
		for(Entry<Integer,String> e: map.entrySet()) {       //displaying map
			System.out.println("key: "+e.getKey());
			System.out.println("value: "+e.getValue());
		}
	}
}
