//converting bytestream into object

import java.io.*;

public class Deserializing 
{
    public static void main(String[] args) throws Exception 
    {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("person.ser"));
        Person p2 = (Person) ois.readObject();
        ois.close();

        System.out.println("Successfully deserialized...");
        System.out.println("Name: " + p2.name);
        System.out.println("Age: " + p2.age);
    }
}