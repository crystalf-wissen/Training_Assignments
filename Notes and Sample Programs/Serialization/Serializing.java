//converting object into bytestream

import java.io.*;

class Person implements java.io.Serializable    //marker interface, doesnt inplement any methods
{
    String name;
    int age;
}

public class Serializing 
{
    public static void main(String[] args) throws Exception 
    {
        Person p1 = new Person();
        p1.name = "Crystal";
        p1.age = 22;

        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("person.ser"));
        oos.writeObject(p1);

        System.out.println("Sucessfully serialized...");
    }
}