import java.io.*;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map.Entry;

import javafx.util.Pair;
import visitor.*;
//import visitor_piglet;
//import syntaxtree_piglet.*;
import syntaxtree.*;
import syntaxtree_piglet.ParseException;
import syntaxtree_piglet.PigletParser;
import symbol.*;
import piglet.*;
import spiglet.*;
public class Main {
	
	
	
	
	
	public static void main(String args[]) throws syntaxtree_piglet.ParseException{
		try{
			// BinaryTree BubbleSort Factorial LinearSearch MoreThan4 LinkedList QuickSort TreeVisitor
			//1-PrintLiteral 2-Add 3-Call 4-Vars 5-OutOfBounds
			minijava2piglet("./examples/QuickSort");
//			piglet2spiglet("./piglet/examples/QuickSort");
			

		} catch (TokenMgrError e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void piglet2spiglet(String arg0) throws FileNotFoundException, ParseException{
		String parse_file = arg0;//input file path  TreeVisitor BubbleSort MoreThan4
		InputStream in = new FileInputStream(parse_file+".pg");
		String outfile = parse_file+".spg";//output file path
		
		//Translate
		new PigletParser(in);
		syntaxtree_piglet.Node root = syntaxtree_piglet.PigletParser.Goal();
		root.accept(new SpigletVisitor(outfile),null);
		System.out.println("Piglet to sPiglet Finished");
	}
	
	public static void minijava2piglet(String arg0) throws ParseException, FileNotFoundException, Exception{
		String parse_file = arg0;//input file path
		InputStream in = new FileInputStream("./"+parse_file+".java");
		String outfile = "./piglet/"+parse_file+".pg";//output file path
		
		new MiniJavaParser(in);
		Node root = MiniJavaParser.Goal();
		//Build Symbol Table
		BuildSymbolTableVisitor symbolTableVisitor= new BuildSymbolTableVisitor();
		root.accept(symbolTableVisitor,(MType)null);
		symbolTableVisitor.InheritCheck();
		//Type check
		HashMap<Pair<String,MType>,MType> symbolTable = symbolTableVisitor.getTable();
		root.accept(new TypeCheckVisitor(symbolTable),symbolTableVisitor.getGlobalScope());
		System.out.println("Type Check Finished. No Error Found.");
		//Translate
		root.accept(new TranslateVisitor(symbolTable,outfile),symbolTableVisitor.getGlobalScope());
		System.out.println("MiniJava to Piglet Finished");
	}
}