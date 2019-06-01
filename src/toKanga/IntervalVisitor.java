package toKanga;
import java.util.Comparator;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.TreeSet;
import java.util.Vector;

import spiglet.visitor.*;
import spiglet.syntaxtree.*;

public class IntervalVisitor extends DepthFirstVisitor{
	int position=0;
	public int max_args=0;
	Hashtable<String, Integer> labels = new Hashtable<String,Integer>();

	public TreeSet<TempInterval> temp_list=new TreeSet<TempInterval>(new Comparator<TempInterval>() {
		public int compare(TempInterval x, TempInterval y) {
			if(x.beg==y.beg) {
				return 1;
			}
			return (x.beg-y.beg);
		}
	}); 
	
	Hashtable<Integer, TempInterval> temp_live = new Hashtable<Integer, TempInterval>();
	
	/**
	 * Grammar production:
	 * f0 -> ( ( Label() )? Stmt() )*
	 */
	public void visit(StmtList n)
	{
		Vector<spiglet.syntaxtree.Node> list=n.f0.nodes;
		for(int i=0;i<list.size();i++) {
			position++;
			list.get(i).accept(this);
		}
		return;
	}
	/**
	 * Grammar production:
	 * f0 -> "CALL"
	 * f1 -> SimpleExp()
	 * f2 -> "("
	 * f3 -> ( Temp() )*
	 * f4 -> ")"
	 */
	public void visit(Call n)
	{
		if(max_args<n.f3.size()) {
			max_args=n.f3.size();
		}
		n.f1.accept(this);
		n.f3.accept(this);
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
	public void visit(Procedure n)
	{		
		position=0;
		n.f4.accept(this);
	}
	
	/**
	 * Grammar production:
	 * f0 -> "TEMP"
	 * f1 -> IntegerLiteral()
	 */
	public void visit(Temp n)
	{
		Integer temp_num=Integer.parseInt(n.f1.f0.toString());
		if(temp_num<4&&!temp_live.containsKey(temp_num)) {
			TempInterval t=new TempInterval(temp_num,0,position);
			temp_live.put(temp_num, t);
			temp_list.add(t);
			return;
		}
		if(temp_num>=4&&temp_num<=19) {
			return;
		}
		if(!temp_live.containsKey(temp_num)) {
			TempInterval t=new TempInterval(temp_num,position,position);
			temp_live.put(temp_num, t);
			temp_list.add(t);
		}
		else {
			TempInterval t=temp_live.get(temp_num);
			t.end=position;
		}
		return;
	}
	
	/**
	 * Grammar production:
	 * f0 -> Temp()
	 *       | IntegerLiteral()
	 *       | Label()
	 */
	public void visit(SimpleExp n)
	{
		if(n.f0.which==2) return;
		n.f0.accept(this);
	}
	
	public void visit(Label n)
	{
		labels.put(n.f0.toString(), position);
	}
	/**
	 * Grammar production:
	 * f0 -> "CJUMP"
	 * f1 -> Temp()
	 * f2 -> Label()
	 */
	public void visit(CJumpStmt n)
	{
		n.f1.accept(this);
		if(labels.containsKey(n.f2.f0.toString())) {
			int i = labels.get(n.f2.f0.toString());
			renew_end(i);
		}
	}
	/**
	 * Grammar production:
	 * f0 -> "JUMP"
	 * f1 -> Label()
	 */
	public void visit(JumpStmt n)
	{
		if(labels.containsKey(n.f1.f0.toString())) {
			int i = labels.get(n.f1.f0.toString());
			renew_end(i);
		}
	}
	/**
	 * Grammar production:
	 * f0 -> "BEGIN"
	 * f1 -> StmtList()
	 * f2 -> "RETURN"
	 * f3 -> SimpleExp()
	 * f4 -> "END"
	 */
	public void visit(StmtExp n)
	{
		n.f1.accept(this);
		position++;
		n.f3.accept(this);
	}
	
	void renew_end(int i)
	{
		Iterator<TempInterval> it=temp_list.iterator();
		while(it.hasNext()) {
			TempInterval a=it.next();
			if(a.beg>i) break;
			if(a.end>=i&&a.end<position) {
				a.end=position;
			}
		}
		return;
	}
}
