import java.util.Hashtable;

public class HelloWorld {
	public static void main(String [] args){
		Hashtable<Integer,str> t=new Hashtable<Integer,str>();
		t.put(1,new str(1));
		str s=t.get(1);
		s.a=2;
		System.out.println(t.get(1).a);
	}
}
class str{
	int a;
	public str(int k){
		a=k;
	}
}