package toMips;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

import com.sun.xml.internal.ws.util.StringUtils;

import kanga.syntaxtree.ALoadStmt;
import kanga.syntaxtree.AStoreStmt;
import kanga.syntaxtree.BinOp;
import kanga.syntaxtree.CJumpStmt;
import kanga.syntaxtree.CallStmt;
import kanga.syntaxtree.ErrorStmt;
import kanga.syntaxtree.Exp;
import kanga.syntaxtree.HAllocate;
import kanga.syntaxtree.HLoadStmt;
import kanga.syntaxtree.HStoreStmt;
import kanga.syntaxtree.IntegerLiteral;
import kanga.syntaxtree.JumpStmt;
import kanga.syntaxtree.Label;
import kanga.syntaxtree.MoveStmt;
import kanga.syntaxtree.NoOpStmt;
import kanga.syntaxtree.Operator;
import kanga.syntaxtree.PassArgStmt;
import kanga.syntaxtree.PrintStmt;
import kanga.syntaxtree.Procedure;
import kanga.syntaxtree.Reg;
import kanga.syntaxtree.SimpleExp;
import kanga.syntaxtree.SpilledArg;
import kanga.syntaxtree.Stmt;
import kanga.syntaxtree.StmtList;
import kanga.syntaxtree.Goal;
import kanga.visitor.GJVoidDepthFirst;
import spiglet.AbstractSPigletResult;
import syntaxtree.Identifier;
import kanga.syntaxtree.NodeOptional;
import toKanga.IntervalVisitor;
import toKanga.RegAllocator;
import toKanga.RegNames;


public class MipsVisitor extends GJVoidDepthFirst<Integer>{
	public static OutputStreamWriter writer;
	String outfile;
	int indent=0;
	int max_arg;
	public MipsVisitor(String out) throws FileNotFoundException{
		outfile = out;
		writer = new OutputStreamWriter(new FileOutputStream(outfile));
	}
	public void mpln(String s, int indent){
		String res = "";
		for(int i=0;i<indent;i++) {
			res+="\t";
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
			res+="\t";
		}
		res+=s;
		try {
			writer.write(res);
			writer.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return;
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
	   public void visit(Goal n, Integer argu) {
		  
		  int a = Integer.parseInt(n.f2.f0.tokenImage);
		  int b = Integer.parseInt(n.f5.f0.tokenImage);
		  int c = Integer.parseInt(n.f8.f0.tokenImage);
		  max_arg =c;
		  mpln(".text",++indent);
		  mpln(".globl main",indent);
		  mpln("main:",--indent);
		  indent++;
		  genHead(a,b,c);
	      n.f10.accept(this, argu);
	      genTail(a,b,c);
	      mpln("",indent);
	      indent--;
	      n.f12.accept(this, argu);
	      
	      printSysfunc();
	   }

	   /**
	    * f0 -> ( ( Label() )? Stmt() )*
	    */
	   public void visit(StmtList n, Integer argu) {//yes
	      n.f0.accept(this, argu);
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
	   public void visit(Procedure n, Integer argu) {
		  int a = Integer.parseInt(n.f2.f0.tokenImage);
		  int b = Integer.parseInt(n.f5.f0.tokenImage);
		  int c = Integer.parseInt(n.f8.f0.tokenImage);
		  max_arg =c;
		  mpln(".text",++indent);
		  mpln(".globl "+n.f0.f0.toString(),indent);
		  mpln(n.f0.f0.toString()+":",--indent);
		  indent++;
		  mpln("sw $fp, -8($sp)",indent);
		  genHead(a,b,c);
	      n.f10.accept(this, argu);
	      genTail(a,b,c);
	      mpln("",indent);
	      indent--;
	   }
	   /**
	    * f0 -> "NOOP"
	    */
	   public void visit(NoOpStmt n, Integer argu) {//yes
	      mpln("nop",indent);
	   }

	   /**
	    * f0 -> "ERROR"
	    */
	   public void visit(ErrorStmt n, Integer argu) {//yes
		  mpln("jal _error",indent);  
	   }

	   /**
	    * f0 -> "CJUMP"
	    * f1 -> Reg()
	    * f2 -> Label()
	    */
	   public void visit(CJumpStmt n, Integer argu) {//yes
		  mp("beqz ",indent);
	      n.f1.accept(this, argu);
	      mp(" ",0);
	      n.f2.accept(this, argu);
	      mpln("",0);
	   }

	   /**
	    * f0 -> "JUMP"
	    * f1 -> Label()
	    */
	   public void visit(JumpStmt n, Integer argu) {//yes
	      mp("j ",indent);
	      n.f1.accept(this, argu);
	      mpln("",0);
	   }

	   /**
	    * f0 -> "HSTORE"
	    * f1 -> Reg()
	    * f2 -> IntegerLiteral()
	    * f3 -> Reg()
	    */
	   public void visit(HStoreStmt n, Integer argu) {//yes
		   mp("sw ",indent);
		   n.f3.accept(this,null);
		   mp(", ",0);
		   n.f2.accept(this,null);
		   mp("(",0);
		   n.f1.accept(this,null);
		   mpln(")",0);             
	   }

	   /**
	    * f0 -> "HLOAD"
	    * f1 -> Reg()
	    * f2 -> Reg()
	    * f3 -> IntegerLiteral()
	    */
	   public void visit(HLoadStmt n, Integer argu) {//yes
		   mp("lw ",indent);
		   n.f1.accept(this,null);
		   mp(" ",0);
		   n.f3.accept(this,null);
		   mp("(",0);
		   n.f2.accept(this,null);
		   mpln(")",0);  
	   }

	   /**
	    * f0 -> "MOVE"
	    * f1 -> Reg()
	    * f2 -> Exp()
	    */
	   public void visit(MoveStmt n, Integer argu) {
	      switch(n.f2.f0.which) {
	      case 0:// HALLOCATE
	    	  n.f2.accept(this,null); 
	    	  mp("move ",indent);
	    	  n.f1.accept(this,null);
	    	  mpln(" $v0",0);                     
	    	  break;
	      case 1://BinOp()
	    	  ((BinOp)(n.f2.f0.choice)).f0.accept(this,null);
	    	  n.f1.accept(this,null);
	    	  mp(", ",0);
	    	  ((BinOp)(n.f2.f0.choice)).f1.accept(this,null);
	    	  mp(", ",0);
	    	  ((BinOp)(n.f2.f0.choice)).f2.accept(this,null);
	    	  mpln("",0);
	    	  break;
	      case 2://SimpleExp()
	    	  switch(((SimpleExp)n.f2.f0.choice).f0.which) {
	    	  case 0://reg
	    		  mp("move ",indent);
	    		  break;
	    	  case 1:// integer literal
	    		  mp("li ",indent);
	    		  break;
	    	  case 2:// label
	    		  mp("la ",indent);
	    		  break;
	    	  }
	    	  n.f1.accept(this,null);
	    	  mp(" ",0);
	    	  n.f2.accept(this,null);
	    	  mpln("",0);
	    	  break;
	      }
	   }

	   /**
	    * f0 -> "PRINT"
	    * f1 -> SimpleExp()
	    */
	   public void visit(PrintStmt n, Integer argu) {//yes
		   switch(n.f1.f0.which) {
	    	  case 0://reg
	    		  mp("move ",indent);
	    		  break;
	    	  case 1:// integer literal
	    		  mp("li ",indent);
	    		  break;
	    	  case 2:// label
	    		  mp("la ",indent);
	    		  break;
	    	  }
	      mp("$a0 ",0);
	      n.f1.accept(this,null);
	      mpln("",0);
	      mpln("jal _print",indent);  
	   }

	   /**
	    * f0 -> "ALOAD"
	    * f1 -> Reg()
	    * f2 -> SpilledArg()
	    */
	   public void visit(ALoadStmt n, Integer argu) {//yes
		   mp("lw ",indent);
		   n.f1.accept(this,null);
		   int pos = Integer.parseInt(n.f2.f1.f0.tokenImage)*4;
		   if(pos>max_arg*4)
			   mpln(","+pos+"($sp)",0);
		   else
			   mpln(","+pos+"($fp)",0);
		  
	   }

	   /**
	    * f0 -> "ASTORE"
	    * f1 -> SpilledArg()
	    * f2 -> Reg()
	    */
	   public void visit(AStoreStmt n, Integer argu) {//yes
		   mp("sw ",indent);
		   n.f2.accept(this,null);
		   int pos = Integer.parseInt(n.f1.f1.f0.tokenImage)*4;
		   if(pos>max_arg*4)
			   mpln(", "+pos+"($sp)",0);
		   else
			   mpln(", "+pos+"($fp)",0); 
	   }

	   /**
	    * f0 -> "PASSARG"
	    * f1 -> IntegerLiteral()
	    * f2 -> Reg()
	    */
	   public void visit(PassArgStmt n, Integer argu) {//yes
		   mp("sw",indent);
		   n.f2.accept(this,null);
		   int pos = (Integer.parseInt(n.f1.f0.tokenImage)-1)*4;
		   mpln(", "+pos+"($sp)",0);
	   }

	   /**
	    * f0 -> "CALL"
	    * f1 -> SimpleExp()
	    */
	   public void visit(CallStmt n, Integer argu) {//yes
		  switch(n.f1.f0.which) {
	    	  case 0://reg
	    		  mp("jalr ",indent);
	    		  n.f1.accept(this,null);
	    		  mpln("",0);
	    		  break;
	    	  case 1:// integer literal
	    		  mp("li $a0",indent);
	    		  n.f1.accept(this,null);
	    		  mpln("",0);
		    	  mpln("jalr $a0",indent);
	    		  break;
	    	  case 2:// label
	    		  mp("jal ",indent);
	    		  n.f1.accept(this,null);
	    		  mpln("",0);
	    		  break;
		  }
	      
	      
	   }


	   /**
	    * f0 -> "HALLOCATE"
	    * f1 -> SimpleExp()
	    */
	   public void visit(HAllocate n, Integer argu) {//yes
		   switch(n.f1.f0.which) {
	    	  case 0://reg
	    		  mp("move ",indent);
	    		  break;
	    	  case 1:// integer literal
	    		  mp("li ",indent);
	    		  break;
	    	  case 2:// label
	    		  mp("la ",indent);
	    		  break;
		   }
		  mp("$a0 ",0);
		  n.f1.accept(this,null);
		  mpln("",0);
		  mpln("jal _halloc",indent);
		  
//	      n.f1.accept(this, argu);
	   }


	   /**
	    * f0 -> "LT"
	    *       | "PLUS"
	    *       | "MINUS"
	    *       | "TIMES"
	    */
	   public void visit(Operator n, Integer argu) {//yes
	      n.f0.accept(this, argu);
	      switch(n.f0.which) {
	      case 0:
	    	  mp("slt ",indent);
	    	  break;
	      case 1:
	    	  mp("add ",indent);
	    	  break;
	      case 2:
	    	  mp("sub ",indent);
	    	  break;
	      case 3:
	    	  mp("mul ",indent);
	    	  break;
	    	  
	      }
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
	   public void visit(Reg n, Integer argu) {//yes
	      mp("$"+n.f0.choice.toString(),0);
	   }

	   /**
	    * f0 -> <INTEGER_LITERAL>
	    */
	   public void visit(IntegerLiteral n, Integer argu) {//yes
	      mp(n.f0.tokenImage,0);
	   }

	   /**
	    * f0 -> <IDENTIFIER>
	    */
	   public void visit(Label n, Integer argu) {//yes
		   mp(n.f0.tokenImage,0);
	   }
	   
	   public void genHead(int a,int b ,int c) {
		   int t1 = (a>4?(a-4):0);
		   int t2 = (c>4?(c-4):0);
		   int stackLength= (b+2+t2-t1)*4;

		   mpln("move $fp, $sp",indent);
		   mpln("subu $sp, $sp, "+stackLength,indent);
		   mpln("sw $ra, -"+4+"($fp)",indent);
		   
		   
	   }
	   
	   public void genTail(int a,int b,int c) {
		   int t1 = (a>4?(a-4):0);
		   int t2 = (c>4?(c-4):0);
		   int stackLength= (b+2+t2-t1)*4;
		   mpln("lw $ra, -"+4+"($fp)",indent);
		   mpln("lw $fp, -"+8+"($fp)",indent);
		   mpln("addu $sp, $sp, "+stackLength,indent);
		   mpln("j $ra",indent);
	   }
	   public void printSysfunc() {
		   String temp = "         .text            \n" +
					 "         .globl _halloc  \n" +
					 "_halloc:                 \n" +
					 "         li $v0, 9        \n" +
					 "         syscall          \n" +
					 "         j $ra            \n" +
					 "                          \n" +
					 "         .text            \n" +
					 "         .globl _print \n" +
					 "_print:                \n" +
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
		   mpln(temp,indent);
	   }
	   public void visit(NodeOptional n, Integer argu) {// for label only
			if(n.present()){
				n.node.accept(this,null);
				mp(":",0);
			}
	   }
}
