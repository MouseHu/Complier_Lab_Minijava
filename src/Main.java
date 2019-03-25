import java.io.*;
import visitor.*;
import syntaxtree.*;

class MyVisitor extends DepthFirstVisitor {
	public void visit(VarDeclaration n) {
		Identifier id = (Identifier)n.f1;
		System.out.println("VarName: "+id.f0.toString());
		n.f0.accept(this);
		n.f1.accept(this);
		n.f2.accept(this);
	}
	public void visit(ClassDeclaration n){
		Identifier id = (Identifier)n.f1;
		System.out.println("ClassName: "+id.f0.toString());
		
	}
}

public class Main {
	public static void main(String args[]){
		try{
			//InputStream in = new FileInputStream(args[0]);
			InputStream in = new FileInputStream("./Test.java");
			new MiniJavaParser(in);
			Node root = MiniJavaParser.Goal();	
			root.accept(new MyVisitor());
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (TokenMgrError e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}