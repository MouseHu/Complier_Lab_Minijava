package toKanga;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

import spiglet.syntaxtree.*;
import toKanga.*;
import spiglet.visitor.GJVoidDepthFirst;

public class KangaVisitor extends GJVoidDepthFirst{
	public static OutputStreamWriter writer;
	String outfile;
	int indent;
	public KangaVisitor(String out) throws FileNotFoundException{
		outfile = out;
		writer = new OutputStreamWriter(new FileOutputStream(outfile));
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
	public void visit(Goal n){
	}
	/**
	 * Grammar production:
	 * f0 -> ( ( Label() )? Stmt() )*
	 */
	public void visit(StmtList n){
	}
	
	/**
	 * Grammar production:
	 * f0 -> Label()
	 * f1 -> "["
	 * f2 -> IntegerLiteral()
	 * f3 -> "]"
	 * f4 -> StmtExp()
	 */
	public void visit(Procedure n){
	}
	/**
	 * Grammar production:
	 * f0 -> "NOOP"
	 */
	public void visit(NoOpStmt n) {
	}
	/**
	 * Grammar production:
	 * f0 -> "ERROR"
	 */
	public void visit(ErrorStmt n){
	}
	/**
	 * Grammar production:
	 * f0 -> "CJUMP"
	 * f1 -> Temp()
	 * f2 -> Label()
	 */
	public void visit(CJumpStmt n){
	}
	/**
	 * Grammar production:
	 * f0 -> "JUMP"
	 * f1 -> Label()
	 */	
	public void visit(JumpStmt n){
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
	
}