import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ConcurrencyDemo2 {
    public static void main(String[] args) throws Exception {
        ExecutorService es = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

        es.submit(new Greet());
        Future<Integer> f1 = es.submit(new Sum(20));
        Future<Integer> f2 = es.submit(new Sum(5));
        Future<Integer> f3 = es.submit(new Sum(10));

        System.out.println("Sum(20): "+f1.get());
        System.out.println("Sum(5): "+f2.get());
        System.out.println("Sum(10): "+f3.get());

        es.shutdown();
    }
}

class Greet implements Runnable{
    public void run(){
        for(int i=1;i<=10;i++)
            System.out.println("From Greet: "+i);
    }
}

class Sum implements Callable<Integer>{
    int val;
    Sum(int val){
        this.val = val;
    }
    public Integer call(){
        int res = 0;
        for(int i = 0; i<=val; i++){
            res = res + i;
        }
        return res;
    }
}
