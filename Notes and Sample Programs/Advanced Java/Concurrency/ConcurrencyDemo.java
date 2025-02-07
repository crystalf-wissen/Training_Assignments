import java.util.concurrent.Semaphore;

class Shared{
    static int count = 0;
}

class IncThread extends Thread{
    String name;
    Semaphore sem;
    IncThread(String name,Semaphore sem){
        this.name = name;
        this.sem = sem;
        new Thread(this).start();
    }
    public void run(){
        try {
            System.out.println(name+" is waiting to get the permission...");
            sem.acquire();
            System.out.println(name+" has got the permission...");
            for(int i = 0; i<=5; i++){
                System.out.println(name+" : "+ ++Shared.count);
                Thread.sleep(1000);
            }
            System.out.println(name+" has released the permission...");
            sem.release();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}

class DecThread extends Thread{
    String name;
    Semaphore sem;
    DecThread(String name,Semaphore sem){
        this.name = name;
        this.sem = sem;
        new Thread(this).start();
    }
    public void run(){
        try {
            System.out.println(name+" is waiting to get the permission...");
            sem.acquire();
            System.out.println(name+" has got the permission...");
            for(int i = 0; i<=5; i++){
                System.out.println(name+" : "+ --Shared.count);
                Thread.sleep(100);
            }
            System.out.println(name+" has released the permission...");
            sem.release();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}

public class ConcurrencyDemo {
    public static void main(String[] args) {
        Semaphore sem = new Semaphore(1);
        new IncThread("Carina",sem);
        new DecThread("Crystal",sem);
        new IncThread("Carol",sem);
        new DecThread("Lexie",sem);
        new IncThread("Derek",sem);
    }
}
