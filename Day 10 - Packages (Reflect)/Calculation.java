import java.lang.reflect.Method;
import java.util.Scanner;

class Calculator
{
	public int add(int a, int b)
	{
		return a+b;
	}
	public int sub(int a, int b)
	{
		return a-b;
	}
	public int mul(int a, int b)
	{
		return a*b;
	}
	public int div(int a, int b)
	{
		return a/b;
	}
}
 
public class Calculation
{
	public static void main(String args[])
	{
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter first number:");
        int num1 = scanner.nextInt();
        
        System.out.print("Enter second number:");
        int num2 = scanner.nextInt();

        System.out.print("Enter operation (add, sub, mul, div): ");
        String operation = scanner.next().toLowerCase();

        try
        {
            Class c1 = Class.forName("Calculator");
            Object obj = c1.newInstance();
            //System.out.println(obj);

			Method method = c1.getMethod(operation, int.class, int.class);
           
			int result = (int) method.invoke(obj, num1, num2);
            System.out.println("Result: " + result);
        }catch(Exception e)
        {
            System.out.println(e);
        }
		scanner.close();
	}
}