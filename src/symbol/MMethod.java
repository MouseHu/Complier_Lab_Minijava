package symbol;
import java.util.Deque;
import java.util.Hashtable;

public class MMethod extends MIdentifier{
	protected VarType returnType;
	protected Hashtable<String,MVariable> parameters;
	protected Hashtable<String,MVariable> variables;
	public MMethod(String _id,VarType _returnType){
		super(_id);
		returnType = _returnType;
		parameters = new Hashtable<>();
	}
	public MVariable getVariable(String id){
		MVariable v = parameters.get(id);
		if(v!=null)
			return v;
		else
			return variables.get(id);
				
	}
}
