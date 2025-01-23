class Custom<T>
{
    public void add(T a,T b)
    {
        System.out.println("Generic Logic...");
    }
}

public class CustomGenerics
{
    public static void main(String args[])
    {
        Custom<String> a1 = new Custom<String>();
        a1.add("Crystal","elaine");

        Custom<Integer> a2 = new Custom<Integer>();
        a2.add(10,20);

        Custom<Double> a3 = new Custom<Double>();
        a3.add(1.5,3.5);
    }
}