package toKanga;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Comparator;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.TreeSet;
import java.util.Vector;

import spiglet.visitor.*;
import spiglet.syntaxtree.*;
class environ{
	String funcName;
	int position=0;
	int regNum=0;
	LinkedList<Table> tables;
	public environ(LinkedList<Table> t,String s,int pos,int n) {
		position=pos;
		regNum=n;
		funcName=s;
		tables=t;
	}
}
public class KangaVisitor extends GJVoidDepthFirst<environ>{
	public static OutputStreamWriter writer;
	String outfile;
	int indent;
	public KangaVisitor(String out) throws FileNotFoundException{
		outfile = out;
		writer = new OutputStreamWriter(new FileOutputStream(outfile));
		indent=0;
	}
	public void kpln(String s, int indent){
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
	public void kp(String s, int indent){
		String res = "";
		for(int i=0;i<indent;i++) {
			res+="    ";
		}
		res+=s;
		res+=' ';
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
	public void visit(Goal n,environ e){
		IntervalVisitor vis=new IntervalVisitor();
		n.accept(vis);
		RegAllocator alloc=new RegAllocator(vis.temp_list,vis.max_args);
		kpln("MAIN [ 0 ][ "+alloc.stackpos+" ][ "+alloc.nargs+" ]",indent++);
		n.f1.accept(this,new environ(alloc.tables,"MAIN",0,alloc.usedRegNum));
		kpln("END",--indent);
		n.f3.accept(this,null);	
	}
	/**
	 * Grammar production:
	 * f0 -> ( ( Label() )? Stmt() )*
	 */
	public void visit(StmtList n, environ e){
		n.f0.accept(this,e);
		return;
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
	public void visit(Stmt n, environ e) {
		e.position++;
		n.f0.accept(this,e);
		return;
	}
	/**
	 * Grammar production:
	 * f0 -> Label()
	 * f1 -> "["
	 * f2 -> IntegerLiteral()
	 * f3 -> "]"
	 * f4 -> StmtExp()
	 */
	public void visit(Procedure n, environ e){
		IntervalVisitor vis=new IntervalVisitor();
		n.accept(vis);
		RegAllocator alloc=new RegAllocator(vis.temp_list,vis.max_args);
		genHead(n.f0.f0.toString(),Integer.parseInt(n.f2.f0.toString()),alloc.stackpos,alloc.nargs,alloc.usedReg,alloc.usedRegNum);
		n.f4.accept(this,new environ(alloc.tables,n.f0.f0.toString(),0,alloc.usedRegNum));
		genTail(alloc.usedReg,alloc.usedRegNum);
		return;
	}
	/**
	 * Grammar production:
	 * f0 -> "NOOP"
	 */
	public void visit(NoOpStmt n,environ e) {
		kpln("NOOP",indent);
	}
	/**
	 * Grammar production:
	 * f0 -> "ERROR"
	 */
	public void visit(ErrorStmt n,environ e){
		kpln("ERROR",indent);
	}
	/**
	 * Grammar production:
	 * f0 -> "CJUMP"
	 * f1 -> Temp()
	 * f2 -> Label()
	 */
	public void visit(CJumpStmt n,environ e){
		kp("CJUMP",indent);
		n.f1.accept(this,e);
		kpln(e.funcName+"_"+n.f2.toString(),indent);
		return;
	}
	/**
	 * Grammar production:
	 * f0 -> "JUMP"
	 * f1 -> Label()
	 */	
	public void visit(JumpStmt n,environ e){
		kpln("JUMP "+e.funcName+"_"+n.f1.f0.toString(),indent);
		return;
	}
	/**
	 * Grammar production:
	 * f0 -> "HSTORE"
	 * f1 -> Temp()
	 * f2 -> IntegerLiteral()
	 * f3 -> Temp()
	 */
	public void visit(HStoreStmt n) {
		
	}
	/**
	 * Grammar production:
	 * f0 -> "HLOAD"
	 * f1 -> Temp()
	 * f2 -> Temp()
	 * f3 -> IntegerLiteral()
	 */
	public void visit(HLoadStmt n){
		
	}
	/**
	 * Grammar production:
	 * f0 -> "MOVE"
	 * f1 -> Temp()
	 * f2 -> Exp()	
	 				 Exp() Grammar production:
	 						* f0 -> Call()
	 						*       | HAllocate()
	 						*       | BinOp()
	 						*       | SimpleExp()
 */
	public void visit(MoveStmt n){
		
	}

	/**
	 * Grammar production:
	 * f0 -> "PRINT"
	 * f1 -> SimpleExp()
	 */
	public void visit(PrintStmt n){
		
	}
	
	/**
	 * Grammar production:
	 * f0 -> "BEGIN"
	 * f1 -> StmtList()
	 * f2 -> "RETURN"
	 * f3 -> SimpleExp()
	 * f4 -> "END"
	 */
	public void visit(StmtExp n){
		
	}
	
	/**
	 * Grammar production:
	 * f0 -> "CALL"
	 * f1 -> SimpleExp()
	 * f2 -> "("
	 * f3 -> ( Temp() )*
	 * f4 -> ")"
	 */
	public void visit(Call n){
		
	}
	/**
	 * Grammar production:
	 * f0 -> "LT"
	 *       | "PLUS"
	 *       | "MINUS"
	 *       | "TIMES"
	 */
	public void visit(Operator n){
		
	}
	/**
	 * Grammar production:
	 * f0 -> <INTEGER_LITERAL>
	 */
	public void visit(IntegerLiteral n){
		
	}
	/**
	 * Grammar production:
	 * f0 -> <IDENTIFIER>
	 */
	public void visit(Label n){
	
	}
	void genHead(String name, int a,int b,int c,int[] regs,int usedNum) {
		kpln(name+" [ "+a+" ][ "+b+" ][ "+c+" ]",indent++);
		for(int i=0;i<usedNum;i++) {
			kpln("ASTORE SPILLDARG "+i+" "+RegNames.REGS[regs[i]],indent);
		}
	}
	void genTail(int[] regs,int usedNum) {
		for(int i=0;i<usedNum;i++) {
			kpln("ALOAD "+RegNames.REGS[regs[i]]+" SPILLEDARG "+i,indent);
		}
	}
	String readTemp(int t, environ e) {
		Iterator<Table> itr=e.tables.iterator();
		Table node=e.tables.getFirst();
		int pos=e.position;
		while(itr.hasNext()) {
			node=itr.next();
			if(node.pos>pos)  break;
		}
		if(node.regs.containsKey(t)) {
			return RegNames.REGS[node.regs.get(t).regnum];
		}
		else if(node.stacks.containsKey(t)) {
			kpln("ALOAD")
		}
		return outfile;
	}
}