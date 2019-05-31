package toKanga;

import java.util.Comparator;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.TreeSet;

class temp2reg{
	int tempnum;
	boolean isreg;
	int regnum;
	int stackpos;
	public temp2reg(int t) {
		tempnum=t;
	}
	public temp2reg(int t, boolean ir, int pos) {
		tempnum=t;
		isreg=ir;
		if(ir==true) regnum=pos;
		else stackpos=pos;
	}
}
class Table{
	int pos;
	Hashtable<Integer,temp2reg> regs;
	Hashtable<Integer,temp2reg> stacks;
	public Table(int p){
		pos=p;
		regs=new Hashtable<Integer,temp2reg>();
		stacks=new Hashtable<Integer,temp2reg>();
	}
	public void addnode(temp2reg t) {
		if(t.isreg==true) regs.put(t.tempnum,t);
		else stacks.put(t.tempnum,t);
	}
}

class regManager{
	public boolean regTable[]=new boolean[24];
	public regManager(){
		for(int i=0;i<24;i++) {
			regTable[i]=false;
		}
	}
	void free(int freeNo) {
		regTable[freeNo]=false;
	}
	int allocate() {
		for(int i=6;i<24;i++) {
			if(regTable[i]==false) {
				regTable[i]=true;
				return i;
			}
		}
		return -1;
	}
}

public class RegAllocator {
	int nargs;
	public int usedRegNum=0;
	TreeSet<TempInterval> tempList;
	int stackpos;
	public LinkedList<Table> tables= new LinkedList<Table>();
	regManager manager;
	public int usedReg[]=new int[24];
	TreeSet<TempInterval> active = new TreeSet<TempInterval>(
			new Comparator<TempInterval>()
			{
				public int compare(TempInterval a,TempInterval b)
				{
					return a.end - b.end;
				}
	});
	
	public RegAllocator(TreeSet<TempInterval> t, int argnum) {
		nargs=argnum;
		tempList=t;
		stackpos=0;
		Table tab=new Table(0);
		for(int i=0;i<nargs&&i<4;i++) {
			temp2reg temp=new temp2reg(i,true,i);
			tab.addnode(temp);
		}
		for(int i=4;i<nargs;i++) {
			temp2reg temp=new temp2reg(i,false,stackpos++);
			tab.addnode(temp);
		}
		tables.add(tab);
		
		/* allocation */
		Iterator<TempInterval> itr=tempList.iterator();
		Table prevnode=tables.getFirst();
		
		//go through tempList, at each point of birth, review table again and add new tables if necessary
		while(itr.hasNext()) {
			TempInterval temp=itr.next();
			boolean add=false;
			if(prevnode.pos<temp.beg) {
				Table temptab=new Table(temp.beg);
				temptab.regs=new Hashtable<Integer,temp2reg>(prevnode.regs);
				temptab.stacks=new Hashtable<Integer,temp2reg>(prevnode.stacks);
				prevnode=temptab;
				add=true;
			}
			prevnode = refresh(prevnode);
			int allopos=manager.allocate();
			if(allopos==-1) {
				TempInterval a=active.last();
				if(a.end>temp.end) { 
					//spill a
					int replaced=prevnode.regs.get(a.temp_num).regnum;
					prevnode.addnode(new temp2reg(a.temp_num,false,stackpos++));
					prevnode.regs.remove(a.temp_num);
					active.remove(a);
					prevnode.addnode(new temp2reg(temp.temp_num,true,replaced));
					active.add(temp);
				}
				else {
					//spill temp
					prevnode.addnode(new temp2reg(temp.temp_num,false,stackpos++));
					
				}
			}
			else {
				active.add(temp);
				temp2reg newTemp=new temp2reg(temp.temp_num,true,allopos);
				prevnode.regs.put(temp.temp_num,newTemp);
				boolean flag=false;
				for(int i=0;i<usedRegNum;i++) {
					if(usedReg[i]==allopos) {
						flag=true;
						break;
					}
				}
				if(flag==false) usedReg[usedRegNum++]=allopos;
			}
			if(add==true) {
				tables.add(prevnode);
			}
			stackpos+=usedRegNum;
		}
	}
	
	Table refresh(Table prevnode) {
		int stage=prevnode.pos;
		Iterator<TempInterval> itr=active.iterator();
		while(itr.hasNext()) {
			TempInterval a=itr.next();
			if(a.end<stage) {
				itr.remove();
				manager.free(prevnode.regs.get(a.temp_num).regnum);
				prevnode.regs.remove(a.temp_num);
			}
			else return prevnode;
		}
		return prevnode;
	}
	
	
}
