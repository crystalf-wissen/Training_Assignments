class A
{
	public class B
	{
		public class C
		{
			public void demo()
			{
				System.out.println("I am coming from the innermost class method");
			}
		}
	}
    static class D{
        static class E{
            // public static void demo() {
            //     System.out.println("I am coming from the innermost class method");
            // }
            public void demo() {
                System.out.println("I am coming from the innermost class method");
            }
        }
    }
}
public class InnerClasses
{
	public static void main(String args[])
	{
		A a = new A();
		A.B b = a.new B();
		A.B.C c= b.new C(); 
		c.demo();

        new A().new B().new C().demo();

        //A.D.E.demo();
        A.D.E e = new A.D.E();
        e.demo();
        //or
        //A.D.E().demo();

        new A.D().E().demo();
 
	}
}