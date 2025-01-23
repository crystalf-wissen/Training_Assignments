public class LangDemo
{
	public static void main(String args[])
	{
		String s1 = "hi";
		System.out.println(s1);
		s1 = "hello";
		System.out.println(s1);
		//s1.toUpperCase();
		//System.out.println(s1);

		s1 = s1.toUpperCase();
		System.out.println(s1);

		StringBuffer sb1 =  new StringBuffer("hi");
		sb1.append("hello");
		System.out.println(sb1);

		//String s1 = "hi";
		String s2 = "hello";
		String s3 = "thanks";
		String s4 = "hi";
		String s5 = "hello";
		String s6 = "thanks";
		String s7 = "hi";
		String s8 = "hello";
		String s9 = "thanks";

	}
}