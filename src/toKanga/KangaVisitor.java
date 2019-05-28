package toKanga;
import java.util.Enumeration;
import java.util.Iterator;
import spiglet.syntaxtree.*;
import spiglet.visitor.GJVoidDepthFirst;
import toKanga.Aze_RegisterAllocation.Temp_location;
import toKanga.Aze_RegisterAllocation.RegisterAllocator;
import toKanga.Aze_RegisterAllocation.RegisterManager;
import toKanga.Gen_interval.Live_interval_visitor;

public class KangaVisitor extends GJVoidDepthFirst<Generation_Info>{
	RegisterManager regMan = new RegisterManager();
	int position=0;	
	
	
	public void visit(Goal n,Generation_Info c)
	{
		Live_interval_visitor li = new Live_interval_visitor();
		n.f1.accept(li);	//得到每一个temp的活性区间
		
		RegisterAllocator ra = new RegisterAllocator(0, li.liveInterval,li.max_args);
		ra.RegisterAllocation();//分配寄存器
		Generation_Info cc = new Generation_Info(ra.tables, ra.stackPos);
		
		FuncHeadGen("MAIN", 0, ra.stackPos, ra.max_args, ra.usedRegsArray, ra.usedRegNum);
		regMan.clear();
		InitFormal(cc, 0);
		n.f1.accept(this, cc);
		FuncEndGen(ra.stackPos,  ra.usedRegsArray, ra.usedRegNum);
		
		n.f3.accept(this, null);
		
	}
	/**
	 * Grammar production:
	 * f0 -> ( ( Label() )? Stmt() )*
	 */
	public void visit(StmtList n, Generation_Info c)
	{
		if ( n.f0.present() )
	         for ( Enumeration<Node> e = n.f0.elements(); e.hasMoreElements(); )
	         {
	        	 position++;
	        	 e.nextElement().accept(this, c);
	         }
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
	public void visit(Procedure n, Generation_Info c)
	{
		position = 0;
		Live_interval_visitor li = new Live_interval_visitor();
		n.f4.accept(li);
		
		RegisterAllocator ra = new RegisterAllocator(Integer.parseInt(n.f2.f0.toString()), li.liveInterval, li.max_args);
		ra.RegisterAllocation();
		FuncHeadGen(n.f0.f0.toString(), Integer.parseInt(n.f2.f0.toString()), ra.stackPos, ra.max_args,  ra.usedRegsArray, ra.usedRegNum);
		regMan.clear();
		Generation_Info cc = new Generation_Info(ra.tables, ra.stackPos);
		InitFormal(cc, Integer.parseInt(n.f2.f0.toString()));
		n.f4.accept(this,cc);
		FuncEndGen(ra.stackPos,  ra.usedRegsArray, ra.usedRegNum);
	}
	
	public void visit(NoOpStmt n, Generation_Info c)
	{
		
		Put.con(" NOOP\n");
	}

	public void visit(ErrorStmt n, Generation_Info c)
	{
		
		Put.con(" ERROR\n");
	}
	/**
	 * Grammar production:
	 * f0 -> "CJUMP"
	 * f1 -> Temp()
	 * f2 -> Label()
	 */
	public void visit(CJumpStmt n, Generation_Info c)
	{
		String t1 = ReadTemp(n.f1, c, 0);
		Put.con(" CJUMP " + t1 + " ");
		n.f2.accept(this, null);	
		Put.con("\n");
	}
	/**
	 * Grammar production:
	 * f0 -> "JUMP"
	 * f1 -> Label()
	 */	
	public void visit(JumpStmt n, Generation_Info c)
	{
		Put.con(" JUMP ");
		n.f1.accept(this, null);
		Put.con("\n");
	}
	/**
	 * Grammar production:
	 * f0 -> "HSTORE"
	 * f1 -> Temp()
	 * f2 -> IntegerLiteral()
	 * f3 -> Temp()
	 */
	public void visit(HStoreStmt n, Generation_Info c)//只是利用temp里的值，和temp在哪里无关
	{
		String s1 = ReadTemp(n.f1, c, 0);
		String s2 = ReadTemp(n.f3, c, 1);
		Put.con(" HSTORE " + s1 + " ");
		n.f2.accept(this, null);
		Put.con(" " + s2 + "\n");
	}
	/**
	 * Grammar production:
	 * f0 -> "HLOAD"
	 * f1 -> Temp()
	 * f2 -> Temp()
	 * f3 -> IntegerLiteral()
	 */
	public void visit(HLoadStmt n, Generation_Info c)
	{
		Temp_location tempLoc1 = new Temp_location();
		String s1 = WriteTemp(n.f1, c, 0, tempLoc1);
		String s2 = ReadTemp(n.f2, c, 0);
		Put.con(" HLOAD " + s1 + " " + s2 + " ");
		n.f3.accept(this, null);
		Put.con("\n");
		WriteMem_ifTempinMem(tempLoc1, s1);
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
	public void visit(MoveStmt n, Generation_Info c)
	{
		int i = n.f2.f0.which;
		if(i == 0)
		{
			n.f2.accept(this, c);
			Put.con("\n");
			//position++;
			Temp_location tempLoc = new Temp_location();
			String s3 = WriteTemp(n.f1, c, 0,tempLoc);
			Put.con(" MOVE " + s3 + " v0\n" );
			WriteMem_ifTempinMem(tempLoc, s3);
			return;
		}
		else if(i == 1)
		{
			String s1;
			String s2;
			HAllocate h = ((HAllocate)n.f2.f0.choice);
			s1 = SimpleExpCode(h.f1, c, 0);
			//position++;
			Temp_location tempLoc=new Temp_location();
			s2 = WriteTemp(n.f1, c, 0, tempLoc);
			Put.con(" MOVE " + s2 + " HALLOCATE " + s1 + "\n");
			WriteMem_ifTempinMem(tempLoc, s2);
			return;			
		}
		else if(i == 2)
		{
			BinOp b = ((BinOp)n.f2.f0.choice);
			String s1 = ReadTemp(b.f1, c, 0);
			String s2 = SimpleExpCode(b.f2, c, 1);
			//position++;
			Temp_location tempLoc = new Temp_location();
			String s3 = WriteTemp(n.f1, c, 0, tempLoc);
			Put.con(" MOVE " + s3 + " ");
			b.f0.accept(this, null);
			Put.con(" " + s1 + " " + s2 + "\n");
			
			WriteMem_ifTempinMem(tempLoc, s3);
		}
		else if(i == 3)
		{
			String s2 = SimpleExpCode(((SimpleExp)n.f2.f0.choice), c, 0);
			//position++;
			//System.out.println("dafad------------------- " + n.f0.toString() + " " + "Temp " + n.f1.f1.f0.toString() + " "+ n.f2.toString());
			Temp_location tempLoc = new Temp_location();
			String s1 = WriteTemp(n.f1, c, 0, tempLoc);
			Put.con(" MOVE " + s1 + " " + s2 + "\n");
			WriteMem_ifTempinMem(tempLoc, s1);
		}
		return;		
	}

	/**
	 * Grammar production:
	 * f0 -> "PRINT"
	 * f1 -> SimpleExp()
	 */
	public void visit(PrintStmt n,Generation_Info c)
	{
		String s1 = SimpleExpCode(n.f1, c, 0);
		Put.con(" PRINT " + s1 + "\n");
	}
	
	/**
	 * Grammar production:
	 * f0 -> "BEGIN"
	 * f1 -> StmtList()
	 * f2 -> "RETURN"
	 * f3 -> SimpleExp()
	 * f4 -> "END"
	 */
	public void visit(StmtExp n, Generation_Info c)
	{
		n.f1.accept(this, c);
		position++;
		String s1 = SimpleExpCode(n.f3, c, 0);
		Put.con(" MOVE " + "v0 " + s1 + "\n");
	}
	
	/**
	 * Grammar production:
	 * f0 -> "CALL"
	 * f1 -> SimpleExp()
	 * f2 -> "("
	 * f3 -> ( Temp() )*
	 * f4 -> ")"
	 */
	public void visit(Call n, Generation_Info c)
	{
		int size = n.f3.size();
		int i;
		Iterator<Node> Itr = n.f3.nodes.iterator();
		for(i=0;i<4&&i<size;i++)
		{
			Temp t = (Temp)Itr.next();
			String s1 = ReadTemp(t, c, 0);
			Put.con(" MOVE " + "a" + i + " " + s1 + "\n");
		}
		int j=1;
		for(;i<size;i++,j++)
		{
			Temp t = (Temp)Itr.next();
			String s1 = ReadTemp(t, c, 0);
			Put.con(" PASSARG " + j + " " + s1 +  "\n");
		}
		String s1 = SimpleExpCode(n.f1, c, 0);
		Put.con(" CALL " + s1 + "\n");
	}
	
	public void visit(Operator n, Generation_Info c)
	{
		switch(n.f0.which)
		{
		case 0: Put.con(" LT ");return;
		case 1: Put.con(" PLUS ");return;
		case 2: Put.con(" MINUS "); return;
		case 3: Put.con(" TIMES "); return;
		}
	}
	
	public void visit(IntegerLiteral n, Generation_Info c)
	{
		String s = n.f0.toString();
		Put.con(" " + s + " ");
	}
	
	public void visit(Label n, Generation_Info c)
	{
		String s = n.f0.toString();
		Put.con(" " + s + " ");
	}
	
	public String WriteTemp(Temp n, Generation_Info c, int cond, Temp_location tempLoc)
	{
		while(c.peeknext() != null && position >= c.peeknext().start)
		{
			c.next();
		}
		int tempnum = Integer.parseInt(n.f1.f0.tokenImage);
		Temp_location templ = c.Curr.regTable.get(new Integer(tempnum));
		
		tempLoc.isReg = templ.isReg;
		tempLoc.loc = templ.loc;
		tempLoc.stackLoc = templ.stackLoc;
		
		if(templ.isReg == true)
		{
			int oldTempnum = regMan.getVar(templ.loc);
			if(c.Curr.spillToMem.containsKey(new Integer(oldTempnum)))
			{
				Temp_location LocInMem = c.Curr.spillToMem.get(oldTempnum);
				Put.con(" ASTORE " + " SPILLEDARG " + LocInMem.stackLoc + " " + Regs.REGS[templ.loc] + "\n");
				c.Curr.spillToMem.remove(oldTempnum);
			}
			regMan.assignReg(tempnum,templ.loc);//更新就职
			return regMan.getRegName(templ.loc);
		}
		else
		{			
			return regMan.getRegName(cond+22);
		}	
	}
	
	public String ReadTemp(Temp n, Generation_Info c, int cond)
	{
		while(c.peeknext() != null && position >= c.peeknext().start)
		{
			c.next();
		}
		int tempnum = Integer.parseInt(n.f1.f0.tokenImage);
		Temp_location templ = c.Curr.regTable.get(new Integer(tempnum));//该temp的分配情况都在templ中
		if(templ.isReg == true)
		{
			int oldTempnum = regMan.getVar(templ.loc);
			if(c.Curr.spillToMem.containsKey(new Integer(oldTempnum)))
			{
				Temp_location LocInMem = c.Curr.spillToMem.get(oldTempnum);
				Put.con(" ASTORE " + " SPILLEDARG " + LocInMem.stackLoc + " " + Regs.REGS[templ.loc] + "\n");
				c.Curr.spillToMem.remove(oldTempnum);
			}
			regMan.assignReg(tempnum,templ.loc);//更新旧值
			return regMan.getRegName(templ.loc);
		}
		else
		{
			Put.con(" ALOAD " + Regs.REGS[cond+22] + " SPILLEDARG " + templ.stackLoc + "\n");
			return regMan.getRegName(cond+22);//v0只是个中转
		}	
	}
	
	public void WriteMem_ifTempinMem(Temp_location tempLoc, String reg)
	{
		if(tempLoc.isReg == true)
		{
			return;
		}
		else
		{
			Put.con(" ASTORE " + " SPILLEDARG " + tempLoc.stackLoc + " " + reg + "\n");
			return;
		}
	}
	
	public String SimpleExpCode(SimpleExp n, Generation_Info c, int cond)
	{
		switch(n.f0.which)
		{
			case 0:	return ReadTemp((Temp)n.f0.choice, c, cond);
			case 1: return ((IntegerLiteral)n.f0.choice).f0.toString();
			case 2: return ((Label)n.f0.choice).f0.toString();
		}
		return null;
	}	
	public void FuncHeadGen(String funcName, int f1, int f2, int f3, int[] usedR, int usedRegNum)
	{
		Put.con(funcName + " [ " + f1 + " ] [ " + f2 + " ] [ " + f3 + " ]\n");
		int j=0;
		for(int i=f2-usedRegNum;i<f2;i++)
		{
			Put.con(" ASTORE " + " SPILLEDARG " + i + " " + Regs.REGS[usedR[j]] + "\n");
			j++;
		}
	}
	
	public void FuncEndGen(int f2, int[] s, int savedRegNum)
	{
		int j=0;
		for(int i=f2-savedRegNum;i<f2;i++)
		{
			Put.con(" ALOAD " + Regs.REGS[s[j]] + " SPILLEDARG " + i + "\n");
			j++;
		}
		Put.con("END\n");
	}
	
	public void InitFormal(Generation_Info c,int f1)
	{
		int i;
			for(i=0;i<4&&i<f1;i++)
			{				
				if(c.Curr.regTable.containsKey(i))
				{
					Temp_location t = c.Curr.regTable.get(i);
					if(t.isReg == true)
					{
						Put.con(" MOVE " + Regs.REGS[t.loc] + " " + Regs.REGS[i] + "\n");
						regMan.assignReg(i, t.loc);
					}
					else
					{
						Put.con(" ASTORE " + " SPILLEDARG " + t.stackLoc + " " + Regs.REGS[i] + "\n");
					}
				}
			}
	}
}
