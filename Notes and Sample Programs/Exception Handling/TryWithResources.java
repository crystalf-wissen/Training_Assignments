class A implements AutoCloseable {
    A() {
        System.out.println("**Allocating resources for class A object**");
    }

    public void process() {
        System.out.println("Performing the required processing on object A");
    }

    public void close() {
        System.out.println("**Releasing resources for class A object**");
    }
}

class B implements AutoCloseable {
    B() {
        System.out.println("**Allocating resources for class B object**");
    }

    public void process() {
        System.out.println("Performing the required processing on object B");
    }

    public void close() {
        System.out.println("**Releasing resources for class B object**");
    }
}

public class TryWithResources {
    public static void main(String[] args) {
        try (A a1 = new A(); B b1 = new B()) {
            a1.process();
            if (true) {
                return; //throw new NullPointerException()
            }
            b1.process();
        }
        //System.out.println(a1);
        System.out.println("Program continues...");
    }
}
