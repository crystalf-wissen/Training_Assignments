import java.util.*;

public class CollectionDemo2 
{
	public static void main(String args[])
	{
		List list = new LinkedList();

		list.add("111");
		list.add("222");
		list.add("333");
		list.add("444");

		System.out.println(list);

		Collections.sort(list);
		System.out.println(list);

		Collections.reverse(list);
		System.out.println(list);

		Collections.swap(list,3,2);
		System.out.println(list);

		Collections.shuffle(list);
		System.out.println(list);
	}
}