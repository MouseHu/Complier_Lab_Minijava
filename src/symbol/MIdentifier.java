package symbol;

import javafx.util.Pair;

interface VarContainer{
	void addVariable(MVariable variable);
}
public class MIdentifier extends MType{
	protected String id;
	protected MType scope;
	public MIdentifier(String _id,MType _scope){
		id = _id;
		scope = _scope;
	}
	public Pair<String,MType> Key(){
		return new Pair<String,MType>(id,scope);
	}
}
