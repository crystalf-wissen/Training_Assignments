public class RuntimeDemo
{
	public static void main(String args[]) throws Exception
	{
		Runtime rt = Runtime.getRuntime();
		System.out.println("Wait and see the magic");
		Thread.sleep(1000);
		Process p1 = rt.exec("mspaint.exe");
		Thread.sleep(5000);
		System.out.println("Lets destory");
		p1.destroy();
		//rt.exec("calc.exe");
		//rt.exec("notepad.exe");
	}
}