package symbol;
import java.util.ArrayList;
import java.util.Hashtable;

public class MMethod extends MIdentifier implements VarContainer{
	protected Hashtable<String,MVariable> parameters;
	protected Hashtable<String,MVariable> variables;
	protected ArrayList<MType> paramList;
	public MMethod(String _id,MType _scope,String _returnType){
		super(_id,_scope);
		type = _returnType;
		parameters = new Hashtable<>();
		variables = new Hashtable<>();
		paramList = new ArrayList<MType>();
		scope = _scope;
	}
	public MMethod(String _id,MType _scope){
		super(_id,_scope);
		parameters = new Hashtable<>();
		variables = new Hashtable<>();
		paramList = new ArrayList<MType>();
		scope = _scope;
	}
	public void addParameter(MVariable parameter){
		String pid = parameter.id;
		if(parameters.containsKey(pid)){
			System.out.println("Error: Multiple Definition of parameters: \""+pid+"\" in Method: \""+id+"\"");
			System.exit(1);
		}
		parameters.put(pid,parameter);
		paramList.add(parameter);		
	}
	public MVariable getVariable(String id){
		MVariable v = parameters.get(id);
		if(v!=null)
			return v;
		else
			return variables.get(id);		
	}
	@Override
	public void addVariable(MVariable variable) {
		String vid = variable.id;
		if(parameters.containsKey(vid)){
			System.out.println("Error: Confliction Definition of parameters and variables: \""+vid+"\" in Method:"+id);
			System.exit(1);
		}
		else if(variables.containsKey(vid)){
			System.out.println("Error: Multiple Definition of variables: \""+vid+"\" in Method: \""+id+"\"");
			System.exit(1);
		}
		variables.put(vid, variable);		
	}
	
}
