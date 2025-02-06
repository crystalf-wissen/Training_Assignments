import java.util.*;
import java.util.function.*;
import java.util.stream.*;

public class StreamDemo{
    public static void main(String args[]){
        List<Integer> list = new ArrayList<Integer>();

        list.add(10);
        list.add(15);
        list.add(20);
        list.add(21);

        List<Integer> olist = list.stream().filter((x)->x%2==0).collect(Collectors.toList());
        System.out.println(olist);

        List<Integer> list2 = list.stream().filter(((Predicate<Integer>)(x)->x%2==0).negate()).collect(Collectors.toList());
        //List<Integer> list2 = list.stream().filter(((x)->x%2!=0)).collect(Collectors.toList());

        System.out.println(list2);

        List<Integer> list3 = list.stream().map(x -> x + x).collect(Collectors.toList());
        System.out.println(list3);

        List<Integer> list4 = list.stream().sorted().limit(3).skip(1).collect(Collectors.toList());
        System.out.println(list4);

        Random random = new Random();
        random.ints().map(x->Math.abs(x)).limit(5).forEach(System.out::println);

        IntStream is = IntStream.rangeClosed(1,10);
        is.forEach(System.out::println);
        System.out.println("------------------");

        OptionalInt result1 = IntStream.range(1,6).reduce((a,b)->a*b);
        System.out.println(result1.getAsInt());

        int res1 = Stream.of(1,2,3).reduce(10, (a,b)->a+b);
        System.out.println(res1);

        int res2 = Stream.of(1,2,3).parallel().reduce(10, (a,b)->a+b, (a,b)->a*b);
        //int res2 = Arrays.asList(1,2,3).parallelStream().reduce(10, (a,b)->a+b, (a,b)->a*b);
        System.out.println(res2);    
        
        IntSummaryStatistics stats = IntStream.range(1,10).summaryStatistics();
            System.out.println(stats.getAverage());
            System.out.println(stats.getCount());
            System.out.println(stats.getMax());
            System.out.println(stats.getMin());
            System.out.println(stats.getSum());
            System.out.println(stats.getClass());
        }
}