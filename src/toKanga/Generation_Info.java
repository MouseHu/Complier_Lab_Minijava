package toKanga;

import java.util.LinkedList;
import java.util.ListIterator;
import toKanga.Aze_RegisterAllocation.AllocateTable;

public class Generation_Info {//������KangaVisitor��Ҫ�õ������мĴ����������Ϣ
	LinkedList<AllocateTable>allocTables;//��AllocateTable��ɵ��������μ�¼��ÿ����Ծ���ڿ�ʼʱ�����ķ���״��
	ListIterator<AllocateTable> itr;//�������һ��������
	AllocateTable Curr;//ָ��ǰ��AllocateTable
	
	Generation_Info(LinkedList<AllocateTable> a, int f)
	{
		allocTables = a;
		itr = allocTables.listIterator();	
		Curr = itr.next();
	}
	
	boolean hasNext()
	{
		return itr.hasNext();
	}
	
	AllocateTable peeknext()
	{
		if(!itr.hasNext())
			return null;
		AllocateTable t = itr.next();
		itr.previous();
		return t;
	}
	
	AllocateTable next()
	{
		Curr = itr.next();
		return Curr;
	}
}
