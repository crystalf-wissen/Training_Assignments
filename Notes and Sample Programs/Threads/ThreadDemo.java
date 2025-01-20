class A extends Thread
{
	public void run()
	{
		for(int i=1;i<=20;i++)
		{
			System.out.println(Thread.currentThread().getName()+" : " +i); //can use 'this' to get name but doesnt work for static methods
			try
			{
				if(i%2==0)
					Thread.sleep(1000);
			}
			catch(Exception e)
			{
				System.out.println(e);
			}
		}
	}
}	

class B extends Thread
{
	public void run()
	{
		for(int i=1;i<=20;i++)
		{
			System.out.println(Thread.currentThread().getName()+" : " +i);
			try
			{
				if(i%4==0)
					Thread.sleep(500);
			}
			catch(Exception e)
			{
				System.out.println(e);
			}
		}
	}
}	

class C extends Thread
{
	public void run()
	{
		for(int i=1;i<=20;i++)
		{
			System.out.println(Thread.currentThread().getName()+" : " +i);
			try
			{
				Thread.sleep(500);
			}
			catch(Exception e)
			{
				System.out.println(e);
			}
		}
	}
}	

public class ThreadDemo{
	public static void main(String args[]) throws Exception{
		A a1 = new A();
		B b1 = new B();
		C c1 = new C();

		a1.setName("Crystal");
		b1.setName("Not Crystal");
		c1.setName("Maybe Crystal");

		//b1.setPriority(8);

		a1.start();
		b1.start();
		c1.start();

		for(int i=1;i<=20;i++)
		{
			System.out.println("Main: "+i);
			/*
				if(i==5)
					b1.suspend();
				if(i==18)
					b1.resume();
			 */
			Thread.sleep(300);
		}
		a1.join();
		c1.join();

		System.out.println("------Main Exit------");
	}
}