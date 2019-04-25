package piglet;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Vector;

import javafx.util.Pair;
import symbol.MType;

public class TranslateTable {
	String outfile;
	public static OutputStreamWriter writer;
	public TranslateTable(HashMap<Pair<String,MType>,MType> symbolTable, String o) throws FileNotFoundException{
		outfile = o;
		writer = new OutputStreamWriter(new FileOutputStream(outfile));
	}
	
}
class Method{
	String methodName;
	String className;
}

class Class{
	Vector vars=new Vector();
	Vector<Method> methods = new Vector();
}
