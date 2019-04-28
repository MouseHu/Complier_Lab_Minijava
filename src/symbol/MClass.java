package symbol;
import java.util.HashMap;

import java.util.Map.Entry;
import javafx.util.Pair;
import jdk.internal.org.objectweb.asm.commons.Method;
public class MClass extends MIdentifier implements VarContainer{
	public HashMap<String, Pair<MMethod,Integer>> methods;
	public HashMap<String, Pair<MVariable,Integer>> variables;
	public MClass parent;
	protected String parentName;
	protected int serialNumber;
	static int classCount = 0;
	int methodSize = 0;
	int variableSize =0;
	public MClass(String _id,  String _parent, MType _scope){
		super(_id,_scope,_id);
		methods = new HashMap<>();
		variables = new HashMap<>();
		parentName = _parent;
		//type = _id;
		serialNumber = classCount;
		classCount++;
	}

	public void addMethod(MMethod method){
		String mid = method.id;
		if(methods.containsKey(mid)){
			System.out.println("Error: Multiple Method Definition/ Method Overload: \""+mid+"\" in Class:"+this.id+"\"");
			System.exit(1);
		}
		methods.put(mid, new Pair<MMethod,Integer>(method,methodSize++));
	}
	
	public MMethod getMethod(String id){
		
		if(methods.get(id) != null) {
			System.out.print("found "+id+" in "+this.id);
			return methods.get(id).getKey();
		}
		else {
			System.out.print("failed to found "+id+" in "+this.id);
			return parent.getMethod(id);
		}
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
		variables.put(vid, new Pair<MVariable,Integer>(variable,variableSize++));
	}
	public int methodSize(){
		int num = 0;
		if(parent!=null){
			num+=parent.methodSize();
			//System.out.println(parentName+" size:"+parent.methodSize+", "+this.id+" size:"+methodSize);
		}
		
		num += methodSize;
		return num;
	}
	public int variableSize(){
		int num = 0;
		if(parent!=null){
			num+=parent.variableSize();
		}
		num += variableSize;
		return num;
	}
	public int methodNumber(String methodName){
		int num = 0;
		Pair<MMethod, Integer> variable = methods.get(methodName);
		if(variable==null){
			assert(parent!=null);
			return parent.variableNumber(methodName);
		}
		if(parent!=null){
			num+=parent.methodSize();
		}
		num+=methods.get(methodName).getValue().intValue();
		return num;
	}
	public int variableNumber(String variableName){
		int num = 0;
		Pair<MVariable, Integer> variable = variables.get(variableName);
		if(variable==null){
			assert(parent!=null);
			return parent.variableNumber(variableName);
		}
		if(parent!=null){
			num+=parent.variableSize();
		}
		num+=variables.get(variableName).getValue().intValue();
		return num;
	}

}
