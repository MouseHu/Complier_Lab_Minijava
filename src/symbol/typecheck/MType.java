package symbol.typecheck;

import javafx.util.Pair;

public class MType {
	protected String type;
	public MType scope;
	public String register="";
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
	public void setRegister(String reg) {
		register = reg;
	}
	public String getRegister() {
		return register;
	}
	public static Pair<String,MType> Key(String id,MType scope){
		return new Pair<String,MType>(id,scope);
	}
}
