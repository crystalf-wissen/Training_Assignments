import java.io.*;

public class KeyInput
{
	public static void main(String args[])
	{
		try
		{
			InputStreamReader isr = new InputStreamReader(System.in);		//converts byte to character stream
			BufferedReader br = new BufferedReader(isr);				//reads data from stream as larger chunks

			System.out.println("Please enter your name: ");
			String name = br.readLine();

			System.out.println("Please enter your age: ");
			int age = Integer.parseInt(br.readLine());				//cant use read() because it only takes the first numeric and returns ascii of it

			System.out.println("Your good name is "+name);
			System.out.println("Your age after 10 years will be "+(age+10));
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
}