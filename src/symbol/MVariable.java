package symbol;

public class MVariable extends MIdentifier{
	protected MType parent_scope;
	public MVariable(String _id, MType _parent){
		super(_id);
		parent_scope = _parent;
	}
}
