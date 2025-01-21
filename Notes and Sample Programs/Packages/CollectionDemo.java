import java.util.*;

public class CollectionDemo 
{
	public static void main(String args[])
	{
		//proof that vector is dynamic- alloted size 2 but can add more 
		Vector v1 = new Vector(2);

		v1.add("Hello");
		v2.add(new Integer(123));
		v2.add(new Date());
		v2.add(new Thread());
		v2.add(324.13);

		Enumeration e = v1.elements();
		while(e.hasMoreElements()){
			Systen.out.println(e.nextElement());

		}

		System.out.println("---------------------------------------");

		//HashSet obj = new HashSet();
		//TreeSet obj = new TreeSet();
		//LinkedHashSet obj = new LinkedHashSet();
		//ArrayList obj = new ArrayList();
		//LinkedList obj = new LinkedList();

		//PriorityQueue obj = new PriorityQueue();
		ArrayDeque obj = new ArrayDeque();

		obj.add("111");
		obj.add("222");
		obj.add("333");
		obj.add("444");
		//obj.addFirst("000");
		//obj.addLast("999");
		//obj.add(3,middle);

		//These were working because of the root api that calls them

		Iterator i = obj.iterator();
		while(i.hasNext())
		{
			Systen.out.println(i.next());
		}

		System.out.println("---------------------------------------");

		//map doesnt extend collection
		//sync not sync , null not null
		//tree map sorts and gives the order
		//linkedmap gives order as we entered

		//Hashtable mobj = new Hashtable();
		//HashMap mobj = new HashMap();
		//TreeMap mobj = new TreeMap();
		LinkedHashMap mobj = new LinkedHashMap();
		
		mobj.put("111","Crystal");
		mobj.put("222","Carol");

		Set s = mobj.entrySet();
		Iterator mi = s.iterator();

		while(mi.hasNext())
		{
			System.out.println(mi.next());
			Map.entry me = (Map.Entry) mi.next();
			System.out.println("ID: "+mi.getKey());
			System.out.println("Name: "+mi.getValue());
		}

		System.out.println("---------------------------------------");

		

	}
}