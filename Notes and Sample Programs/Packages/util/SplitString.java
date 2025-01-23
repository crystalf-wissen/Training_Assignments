import java.util.*;

public class SplitString
{
	public static void main(String args[])
	{
		String str = "Raju|25|30000:Tester_9321519032";
		StringTokenizer st = new StringTokenizer(str, "|;:_");
		int count = st.countTokens();

		for(int i=0;i<count;i++)
		{
			System.out.println(st.nextToken());
		}
	}
}