import java.util.*;
import java.util.function.Consumer;

class A implements Consumer<String>
{
    public void accept(String str)
    {
        System.out.println("Good name is: "+str);
    }
}

class B
{
    public static void writeToFile(String str)
    {
        System.out.println("Writing to csv file: "+str);
    }
}

public class ForEachMethod
{
    public static void main(String[] args) {
        List<String> employees = Arrays.asList("Crystal", "Carol", "Carina");
        employees.forEach(new A());
        employees.forEach(B::writeToFile);
        employees.forEach((arg)-> System.out.println("Printing: "+arg));
    }
}