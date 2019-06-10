import java.io.*;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map.Entry;
import spiglet.SpigletParser;
import kanga.KangaParser;
import toKanga.KangaVisitor;
import toMips.MipsVisitor;
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
			
			//piglet2spiglet("./piglet/examples/QuickSort");
			kanga2mips("./spigletFile/examples/Factorial2");

		} catch (TokenMgrError e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void kanga2mips(String arg0) throws FileNotFoundException, kanga.ParseException{
		String parse_file = arg0;//input file path  TreeVisitor BubbleSort MoreThan4
		InputStream in = new FileInputStream(parse_file+".kg");
		String outfile = parse_file+".s";//output file path
		
		//Translate
		new kanga.KangaParser(in);
		kanga.syntaxtree.Node kangaroot = KangaParser.Goal();
		kangaroot.accept(new MipsVisitor(outfile),null);
		System.out.println("Kanga to Mips Finished");
	}
	
	public static void spiglet2kanga(String arg0) throws FileNotFoundException, spiglet.ParseException{
		String parse_file = arg0;//input file path  TreeVisitor BubbleSort MoreThan4
		InputStream in = new FileInputStream(parse_file+".spg");
		String outfile = parse_file+".kg";//output file path
		
		//Translate
		new spiglet.SpigletParser(in);
		spiglet.syntaxtree.Node spigletroot = SpigletParser.Goal();
		spigletroot.accept(new KangaVisitor(outfile),null);
		System.out.println("sPiglet to Kanga Finished");
	}
	
	public static void piglet2spiglet(String arg0) throws FileNotFoundException, ParseException{
		String parse_file = arg0;//input file path  TreeVisitor BubbleSort MoreThan4
		InputStream in = new FileInputStream(parse_file+".txt");
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
		String outfile = "./piglet/"+parse_file+".spg";//output file path
		
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