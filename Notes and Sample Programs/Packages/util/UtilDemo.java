import java.util.*;

public class UtilDemo
{
	public static void main(String args[])
	{
		Date d1 = new Date();
		System.out.println(d1);
		System.out.println(d1.getTime());
		d1.setTime(1737436681717L);
		System.out.println(d1);

		Date d2 = new Date();
		System.out.println(d2);
		d2.setMonth(48);
		System.out.println(d2);
		System.out.println(1900+d1.getYear());

		System.out.println("----------------------------");
	
	}
}