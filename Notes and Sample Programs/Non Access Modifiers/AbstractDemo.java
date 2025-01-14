abstract class A {    
    // Abstract class can't have instance variables like this (x=10) unless it's final/static.
    // abstract int x = 10; 
   
    A() {
        // Constructor code here, if needed
    }
    
    public abstract void abc(); // Abstract method needs to be implemented by subclasses
    
    public void xyz() {
        // Concrete method
        System.out.println("xyz method in class A");
    }
}

abstract class B extends A {
    // This class does not need to implement abc() yet because it is still abstract
}

class C extends B {  // Class C extends B and implements the abstract methods
    public void abc() {
        System.out.println("abc method implemented in class C");
    }
}

public class AbstractDemo {
    public static void main(String args[]) {
        // You cannot instantiate an abstract class directly, so this would be incorrect:
        // B b1 = new B(); 
        
        // Correct instantiation (of a concrete subclass):
        C c1 = new C();
        c1.abc(); // Calling the implemented method from class C
        c1.xyz(); // Calling the inherited concrete method from class A
    }
}
