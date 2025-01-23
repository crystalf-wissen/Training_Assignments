import java.util.*;

public class BitSetDemo
{
	public static void main(String args[])
	{
		BitSet b1 = new BitSet(16);
		BitSet b2 = new BitSet(16);	//0000 0000 0000 0000

		for(int i=0;i<16;i++)
		{
			if(i%3==0)
				b1.set(i);
			if(i%4==0)
				b2.set(i);
		}
		
		System.out.println(b1);
		System.out.println(b2);
	
	}
}