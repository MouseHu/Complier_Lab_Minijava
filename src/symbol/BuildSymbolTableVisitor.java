package symbol;
import visitor.*;
import syntaxtree.*;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map.Entry;

import javafx.util.Pair;
public class BuildSymbolTableVisitor extends GJDepthFirst<MType,MType>{
	protected HashMap<Pair<String,MType>,MType> symbolTable;
	
	public BuildSymbolTableVisitor(){
		symbolTable = new HashMap<>();
	}
	public HashMap<Pair<String,MType>,MType> getTable(){
		return this.symbolTable;
	}
	public MType visit(Goal n, MType argu){
		
		MClassList classList = new MClassList();
		//System.out.println("Global Scope");
		n.f0.accept(this,classList);
		n.f1.accept(this,classList);
		this.symbolTable.put(new Pair<String,MType>("Global Scope",null),classList);
		return classList;
	}
	public MType visit(ClassDeclaration n, MType argu){
		
		String id = n.f1.f0.toString();
		//System.out.println("New class:"+id);
		MClass mclass = new MClass(id,null,argu);
		this.symbolTable.put(new Pair<String, MType>(id,argu), mclass);
		//System.out.println(id);
		n.f1.accept(this,mclass);
		n.f3.accept(this,mclass);
		n.f4.accept(this,mclass);
		MClassList list= (MClassList)argu;
		(list).addClass(mclass,id);
		return mclass;
	}
	
	public MType visit(MainClass n, MType argu){
		
		String id = n.f1.f0.toString();
		String input_id = n.f11.f0.toString();
		MClass mclass = new MClass(id,null,argu);
		MVariable mvariable = new MVariable(input_id,argu,"String[]");
		mclass.addVariable(mvariable);
		this.symbolTable.put(new Pair<String, MType>(id,argu), mclass);
		this.symbolTable.put(new Pair<String, MType>(input_id,mclass), mvariable);
		n.f1.accept(this,mclass);
		n.f11.accept(this,mclass);
		n.f14.accept(this,mclass);
		n.f15.accept(this,mclass);
		((MClassList)argu).addClass(mclass,id);
		return mclass;
	}
	
	public MType visit(ClassExtendsDeclaration n, MType argu){
		String id = n.f1.f0.toString();
		String parent = n.f3.f0.toString();
		//MClass parent = ((MClassList)argu).getClass(parentName);
		MClass mclass = new MClass(id,parent,argu);
		this.symbolTable.put(new Pair<String, MType>(id,argu), mclass);
		n.f1.accept(this,mclass);
		n.f3.accept(this,mclass);
		n.f5.accept(this,mclass);
		n.f6.accept(this,mclass);
		MClassList list= (MClassList)argu;
		(list).addClass(mclass,id);
		return mclass;
	}
	
	public MType visit(VarDeclaration n, MType argu){
		
		String id = n.f1.f0.toString();
		MVariable mvariable = new MVariable(id,argu);
		this.symbolTable.put(new Pair<String, MType>(id,argu), mvariable);
		n.f0.accept(this,mvariable);
		n.f1.accept(this,mvariable);
		n.f2.accept(this,mvariable);
		((VarContainer)argu).addVariable(mvariable);
		return mvariable;
	}
	public MType visit(Type n,MType argu){
		MType type =n.f0.accept(this,argu);
		argu.setType(type.getType());
		return type;
	}
	
	public MType visit(IntegerType n,MType argu){
		return new MType("int");
	}
	
	public MType visit(BooleanType n,MType argu){
		return new MType("boolean");
	}
	public MType visit(ArrayType n,MType argu){
		return new MType("int[]");
	}
	public MType visit(Identifier n,MType argu){
		return new MType(n.f0.toString());
	}
	
	
	public MType visit(MethodDeclaration n, MType argu){
		
		String id = n.f2.f0.toString();
		
		MMethod mmethod = new MMethod(id,argu);
		this.symbolTable.put(new Pair<String,MType>(id,argu), mmethod);
		n.f1.accept(this,mmethod);
		n.f4.accept(this,mmethod);
		n.f7.accept(this,mmethod);
		((MClass)argu).addMethod(mmethod);
		return mmethod;
	}
	
	public MType visit(FormalParameter n, MType argu){
		
		String id = n.f1.f0.toString();
		//System.out.println("New class:"+id);
		MVariable mparameter = new MVariable(id,argu);
		this.symbolTable.put(new Pair<String, MType>(id,argu), mparameter);
		//System.out.println(id);
		n.f0.accept(this,mparameter);
		n.f1.accept(this,mparameter);
		((MMethod)argu).addParameter(mparameter);
		return mparameter;
	}
	
	public VarType convertType(Type t){
		return VarType.Integer;
	}
	
	public MType getGlobalScope(){
		return symbolTable.get(new Pair<String,MType>("Global Scope",null));// problem??
	}
	
	public void InheritCheck(){
		HashMap<String,MClass> classList = ((MClassList) getGlobalScope()).classList;
		int [] visited = new int[classList.size()];
		int [] parents = new int[classList.size()];
		Arrays.fill(visited, -1);
		Arrays.fill(parents, -1);
	    for(Entry<String, MClass> entry:classList.entrySet()){
	    	String parent = entry.getValue().parentName;
	    	if(parent!=null && !classList.containsKey(parent)){
	    		System.out.println("Error: Class: \""+parent+"\" inherited by Class: \""+entry.getKey()+"\" not found.");
	    		System.exit(1);
	    	}
	    	else if(parent !=null){
	    		parents[entry.getValue().serialNumber]=classList.get(parent).serialNumber;
	    	}
	    }
		for(int i =0;i<classList.size();i++)
			dfs(i,visited,parents);// check loop inherit by depth first search
	}
	
	void dfs(int x, int[] visited,int[] parents){
		if(visited[x]>=0)
			return;
	    visited[x] = 0;//x正在被反问，状态为0

	    if(parents[x]>=0 && visited[parents[x]] == -1){//与x相连的结点状态也为-1，代表还未被访问，则继续搜索
	        dfs(parents[x],visited,parents);
	    } else if(parents[x]>=0 && visited[parents[x]]==0){//与x相连的结点状态也为0，代表有环，返回
	    	System.out.println("Error: Loop Class Inheritation");
    		System.exit(1);
	    }
	    visited[x] = 1;//对x的访问结束
	}
	
}
