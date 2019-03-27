package symbol;

import javafx.util.Pair;

interface VarContainer{
	void addVariable(MVariable variable);
}
public class MIdentifier extends MType{
	protected String id;
	protected String type;
	public MIdentifier(String _id,MType _scope){
		id = _id;
		scope = _scope;
	}
	public MIdentifier(String _id,MType _scope,String _type){
		super(_type);
		id = _id;
		scope = _scope;
		
	}
	

}
