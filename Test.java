//entends
//parameters checking
class Test{
    public static void main(String[] aaa){
    	int b;
    	System.out.println(new B().getb());
    }
}
class B extends A{
	int b;
	public int getb() {
		c = new int[10];
		c[2]=1;
		System.out.println(c[2]);
		//x =3;
		//System.out.println(x);
		return 0;
	}
}
class A {
	int b;
	int []c;
	int x;
	public int getb() {
		return b;
	}
}
