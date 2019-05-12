import java.io.*;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map.Entry;

import javafx.util.Pair;
import visitor.*;
//import visitor_piglet;
//import syntaxtree_piglet.*;
import syntaxtree.*;
import syntaxtree_piglet.PigletParser;
import symbol.*;
import piglet.*;
import spiglet.*;
public class Main {
	public static void main(String args[]){
		try{
			//InputStream in = new FileInputStream(args[0]);
			//InputStream in = new FileInputStream("./examples/TreeVisitor.java");// BinaryTree BubbleSort Factorial LinearSearch MoreThan4 LinkedList QuickSort TreeVisitor
			//InputStream in = new FileInputStream("./examples/TreeVisitor-Error.java");
			
			
			String parse_file = "examples/Factorial";//input file path 
//			InputStream in = new FileInputStream("./"+parse_file+".java");
			InputStream in = new FileInputStream("./piglet/"+parse_file+".txt");
			String outfile = "./piglet/"+parse_file+".spg";//output file path
			new PigletParser(in);
//			Node root = MiniJavaParser.Goal();
			syntaxtree_piglet.Node root2 = syntaxtree_piglet.PigletParser.Goal();
			SpigletVisitor spigletvisitor = new SpigletVisitor(outfile);
			root2.accept(spigletvisitor,null);
//			BuildSymbolTableVisitor symbolTableVisitor= new BuildSymbolTableVisitor();
//			root.accept(symbolTableVisitor,(MType)null);
//			symbolTableVisitor.InheritCheck();
//			
//			HashMap<Pair<String,MType>,MType> symbolTable = symbolTableVisitor.getTable();
//			root.accept(new TypeCheckVisitor(symbolTable),symbolTableVisitor.getGlobalScope());
//			//System.out.println(symbolTable);
//			System.out.println("Type Check Finished. No Error Found.");
//			root.accept(new TranslateVisitor(symbolTable,outfile),symbolTableVisitor.getGlobalScope());

			
			
		} catch (syntaxtree_piglet.ParseException e) {
			e.printStackTrace();
		} catch (TokenMgrError e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}