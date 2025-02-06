import java.util.*;
import java.util.stream.*;

public class FlatMapDemo {
    public static void main(String[] args) {
        List<Integer> l1 = Arrays.asList(1,2,3);
        Set<Integer> l2 = new TreeSet<Integer>();
        l2.add(6);
        l2.add(4);
        l2.add(7);
        l2.add(3);
        List<Integer> l3 = Arrays.asList(8,9,10);
        List<Collection<Integer>> list = Arrays.asList(l1,l2,l3);
        System.out.println(list);
    
        List<Integer> flatList = list.stream().flatMap(cl->cl.stream()).map(x->x+x).collect(Collectors.toList());
        System.out.println(flatList);
    }
}
