import java.io.*;

public class ByteStreamDemo {
    public static void main(String[] args) {
        try (FileOutputStream fos = new FileOutputStream("output.txt")) {
            String message = "Hello, World!";
            fos.write(message.getBytes());  // Writing bytes to the file
            System.out.println("Text written as raw bytes.");
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
