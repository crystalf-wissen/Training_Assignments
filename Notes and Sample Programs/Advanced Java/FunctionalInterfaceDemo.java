@FunctionalInterface
interface I 
{
    public void abc();
    public boolean equals(Object obj);
    public int hashCode();
}

class A implements I
{
    public void abc()
    {
        System.out.println("From A class abc() method");
    }
}

class B 
{
    public void demo()
    {
        System.out.println("Hi everybody");
    }
}

public class FunctionalInterfaceDemo
{
    public static void main(String args[])
    {
        I i1 = new A();
        i1.abc();

        I i2 =  new I()
                {
                    public void abc()
                    {
                        System.out.println("From anonymous class A abc() method");
                    }
                };
        i2.abc();

        B b1 =  new B()
                {
                    public void demo()
                    {
                        System.out.println("Have a good day :)");
                    }
                };

        b1.demo();
        
        //added for security .class file doesn't form
        I i3 = ()-> System.out.println("From lambda expression");
        i3.abc();
    }
}