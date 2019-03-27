package symbol;
import java.util.Hashtable;

public class MMethod extends MIdentifier implements VarContainer{
	protected VarType returnType;
	protected Hashtable<String,MVariable> parameters;
	protected Hashtable<String,MVariable> variables;
	public MMethod(String _id,MType _scope,VarType _returnType){
		super(_id,_scope);
		returnType = _returnType;
		parameters = new Hashtable<>();
		variables = new Hashtable<>();
		scope = _scope;
	}
	public void addParameter(MVariable parameter){
		String pid = parameter.id;
		if(parameters.containsKey(pid)){
			System.out.println("Error: Multiple Definition of parameters: \""+pid+"\" in Method: \""+id+"\"");
			System.exit(1);
		}
		parameters.put(id,parameter);
		
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
