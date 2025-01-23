import java.io.*;

public class RAFDemo {
    public static void main(String args[]) {
        try {
	    
            /*
            RandomAccessFile raf = new RandomAccessFile("abc.txt", "rws");

            raf.seek(5);  // Moves to the 5th byte in the file
            
            // Reads and prints the next line
            System.out.println(raf.readLine());
            System.out.println(raf.readLine());

            // Moves the cursor to the end of the file to append new data
            raf.seek(raf.length());
            raf.writeBytes("Just for Demo");  

            raf.close();

	    */

	    //PrintWriter pw = new PrintWriter(System.out);
	    //PrintWriter pw = new PrintWriter(new File("abc.txt");	here it replaces all the existing text in the file
	    PrintWriter pw = new PrintWriter(new FileWriter("abc.txt",true)); 	//for append mode
	    pw.println("Hello Everyone\n");
	    pw.flush();
	    pw.close();
  
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
