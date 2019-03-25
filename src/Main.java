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
			InputStream in = new FileInputStream("./Test.java");
			new MiniJavaParser(in);
			Node root = MiniJavaParser.Goal();
			//System.out.println("??");
			BuildSymbolTableVisitor symbolTableVisitor= new BuildSymbolTableVisitor();
			root.accept(symbolTableVisitor,(MScope)null);
			symbolTableVisitor.InheritCheck();
			HashMap<Pair<String,MScope>,MScope> symbolTable = symbolTableVisitor.getTable();
			for(Entry<Pair<String, MScope>, MScope> entry:symbolTable.entrySet()){
					System.out.println("Name:"+entry.getKey().getKey()+" Scope: "+entry.getKey().getValue()+" Node: "+entry.getValue());
			}
			
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (TokenMgrError e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}