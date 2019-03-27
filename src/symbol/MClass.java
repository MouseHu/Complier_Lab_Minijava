package symbol;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
public class MClass extends MIdentifier implements VarContainer{
	protected HashMap<String, MMethod> methods;
	protected HashMap<String, MVariable> variables;
	protected MClass parent;
	protected String parentName;
	protected int serialNumber;
	static int classCount = 0;
	public MClass(String _id,  String _parent, MType _scope){
		super(_id,_scope);
		methods = new HashMap<>();
		variables = new HashMap<>();
		parentName = _parent;
		serialNumber = classCount;
		classCount++;
	}

	public void addMethod(MMethod method){
		String mid = method.id;
		if(methods.containsKey(mid)){
			System.out.println("Error: Multiple Method Definition/ Method Overload: \""+mid+"\" in Class:"+this.id+"\"");
			System.exit(1);
		}
		methods.put(mid, method);
	}
	
	public MMethod getMethod(String id){
		return methods.get(id);
	}
	public void setParent(MClass _parent){
		parent = _parent;
	}
	@Override
	public void addVariable(MVariable variable) {
		String vid = variable.id;
		if(variables.containsKey(vid)){
			System.out.println("Error: Multiple Variable Definition: \""+vid+"\" in Class:"+this.id+"\"");
			System.exit(1);
		}
		variables.put(vid, variable);
	}
}
