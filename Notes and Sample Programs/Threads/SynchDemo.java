//Implementing through runnable, Creating multiple threads of single object, synchronized method/block

class A implements Runnable
{
	public void run()
	{
		System.out.println(Thread.currentThread().getName()+" is waiting for permission...");
		synchronized(this)
		{
			System.out.println(Thread.currentThread().getName()+" has got the permission...");
			for(int i=0;i<=10;i++)
			{
				System.out.println(Thread.currentThread().getName()+" : "+i);
				try
				{
					Thread.sleep(100);
				}	
				catch(Exception e)
				{
					System.out.println(e);
				}
			}
		}
		
	}
}

public class SynchDemo
{
	public static void main(String args[])
	{
		A a1 = new A();
		Thread t1 = new Thread(a1);
		Thread t2 = new Thread(a1);
		Thread t3 = new Thread(a1);

		t1.start();
		t2.start();
		t3.start();
		
	}
}