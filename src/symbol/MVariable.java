package symbol;

public class MVariable extends MIdentifier{
	public MVariable(String _id, MType _scope,String _type){
		super(_id,_scope,_type);
	}
	public MVariable(String _id, MType _scope){
		super(_id,_scope);
	}
}
