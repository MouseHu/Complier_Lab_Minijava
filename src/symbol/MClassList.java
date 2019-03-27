package symbol;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
public class MClassList extends MType{
	protected HashMap<String,MClass> classList;
	public MClassList(){
		super();
		classList = new HashMap<>();
	}
	public void addClass(MClass mclass, String id){
		if(classList.containsKey(id)){
			// multiple definition
			System.out.println("Error: Multiple Deifinition of Class: \""+id+"\"");
			System.exit(1);
		}
		classList.put(id, mclass);
	}
	public MClass getClass(String id){
		return classList.get(id);
	}
}
