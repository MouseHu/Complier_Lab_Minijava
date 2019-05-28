package toKanga;

import java.util.LinkedList;
import java.util.ListIterator;
import toKanga.Aze_RegisterAllocation.AllocateTable;

public class Generation_Info {//包含了KangaVisitor中要用到的所有寄存器分配的信息
	LinkedList<AllocateTable>allocTables;//由AllocateTable组成的链表，依次记录了每个活跃周期开始时变量的分配状况
	ListIterator<AllocateTable> itr;//该链表的一个迭代器
	AllocateTable Curr;//指向当前的AllocateTable
	
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
