interface I
{
    public void abc();
    public default void xyz()
    {
        System.out.println("Some logic here in I.xyz() method");
    }
    public static void atoz()
    {
        System.out.println("Some logic here in I.atoz() method");
    }
    public void demo()
    {
        System.out.println("With private method in interface");
    }
}

class A implements I
{
    public void abc()
    {
        System.out.println("abc()method from class A");
    }
}

interface X
{
    public default void hello()
    {
        System.out.println("From X interface hello() method");

    }
}

interface Y
{
    public default void hello()
    {
        System.out.println("From Y interface hello() method");

    }
}

class B implements X,Y
{
    public void hello()
    {
        System.out.println("It's the logic of B class");
        X.super.hello();
        Y.super.hello();
    }
}

public interface InterfaceDemo
{
    public static void main(String args[])
    {

    }
}