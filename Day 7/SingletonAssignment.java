class A {
    private static A a1 = null;
    A() {
        if(this instanceof B)
            System.out.println("B instance created...");
        else if(this instanceof A && a1==null){
            a1 = this;
            System.out.println("A instance created...");
        }else
            throw new InstanceAlreadyExistsException();
    }
    public static A getObject(){
        if(a1==null)
            a1 = new A();
        return a1;
    }
}

class B extends A {
    static private final B b1 = new B();
    private B() {
    }
    public static B getObject(){
        return b1;
    }
}

class InstanceAlreadyExistsException extends RuntimeException{
    public InstanceAlreadyExistsException(){
    }
    public InstanceAlreadyExistsException(String msg){
        super(msg);
    }
}

public class SingletonAssignment {
	public static void main(String args[]) {
        //A a3 =  new A();
        //A a4 =  new A();
        //System.out.println(a3);
        //System.out.println(a4);

		B b1 = B.getObject();
        A a1 = A.getObject();

        System.out.println(a1);
        System.out.println(b1);

		A a2 = A.getObject();
		B b2 = B.getObject();

        System.out.println(a2);
        System.out.println(b2);

        //B b3 = new B();
        //System.out.println(b3);

        A a5 = new A();
        System.out.println(a5);
	}
}