package toKanga;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Comparator;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.TreeSet;
import java.util.Vector;

import spiglet.visitor.*;
import spiglet.syntaxtree.*;
class environ{
	String funcName;
	int position=0;
	int regNum=0;
	LinkedList<Table> tables;
	public environ(LinkedList<Table> t,String s,int pos,int n) {
		position=pos;
		regNum=n;
		funcName=s;
		tables=t;
	}
}
public class KangaVisitor extends GJVoidDepthFirst<environ>{
//	regManager regMananger = new regManager();
	public static OutputStreamWriter writer;
	String outfile;
	int indent;
	public KangaVisitor(String out) throws FileNotFoundException{
		outfile = out;
		writer = new OutputStreamWriter(new FileOutputStream(outfile));
		indent=0;
	}
	public void kpln(String s, int indent){
		String res = "";
		for(int i=0;i<indent;i++) {
			res+="    ";
		}
		res+=s;
		res+='\n';
		try {
			writer.write(res);
			writer.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return;
	}
	public void kp(String s, int indent){
		String res = "";
		for(int i=0;i<indent;i++) {
			res+="    ";
		}
		res+=s;
		res+=' ';
		try {
			writer.write(res);
			writer.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return;
	}
	/**
	 * Grammar production:
	 * f0 -> "MAIN"
	 * f1 -> StmtList()
	 * f2 -> "END"
	 * f3 -> ( Procedure() )*
	 * f4 -> <EOF>
	 */
	public void visit(Goal n,environ e){
		IntervalVisitor vis=new IntervalVisitor();
		n.accept(vis);
		RegAllocator allocator=new RegAllocator(vis.temp_list,0);
		kpln("MAIN [ 0 ][ "+allocator.stackpos+" ][ "+vis.max_args+" ]",indent++);
		int j = 0;
		for(int i=allocator.stackpos-allocator.usedRegNum;i<allocator.stackpos;i++)
		{
			kpln("ASTORE " + " SPILLEDARG " + i + " " + RegNames.REGS[allocator.usedReg[j]],indent);
			j++;
		}
//		regMananger.clear();
		environ env = new environ(allocator.tables,"MAIN",0,allocator.usedRegNum);
		initParam(env, 0);
		n.f1.accept(this,env);
		j=0;
		for(int i=allocator.stackpos-allocator.usedRegNum;i<allocator.stackpos;i++)
		{
			kpln(" ALOAD " + RegNames.REGS[allocator.usedReg[j]] + " SPILLEDARG " + i,indent);
			j++;
		}
		kpln("END",--indent);
		n.f3.accept(this,null);	
	}
	/**
	 * Grammar production:
	 * f0 -> ( ( Label() )? Stmt() )*
	 */
	public void visit(StmtList n, environ env){
		if ( n.f0.present() )
	         for ( Enumeration<Node> e = n.f0.elements(); e.hasMoreElements(); )
	         {
	        	 env.position++;
	        	 e.nextElement().accept(this, env);
	         }
		return;
	}
	/**
	 * Grammar production:
	 * f0 -> NoOpStmt()
	 *       | ErrorStmt()
	 *       | CJumpStmt()
	 *       | JumpStmt()
	 *       | HStoreStmt()
	 *       | HLoadStmt()
	 *       | MoveStmt()
	 *       | PrintStmt()
	 */
	public void visit(Stmt n, environ env) {
//		env.position++;
		n.f0.accept(this,env);
		return;
	}
	/**
	 * Grammar production:
	 * f0 -> Label()
	 * f1 -> "["
	 * f2 -> IntegerLiteral()
	 * f3 -> "]"
	 * f4 -> StmtExp()
	 */
	public void visit(Procedure n, environ e){
		IntervalVisitor vis=new IntervalVisitor();
		n.accept(vis);
		RegAllocator alloc=new RegAllocator(vis.temp_list,Integer.parseInt(n.f2.f0.toString()));
		genHead(n.f0.f0.toString(),Integer.parseInt(n.f2.f0.toString()),alloc.stackpos,vis.max_args,alloc.usedReg,alloc.usedRegNum);
		environ env = new environ(alloc.tables,n.f0.f0.toString(),0,alloc.usedRegNum);
		initParam(env,Integer.parseInt(n.f2.f0.toString()));
		n.f4.accept(this,env);
		genTail(alloc.usedReg,alloc.usedRegNum);
		return;
	}
	/**
	 * Grammar production:
	 * f0 -> "NOOP"
	 */
	public void visit(NoOpStmt n,environ e) {
		kpln("NOOP ",indent);
	}
	/**
	 * Grammar production:
	 * f0 -> "ERROR"
	 */
	public void visit(ErrorStmt n,environ e){
		kpln("ERROR ",indent);
	}
	/**
	 * Grammar production:
	 * f0 -> "CJUMP"
	 * f1 -> Temp()
	 * f2 -> Label()
	 */
	public void visit(CJumpStmt n,environ env){
		kp("CJUMP ",indent);
		String f1 = readTemp(Integer.parseInt(n.f1.f1.f0.tokenImage),env,0);
//		kpln(e.funcName+"_"+n.f2.toString(),indent);
		
		kpln(env.funcName+"_"+n.f2.f0.toString(),indent);
		return;
	}
	/**
	 * Grammar production:
	 * f0 -> "JUMP"
	 * f1 -> Label()
	 */	
	public void visit(JumpStmt n,environ env){
		kpln("JUMP "+env.funcName+"_"+n.f1.f0.toString(),indent);
		return;
	}
	/**
	 * Grammar production:
	 * f0 -> "HSTORE"
	 * f1 -> Temp()
	 * f2 -> IntegerLiteral()
	 * f3 -> Temp()
	 */
	public void visit(HStoreStmt n,environ env) {
		String s1 = readTemp(Integer.parseInt(n.f1.f1.f0.tokenImage), env, 0);
		String s2 = readTemp(Integer.parseInt(n.f3.f1.f0.tokenImage), env, 1);
		kp("HSTORE " + s1,indent );
		n.f2.accept(this, null);
		kpln(" " + s2,indent);
		
	}
	/**
	 * Grammar production:
	 * f0 -> "HLOAD"
	 * f1 -> Temp()
	 * f2 -> Temp()
	 * f3 -> IntegerLiteral()
	 */
	public void visit(HLoadStmt n,environ env){
		temp2reg temp = new temp2reg(0);
//		Temp_location tempLoc1 = new Temp_location();
		String s1 = writeTemp(Integer.parseInt(n.f1.f1.f0.tokenImage), env, 0,temp);
		String s2 = readTemp(Integer.parseInt(n.f2.f1.f0.tokenImage), env, 0);
		kp("HLOAD " + s1 + " " + s2,indent);
		n.f3.accept(this, null);
		kpln("",indent);
		WriteMem_ifTempinMem(temp, s1);
	}
	/**
	 * Grammar production:
	 * f0 -> "MOVE"
	 * f1 -> Temp()
	 * f2 -> Exp()	
	 				 Exp() Grammar production:
	 						* f0 -> Call()
	 						*       | HAllocate()
	 						*       | BinOp()
	 						*       | SimpleExp()
 */
	public void visit(MoveStmt n,environ env){
		int i = n.f2.f0.which;
		temp2reg tempWrite = new temp2reg(0);
		String s1;
		String s2;
		String s3 = "";
		switch(n.f2.f0.which){
		case 0://call
			n.f2.accept(this, env);
			kpln("",indent);
			s3 = writeTemp(Integer.parseInt(n.f1.f1.f0.tokenImage), env, 0,tempWrite);
			kpln("MOVE " + s3 + " v0",indent);
			WriteMem_ifTempinMem(tempWrite, s3);
			return;
		case 1://HAllocate
			
			HAllocate h = ((HAllocate)n.f2.f0.choice);
			s1 = simpleExp(((HAllocate)n.f2.f0.choice).f1, env, 0);
			s3 = writeTemp(Integer.parseInt(n.f1.f1.f0.tokenImage), env, 0,tempWrite);
			kpln("MOVE " + s3 + " v0",indent);
			WriteMem_ifTempinMem(tempWrite, s3);
			return;
		case 2:
			BinOp b = ((BinOp)n.f2.f0.choice);
			s1 = readTemp(Integer.parseInt(b.f1.f1.f0.tokenImage), env, 0);
			s2 = simpleExp(b.f2, env, 1);
			s3 = writeTemp(Integer.parseInt(n.f1.f1.f0.tokenImage), env, 0, tempWrite);
			kp("MOVE " + s3 + " ",indent);
			b.f0.accept(this, null);
			kpln(" " + s1 + " " + s2,indent);
			WriteMem_ifTempinMem(tempWrite, s3);
			return;
		case 3:
			s2 = simpleExp(((SimpleExp)n.f2.f0.choice), env, 0);
			s1 = writeTemp(Integer.parseInt(n.f1.f1.f0.tokenImage), env, 0, tempWrite);
			kpln(" MOVE " + s1 + " " + s2 ,indent);
			WriteMem_ifTempinMem(tempWrite, s1);
			return;
		}
		return;	
	}

	/**
	 * Grammar production:
	 * f0 -> "PRINT"
	 * f1 -> SimpleExp()
	 */
	public void visit(PrintStmt n,environ env){
		String s1 = simpleExp(n.f1, env, 0);
		kpln("PRINT " + s1,indent);
	}
	
	/**
	 * Grammar production:
	 * f0 -> "BEGIN"
	 * f1 -> StmtList()
	 * f2 -> "RETURN"
	 * f3 -> SimpleExp()
	 * f4 -> "END"
	 */
	public void visit(StmtExp n,environ env){
		n.f1.accept(this, env);
		env.position++;
		String s1 = simpleExp(n.f3, env, 0);
		kpln("MOVE " + "v0 " + s1 ,indent);
	}
	
	/**
	 * Grammar production:
	 * f0 -> "CALL"
	 * f1 -> SimpleExp()
	 * f2 -> "("
	 * f3 -> ( Temp() )*
	 * f4 -> ")"
	 */
	public void visit(Call n,environ env){
		int size = n.f3.size();
		int i;
//		env.position++;
		Iterator<Node> Itr = n.f3.nodes.iterator();
		for(i=0;i<4&&i<size;i++)
		{
			Temp t = (Temp)Itr.next();
			String s1 = readTemp(Integer.parseInt(t.f1.f0.tokenImage), env, 0);
			kpln("MOVE " + "a" + i + " " + s1,indent);
		}
		int j=1;
		for(;i<size;i++,j++)
		{
			Temp t = (Temp)Itr.next();
			String s1 = readTemp(Integer.parseInt(t.f1.f0.tokenImage), env, 0);
			kpln("PASSARG " + j + " " + s1,indent);
		}
		String s1 = simpleExp(n.f1, env, 0);
		kpln("CALL " + s1,indent);
	}
	/**
	 * Grammar production:
	 * f0 -> "LT"
	 *       | "PLUS"
	 *       | "MINUS"
	 *       | "TIMES"
	 */
	public void visit(Operator n,environ env){
		switch(n.f0.which)
		{
		case 0: kp("LT ",indent);return;
		case 1: kp("PLUS ",indent);return;
		case 2: kp("MINUS ",indent); return;
		case 3: kp("TIMES ",indent); return;
		}
	}
	/**
	 * Grammar production:
	 * f0 -> <INTEGER_LITERAL>
	 */
	public void visit(IntegerLiteral n,environ env){
		String s = n.f0.toString();
		kp(s+" ",indent);
	}
	/**
	 * Grammar production:
	 * f0 -> <IDENTIFIER>
	 */
	public void visit(Label n,environ env){
		String s = n.f0.toString();
		kp(env.funcName+"_"+s+" ",indent);
	
	}
	void genHead(String name, int a,int b,int c,int[] regs,int usedNum) {
		kpln(name+" [ "+a+" ][ "+b+" ][ "+c+" ]",indent++);
		for(int i=0;i<usedNum;i++) {
			kpln("ASTORE SPILLDARG "+i+" "+RegNames.REGS[regs[i]],indent);
		}
	}
	void genTail(int[] regs,int usedNum) {
		for(int i=0;i<usedNum;i++) {
			kpln("ALOAD "+RegNames.REGS[regs[i]]+" SPILLEDARG "+i,indent);
		}
	}
	
	String readTemp(int t, environ e,int cond) {
		Iterator<Table> itr=e.tables.iterator();
		Table node=e.tables.getFirst();
		int pos=e.position;
		while(itr.hasNext()) {
			node=itr.next();
			if(node.pos>=pos)  break;
		}

		if(node.regs.containsKey(t)) {
			return RegNames.REGS[node.regs.get(t).regnum];
		}
		else if(node.stacks.containsKey(t)) {
			kpln("ALOAD " + RegNames.REGS[cond+22] + " SPILLEDARG " + node.stacks.get(t).stackpos,indent);
			return  RegNames.REGS[cond+22];
		}
		return outfile;
	}
	
	
	public String writeTemp(int t, environ env, int cond,temp2reg temp)
	{
		Iterator<Table> itr=env.tables.iterator();
		Table node=env.tables.getFirst();
		int pos=env.position;
		while(itr.hasNext()) {
			node=itr.next();
			if(node.pos>=pos)  break;
		}

		temp = node.regs.get(t);

		if(temp!=null)
		{
//			int oldTempnum = regMan.getVar(templ.loc);
//			if(c.Curr.spillToMem.containsKey(new Integer(oldTempnum)))
//			{
//				Temp_location LocInMem = c.Curr.spillToMem.get(oldTempnum);
//				kpln(" ASTORE " + " SPILLEDARG " + LocInMem.stackLoc + " " + Regs.REGS[templ.loc] + "\n");
//				c.Curr.spillToMem.remove(oldTempnum);
//			}
//			regMan.assignReg(tempnum,templ.loc);
			return RegNames.REGS[temp.regnum];
		}
		else
		{		
			temp = node.stacks.get(t);
			return  RegNames.REGS[cond+22];
		}	
	}
	
	public String simpleExp(SimpleExp n, environ env, int cond)
	{
		switch(n.f0.which)
		{
			case 0:	return readTemp(Integer.parseInt(((Temp)n.f0.choice).f1.f0.tokenImage), env, cond);
			case 1: return ((IntegerLiteral)n.f0.choice).f0.toString();
			case 2: return ((Label)n.f0.choice).f0.toString();
		}
		return null;
	}	
	public void initParam(environ env,int f1)
	{
		Iterator<Table> itr=env.tables.iterator();
		Table node=env.tables.getFirst();
		while(itr.hasNext()) {
			node=itr.next();
			if(node.pos>=env.position)  
				break;
		}
		int i;
		for(i=0;i<4&&i<f1;i++)
		{				
			if(node.regs.containsKey(i))
			{
				temp2reg t = node.regs.get(i);
				if(t.isreg == true)
				{
					kpln("MOVE " + RegNames.REGS[t.regnum] + " " + RegNames.REGS[i] ,indent);
//					regManager.assignReg(i, t.loc);
				}
				else
				{
					kpln(" ASTORE " + " SPILLEDARG " + t.stackpos + " " + RegNames.REGS[i],indent);
				}
			}
		}
	}
	
	public void WriteMem_ifTempinMem(temp2reg tempLoc, String reg)
	{
		if(!tempLoc.isreg != true)
			kpln(" ASTORE " + " SPILLEDARG " + tempLoc.stackpos + " " + reg,indent);
	
		return;
	}
}