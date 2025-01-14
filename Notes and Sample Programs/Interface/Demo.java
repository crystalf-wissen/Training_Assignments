interface I{
}

class A //implements I{
	public void doSomething(){
		if(this instanceof I)
			System.out.println("Yes")
		else
			throw new NullPointerException();
	}
}

class Demo{
	public static void main(String args[]){
		A a1=new A();
		a1.doSomething();
	}
}