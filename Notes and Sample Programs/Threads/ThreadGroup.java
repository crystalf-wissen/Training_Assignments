class A extends Thread
{
	public void run()
	{
		for(int i=1;i<=20;i++)
		{
			System.out.println(Thread.currentThread().getName()+" : " +i); //can use this to get name but doesnt work for static methods
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
					Thread.sleep(2000);
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
				Thread.sleep(3000);
			}
			catch(Exception e)
			{
				System.out.println(e);
			}
		}
	}
}	

public class ThreadGroup
{
	public static void main(String args[]) throws Exception
	{
		ThreadGroup tg = new ThreadGroup("Demo");

		Thread a1 = new Thread(tg, new A());
		Thread b1 = new Thread(tg, new B());
		Thread c1 = new Thread(tg, new C());

		a1.setName("Rita");
		b1.setName("Geeta");
		c1.setName("Sita");

		//b1.setPriority(8);

		a1.start();
		b1.start();
		c1.start();

		for(int i=1;i<=20;i++)
		{
			System.out.println("****** Main: "+i+" ******");
				if(i==5)
					tg.suspend();
				if(i==10)
					tg.resume();
				if(i==13)
					tg.stop();
				if(i==12)
				{
					System.out.println(" a1 is a live"+a1.isAlive());
					System.out.println(" b1 is a live"+b1.isAlive());
					System.out.println(" c1 is a live"+c1.isAlive());
				}

			Thread.sleep(2000);
		}
		a1.join();
		c1.join();

		System.out.println("------Main Exit------");
	}
}