
class AnimalAndDog {
	public static void main(String[] args){
		Dog a;
		a = new Dog();
		System.out.println(a.change_id());
		System.out.println(a.get_id());
		System.out.println(a.paramcheck(5,10,15,20));
		//a = new Animal();
		//System.out.println(a.change_id());
		//System.out.println(a.get_id());
	}
}
class Animal{
	int animal_id;
	public int change_id() {
		animal_id = 1;
		return animal_id;
	}
	public int get_id( ) {
		return animal_id;
	}
}
class Dog extends Animal{
	int animal_id;
	public int change_id( ) {
		animal_id = 2;
		return animal_id;
	}
	public int paramcheck(int a, int b,int c,int d) {
		int [] p;
		p = new int[4];
		p[0]=a*2;
		p[1]=10;
		p[2]=15;
		p[3]=20;
		//System.out.println(p[0]);
		System.out.println(p[1]);
		System.out.println(p[2]);
		System.out.println(p[3]);
		//System.out.println(k);
		//System.out.println(b);
		//System.out.println(c);
		//System.out.println(d);
		return 0;
	}
}