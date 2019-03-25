package symbol;

import javafx.util.Pair;

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
	public Pair<String,MScope> Key(){
		return new Pair<String,MScope>(id,scope);
	}
}
