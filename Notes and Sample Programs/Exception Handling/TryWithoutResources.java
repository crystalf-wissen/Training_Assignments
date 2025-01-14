class A{
    A() {
        System.out.println("**Allocating resources for class A object**");
    }

    public void process() {
        System.out.println("Performing the required processing on object A");
    }

    public void release() {
        System.out.println("**Releasing resources for class A object**");
    }
}

public class TryWithoutResources {
    public static void main(String[] args) {
	A a1=null;
        try{
            a1 = new A();
	    a1.process();
	    if(true)
	    	throw new NullPointerException();
            }catch(Exception e){
	    	System.out.println(e);
	    }
	    finally{
	    	a1.release();
	    }
        System.out.println(a1);	//deallocation of a1 not done. Not good coding practice. Use try with resource for I/O
    }
}
