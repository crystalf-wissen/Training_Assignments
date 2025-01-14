abstract class A
{	// abstract int x=10;
	A()
	{
	}
	public abstract void abc();
	public void xyz()
	{
	}
}

abstract class B extends A
{
}

class B extends B
{
	public void abc()
	{
	}
}

public class AbstractDemo
{
	public static void main(String args[])
	{
		//B b1=new B();
	}
}