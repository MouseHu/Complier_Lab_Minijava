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
	public MType visit(Expression n,MType argu){
		return n.f0.accept(this,argu);
	}
	
	public MType visit(PrimaryExpression n,MType argu){
		return n.f0.accept(this,argu);
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
	
	public MType visit(MainClass n,MType argu){
		this.globalScope = argu;
		String id = n.f1.f0.toString();
		MType declaration =  symbolTable.get(MType.Key(id, argu));
		n.f1.accept(this,declaration);
		n.f11.accept(this,declaration);
		n.f14.accept(this,declaration);
		n.f15.accept(this,declaration);
		return declaration;
	}
	
	public MType visit(MethodDeclaration n,MType argu){
		String id = n.f2.f0.toString();
		MType declaration =  symbolTable.get(MType.Key(id, argu));
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
		//System.out.println(MType.Key(id, scope));
		while((scope!=null) &&(declaration==null)){
			scope = scope.scope;
			declaration =  symbolTable.get(MType.Key(id, scope));
		}
		if(declaration == null){
			System.out.println("Error: Declaration of token \""+id+"\" not found");
			System.exit(1);
		}
		//System.out.println(declaration.toString()+declaration.getType());
		return declaration;
	}
	
	public MType visit(AndExpression n,MType argu){
		MType type1 = n.f0.accept(this,argu);
		MType type2 = n.f2.accept(this,argu);
		if((type1.getType()!="boolean")){
			System.out.println("Error: the type of experssions for && operator must be boolean. Got: "+type1.getType());
			System.exit(1);
		}else if((type2.getType()!="boolean")){
			System.out.println("Error: the type of experssions for && operator must be boolean. Got: "+type2.getType());
			System.exit(1);
		}
		return new MType("boolean");
	}
	public MType visit(AllocationExpression n, MType argu){
		//System.out.println("???");
		System.out.println(n.f1.f0.toString());
		System.out.println(n.f1.accept(this,argu));
		return n.f1.accept(this,argu);
	}
	
	public MType visit(CompareExpression n,MType argu){
		MType type1 = n.f0.accept(this,argu);
		MType type2 = n.f2.accept(this,argu);
		if((type1.getType()!="int")){
			System.out.println("Error: the type of experssions for < operator must be int. Got: "+type1.getType());
			System.exit(1);
		}else if((type2.getType()!="int")){
			System.out.println("Error: the type of experssions for < operator must be int. Got: "+type2.getType());
			System.exit(1);
		}
		return new MType("boolean");
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

	
}
