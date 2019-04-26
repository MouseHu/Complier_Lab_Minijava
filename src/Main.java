import java.io.*;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map.Entry;

import javafx.util.Pair;
import visitor.*;
import syntaxtree.*;
import symbol.*;
import piglet.*;

public class Main {
	public static void main(String args[]){
		try{
			//InputStream in = new FileInputStream(args[0]);
			//InputStream in = new FileInputStream("./examples/TreeVisitor.java");// BinaryTree BubbleSort Factorial LinearSearch MoreThan4 LinkedList QuickSort TreeVisitor
			//InputStream in = new FileInputStream("./examples/TreeVisitor-Error.java");
			String parse_file = "Test";
			InputStream in = new FileInputStream("./"+parse_file+".java");
			String outfile = "./piglet/"+parse_file+".pg";
			new MiniJavaParser(in);
			Node root = MiniJavaParser.Goal();
			
			BuildSymbolTableVisitor symbolTableVisitor= new BuildSymbolTableVisitor();
			root.accept(symbolTableVisitor,(MType)null);
			//symbolTableVisitor.InheritCheck();
			
			HashMap<Pair<String,MType>,MType> symbolTable = symbolTableVisitor.getTable();
			root.accept(new TypeCheckVisitor(symbolTable),symbolTableVisitor.getGlobalScope());
			//System.out.println(symbolTable);
			System.out.println("Type Check Finished. No Error Found.");
			root.accept(new TranslateVisitor(symbolTable,outfile),symbolTableVisitor.getGlobalScope());


			
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (TokenMgrError e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}