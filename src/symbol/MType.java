package symbol;

import javafx.util.Pair;

enum VarType{
	Integer,
	Boolean,
	IntArray,
	ClassType
}
public class MType {
	protected String type;
	protected MType scope;
	public MType(){
		type = "";
		scope = null;
	}
	public MType(String _type){
		type = _type;
	}
	public String getType(){
		return type;
	}
	public void setType(String _type){
		type=_type;
	}
	public static Pair<String,MType> Key(String id,MType scope){
		return new Pair<String,MType>(id,scope);
	}
}
