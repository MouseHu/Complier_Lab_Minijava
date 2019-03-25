package symbol;
interface VarContainer{
	void addVariable(MVariable variable);
}
public class MIdentifier extends MScope{
	protected String id;
	protected MScope scope;
	public MIdentifier(String _id,MScope _scope){
		id = _id;
		scope = _scope;
	}
	
}
