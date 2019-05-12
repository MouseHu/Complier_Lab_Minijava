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
	
	//map to store previous temp
	HashMap<String, String> tempMap=new HashMap<>();
//	HashMap<String, String> labelMap=new HashMap<>();
	public SpigletVisitor(String out) throws FileNotFoundException{
		outfile = out;
		writer = new OutputStreamWriter(new FileOutputStream(outfile));
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
	

	
	/**
	 * Grammar production:
	 * f0 -> "MAIN"
	 * f1 -> StmtList()
	 * f2 -> "END"
	 * f3 -> ( Procedure() )*
	 * f4 -> <EOF>
	 */
	public AbstractSPigletResult visit(Goal n, AbstractSPigletResult argu) {
		spiglet_print("MAIN",indent++);
		n.f1.accept(this,argu);
		spiglet_print("END", --indent);
		n.f3.accept(this,argu);
		return null;
	}
	
	/**
	 * Grammar production:
	 * f0 -> ( ( Label() )? Stmt() )*
	 */
	public AbstractSPigletResult visit(StmtList n, AbstractSPigletResult argu) {
		n.f0.accept(this,argu);
		return null;
	}
	
	/**
	 * Grammar production:
	 * f0 -> Label()
	 * f1 -> "["
	 * f2 -> IntegerLiteral()
	 * f3 -> "]"
	 * f4 -> StmtExp()
	 */
	public AbstractSPigletResult visit(Procedure n, AbstractSPigletResult argu) {
		SPigletResult r = (SPigletResult)n.f0.accept(this,argu);
		spiglet_print(r+" ["+n.f2.f0.toString()+"]",indent++);
		n.f4.accept(this,argu);
		indent--;
		return null;
	}
	/**
	 * Grammar production:
	 * f0 -> NoOpStmt()
	 *       | ErrorStmt()
	 *       | CJumpStmt()
	 *       | JumpStmt()
	 *       | HStoreStmt()
	 *       | HLoadStmt()
	 *       | MoveStmt()
	 *       | PrintStmt()
	 */
	public AbstractSPigletResult visit(Stmt n, AbstractSPigletResult argu) {
		n.f0.accept(this,argu);
		return null;
	}
	/**
	 * Grammar production:
	 * f0 -> "NOOP"
	 */
	public AbstractSPigletResult visit(NoOpStmt n, AbstractSPigletResult argu) {
		spiglet_print(n.f0.toString(),indent);
		return null;
	}
	/**
	 * Grammar production:
	 * f0 -> "ERROR"
	 */
	public AbstractSPigletResult visit(ErrorStmt n, AbstractSPigletResult argu) {
		spiglet_print(n.f0.toString(),indent);
		return null;
	}
	
	/**
	 * Grammar production:
	 * f0 -> "CJUMP"
	 * f1 -> Exp()
	 * f2 -> Label()
	 */
	public AbstractSPigletResult visit(CJumpStmt n, AbstractSPigletResult argu) {
		String exp = ((SPigletResult)n.f1.accept(this,argu)).toString();
		
		String temp = getTemp();
		spiglet_print("MOVE "+temp+" "+exp,indent);
		spiglet_print("CJUMP "+temp,indent);
		n.f2.accept(this,argu);
		return null;
		
	}
	/**
	 * Grammar production:
	 * f0 -> "JUMP"
	 * f1 -> Label()
	 */
	public AbstractSPigletResult visit(JumpStmt n, AbstractSPigletResult argu) {
		spiglet_print("JUMP ",indent);
		n.f1.accept(this,argu);
		return null;
	}
	/**
	 * Grammar production:
	 * f0 -> "HLOAD"
	 * f1 -> Temp()
	 * f2 -> Exp()
	 * f3 -> IntegerLiteral()
	 */
	//temp should NOT print
	public AbstractSPigletResult visit(HLoadStmt n, AbstractSPigletResult argu) {
		SPigletResult temp = (SPigletResult)n.f1.accept(this,argu);
		SPigletResult exp = (SPigletResult)n.f2.accept(this,argu);
		if(!exp.isTemp()) {
			String t = getTemp();
			spiglet_print("MOVE "+t+" "+exp.toString(),indent);
			exp = new SPigletResult(t,true);
		}
		spiglet_print("HLOAD "+temp+" "+exp.toString()+" "+n.f3.f0.toString(),indent);
		return temp;
	}
	/**
	 * Grammar production:
	 * f0 -> "MOVE"
	 * f1 -> Temp()
	 * f2 -> Exp()
	 */
	
	public AbstractSPigletResult visit(MoveStmt n, AbstractSPigletResult argu) {
		SPigletResult temp = (SPigletResult)n.f1.accept(this,argu);
		SPigletResult exp = (SPigletResult)n.f2.accept(this,argu);
		spiglet_print("MOVE "+temp+" "+exp,indent);
		return null;
	}
	/**
	 * Grammar production:
	 * f0 -> "PRINT"
	 * f1 -> Exp()
	 */
	public AbstractSPigletResult visit(PrintStmt n, AbstractSPigletResult argu) {
		SPigletResult exp = (SPigletResult)n.f1.accept(this,argu);
		if(!exp.isSimple()) {
			String t = getTemp();
			spiglet_print("MOVE "+t+" "+exp,indent);
			exp = new SPigletResult(t,true);
		}
		spiglet_print("PRINT "+exp,indent);
		return null;
	}
	/**
	 * Grammar production:
	 * f0 -> "HSTORE"
	 * f1 -> Exp()
	 * f2 -> IntegerLiteral()
	 * f3 -> Exp()
	 */
	//ALWAYS returns null
	public AbstractSPigletResult visit(HStoreStmt n, AbstractSPigletResult argu) {
		AbstractSPigletResult ret=null;
		SPigletResult exp1=(SPigletResult)n.f1.accept(this,argu);
		if(!exp1.isTemp()) {
			String newTemp=getTemp();
			spiglet_print("MOVE "+newTemp+" "+exp1,indent);
			exp1=new SPigletResult(newTemp,true);
		}
		SPigletResult exp2=(SPigletResult)n.f3.accept(this,argu);
		if(!exp2.isTemp()) {
			String newTemp=getTemp();
			spiglet_print("MOVE "+newTemp+" "+exp2,indent);
			exp2=new SPigletResult(newTemp,true);
		}
//		n.f0.accept(this,argu);//assume operator is printed in Operator...
		spiglet_print("HSTORE "+exp1+" "+n.f2.f0.tokenImage+" "+exp2,indent);
		return ret;
	}
	/**
	 * Grammar production:
	 * f0 -> StmtExp()
	 *       | Call()
	 *       | HAllocate()
	 *       | BinOp()
	 *       | Temp()
	 *       | IntegerLiteral()
	 *       | Label()
	 */
	public AbstractSPigletResult visit(Exp n, AbstractSPigletResult argu) {
		AbstractSPigletResult r = n.f0.accept(this,argu);
		return r;
		
	}
	/**
	 * Grammar production:
	 * f0 -> NoOpStmt()
	 *       | ErrorStmt()
	 *       | CJumpStmt()
	 *       | JumpStmt()
	 *       | HStoreStmt()
	 *       | HLoadStmt()
	 *       | MoveStmt()
	 *       | PrintStmt()
	 */
	public AbstractSPigletResult visit(StmtExp n, AbstractSPigletResult argu) {
		String ret = "";
		spiglet_print("BEGIN",indent);
		n.f1.accept(this,argu);
		spiglet_print("RETURN",indent);
		SPigletResult exp1= (SPigletResult)n.f3.accept(this,argu);
		if(!exp1.isSimple()){
			String newTemp=getTemp();
			spiglet_print("MOVE "+newTemp+" "+exp1,indent);
			exp1=new SPigletResult(newTemp,true);
		
		}
		spiglet_print(exp1.toString(),indent);
		spiglet_print("END",indent);
		return new SPigletResult("",false);
		
	}
	/**
	 * Grammar production:
	 * f0 -> "CALL"
	 * f1 -> Exp()
	 * f2 -> "("
	 * f3 -> ( Exp() )*
	 * f4 -> ")"
	 */
	public AbstractSPigletResult visit(Call n, AbstractSPigletResult argu) {
		spiglet_print("CALL",indent++);
		SPigletResult exp1 =(SPigletResult)n.f1.accept(this,argu);
		if(!exp1.isSimple()){
			String newTemp=getTemp();
			spiglet_print("MOVE "+newTemp+" "+exp1,indent);
			exp1=new SPigletResult(newTemp,true);
		}
		spiglet_print(exp1.toString(),indent);
		spiglet_print("(",indent++);
		n.f3.accept(this,argu);// ? node list optional
		spiglet_print(")",--indent);
		indent--;
//		n.f3.accept(this,argu);
		return new SPigletResult("",false);
		
	}
	/**
	 * Grammar production:
	 * f0 -> "HALLOCATE"
	 * f1 -> Exp()
	 */
	public AbstractSPigletResult visit(HAllocate n, AbstractSPigletResult argu) {
		String ret = "";
		spiglet_print("HALLOCATE",indent);
		SPigletResult exp1 = (SPigletResult)n.f1.accept(this,argu); 
		if(!exp1.isSimple()) {
			String newTemp=getTemp();
			spiglet_print("MOVE "+newTemp+" "+exp1,indent);
			exp1=new SPigletResult(newTemp,true);
		}
		spiglet_print(exp1.toString(),indent);
		return new SPigletResult(ret,false);
	}
	/**
	 * Grammar production:
	 * f0 -> Operator()
	 * f1 -> Exp()
	 * f2 -> Exp()
	 */
	public AbstractSPigletResult visit(BinOp n, AbstractSPigletResult argu) {
		String ret = "";
		n.f0.accept(this,argu);
		SPigletResult exp1=(SPigletResult)n.f1.accept(this,argu);
		if(!exp1.isTemp()) {
			String newTemp=getTemp();
			spiglet_print("MOVE "+newTemp+" "+exp1,indent);
			exp1=new SPigletResult(newTemp,true);
		}
		
		SPigletResult exp2=(SPigletResult)n.f2.accept(this,argu);
		if(!exp2.isSimple()) {
			String newTemp=getTemp();
			spiglet_print("MOVE "+newTemp+" "+exp2,indent);
			exp2=new SPigletResult(newTemp,true);
		}
		n.f0.accept(this,argu);//assume operator is printed in Operator...
		spiglet_print(exp1.toString()+" "+exp2,indent);
		return new SPigletResult("",false);
	}
	/**
	 * Grammar production:
	 * f0 -> "LT"
	 *       | "PLUS"
	 *       | "MINUS"
	 *       | "TIMES"
	 */
	public AbstractSPigletResult visit(Operator n, AbstractSPigletResult argu) {
		//this is weird ?
		AbstractSPigletResult r = n.f0.accept(this,argu);
		return r;
		
	}
	/**
	 * Grammar production:
	 * f0 -> "TEMP"
	 * f1 -> IntegerLiteral()
	 */
	public AbstractSPigletResult visit(Temp n, AbstractSPigletResult argu) {
		String temp;
		String temp_piglet = "TEMP "+n.f1.f0.tokenImage; 
		if(tempMap.containsKey(temp_piglet)){
			temp = tempMap.get(temp_piglet);
		}
		else{
			temp = getTemp();
			tempMap.put(temp_piglet, temp);
		}
//		spiglet_print(temp,indent);
		return new SPigletResult(temp,true);
		
	}
	/**
	 * Grammar production:
	 * f0 -> <IDENTIFIER>
	 */
	public AbstractSPigletResult visit(Label n, AbstractSPigletResult argu) {
//		spiglet_print(n.f0.tokenImage,indent);
		return new SPigletResult(n.f0.tokenImage,true);
		
	}
	/**
	 * Grammar production:
	 * f0 -> <INTEGER_LITERAL>
	 */
	public AbstractSPigletResult visit(IntegerLiteral n, AbstractSPigletResult argu) {
//		spiglet_print(n.f0.tokenImage,indent);
		return new SPigletResult(n.f0.tokenImage,true);
		
	}
}




