package toMips;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

import kanga.syntaxtree.*;
import kanga.visitor.*;
public class MipsVisitor extends GJDepthFirst<String,Object> {
	public String outfile;
	public static OutputStreamWriter writer;
	int max_arg=0;
	int indent;
	public MipsVisitor(String out) throws FileNotFoundException{
		outfile=out;
		writer = new OutputStreamWriter(new FileOutputStream(outfile));
		indent=0;
	}
	/**
	 * f0 -> "MAIN"
	 * f1 -> "["
	 * f2 -> IntegerLiteral()
	 * f3 -> "]"
	 * f4 -> "["
	 * f5 -> IntegerLiteral()
	 * f6 -> "]"
	 * f7 -> "["
	 * f8 -> IntegerLiteral()
	 * f9 -> "]"
	 * f10 -> StmtList()
	 * f11 -> "END"
	 * f12 -> ( Procedure() )*
	 * f13 -> <EOF>
	 */
	public void mpln(String s, int indent){
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
			e.printStackTrace();
		}
		return;
	}
	public void mp(String s, int indent){
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
			e.printStackTrace();
		}
		return;
	}
	void genHead(int l) {
		mpln("sw $fp, -8($sp)",indent);
		mpln("move $fp $sp",indent);
		mpln("subu $sp, $sp, "+l,indent);
		mpln("sw $ra, -4($fp)",indent);
		return;
	}
	void genTail(int l) {
		mpln("lw $ra -4($fp)",indent);
		mpln("lw $fp -8($fp)",indent);
		mpln("addu $sp, $sp, "+l,indent);
		mpln("j $ra",indent);
		return;
	}
	/**
	 * Grammar production:
	 * f0 -> "MAIN"
	 * f1 -> "["
	 * f2 -> IntegerLiteral()
	 * f3 -> "]"
	 * f4 -> "["
	 * f5 -> IntegerLiteral()
	 * f6 -> "]"
	 * f7 -> "["
	 * f8 -> IntegerLiteral()
	 * f9 -> "]"
	 * f10 -> StmtList()
	 * f11 -> "END"
	 * f12 -> ( Procedure() )*
	 * f13 -> <EOF>
	 */
	
	public String visit(Goal n, Object argu){
		max_arg=Integer.parseInt(n.f8.f0.tokenImage);
		int a=Integer.parseInt(n.f2.toString());
		int b=Integer.parseInt(n.f5.toString());
		int c=Integer.parseInt(n.f8.toString());
		int t1 = (a>4?(a-4):0);
		int t2 = (c>4?(c-4):0);
		int stackLength= (b+2+t2-t1)*4;
		mpln(".text",++indent);
		mpln(".globl main",indent);
		mpln("main:",--indent);
		indent++;
		genHead(stackLength);
		n.f10.accept(this,argu);
		genTail(stackLength);
		indent--;
		n.f12.accept(this,argu);
		mp(extra(),0);
		return null;
	}

	/**
	 * f0 -> ( ( Label() )? Stmt() )*
	 */
	public String visit(StmtList n, Object argu){
		n.f0.accept(this,argu);
		return null;
	}

	/**
	 * f0 -> Label()
	 * f1 -> "["
	 * f2 -> IntegerLiteral()
	 * f3 -> "]"
	 * f4 -> "["
	 * f5 -> IntegerLiteral()
	 * f6 -> "]"
	 * f7 -> "["
	 * f8 -> IntegerLiteral()
	 * f9 -> "]"
	 * f10 -> StmtList()
	 * f11 -> "END"
	 */
	public String visit(Procedure n, Object argu) {
		max_arg=Integer.parseInt(n.f8.f0.tokenImage);
		int a=Integer.parseInt(n.f2.toString());
		int b=Integer.parseInt(n.f5.toString());
		int c=Integer.parseInt(n.f8.toString());
		int t1 = (a>4?(a-4):0);
		int t2 = (c>4?(c-4):0);
		int stackLength= (b+2+t2-t1)*4;
		mpln(".text",++indent);
		mpln(".globl "+n.f0.toString(),indent);
		mpln(n.f0.toString()+":",--indent);
		indent++;
		genHead(stackLength);
		n.f10.accept(this,argu);
		genTail(stackLength);
		indent--;
		return null;
	}
	/**
	 * f0 -> NoOpStmt()
	 *       | ErrorStmt()
	 *       | CJumpStmt()
	 *       | JumpStmt()
	 *       | HStoreStmt()
	 *       | HLoadStmt()
	 *       | MoveStmt()
	 *       | PrintStmt()
	 *       | ALoadStmt()
	 *       | AStoreStmt()
	 *       | PassArgStmt()
	 *       | CallStmt()
	 */
	public String visit(Stmt n, Object argu) {
		n.f0.accept(this,argu);
		return null;
	}

	/**
	 * f0 -> "NOOP"
	 */
	public String visit(NoOpStmt n, Object argu){
		mpln("nop",indent);
		return null;
	}

	/**
     * f0 -> "ERROR"
	 */
	public String visit(ErrorStmt n, Object argu) {
		mpln("jal _error",indent);
		return null;
	}

	   /**
	    * f0 -> "CJUMP"
	    * f1 -> Reg()
	    * f2 -> Label()
	    */
	public String visit(CJumpStmt n, Object argu){
		mpln("beqz "+n.f1.accept(this,argu)+" "+n.f2.f0.tokenImage,indent);
		return null;
	}

	   /**
	    * f0 -> "JUMP"
	    * f1 -> Label()
	    */
	   public String visit(JumpStmt n, Object argu){
		   mpln("b "+n.f1.f0.tokenImage,indent);
		   return null;
	   }

	   /**
	    * f0 -> "HSTORE"
	    * f1 -> Reg()
	    * f2 -> IntegerLiteral()
	    * f3 -> Reg()
	    */
	   public String visit(HStoreStmt n, Object argu){
		   mpln("sw "+n.f3.accept(this,argu)+", "+n.f2.toString()+"("+n.f1.accept(this,argu)+")",indent);
		   return null;
	   }

	   /**
	    * f0 -> "HLOAD"
	    * f1 -> Reg()
	    * f2 -> Reg()
	    * f3 -> IntegerLiteral()
	    */
	   public String visit(HLoadStmt n, Object argu){
		   mpln("lw "+n.f1.accept(this,argu)+", "+n.f3.toString()+"("+n.f2.accept(this,argu)+")",indent);
		   return null;
	   }

	   /**
	    * f0 -> "MOVE"
	    * f1 -> Reg()
	    * f2 -> Exp()
	    * 				/** Exp()
	    				* Grammar production:
						 * f0 -> HAllocate()
						 *       | BinOp()
						 *       | SimpleExp()
	    */
	   public String visit(MoveStmt n, Object argu){	     
		   if(n.f2.f0.which==0) {
			   n.f2.f0.accept(this,argu);
			   mpln("jal _halloc",indent);
			   mpln("move "+n.f1.accept(this,argu)+" $v0",indent);
		   }
		   else if(n.f2.f0.which==1) {
			   BinOp op = ((BinOp)n.f2.f0.choice);
			   String exp=op.f2.accept(this,argu);
			   mpln(op.f0.accept(this,argu)+" "+n.f1.toString()+", "+op.f1.toString()+", "+exp,indent);
		   }
		   else {
			   SimpleExp e=((SimpleExp)n.f2.f0.choice);
			   if(e.f0.which==0) {
				   mpln("move "+n.f1.accept(this,argu)+" "+e.accept(this,argu),indent);
			   }
			   else if(e.f0.which==1) {
				   mpln("li "+n.f1.toString()+" "+e.accept(this,argu),indent);
			   }
			   else {
				   mpln("la "+n.f1.toString()+" "+e.accept(this,argu),indent);
			   }
		   }
		   return  null;
	   }

	   /**
	    * f0 -> "PRINT"
	    * f1 -> SimpleExp()
	    * 					/** SimpleExp
					    * Grammar production:
					    * f0 -> Reg()
					    *       | IntegerLiteral()
					    *       | Label()
					    */
	   public String visit(PrintStmt n, Object argu){
		   if(n.f1.f0.which==0) {
			   mpln("move $a0 "+n.f1.f0.accept(this,argu),indent);
		   }
		   else if(n.f1.f0.which==1) {
			   mpln("li $a0 "+n.f1.f0.accept(this,argu),indent);
		   }
		   else {
			   mpln("la $a0 "+n.f1.f0.accept(this,argu),indent);
		   }
		   mpln("jal _print",indent);
		   return null;
	   }
	   
	   

	   /**
	    * f0 -> "ALOAD"
	    * f1 -> Reg()
	    * f2 -> SpilledArg()
	    */
	   public String visit(ALoadStmt n, Object argu){
		   int val=4*Integer.parseInt(n.f2.f1.toString());
		   if(val >= max_arg*4) mpln("lw "+n.f1.accept(this,argu)+", "+val+"($sp)",indent);
		   else mpln("lw "+n.f1.accept(this,argu)+", "+val+"($fp)",indent);
		   return null;
	   }

	   /**
	    * f0 -> "ASTORE"
	    * f1 -> SpilledArg()
	    * f2 -> Reg()
	    */
	   public String visit(AStoreStmt n, Object argu){
		   int val=4*Integer.parseInt(n.f1.f1.toString());
		   if(val >= max_arg*4) mpln("sw "+n.f2.accept(this,argu)+", "+val+"($sp)",indent);
		   else mpln("sw "+n.f2.accept(this,argu)+", "+val+"($fp)",indent);
		   return null;
	   }

	   /**
	    * f0 -> "PASSARG"
	    * f1 -> IntegerLiteral()
	    * f2 -> Reg()
	    */
	   public String visit(PassArgStmt n, Object argu){
		   int pos=Integer.parseInt(n.f1.f0.tokenImage);
		   mpln("sw "+n.f2.accept(this,argu)+", "+4*(pos-1)+"($sp)",indent);
		   return null;
	   }

	   /**
	    * f0 -> "CALL"
	    * f1 -> SimpleExp()
	    */
	   public String visit(CallStmt n, Object argu) {
		   String val = n.f1.accept(this,argu);
		   if(n.f1.f0.which == 0){
			   mpln("jalr "+val,indent);
		   }
		   if(n.f1.f0.which == 1){
		       mpln("li $a0 "+val,indent);
		       mpln("jalr $a0",indent);
		   }
		   else if(n.f1.f0.which == 2) mpln("jal "+val,indent);	 
		   return null;
	   }
	   

	   /**
	    * f0 -> HAllocate()
	    *       | BinOp()
	    *       | SimpleExp()
	    */
	   public String visit(Exp n, int argu){
		   n.f0.accept(this,argu);
		   return null;
	   }

	   /**
	    * f0 -> "HALLOCATE"
	    * f1 -> SimpleExp()
	    */
	   public String visit(HAllocate n, Object argu){
		   if(n.f1.f0.which==0) {
			   mpln("move $a0 "+n.f1.f0.accept(this,argu),indent);
		   }
		   else if(n.f1.f0.which==1) {
			   mpln("li $a0 "+n.f1.f0.accept(this,argu),indent);
		   }
		   else {
			   mpln("la $a0 "+n.f1.f0.accept(this,argu),indent);
		   }
		   return null;
	   }

	   /**
	    * f0 -> Operator()
	    * f1 -> Reg()
	    * f2 -> SimpleExp()
	    */
	   public String visit(BinOp n, Object argu){
		   if(n.f0.f0.which==0) {
			   return "slt";
		   }
		   else if(n.f0.f0.which==1) {
			   return "add";
		   }
		   else if(n.f0.f0.which==2) {
			   return "sub";
		   }
		   else{
			   return "mul";
		   }
	   }

	   /**
	    * f0 -> "LT"
	    *       | "PLUS"
	    *       | "MINUS"
	    *       | "TIMES"
	    */
	   public String visit(Operator n, Object argu){
		   return null;
	   }

	   /**
	    * f0 -> "SPILLEDARG"
	    * f1 -> IntegerLiteral()
	    */
	   public String visit(SpilledArg n, Object argu){
		   return null;
	   }

	   /**
	    * f0 -> Reg()
	    *       | IntegerLiteral()
	    *       | Label()
	    */
	   public String visit(SimpleExp n, Object argu) {
		   if(n.f0.which != 2) return n.f0.accept(this,argu);
		   else return ((Label)n.f0.choice).f0.tokenImage;
	   }

	   /**
	    * f0 -> "a0"
	    *       | "a1"
	    *       | "a2"
	    *       | "a3"
	    *       | "t0"
	    *       | "t1"
	    *       | "t2"
	    *       | "t3"
	    *       | "t4"
	    *       | "t5"
	    *       | "t6"
	    *       | "t7"
	    *       | "s0"
	    *       | "s1"
	    *       | "s2"
	    *       | "s3"
	    *       | "s4"
	    *       | "s5"
	    *       | "s6"
	    *       | "s7"
	    *       | "t8"
	    *       | "t9"
	    *       | "v0"
	    *       | "v1"
	    */
	   public String visit(Reg n, Object argu){
		   return "$"+n.toString();
	   }

	   /**
	    * f0 -> <INTEGER_LITERAL>
	    */
	   public String visit(IntegerLiteral n, Object argu) {
		   return n.toString();
	   }

	   /**
	    * f0 -> <IDENTIFIER>
	    */
	   public String visit(Label n, Object argu){
		   return n.f0.tokenImage;
	   }
	   public String extra()
		{
			String temp = "         .text            \n" +
			 "         .globl _hallocs  \n" +
			 "_hallocs:                 \n" +
			 "         li $v0, 9        \n" +
			 "         syscall          \n" +
			 "         j $ra            \n" +
			 "                          \n" +
			 "         .text            \n" +
			 "         .globl _printint \n" +
			 "_printint:                \n" +
			 "         li $v0, 1        \n" +
			 "         syscall          \n" +
			 "         la $a0, newl     \n" +
			 "         li $v0, 4        \n" +
			 "         syscall          \n" +
			 "         j $ra            \n" +
			 "                          \n" +
			 "         .data            \n" +
			 "         .align   0       \n" +
			 "newl:    .asciiz \"\\n\"  \n" +
			 "         .data            \n" +
			 "         .align   0       \n" +
			 "str_er:  .asciiz \" ERROR: abnormal termination\\n\" "+
			 "                          \n" +
			 "         .text            \n" +
			 "         .globl _error    \n" +
			 "_error:                   \n" +
			 "         li $v0, 4        \n" +
			 "         la $a0, str_er   \n" +
			 "         syscall          \n" +
			 "         li $v0, 10       \n" +
			 "         syscall          \n";
			return temp;
		}
}
