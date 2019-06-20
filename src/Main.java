import java.io.*;
import java.util.HashMap;
import spiglet.SpigletParser;
import spiglet.tospiglet.SpigletVisitor;
import kanga.KangaParser;
import kanga.tokanga.KangaVisitor;
import mips.MipsVisitor;
import javafx.util.Pair;
import symbol.syntaxtree.*;
import symbol.*;
import symbol.ParseException;
import symbol.TokenMgrError;
import symbol.typecheck.BuildSymbolTableVisitor;
import symbol.typecheck.MType;
import symbol.typecheck.TypeCheckVisitor;
import piglet.*;
import piglet.translate.TranslateVisitor;
public class Main {
	
	public static void main(String args[]) {
		try{
			minijava2mips("examples/"+"TreeVisitor");
//			String[] files = {"BinaryTree", "BubbleSort", "Factorial", "LinearSearch", "MoreThan4", "LinkedList", "QuickSort", "TreeVisitor"};
//			for(int i=0;i<files.length;i++) {
//				minijava2mips("examples/"+files[i]);
//			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void minijava2mips(String parse_file) {
		try {
			
			minijava2piglet(parse_file);
			piglet2spiglet(parse_file);
			spiglet2kanga(parse_file);
			kanga2mips(parse_file);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void kanga2mips(String parse_file) throws FileNotFoundException, kanga.ParseException{
		InputStream in = new FileInputStream("./output/kanga/"+parse_file+".kg");
		String outfile = "./output/mips/"+parse_file+".s";//output file path
		
		//Translate
		new kanga.KangaParser(in);
		kanga.syntaxtree.Node kangaroot = KangaParser.Goal();
		kangaroot.accept(new MipsVisitor(outfile),null);
		System.out.println("Kanga to Mips Finished");
	}
	
	public static void spiglet2kanga(String parse_file) throws FileNotFoundException, spiglet.ParseException{
		InputStream in = new FileInputStream("./output/spiglet/"+parse_file+".spg");
		String outfile = "./output/kanga/"+parse_file+".kg";//output file path
		
		//Translate
		new spiglet.SpigletParser(in);
		spiglet.syntaxtree.Node spigletroot = SpigletParser.Goal();
		spigletroot.accept(new KangaVisitor(outfile),null);
		System.out.println("sPiglet to Kanga Finished");
	}
	
	public static void piglet2spiglet(String parse_file) throws FileNotFoundException, ParseException{
		InputStream in = new FileInputStream("./output/piglet/"+parse_file+".pg");
		String outfile = "./output/spiglet/"+parse_file+".spg";//output file path
		
		//Translate
		new PigletParser(in);
		try {
			piglet.syntaxtree.Node root = piglet.PigletParser.Goal();
			root.accept(new SpigletVisitor(outfile),null);
		} catch (piglet.ParseException e) {
			e.printStackTrace();
		}
		
		System.out.println("Piglet to sPiglet Finished");
	}
	
	public static void minijava2piglet(String parse_file) throws ParseException, FileNotFoundException, Exception{
		InputStream in = new FileInputStream("./input/"+parse_file+".java");
		String outfile = "./output/piglet/"+parse_file+".pg";//output file path
		
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