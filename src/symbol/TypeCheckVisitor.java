package symbol;
import visitor.*;
import syntaxtree.*;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map.Entry;

import javafx.util.Pair;

public class TypeCheckVisitor extends GJDepthFirst<MType,MType>{
	protected HashMap<Pair<String,MType>,MType> symbolTable;
	protected MType globalScope;
	public TypeCheckVisitor(){
		symbolTable = new HashMap<>();
	}	
	public TypeCheckVisitor(HashMap<Pair<String,MType>,MType> _symbolTable){
		symbolTable = _symbolTable;
	}
	public MType visit(Goal n, MType argu) {
		n.f0.accept(this, argu);
		n.f1.accept(this, argu);
		return new MType("goal");
	}

	public MType visit(TypeDeclaration n, MType argu) {
		n.f0.accept(this, argu);
		return new MType("class");
	}
	public MType visit(Expression n,MType argu){
		return n.f0.accept(this,argu);
	}
	
	public MType visit(PrimaryExpression n,MType argu){
		return n.f0.accept(this,argu);
	}
	
	public MType visit(IntegerLiteral n, MType argu) {
		return new MType("int");
	}
	public MType visit(TrueLiteral n, MType argu) {
		return new MType("boolean");
	}
	public MType visit(FalseLiteral n, MType argu) {
		return new MType("boolean");
	}
	
	
	public MType visit(ThisExpression n, MType argu) {
		MType funType = null;
		for(HashMap.Entry<Pair<String, MType>, MType> p: symbolTable.entrySet()) {
			if(p.getValue()==argu) {
				funType = p.getKey().getValue();
				break;
			}
		}
		return funType;
	}
	
	public MType visit(ArrayAllocationExpression n, MType argu) {
		MType exprtype = n.f3.accept(this, argu);
		if(exprtype.type!="int") {
			System.out.println("Error: Length of an arraylist must be an integer instead of " + exprtype.type);
			System.exit(1);
		}
		return new MType("int[]");
	}
	
	public MType visit(AllocationExpression n, MType argu) {
		return n.f1.accept(this, this.globalScope);
	}
	
	public MType visit(BracketExpression n, MType argu) {
		return n.f1.accept(this, argu);
	}
	
	public MType visit(NotExpression n, MType argu) {
		MType exprtype = n.f1.accept(this, argu);
		if(exprtype.type!="boolean") {
			System.out.println("Error: A NotExpression like ![Expr] should have [Expr] with boolean type instead of " + exprtype.type);
			System.exit(1);
		}
		return new MType("boolean");
	}
	
	public MType visit(ClassDeclaration n,MType argu){
		String id = n.f1.f0.toString();
		MType declaration =  symbolTable.get(MType.Key(id, argu));
		n.f3.accept(this,declaration);
		n.f4.accept(this,declaration);
		return declaration;
	}
	
	public MType visit(ClassExtendsDeclaration n,MType argu){
		String id = n.f1.f0.toString();
		MType declaration =  symbolTable.get(MType.Key(id, argu));
		n.f5.accept(this,declaration);
		n.f6.accept(this,declaration);
		return declaration;
	}
	
	public MType visit(MainClass n, MType argu){
		this.globalScope = argu;
		String id = n.f1.f0.toString();
		MType declaration =  symbolTable.get(MType.Key(id, argu));
		//System.out.println("MainClass:" + declaration.type);
		n.f1.accept(this,argu);
		n.f14.accept(this,declaration);
		n.f15.accept(this,declaration);
		return declaration;
	}

	public MType visit(MethodDeclaration n,MType argu){
		String id = n.f2.f0.toString();
		MType declaration = symbolTable.get(MType.Key(id, argu));
		n.f7.accept(this,declaration);
		n.f8.accept(this,declaration);
		MType returnType=n.f10.accept(this,declaration);
		if(returnType.getType()!=declaration.getType()){
			System.out.println("Error: Return type of \""+((MMethod)declaration).id+"\" in class: \""+((MClass)argu).id+"\" does not match. Expecting: \""+declaration.getType()+"\", Got \""+returnType.getType()+"\".");
			System.exit(1);
		}
		return declaration;
	}
	
	public MType visit(VarDeclaration n,MType argu){
		MType type = n.f1.accept(this,argu);
		String typeName = type.getType();
		if((typeName == "int") || (typeName == "boolean") || (typeName == "int[]") )
			return type;
		else{
			MClass mclass = (MClass)symbolTable.get(MType.Key(typeName, globalScope));
			if(mclass==null){
				System.out.println("Error: Type of Class \""+typeName+"\" not found.");
				System.exit(1);
			}
		}
		return type;	
	}
	
	public MType visit(Identifier n,MType argu){
		String id = n.f0.toString();
		MType scope = argu;
		MType declaration =  symbolTable.get(MType.Key(id, argu));
		while((scope!=null) &&(declaration==null)){
			scope = scope.scope;
			declaration =  symbolTable.get(MType.Key(id, scope));
		}
		if(declaration == null){
			System.out.println("Error: Declaration of token \""+id+"\" not found");
			System.exit(1);
		}
		return declaration;
	}
	
	

	public MType visit(AndExpression n,MType argu){
		MType type1 = n.f0.accept(this,argu);
		MType type2 = n.f2.accept(this,argu);
		if((type1.getType()!="boolean")){
			System.out.println("Error: the type of experssions for && operator must be boolean. Got: "+type1.getType());
			System.exit(1);
		}
		else if((type2.getType()!="boolean")){
			System.out.println("Error: the type of experssions for && operator must be boolean. Got: "+type2.getType());
			System.exit(1);
		}
		return new MType("boolean");
	}
	
	public MType visit(CompareExpression n,MType argu){
		MType type1 = n.f0.accept(this,argu);
		MType type2 = n.f2.accept(this,argu);
		if((type1!=null && type1.getType()!="int")){
			System.out.println("Error: the type of experssions for < operator must be int. Got: "+type1.getType());
			System.exit(1);
		}else if((type2!=null && type2.getType()!="int")){
			System.out.println("Error: the type of experssions for < operator must be int. Got: "+type2.getType());
			System.exit(1);
		}
		return new MType("boolean");
	}
	public MType visit(PlusExpression n, MType argu) {
		MType type1 = n.f0.accept(this, argu);
		MType type2 = n.f2.accept(this, argu);
		if(type1.getType() != "int") {
			System.out.println("Error: the type of experssions for + operator must be int. Got: "+type1.getType());
			System.exit(1);
		}
		if(type2.getType()!="int") {
			System.out.println("Error: the type of experssions for + operator must be int. Got: "+type2.getType());
			System.exit(1);
		}
		return new MType("int");
	}
	
	public MType visit(MinusExpression n, MType argu) {
		MType type1 = n.f0.accept(this, argu);
		MType type2 = n.f2.accept(this, argu);
		if(type1.getType() != "int") {
			System.out.println("Error: the type of experssions for - operator must be int. Got: "+type1.getType());
			System.exit(1);
		}
		if(type2.getType()!="int") {
			System.out.println("Error: the type of experssions for - operator must be int. Got: "+type2.getType());
			System.exit(1);
		}
		return new MType("int");
	}
	
	public MType visit(TimesExpression n,MType argu){
		MType type1 = n.f0.accept(this,argu);
		MType type2 = n.f2.accept(this,argu);
		if((type1.getType()!="int")){
			System.out.println("Error: the type of experssions for * operator must be int. Got: "+type1.getType());
			System.exit(1);
		}else if((type2.getType()!="int")){
			System.out.println("Error: the type of experssions for * operator must be int. Got: "+type2.getType());
			System.exit(1);
		}
		return new MType("int");
	}
	
	public MType visit(ArrayLookup n, MType argu) {
		MType isarray = n.f0.accept(this, argu);
		MType isint = n.f2.accept(this, argu);
		if(isarray.getType()!="int[]") {
			System.out.println("Error: assignment to array should have an object with type int[]. Got:"+isarray.getType());
			System.exit(1);
		}
		if(isint.getType()!="int") {
			System.out.println("Error: assignment to array should have an index with type int. Got:"+isint.getType());
			System.exit(1);
		}
		return new MType("int");
	}
	
	public MType visit(ArrayLength n, MType argu) {
		MType arrayname = n.f0.accept(this, argu);
		if(arrayname.getType()!="int[]") {
			System.out.println("Error: only an array name can be follwed by .length. Got:"+arrayname.getType());
			System.exit(1);
		}
		return new MType("int");
	}
	//still buggy, Tree Node = new Tree(); Node.Init(); method
	public MType visit(MessageSend n, MType argu) {
		MType objType = n.f0.accept(this, argu);
		MType idnType = null;
		for(HashMap.Entry<Pair<String, MType>, MType> p: symbolTable.entrySet()) {
			if(p.getKey().getKey() == objType.getType()) {
				idnType = p.getValue();
				break;
			}
		}
		MType metType = n.f2.accept(this, idnType);
		MType optType = n.f4.accept(this, argu);
		return metType;
	}
	
	public MType visit(Block n, MType argu) {
		n.f1.accept(this, argu);
		return null;
	}
	
	public MType visit(AssignmentStatement n, MType argu) {
		MType objType = n.f0.accept(this, argu);
		MType valType = n.f2.accept(this, argu);
		if(objType.getType()!=valType.getType()) {
			System.out.println("Error: assignment type should be consistent. Got:"+objType.getType()+" and "+valType.getType());
			System.exit(1);
		}
		return null;
	}
	
	public MType visit(ArrayAssignmentStatement n, MType argu) {
		MType objType = n.f0.accept(this, argu);
		MType indType = n.f2.accept(this, argu); 
		MType valType = n.f5.accept(this, argu);
		if(objType.getType()!="int[]") {
			System.out.println("Error: should assign value to an array. Got:"+objType.getType());
			System.exit(1);
		}
		if(indType.getType()!="int") {
			System.out.println("Error: index when assigning value to an array should be int. Got:" + indType.getType());
			System.exit(1);
		}
		if(valType.getType()!="int") {
			System.out.println("Error: value assigning to an array should be int. Got:" + valType.getType());
			System.exit(1);
		}
		return null;
	}
	
	public MType visit(IfStatement n, MType argu) {
		MType isbool = n.f2.accept(this, argu);
		MType expr1 = n.f4.accept(this, argu);
		MType expr2 = n.f6.accept(this, argu);
		if(isbool.getType()!="boolean") {
			System.out.println("Error: type of judgement expression after \"if\" should be boolean. Got: "+isbool.getType());
			System.exit(1);
		}
		return null;
	}
	
	public MType visit(WhileStatement n, MType argu) {
		MType isbool = n.f2.accept(this, argu);
		MType statement = n.f4.accept(this, argu);
		if(isbool.getType()!="boolean") {
			System.out.println("Error: type of judgement expression after \"while\" should be boolean. Got: "+isbool.getType());
			System.exit(1);
		}
		return null;
	}
	
	public MType visit(PrintStatement n, MType argu) {
		n.f2.accept(this, argu);
		return null;
	}
	
	public MType visit(ExpressionList n, MType argu) {
		n.f0.accept(this, argu);
		n.f1.accept(this, argu);
		return null;
	}
	
	public MType visit(ExpressionRest n, MType argu) {
		n.f1.accept(this, argu);
		return null;
	}
	
}
