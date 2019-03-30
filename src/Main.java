import java.io.*;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map.Entry;

import javafx.util.Pair;
import visitor.*;
import syntaxtree.*;
import symbol.*;

public class Main {
	public static void main(String args[]){
		try{
			//InputStream in = new FileInputStream(args[0]);
			InputStream in = new FileInputStream("../examples/TreeVisitor-error.java");
			//InputStream in = new FileInputStream("../Test.java");
			new MiniJavaParser(in);
			Node root = MiniJavaParser.Goal();
			BuildSymbolTableVisitor symbolTableVisitor= new BuildSymbolTableVisitor();
			root.accept(symbolTableVisitor,(MType)null);
			symbolTableVisitor.InheritCheck();
			
			HashMap<Pair<String,MType>,MType> symbolTable = symbolTableVisitor.getTable();
			for(Entry<Pair<String, MType>, MType> entry:symbolTable.entrySet()){
				System.out.println("Name:"+entry.getKey().getKey()+" Scope: "+entry.getKey().getValue()+" Node: "+entry.getValue());
			}
			//System.out.println(symbolTableVisitor.getGlobalScope());
			root.accept(new TypeCheckVisitor(symbolTable),symbolTableVisitor.getGlobalScope());
			System.out.println("Type Check Finished. No Error Found.");
			
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (TokenMgrError e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}