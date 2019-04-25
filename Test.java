//entends
//parameters checking
class Test71{
    public static void main(String[] a){
    	//int b;
    	//b=1+1;
    	Test test;
    	Start start;
        //System.out.println(new Start().start());
    	System.out.println(start.start());
    }
}
class C extends B{
	public A zzz(int a ,int b){
		return new A();
	}
}
class A{
	A ab;
	public A zzz(int a,int  b) {
		return new A();
	}
}
class B extends A{
	A ab;

	public int yyy(int b, boolean t){

		boolean x;
		int[] p;
		return 1;
	}
	public A xxx(){
		A a;
		B b;
		int c;
		int d;
		c = b.yyy(c,true);
		a = b.zzz(1,2);
		a=b;
		return new B();
	}
	public A zzz(int a,int x){
		return new A();
	}
}

class Start{
	public int start(){
		System.out.println(0);
		return 0;
	}
}

class Test{
	Test test;
	int j;
	int a;
	public int start(){
		j=test.next(true);
		return 0;
	}
	public int next(boolean i){
		//int i;
		int j;
		return 0;
	}
	
}
