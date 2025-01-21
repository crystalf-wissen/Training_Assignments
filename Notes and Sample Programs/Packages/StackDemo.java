import java.util.*;

public class StackDemo
{
	public static void main(String args[])
	{
		Stack s1 = new Stack();
		s1.push("111");
		s1.push("222");
		s1.push("333");

		System.out.println(s1.pop());
		System.out.println(s1.pop());
		System.out.println(s1.pop());

		System.out.println(s1.search("222"));

		//System.out.println(s1.peek());
		//System.out.println(s1.peek());
		//System.out.println(s1.peek());

		//System.out.println(s1.empty());
		

		System.out.println("----------------------------");
	
	}
}