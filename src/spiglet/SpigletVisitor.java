package spiglet;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.HashMap;

import javafx.util.Pair;
import symbol.*;
import visitor_piglet.*;
import syntaxtree_piglet.*;
import visitor.GJDepthFirst;
public class SpigletVisitor extends visitor_piglet.GJDepthFirst<String, MType> {
	int test;
	public SpigletVisitor() {
		this.test = 10;

	}
}
