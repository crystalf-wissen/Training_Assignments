import java.util.concurrent.*;

class A {
	public void print1to10()
	{
		for(int i=1; i<=10; i++)
		{
			System.out.println("Range : "+i);
		}
	}
}
class B {
	public void evenTill50()
	{
		for(int i=0; i<=50; i+=2)
		{
			System.out.println("Even : "+i);
		}
	}
}
class C {
    public void fibonacciFrom1to1000() {
        int sum;
        int a = 0;
        int b = 1;
        System.out.println("Fib: " + a); 
        System.out.println("Fib: " + b); 
        
        while (true) {
            sum = a + b;
            if (sum > 1000) {
                break;
            }
            System.out.println("Fib: " + sum);
            a = b;
            b = sum;
        }
    }
}

class Multithreading
{
	public static void main(String args[])
	{
        A a = new A();
        B b = new B();
        C c = new C();

        ExecutorService executorService = Executors.newFixedThreadPool(3);

        // executorService.execute(() -> a.print1to10());

        // Thread threadA = new Thread(new Runnable() {
        //     public void run() {
        //         a.print1to10();
        //     }
        // });
        // threadA.start();

        executorService.execute(new Runnable() {
            public void run() {               
                a.print1to10();
            }
        });

        executorService.execute(new Runnable() {
            public void run() {               
                b.evenTill50();
            }
        });

        executorService.execute(new Runnable() {
            public void run() {               
                c.fibonacciFrom1to1000();
            }
        });

        executorService.shutdown();

        // Runnable r1 = () -> new A().print1to10();;
        // Runnable r2 = () -> new B().evenTill50();;
        // Runnable r3 = () -> new C().fibonacciFrom1to1000();;
 
        // Thread a1 = new Thread(r1);
        // Thread b1 = new Thread(r2);
        // Thread c1 = new Thread(r3);
 
        // a1.start();
        // b1.start();
        // c1.start();

        new Thread(new A()::print1to10).start();
        new Thread(new B()::evenTill50).start();
        new Thread(new C()::fibonacciFrom1to1000).start();
	}
}
 