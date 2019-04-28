
public class HelloWorld {
	public static void main(String args[]){
		System.out.println("Hello World");
		A aa = new B();
		System.out.println(aa.a());
	}
}

class A{
	public int x =2;
	public int a(){
		return 0;
	}
}

class B extends A{
	public int b(){
		return 2;
	}
}


