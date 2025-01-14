class A
{	
	final int x=10;
	final public void abc()
	{
		System.out.println("HI");
	}
}

final class B extends A
{ /*
	final public void abc()
	{
		System.out.println("HELLO");
	} */
}

class C //extends B
{
}

public class FinalDemo
{
	public static void main(String args[])
	{
		A a1= new A();
		System.out.println(a1.x);
		//a1.x=50;
		System.out.println(a1.x+50);
		a1.abc();

		B b1=new B();
		b1.abc();
	}
}