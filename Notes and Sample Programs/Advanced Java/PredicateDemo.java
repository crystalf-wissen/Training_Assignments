import java.util.function.*;

public class PredicateDemo {

    public static void main(String[] args) {
        int arr[] = {11,22,33,44,55,66,77,88,99};

        Predicate< Integer> p1 = (x)->x%2==0;
        Predicate< Integer> p2 = (x)->x>50;

        process(arr,p1);
        process(arr,p1.negate());

        process(arr,p2);
        process(arr,p2.negate());

        process(arr,p1.negate().and(p2.negate()));
        process(arr,p1.negate().or(p2));
    }

    public static void process(int arr[], Predicate<Integer> p)
    {
        for(int i=0;i<arr.length;i++)           //for(int x : arr)
        {
            if(p.test(arr[i]))                      //if(p.test(x))
                System.out.println(arr[i]);
        }
        System.out.println("---------------------");
    }
}