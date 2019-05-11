package spiglet;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.HashMap;

import javafx.util.Pair;
import symbol.*;
import visitor_piglet.*;
import syntaxtree_piglet.*;
import visitor_piglet.GJDepthFirst;

public class SpigletVisitor extends GJDepthFirst<AbstractSPigletResult, AbstractSPigletResult>{
	int indent = 0;
	int label = 0;
	int temp_num = 20;
	public static OutputStreamWriter writer;
	String outfile;
	
	public AbstractSPigletResult visit(HStoreStmt n, AbstractSPigletResult argu) {
		AbstractSPigletResult ret=null;
		n.f0.accept(this,argu);
		SPigletResult exp1=(SPigletResult)n.f1.accept(this,argu);
		if(!exp1.isTemp()) {
			String newTemp=getTemp();
			spiglet_print("MOVE "+newTemp+" "+exp1,indent);
			exp1=new SPigletResult("TEMP "+newTemp,true);
		}
		n.f2.accept(this,argu);
		SPigletResult exp2=(SPigletResult)n.f3.accept(this,argu);
		if(!exp2.isTemp()) {
			String newTemp=getTemp();
			spiglet_print("MOVE "+newTemp+" "+exp1,indent);
			exp2=new SPigletResult("TEMP "+newTemp,true);
		}
		spiglet_print("HSTORE "+exp1+" "+n.f2.f0.tokenImage+" "+exp2,indent);
		return ret;
	}
	
	String getTemp() {
		String s = "TEMP "+temp_num;
		temp_num+=1;
		return s;
	}
	
	String getLabel() {
		String s = "L"+label;
		label+=1;
		return s;
	}
	
	public void spiglet_print(String s, int indent){
		String res = "";
		for(int i=0;i<indent;i++) {
			res+="    ";
		}
		res+=s;
		res+='\n';
		try {
			writer.write(res);
			writer.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return;
	}
}




