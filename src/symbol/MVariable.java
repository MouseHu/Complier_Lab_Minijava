package symbol;

public class MVariable extends MIdentifier{
	protected VarType type;
	public MVariable(String _id, MType _scope,VarType _type){
		super(_id,_scope);
		type = _type;
	}
}
