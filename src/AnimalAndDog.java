
public class AnimalAndDog {
	public static void main(String[] args){
		Animal a;
		a = new Dog();
		System.out.println(a.change_id());
		System.out.println(a.get_id());
		a = new Animal();
		System.out.println(a.change_id());
		System.out.println(a.get_id());
	}
}
class Animal{
	int animal_id;
	public int change_id() {
		animal_id = 1;
		return animal_id;
	}
	public int get_id() {
		return animal_id;
	}
}
class Dog extends Animal{
	int animal_id;
	public int change_id( ) {
		animal_id = 2;
		return animal_id;
	}

}