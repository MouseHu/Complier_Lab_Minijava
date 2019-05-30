//entends
//parameters checking
class Test{
    public static void main(String[] aaa){

    	int a;
    	int b;
    	a =5;
    	System.out.println(a);

    	
    	System.out.println(new B().getb(1,2,3,4,5,6));

    }
}
class B extends A{
	int b;
	public int getb(int x,int y,int z,int u,int v,int w) {
		System.out.println(x);
		System.out.println(y);
		System.out.println(z);
		System.out.println(u);
		System.out.println(v);
		System.out.println(w);
		
		return b;
	}
}
class A {

	int a;
	public int ma(int d){
		int c;
		c = 1;
		System.out.println(c);
		return c;
	}
}

	

