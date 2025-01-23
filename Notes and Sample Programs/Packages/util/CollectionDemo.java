import java.util.*;

public class CollectionDemo 
{
	public static void main(String args[])
	{
		//proof that vector is dynamic- alloted size 2 but can add more 
		Vector<Object> v1 = new Vector<>(2);  // Use generic type for type safety

		v1.add("Hello");
		v1.add(new Integer(123));
		v1.add(new Date());
		v1.add(new Thread());
		v1.add(324.13);

		Enumeration<Object> e = v1.elements();
		while (e.hasMoreElements()) {
			System.out.println(e.nextElement());
		}

		System.out.println("---------------------------------------");

		//HashSet obj = new HashSet();
		//TreeSet obj = new TreeSet();
		//LinkedHashSet obj = new LinkedHashSet();
		//ArrayList obj = new ArrayList();
		//LinkedList obj = new LinkedList();

		//PriorityQueue obj = new PriorityQueue();
		ArrayDeque<String> obj = new ArrayDeque<>();  // Use generic type for type safety

		obj.add("111");
		obj.add("222");
		obj.add("333");
		obj.add("444");
		//obj.addFirst("000");
		//obj.addLast("999");
		//obj.add(3, middle);

		//These were working because of the root api that calls them

		Iterator<String> i = obj.iterator();  // Use generic type for type safety
		while (i.hasNext()) {
			System.out.println(i.next());
		}

		System.out.println("---------------------------------------");

		// map doesnt extend collection
		// sync not sync , null not null
		// tree map sorts and gives the order
		// linkedmap gives order as we entered

		//Hashtable mobj = new Hashtable();
		//HashMap mobj = new HashMap();
		//TreeMap mobj = new TreeMap();
		LinkedHashMap<String, String> mobj = new LinkedHashMap<>();  // Use generic type for type safety
		
		mobj.put("111", "Crystal");
		mobj.put("222", "Carol");

		Set<Map.Entry<String, String>> s = mobj.entrySet();  // Use proper type for Set
		Iterator<Map.Entry<String, String>> mi = s.iterator();  // Use proper type for Iterator

		while (mi.hasNext()) {
			Map.Entry<String, String> me = mi.next();  // Extract entry from iterator
			System.out.println("ID: " + me.getKey());
			System.out.println("Name: " + me.getValue());
		}

		System.out.println("---------------------------------------");
	}
}
