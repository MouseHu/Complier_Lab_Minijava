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
	public MType scope;
	protected String register = "";
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
	public void setRegister(String r){
		register = r;
	}
	public String getRegister(){
		return register;
	}
	public void setType(String _type){
		type=_type;
	}
	public static Pair<String,MType> Key(String id,MType scope){
		return new Pair<String,MType>(id,scope);
	}
}
