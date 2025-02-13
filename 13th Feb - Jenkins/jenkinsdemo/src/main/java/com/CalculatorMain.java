package com;

class Calculator
{
	public int add(int a, int b) {
		System.out.println("Add : "+(a+b));
		return a+b;
	}
	public int sub(int a, int b) {
		System.out.println("Subtract : "+(a-b));
		return a-b;
	}
	public int mul(int a, int b) {
		System.out.println("Multiply : "+(a-b));
		return a*b;
	}
}
public class CalculatorMain
{
	public static void main(String args[])
	{
		System.out.println("Calculator Operations......");
 
		Calculator c1 = new Calculator();
		c1.add(10, 20);
		c1.sub(10, 20);
		c1.mul(10, 20);
	}
}