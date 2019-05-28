package toKanga.Gen_interval;
import java.util.Comparator;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.TreeSet;
import spiglet.visitor.*;
import spiglet.syntaxtree.*;
//���Է���
public class Live_interval_visitor extends DepthFirstVisitor{
	int position=0;//��¼��ǰλ��
	public int max_args=0;//��������
	Hashtable<String, Integer>Labels = new Hashtable<String,Integer>();//��¼��ת��ǩ��hash��
	public TreeSet<EachTemp_interval>liveInterval = new TreeSet<EachTemp_interval>(new Comparator<EachTemp_interval>()
	{//����liveInterval������Ծ���ڵ���������
		public int compare(EachTemp_interval a, EachTemp_interval b)
		{
			if(a.liveInterval.begin == b.liveInterval.begin)
				return 1;
			return (a.liveInterval.begin - b.liveInterval.begin);
		}
	});
	
	Hashtable<Integer, EachTemp_interval>hashLI = new Hashtable<Integer, EachTemp_interval>();
	//��¼ÿһ�������Ļ�������
	
	/**
	 * Grammar production:
	 * f0 -> ( ( Label() )? Stmt() )*
	 */
	public void visit(StmtList n)
	{
		if ( n.f0.present() )
	         for ( Enumeration<Node> e = n.f0.elements(); e.hasMoreElements(); )
	         {
	        	 position++;//ÿһ�䣬position++;
	        	 e.nextElement().accept(this);
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
	public void visit(Call n)//��¼������
	{
		if(max_args < n.f3.size())
		{
			max_args = n.f3.size();
		}
		n.f1.accept(this);
		n.f3.accept(this);
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
		n.f4.accept(this);
	}
	
	/**
	 * Grammar production:
	 * f0 -> "TEMP"
	 * f1 -> IntegerLiteral()
	 */
	public void visit(Temp n)
	{
		Integer t = new Integer(Integer.parseInt(n.f1.f0.toString()));
		if(t.intValue()>=0&&t.intValue()<=3 && !hashLI.containsKey(t))
		{//�����TEMP0--TEMP3,Ϊ�˷�ֹ�ݹ����ʱa0-a3���ƻ���������Ҳ������Է�����ռ��ͨ�üĴ���
			EachTemp_interval a = new EachTemp_interval(Integer.parseInt(n.f1.f0.toString()), 0, position);
			hashLI.put(t, a);
			liveInterval.add(a);//ÿһ���������������
			return;
		}		
		if(t.intValue()>=4&&t.intValue()<=19)
		{//��4-19����ʱ����������һ�����ڴ��У����ز�����Է�����
			return;
		}		
		if(hashLI.containsKey(t))
		{//������������������Է���
			EachTemp_interval a = hashLI.get(t);
			a.liveInterval.end = position;			
		}
		else
		{
			EachTemp_interval a = new EachTemp_interval(Integer.parseInt(n.f1.f0.toString()), position, position);
			hashLI.put(t, a);
			liveInterval.add(a);
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
		if(n.f0.which == 2)			//��ʱ��label�����в���,��ÿһ����ת��label��������������������
			return;
		else
			n.f0.accept(this);
	}
	
	public void visit(Label n)//��ÿһ��label��Ҫ��hash�����ǿ϶���
	{
		if(Labels.containsKey(n.f0.toString()))
		{
			System.out.println("Error in LIBuildVisitor.visit(Label n), duplicate label defines may exist.");
			System.exit(1);
		}
		else
		{
			Labels.put(n.f0.toString(), new Integer(position));
		}
		return;		
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
		if(Labels.containsKey(n.f2.f0.toString()))
		{
			int endn = Labels.get(n.f2.f0.toString()).intValue();
			modifyInterval_end(endn);
		}
		return;
	}
	/**
	 * Grammar production:
	 * f0 -> "JUMP"
	 * f1 -> Label()
	 */
	public void visit(JumpStmt n)
	{
		if(Labels.containsKey(n.f1.f0.toString()))
		{
			int endn = Labels.get(n.f1.f0.toString()).intValue();
			modifyInterval_end(endn);
		}
		return;
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
		position++;//����ǲ����
		n.f3.accept(this);
	}
	/*modifyInterval_end
	 * 	
	 ��������Ĺ������ڣ�����ÿһ����ת��������Ҫ���һ���Ƿ����ĳһ������Ȼ����л������������
	����������ǣ����ĳһ��temp����ʼλ�ô���label����ʼλ�ã��ҵ���temp�ļȶ�����λ����label��
	 ��ʼλ�ú͵�ǰposition������ת��䣩֮�䣬����Ҫ��temp�Ľ���λ�ý���������
	 */
	void modifyInterval_end(int i)
	{
		Iterator<EachTemp_interval> itr = liveInterval.iterator();
		while(itr.hasNext())
		{
			EachTemp_interval a = itr.next();
			if(a.liveInterval.begin > i)
				break;
			else
				if(i<=a.liveInterval.end && a.liveInterval.end<=position)
					a.liveInterval.end = position;
		}
		return;
	}
}
