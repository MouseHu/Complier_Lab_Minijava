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

public class TranslateVisitor extends GJDepthFirst<String, MType>{
	int indent = 0;
	int label = 0;
	int temp_num = 20;
	public static OutputStreamWriter writer;
	String outfile;
	//HashMap<Pair<MType,String>,MType> instanceTable=new HashMap<>();
	HashMap<Pair<String,MType>,MType> symbolTable = null;
	MType globalScope = null;
	TypeCheckVisitor typecheck;
	public TranslateVisitor(HashMap<Pair<String,MType>,MType> symbolTable,String outfile) throws FileNotFoundException {
		this.symbolTable = symbolTable;
		typecheck = new TypeCheckVisitor(symbolTable);
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

	public String visit(FormalParameterList n, MType argu) {
		return null;
	}
	
	public String visit(FormalParameter n, MType argu) {
		return null;
		
	}
	
	public String visit(FormalParameterRest n, MType argu) {
		return null;
		
	}
	
	/*
	public String visit(Type n, MType argu) {
	
	}
	public String visit(ArrayType n, MType argu) {
		
	}
	public String visit(BooleanType n, MType argu) {
		
	}
	public String visit(IntegerType n, MType argu) {
		
	}
	*/
	
	public String visit(Goal n, MType argu) {
		globalScope = argu;
		typecheck.globalScope=argu;
		n.f0.accept(this,argu);
		n.f1.accept(this,argu);
		return null;
	}

	public String visit(TypeDeclaration n, MType argu) {
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
	public String visit(Expression n,MType argu){
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
	public String visit(PrimaryExpression n,MType argu){
		n.f0.accept(this,argu);
		return null;
	}
	
	public String visit(IntegerLiteral n, MType argu) {
		piglet_print(n.f0.tokenImage+"\n",indent);
		return null;
	}
	public String visit(TrueLiteral n, MType argu) {
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
	public String visit(ArrayAllocationExpression n, MType argu) {
		piglet_print("BEGIN\n",indent++);
		String lab1 = getTemp();
		piglet_print("MOVE "+lab1+"\n",indent);
		n.f3.accept(this,argu);
		String addr = getTemp();
		piglet_print("MOVE "+addr+" HALLOCATE TIMES 4 PLUS 1 "+lab1+"\n",indent);
		piglet_print("HSTORE "+addr+" 0 "+lab1+"\n",indent);
		piglet_print("RETURN "+addr+"\n",indent);
		piglet_print("END\n",--indent);
		return null;
	}
	
	
	public String visit(BracketExpression n, MType argu) {
		return n.f1.accept(this, argu);
	}
	  /**
	   * f0 -> "!"
	   * f1 -> Expression()
	   */
	public String visit(NotExpression n, MType argu) {
		piglet_print("LT\n",indent);
		n.f1.accept(this,argu);
		piglet_print("1\n",indent);
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
	public String visit(ClassDeclaration n,MType scope){
		String className = n.f1.f0.tokenImage;
		MType mclass = symbolTable.get(MType.Key(className,scope));
		n.f4.accept(this,mclass);
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
	public String visit(ClassExtendsDeclaration n,MType scope){
		String className = n.f1.f0.tokenImage;
		MType mclass = symbolTable.get(MType.Key(className,scope));
		n.f6.accept(this,mclass);
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
	public String visit(MainClass n, MType scope){
		System.out.println("hhh");
		piglet_print("MAIN\n",indent++);
		String className = n.f1.f0.tokenImage;
		MType mclass = symbolTable.get(MType.Key(className,scope));
		MType mmethod = symbolTable.get(MType.Key("main", mclass));
		n.f14.accept(this,mclass);
		n.f15.accept(this,mclass);
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
	public String visit(MethodDeclaration n,MType scope){
		MMethod method = (MMethod) symbolTable.get(MType.Key(n.f2.f0.tokenImage,scope));
		int paramNum = method.paramNum();
		

		piglet_print(scope.getType()+"_"+n.f2.f0.tokenImage+" [ 2 ]\n", indent++);
		piglet_print("BEGIN\n", indent++);
		for(int i=0; i<paramNum; i++) {
			String reg = getTemp();
			piglet_print("HLOAD "+reg+" TEMP 1 "+i*4+"\n",indent);
			method.paramList.get(i).setRegister(reg);
		}
		n.f8.accept(this,method);
		piglet_print("RETURN\n", indent++);
		n.f10.accept(this,method);
		indent--;
		piglet_print("END\n", --indent);
		indent--;
		return null;                                                                                       
	}
	
	public String visit(VarDeclaration n,MType scope){
		String varName = n.f1.f0.tokenImage;
		MVariable variable = (MVariable) symbolTable.get(MType.Key(varName, scope));
		if(variable.getRegister()=="")
			variable.setRegister(getTemp());
		return null;
		
	}
	
	public String visit(Identifier n,MType scope){
		//System.out.println("Iden:"+n.f0.tokenImage);
		String varName = n.f0.tokenImage;
		MVariable mvariable= (MVariable) symbolTable.get(MType.Key(varName,scope));
		String reg;
		if(mvariable==null){
			//System.out.println(n.f0.tokenImage+"null!");
			//System.out.println("scope:"+scope.getType()+varName);
			scope = ((MMethod)scope).scope;

			assert(scope instanceof MClass);
			while(mvariable==null){
				assert(scope != null);
				mvariable= (MVariable)(symbolTable.get(MType.Key(varName,scope)));
				if(mvariable==null)
					scope = ((MClass)scope).parent;
			}
			//String vTable = mvariable.getRegister();
			String vTable = "TEMP 0";
			
			//System.out.println("vTableaddr:"+vTable);
			//MClass mclass= (MClass) symbolTable.get(MType.Key((mvariable).getRunningType(),globalScope));
			reg = getTemp();
			piglet_print("BEGIN\n",indent++);
			piglet_print("HLOAD "+reg+" "+vTable+" "+(((MClass)scope).variableNumber(varName)*4+4)+"\n",indent);
			piglet_print("RETURN "+reg+" \n",indent);
			piglet_print("END\n",--indent);
			//reg = ???;
		}
		else {

			reg=mvariable.getRegister();
			piglet_print(reg+"\n",indent);
		}
			
		return null;
		
	}
	
	public String visit(ThisExpression n, MType argu) {
		piglet_print("TEMP 0\n",indent);
		return null;
	}
	   /**
	    * f0 -> PrimaryExpression()
	    * f1 -> "&&"
	    * f2 -> PrimaryExpression()
	    */
	public String visit(AndExpression n,MType argu){
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
	public void allocateDTable(MClass mclass,String dTableAddr){
		int pos = 0;
		//System.out.println("mclass:"+mclass.id);
		if(mclass.parent!=null){
			//System.out.print("find parent!");
			allocateDTable(mclass.parent,dTableAddr);
		}
		for (Entry<String, Pair<MMethod, Integer>> entry : mclass.methods.entrySet()) {
			MMethod method =entry.getValue().getKey();
			piglet_print("HSTORE "+dTableAddr+" "+4*mclass.methodNumber(method.id)+" "+mclass.id+"_"+method.id+"\n",indent);
		}
	}
	public void allocateVTable(MClass mclass,String vTableAddr){
		int pos = 0;
		if(mclass.parent!=null){
			allocateVTable(mclass.parent,vTableAddr);
			pos+=mclass.parent.variableSize();
		}
		for (Entry<String, Pair<MVariable, Integer>> entry : mclass.variables.entrySet()) {
			//MVariable variable =entry.getValue().getKey();
			int ind = entry.getValue().getValue();
			piglet_print("HSTORE "+vTableAddr+" "+4*(ind+pos+1)+" "+"0\n",indent);
		}
	}
	  /**
	   * f0 -> "new"
	   * f1 -> Identifier()
	   * f2 -> "("
	   * f3 -> ")"
	   */
	public String visit(AllocationExpression n, MType scope){
		String className = n.f1.f0.toString();
		MClass mclass = (MClass)symbolTable.get(MType.Key(className,globalScope));
		//String tmp = getTemp();
		//piglet_print("MOVE "+tmp+"\n", indent++);
		piglet_print("BEGIN\n",indent++);
		
		//Allocate Dtable
		String dTableAddr = getTemp();
		System.out.println("Allocate "+n.f1.f0.tokenImage+":size "+4*mclass.methodSize());
		piglet_print("MOVE "+dTableAddr+" HALLOCATE "+4*mclass.methodSize()+"\n",indent);
		allocateDTable(mclass,dTableAddr);
		
		//Allocate Vtable
		String vTableAddr = getTemp();
		piglet_print("MOVE "+vTableAddr+" HALLOCATE "+ 4*(mclass.variableSize()+1)+"\n",indent);
		allocateVTable(mclass,vTableAddr);
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
	public String visit(CompareExpression n,MType argu){
		piglet_print("LT\n",indent);
		n.f0.accept(this, argu);
		n.f2.accept(this, argu);
		return null;
	}
	   /**
	    * f0 -> PrimaryExpression()
	    * f1 -> "+"
	    * f2 -> PrimaryExpression()
	    */
	public String visit(PlusExpression n, MType argu) {
		piglet_print("PLUS\n",indent);
		n.f0.accept(this, argu);
		n.f2.accept(this, argu);
		return null;
	}
	   /**
	    * f0 -> PrimaryExpression()
	    * f1 -> "-"
	    * f2 -> PrimaryExpression()
	    */
	public String visit(MinusExpression n, MType argu) {
		piglet_print("MINUS\n",indent);
		n.f0.accept(this, argu);
		n.f2.accept(this, argu);
		return null;
	}
	   /**
	    * f0 -> PrimaryExpression()
	    * f1 -> "*"
	    * f2 -> PrimaryExpression()
	    */
	public String visit(TimesExpression n,MType argu){
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

	public String visit(ArrayLookup n, MType argu) {
		//System.out.println("lookup:"+argu.getType());
		piglet_print("BEGIN\n",indent++);
	    String arr_addr = getTemp();
	    piglet_print("MOVE "+arr_addr+"\n",indent);
	    n.f0.accept(this,argu);
	    String bias = getTemp();
	    piglet_print("MOVE "+bias+"\n",indent);
	    n.f2.accept(this,argu);
	    piglet_print("MOVE "+arr_addr+" PLUS "+arr_addr+" TIMES 4 PLUS 1 "+bias+"\n",indent);
	    String len = getTemp();

	    piglet_print("HLOAD "+len+" "+arr_addr+" 0\n",indent);

	    piglet_print("RETURN "+len+"\n",--indent);
	    piglet_print("END\n",indent);
	    return null;
	}
	   /**
	    * f0 -> PrimaryExpression()
	    * f1 -> "."
	    * f2 -> "length"
	    */
	public String visit(ArrayLength n, MType argu) {
		piglet_print("BEGIN\n",indent++);
	    String arr_addr = getTemp();
	    piglet_print("MOVE "+arr_addr+"\n",indent);
	    n.f0.accept(this,argu);
	    String len = getTemp();
	    piglet_print("HLOAD "+len+" "+arr_addr+" 0\n",indent);
	    piglet_print("RETURN "+len+"\n",--indent);
	    piglet_print("END\n",indent);
	    return null;
	}
	   /**
	    * f0 -> PrimaryExpression()
	    * f1 -> "."
	    * f2 -> Identifier()
	    * f3 -> "("
	    * f4 -> ( ExpressionList() )?
	    * f5 -> ")"
	    */
	public String visit(MessageSend n, MType argu) {
		piglet_print("CALL\n",indent);
		piglet_print("BEGIN\n",indent++);
		String objaddr = getTemp();
		piglet_print("MOVE "+objaddr+"\n",indent);
		//////////////////////////////
		n.f0.accept(this,argu);
		String vtableaddr = getTemp();
		String funcaddr = getTemp();
		MType pe = n.f0.accept(typecheck, argu);
		MClass mclass = null;
		if(pe instanceof MVariable)
			mclass= (MClass) symbolTable.get(MType.Key(((MVariable)pe).getType(),globalScope));
		//mclass= (MClass) symbolTable.get(MType.Key(((MVariable)pe).getRunnningType(),globalScope));

		else if(pe instanceof MClass)
			mclass = (MClass)pe;
		else if(pe instanceof MMethod)
			mclass = (MClass) symbolTable.get(MType.Key(((MMethod)pe).getType(),globalScope));
		else
			assert(false);
		String func_name = n.f2.f0.tokenImage;
		int fun_num = mclass.methodNumber(func_name);
		MMethod method = mclass.getMethod(func_name);
		piglet_print("HLOAD "+vtableaddr+" "+objaddr+" 0\n",indent);
		piglet_print("HLOAD "+funcaddr+" "+vtableaddr+" "+(fun_num*4)+"\n",indent);
		piglet_print("RETURN "+funcaddr+"\n",--indent);
		piglet_print("END\n",indent);
		//????????????????????????
		piglet_print("("+objaddr+"\n",indent);
		piglet_print("BEGIN\n",indent++);
		String lab = getTemp();
		piglet_print("MOVE "+lab+" HALLOCATE TIMES 4 "+(method.paramNum())+"\n",indent);
		
		ExpressionList list = (ExpressionList)n.f4.node;
	    if (list != null){
	    	ArrayList<Expression> arrayList = new ArrayList<>();
	    	arrayList.add(list.f0);
	    	for (Node node: list.f1.nodes)
	    		arrayList.add(((ExpressionRest)node).f1);
	    	int size = arrayList.size();
	    	for(int i=0;i<size;i++) {
	    		piglet_print("HSTORE "+lab+" "+i*4+"\n",indent++);
	    		arrayList.get(i).accept(this,argu);
	    		indent--;
	    	}
	    }
	    piglet_print("RETURN "+lab+"\n",indent);
	    piglet_print("END\n",--indent);
		piglet_print(")\n",indent);
		return null;
	}
	
	public String visit(Block n, MType argu) {
		return n.f1.accept(this, argu);
	}
			/**
		    * AssignmentStatement
		    * f0 -> Identifier()
		    * f1 -> "="
		    * f2 -> Expression()
		    * f3 -> ";"
		    */
	public String visit(AssignmentStatement n, MType scope) {
		String varName = n.f0.f0.tokenImage;
		//System.out.println(varName);
		//System.out.println(scope.getType());
		MType variable = symbolTable.get(MType.Key(varName,scope));
		String varTemp;
		if(variable==null){
			MType varScope = ((MMethod)scope).scope;
			variable = symbolTable.get(MType.Key(varName,varScope));
			while(variable==null){
				varScope=((MClass)varScope).parent;
				variable = symbolTable.get(MType.Key(varName,varScope));
			}
			System.out.println(variable);
			varTemp ="TEMP 0 ";
			piglet_print("HSTORE "+ varTemp +(((MClass)varScope).variableNumber(varName)*4+4)+"\n",indent);
		}
		else {
			
			if(variable.getRegister()==""){
				varTemp = getTemp();
				variable.setRegister(varTemp);
			}
			else{
					varTemp = variable.getRegister();
			}
			piglet_print("MOVE "+varTemp+"\n",indent);
		}
		
		//System.out.println(scope.getType());
		//System.out.println(n.f2.accept(typecheck,scope));
		//System.out.println(variable);
		((MVariable)variable).setRunningType(n.f2.accept(typecheck,scope).getType());
		n.f2.accept(this,scope);
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
	public String visit(ArrayAssignmentStatement n, MType scope) {
		String varName = n.f0.f0.tokenImage;
		MType variable = symbolTable.get(MType.Key(varName,scope));
		String varTemp;
		String var_addr;
		if(variable==null){
			MType varScope=((MMethod)scope).scope;
			variable = symbolTable.get(MType.Key(varName,varScope));
			while(variable==null){
				varScope=((MClass)varScope).parent;
				variable = symbolTable.get(MType.Key(varName,varScope));
			}
			var_addr = getTemp();
			variable = symbolTable.get(MType.Key(varName,varScope));
			varTemp ="TEMP 0 ";
			piglet_print("HLOAD "+ var_addr +" "+varTemp +(((MClass)varScope).variableNumber(varName)*4+4)+"\n",indent);//from move
		}
		else {
			
			if(variable.getRegister()==""){
				var_addr = getTemp();
				variable.setRegister(var_addr);
			}
			else{
					var_addr = variable.getRegister();
			}
		}
		String bias = getTemp();
		piglet_print("MOVE "+bias+"\n",indent);
		n.f2.accept(this,scope);
		piglet_print("HSTORE PLUS "+var_addr+" TIMES 4 PLUS 1 "+bias+" 0"+"\n",indent);
		n.f5.accept(this,scope);
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
	public String visit(IfStatement n, MType argu) {
		//System.out.println("if:"+argu.getType());
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
	public String visit(WhileStatement n, MType argu) {
		//System.out.println("while:"+argu.getType());
		String lab1=getLabel();
		piglet_print("JUMP "+lab1+"\n",indent++);
		piglet_print(lab1+" CJUMP\n",indent);
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
	public String visit(PrintStatement n, MType argu) {
		piglet_print("PRINT\n",indent++);
		n.f2.accept(this,argu);
		indent--;
		return null;
	}
		/**
		 * f0 -> Expression()
		 * f1 -> ( ExpressionRest() )*
		 */
	public String visit(ExpressionList n, MType argu) {
		n.f0.accept(this,argu);
		n.f1.accept(this,argu);
		return null;
	}
	/**
	 * f0 -> ","
	 * f1 -> Expression()
	 */
	public String visit(ExpressionRest n, MType argu) {
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
			writer.write(res);
			writer.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return;
	}

}
