import java.util.*;
import java.lang.reflect.*;

class B
{
	int x;
	B(int x)
	{
		this.x = x;
	}
	public void finalize()
	{
		System.out.println("Object with x value : "+x+" is getting removed....");
	}
}

public class GetClassDemo
{
	public static void main(String args[]) throws Exception
	{
		B b1 = new B(10);
		B b2 = new B(20);
		B b3 = new B(30);


		System.out.println(b1.x);
		System.out.println(b2.x);
		System.out.println(b3.x);
		
		b2 = null;	
		b1 = null;
		b3 = null;

		System.gc();	//may or may not be called
		Thread.sleep(100);

		//Class c1 = b1.getClass();	

		System.out.println("Enter class name: ");
		Class c2 = Class.forName(new Scanner(System.in).next());
		Object obj = c2.newInstance();
		System.out.println(obj);
		Field fields[] = c2.getFields();
		Method methods[] = c2.getMethods();	
		Constructor constructors[] = c2.getConstructors();

		System.out.println(c2.getAccessModifier()+" class "+c2.getName());
		System.out.println("{");

		for(int i=0; i<methods.length;i++)
		{
			System.out.println(methods[i]getAccessModifier()+methods[i].getName()+"()");
		}
		System.out.println("}");
	}
}