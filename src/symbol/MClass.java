package symbol;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Hashtable;
public class MClass extends MIdentifier{
	protected Hashtable<String, MMethod> methods;
	protected Hashtable<String, MVariable> variables;
	protected MType parent;
	public MClass(String _id,  MType _parent){
		super(_id);
		methods = new Hashtable<>();
		variables = new Hashtable<>();
		parent = _parent;
	}
	public boolean addMethod(MMethod method){
		if(methods.contains(method))
			return false;
		methods.put(method.id, method);
		return true;
	}
	public MMethod getMethod(String id){
		return methods.get(id);
	}
}
