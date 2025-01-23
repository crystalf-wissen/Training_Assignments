class A
{
	int x;
	A(int x)
	{
		this.x = x;
	}
	public void finalize()
	{
		System.out.println("Object with x value : "+x+" is getting removed....");
	}
}
public class FinalizeDemo
{
	public static void main(String args[]) throws Exception
	{
		A a1 = new A(10);
		A a2 = new A(20);
		A a3 = new A(30);

		//a2.finalize();

		System.out.println(a1.x);
		System.out.println(a2.x);
		System.out.println(a3.x);

		a2 = null;	
		a1 = null;
		a3 = null;
		System.gc();	//may or maynot be called
		Thread.sleep(100);

	}
}