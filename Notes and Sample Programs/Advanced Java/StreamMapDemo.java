import java.util.*;
import java.util.stream.*;

public class StreamMapDemo {

    public static void main(String[] args) {
        Map<Integer,String> map = new HashMap<Integer,String>();

        map.put(11,"Crystal");
        map.put(12,"Carol");
        map.put(13,"Carina");
        map.put(14,"Ciriaca");
        map.put(34,"Edwin");

        // map.entrySet().stream().map(Map.Entry::getKey).forEach(System.out::println);
        // map.entrySet().stream().map(Map.Entry::getValue).forEach(System.out::println);
        
        // map.values().stream()
        //     .map(value -> "Value: " + value) // Modify or process values
        //     .forEach(System.out::println);

        map.keySet().stream()
            .map(key -> key + ": " + map.get(key)) // Combining key and value
            .forEach(System.out::println);

        // System.out.println("------------------");
        // map.entrySet().stream().filter(me->me.getKey()>30).forEach(System.out::println);
        // System.out.println("------------------");

    }
}