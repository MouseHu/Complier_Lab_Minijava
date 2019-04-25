package piglet;

import symbol.*;
import visitor.*;
import syntaxtree.*;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map.Entry;

import javafx.util.Pair;

public class TranslateVisitor extends GJDepthFirst<String, String>{
	int indent = 0;
	int label = 0;
	int temp_num = 20;

	
	HashMap<String, String> var_table = null;
	
	public TranslateVisitor(TranslateTable transTab) {
		// TODO Auto-generated constructor stub
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

	public MType visit(FormalParameterList n, MType argu) {
		
	}
	
	public MType visit(FormalParameter n, MType argu) {
		
	}
	
	public MType visit(FormalParameterRest n, MType argu) {
		
	}
	
	/*
	public MType visit(Type n, MType argu) {
	
	}
	public MType visit(ArrayType n, MType argu) {
		
	}
	public MType visit(BooleanType n, MType argu) {
		
	}
	public MType visit(IntegerType n, MType argu) {
		
	}
	*/
	
	public String visit(Goal n, String argu) {
		var_table = new HashMap<>();
		n.f0.accept(this,argu);
		n.f1.accept(this,argu);
		return null;
	}

	public String visit(TypeDeclaration n, String argu) {
		n.f0.accept(this,argu);
		return null;
	}
	/**
	 * f0 -> AndExpression()
	 *       | CompareExpression()
	 *       | PlusExpression()
	 *       | MinusExpression()
	 *       | TimesExpression()
	 *       | ArrayLookup()
	 *       | ArrayLength()
	 *       | MessageSend()
	 *       | PrimaryExpression()
	 */
	public String visit(Expression n,String argu){
		n.f0.accept(this,argu);
		return null;
	}
	   /**
	    * f0 -> IntegerLiteral()
	    *       | TrueLiteral()
	    *       | FalseLiteral()
	    *       | Identifier()
	    *       | ThisExpression()
	    *       | ArrayAllocationExpression()
	    *       | AllocationExpression()
	    *       | NotExpression()
	    *       | BracketExpression()
	    */
	public String visit(PrimaryExpression n,String argu){
		n.f0.accept(this,argu);
		return null;
	}
	
	public String visit(IntegerLiteral n, String argu) {
		piglet_print(n.f0.tokenImage+"\n",indent);
		return null;
	}
	public String visit(TrueLiteral n, String argu) {
		piglet_print("1\n",indent);
		return null;
	}
	public String visit(FalseLiteral n, MType argu) {
		piglet_print("0\n",indent);
		return null;
	}
	
	/*
	public MType visit(ThisExpression n, MType argu) {
		
	}
	*/
	  /**
	   * ArrayAllocationExpression
	   * Grammar production:
	   * f0 -> "new"
	   * f1 -> "int"
	   * f2 -> "["
	   * f3 -> Expression()
	   * f4 -> "]"
	   */
	public String visit(ArrayAllocationExpression n, String argu) {
		piglet_print("HALLOCATE TIMES 4\n",indent);
		n.f3.accept(this,argu);
		return null;
	}
	
	
	public String visit(BracketExpression n, String argu) {
		return n.f1.accept(this, argu);
	}
	  /**
	   * f0 -> "!"
	   * f1 -> Expression()
	   */
	public String visit(NotExpression n, String argu) {
		String s = n.f1.accept(this,argu);
		piglet_print("LT "+s+" 1\n",indent);
		return null;
	}
	   /**
	    * f0 -> "class"
	    * f1 -> Identifier()
	    * f2 -> "{"
	    * f3 -> ( VarDeclaration() )*
	    * f4 -> ( MethodDeclaration() )*
	    * f5 -> "}"
	    */
	public String visit(ClassDeclaration n,String argu){
		String classname = n.f1.f0.tokenImage;
		n.f4.accept(this,classname);
		return null;
	}
	/**
	 * f0 -> "class"
	 * f1 -> Identifier()
	 * f2 -> "extends"
	 * f3 -> Identifier()
	 * f4 -> "{"
	 * f5 -> ( VarDeclaration() )*
	 * f6 -> ( MethodDeclaration() )*
	 * f7 -> "}"
	 */
	public String visit(ClassExtendsDeclaration n,String argu){
		String classname = n.f1.f0.tokenImage;
		n.f6.accept(this,classname);
		return null;
	}
	   /**
	    * f0 -> "class"
	    * f1 -> Identifier()
	    * f2 -> "{"
	    * f3 -> "public"
	    * f4 -> "static"
	    * f5 -> "void"
	    * f6 -> "main"
	    * f7 -> "("
	    * f8 -> "String"
	    * f9 -> "["
	    * f10 -> "]"
	    * f11 -> Identifier()
	    * f12 -> ")"
	    * f13 -> "{"
	    * f14 -> ( VarDeclaration() )*
	    * f15 -> ( Statement() )*
	    * f16 -> "}"
	    * f17 -> "}"
	    */
	public String visit(MainClass n, String argu){
		piglet_print("MAIN\n",indent++);
		n.f14.accept(this,argu);
		n.f15.accept(this,argu);
		piglet_print("END\n",--indent);
		return null;
	}
	   /**
	    * f0 -> "public"
	    * f1 -> Type()
	    * f2 -> Identifier()
	    * f3 -> "("
	    * f4 -> ( FormalParameterList() )?
	    * f5 -> ")"
	    * f6 -> "{"
	    * f7 -> ( VarDeclaration() )*
	    * f8 -> ( Statement() )*
	    * f9 -> "return"
	    * f10 -> Expression()
	    * f11 -> ";"
	    * f12 -> "}"
	    */
	public String visit(MethodDeclaration n,String argu){
		String s = "";
		piglet_print(argu+"_"+n.f2.f0.tokenImage, indent++);
		
	}
	
	public MType visit(VarDeclaration n,MType argu){
		
	}
	
	public MType visit(Identifier n,MType argu){
		
	}
	
	   /**
	    * f0 -> PrimaryExpression()
	    * f1 -> "&&"
	    * f2 -> PrimaryExpression()
	    */
	public String visit(AndExpression n,String argu){
		piglet_print("BEGIN\n",indent++);
		piglet_print("CJUMP LT 0\n",indent);
		n.f0.accept(this, argu);
		String lab1 = getLabel();
		piglet_print(lab1,indent);
		piglet_print("CJUMP LT 0\n",indent);
		n.f2.accept(this,argu);
		piglet_print(lab1,indent);
		String temp = getTemp();
		piglet_print("MOVE "+temp+" 1\n",indent);
		String lab2=getLabel();
		piglet_print("JUMP "+lab2+"\n",indent);
		piglet_print(lab1+" "+"MOVE "+temp+" 0\n",indent);
		piglet_print(lab2+" NOOP\n",indent);
		piglet_print("RETURN "+temp+"\n",indent);
		piglet_print("END\n",--indent);
		return null;
	}
	  /**
	   * f0 -> "new"
	   * f1 -> Identifier()
	   * f2 -> "("
	   * f3 -> ")"
	   */
	public String visit(AllocationExpression n, String argu){
		String className = n.f1.f0.toString();
		Class c = getClass(className);
		
		piglet_print("MOVE "+argu+"\n", indent++);
		piglet_print("BEGIN\n",indent);
		
		//Allocate Dtable
		String dTableAddr = getTemp();
		piglet_print("MOVE "+dTableAddr+" HALLOCATE "+4*c.methods.size()+"\n",indent);
		for(int i=0;i<c.methods.size();i++) {
			Method method = c.methods.get(i);
			piglet_print("HSTORE "+dTableAddr+" "+4*i+" "+method.className+"_"+method.methodName+"\n",indent);
		}
		
		//Allocate Vtable
		String vTableAddr = getTemp();
		piglet_print("MOVE "+vTableAddr+" HALLOCATE "+ 4*(c.vars.size()+1)+"\n",indent);
		for(int i=1; i<c.vars.size();i++) {
			piglet_print("HSTORE "+vTableAddr+" "+4*i+" "+"0\n",indent);
		}
		piglet_print("HSTORE "+vTableAddr+" 0 "+dTableAddr+"\n",indent);
		piglet_print("RETURN "+vTableAddr+"\n",indent);
		piglet_print("END\n",--indent);
		
		return null;
	}
	   /**
	    * f0 -> PrimaryExpression()
	    * f1 -> "<"
	    * f2 -> PrimaryExpression()
	    */
	public String visit(CompareExpression n,String argu){
		String s = "LE";
		String s1 = n.f0.accept(this, argu);
		String s2 = n.f2.accept(this, argu);
		piglet_print(s+" "+s1+" "+s2+"\n",indent);
		return null;
	}
	   /**
	    * f0 -> PrimaryExpression()
	    * f1 -> "+"
	    * f2 -> PrimaryExpression()
	    */
	public String visit(PlusExpression n, String argu) {
		String s = "PLUS";
		String s1 = n.f0.accept(this, argu);
		String s2 = n.f2.accept(this, argu);
		piglet_print(s+" "+s1+" "+s2+"\n",indent);
		return null;
	}
	   /**
	    * f0 -> PrimaryExpression()
	    * f1 -> "-"
	    * f2 -> PrimaryExpression()
	    */
	public String visit(MinusExpression n, String argu) {
		String s = "MINUS";
		String s1 = n.f0.accept(this, argu);
		String s2 = n.f2.accept(this, argu);
		piglet_print(s+" "+s1+" "+s2+"\n",indent);
		return null;
	}
	   /**
	    * f0 -> PrimaryExpression()
	    * f1 -> "*"
	    * f2 -> PrimaryExpression()
	    */
	public String visit(TimesExpression n,String argu){
		piglet_print("TIMES\n",indent);
		n.f0.accept(this, argu);
		n.f2.accept(this, argu);
		return null;
	}
	   /**
	    * f0 -> PrimaryExpression()
	    * f1 -> "["
	    * f2 -> PrimaryExpression()
	    * f3 -> "]"
	    */

	public MType visit(ArrayLookup n, MType argu) {
		
	}
	   /**
	    * f0 -> PrimaryExpression()
	    * f1 -> "."
	    * f2 -> "length"
	    */
	public MType visit(ArrayLength n, MType argu) {
	
	}
	   /**
	    * f0 -> PrimaryExpression()
	    * f1 -> "."
	    * f2 -> Identifier()
	    * f3 -> "("
	    * f4 -> ( ExpressionList() )?
	    * f5 -> ")"
	    */
	public MType visit(MessageSend n, MType argu) {
	
	}
	
	public String visit(Block n, String argu) {
		return n.f1.accept(this, argu);;
	}
			/**
		    * AssignmentStatement
		    * f0 -> Identifier()
		    * f1 -> "="
		    * f2 -> Expression()
		    * f3 -> ";"
		    */
	public String visit(AssignmentStatement n, String argu) {
		String var_name = n.f0.f0.tokenImage;
		var_name = argu+"_"+var_name; //argu:classname_funcname
		if(!var_table.containsKey(var_name)) {
			String var_temp = getTemp();//get a temp address to store
			var_table.put(var_name, var_temp);
			piglet_print("MOVE "+var_temp+"\n",indent);
			n.f2.accept(this,argu);
		}
		else {
			String var_temp = var_table.get(var_name);
			piglet_print("MOVE "+var_temp+"\n",indent);
			n.f2.accept(this,argu);
		}
		return null;
	}
	/**
	 * Grammar production:
	 * f0 -> Identifier()
	 * f1 -> "["
	 * f2 -> Expression()
	 * f3 -> "]"
	 * f4 -> "="
	 * f5 -> Expression()
	 * f6 -> ";"
	 */
	public String visit(ArrayAssignmentStatement n, String argu) {
		String var_name = n.f0.f0.tokenImage;
		var_name = argu+"_"+var_name; //??
		String var_addr = var_table.get(var_name);//?
		String bias = getTemp();
		piglet_print("MOVE "+bias+"\n",indent);
		n.f2.accept(this,argu);
		piglet_print("HSTORE PLUS "+var_addr+" TIMES 4 "+bias+" 0"+"\n",indent);
		n.f5.accept(this,argu);
		return null;
	}
	/**
	 * f0 -> "if"
	 * f1 -> "("
	 * f2 -> Expression()
	 * f3 -> ")"
	 * f4 -> Statement()
	 * f5 -> "else"
	 * f6 -> Statement()
	 */
	public String visit(IfStatement n, String argu) {
		piglet_print("CJUMP\n",indent++);
		n.f2.accept(this,argu);
		String lab1=getLabel();
		piglet_print(lab1+"\n",indent);
		n.f4.accept(this,argu);
		String lab2 = getLabel();
		piglet_print("JUMP "+lab2+"\n",indent);
		piglet_print(lab1+"\n",indent++);
		n.f6.accept(this,argu);
		indent--;
		piglet_print(lab2+" NOOP\n",indent);
		indent--;
		return null;
	}
	/**
	 * f0 -> "while"
	 * f1 -> "("
	 * f2 -> Expression()
	 * f3 -> ")"
	 * f4 -> Statement()
	 */
	public String visit(WhileStatement n, String argu) {
		String lab1=getLabel();
		piglet_print("JUMP "+lab1+"\n",indent++);
		piglet_print(lab1+" CJUMPsz\n",indent);
		n.f2.accept(this,argu);
		String lab2 = getLabel();
		piglet_print(lab2+"\n",indent);
		n.f4.accept(this,argu);
		piglet_print("JUMP "+lab1+"\n",indent);
		piglet_print(lab2+" NOOP\n",indent);
		indent--;
		return null;
	}
	/**
	 * f0 -> "System.out.println"
	 * f1 -> "("
	 * f2 -> Expression()
	 * f3 -> ")"
	 * f4 -> ";"
	 */
	public String visit(PrintStatement n, String argu) {
		piglet_print("PRINT\n",indent++);
		n.f2.accept(this,argu);
		indent--;
		return null;
	}
		/**
		 * f0 -> Expression()
		 * f1 -> ( ExpressionRest() )*
		 */
	public String visit(ExpressionList n, String argu) {
		n.f0.accept(this,argu);
		n.f1.accept(this,argu);
		return null;
	}
	/**
	 * f0 -> ","
	 * f1 -> Expression()
	 */
	public String visit(ExpressionRest n, String argu) {
		n.f1.accept(this,argu);
		return null;
	}
	
	public void piglet_print(String s, int indent){
		String res = "";
		for(int i=0;i<indent;i++) {
			res+="    ";
		}
		res+=s;
		try {
			TranslateTable.writer.write(res);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return;
	}

}
