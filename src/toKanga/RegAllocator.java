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
	public int pos;
	public Hashtable<Integer,temp2reg> regs;
	public Hashtable<Integer,temp2reg> spills;
	public Table(int p){
		pos=p;
		regs=new Hashtable<Integer,temp2reg>();
		spills=new Hashtable<Integer,temp2reg>();
	}
}

class regManager{
	public boolean regTable[]=new boolean[24];
	int [] tempInReg = new int[24];//�Ĵ���i������ĸ�����
	public int allocated = 0;
	public regManager(){
		for(int i=0;i<24;i++) {
			regTable[i]=false;
			tempInReg[i]=0;
		}
	}
	void free(int freeNo) {
		regTable[freeNo]=false;
		tempInReg[freeNo]=-1;
		allocated--;
	}
	int allocate(int tempnum) {
		for(int i=4;i<22;i++) {
			if(regTable[i]==false) {
				allocated++;
				regTable[i]=true;
				tempInReg[i]= tempnum;
				return i;
			}
		}
		return -1;
	}
	void assign(int a,int b) {
		if(regTable[a]==false)
			allocated++;
		tempInReg[a]=b;
		regTable[a]=true;
	}
	int get(int a) {
		if(regTable[a]==false) return -1;
		else return tempInReg[a];
	}
}

public class RegAllocator {
	final static int GENERALREG = 18;
	int nargs;
	public int usedRegNum=0;
	TreeSet<TempInterval> tempList;
	int stackpos;
	public LinkedList<Table> tables= new LinkedList<Table>();
	regManager manager = new regManager();
	public int usedReg[]=new int[24];
	TreeSet<TempInterval> active = new TreeSet<TempInterval>(
			new Comparator<TempInterval>()
			{
				public int compare(TempInterval a,TempInterval b)
				{
					if(a==b)
						return 0;
					if( a.end == b.end)
						return 1;
					else
						return a.end - b.end;
				}
	});
	
	public RegAllocator(TreeSet<TempInterval> t, int argnum) {
		nargs=argnum; //number of args of the function itself
		tempList=t;
		stackpos=0;
		Table tab=new Table(0);
		for(int i=0;i<nargs&&i<4;i++) {
			temp2reg temp=new temp2reg(i,true,i);
			tab.regs.put(i,temp);
		}
		for(int i=4;i<nargs;i++) {
			temp2reg temp=new temp2reg(i,false,stackpos++);
			tab.regs.put(i,temp);
		}
		tables.add(tab);
		
		/* allocation */
		Iterator<TempInterval> itr=tempList.iterator();
		Table prevnode=tables.getFirst();
		active.clear();
		//go through tempList, at each point of birth, review table again and add new tables if necessary
		while(itr.hasNext()) {
			TempInterval temp=itr.next();
			boolean add=false;
			if(prevnode.pos<temp.beg) {
				prevnode=new Table(temp.beg);
				prevnode.regs=new Hashtable<Integer,temp2reg>(tables.getLast().regs);
				prevnode.spills=new Hashtable<Integer,temp2reg>(tables.getLast().spills);
				add=true;
			}
			refresh(temp,prevnode);
			if(active.size() == GENERALREG){//����Ĵ���������ѡ��ĳ����Ծ����������ڴ���
				TempInterval a=active.last();
				if(a.end>temp.end) { 
					//spill a
					int replaced=prevnode.regs.get(a.temp_num).regnum;
					manager.assign(temp.temp_num, replaced);
					prevnode.regs.get(temp.temp_num).isreg=false;
					prevnode.regs.get(temp.temp_num).stackpos=stackpos;
					prevnode.spills.put(temp.temp_num,new temp2reg(temp.temp_num,false,replaced));
					prevnode.regs.put(a.temp_num,new temp2reg(a.temp_num,true,stackpos++));

					active.remove(a);
					active.add(temp);
				}
				else {
					//spill temp
//					prevnode.addNode(new temp2reg(temp.temp_num,false,stackpos++));
					prevnode.regs.put(temp.temp_num,new temp2reg(temp.temp_num,false,stackpos++));
				}
			}
			else//�������һ���Ĵ���
			{
				int regNum = manager.allocate(temp.temp_num);
				int i;
				for(i=0;i<usedRegNum;i++)//������Ĵ�������Ŀ
				{
					if(usedReg[i]==regNum)
						break;
				}
				if(i == usedRegNum)
				{
					usedReg[usedRegNum++]=regNum;//usedRegsArrayΪ����Ĵ�������
				}
				active.add(temp);//��ǰa�������˼Ĵ���
				prevnode.regs.put(temp.temp_num,new temp2reg(temp.temp_num,true, regNum));//������Ϣ�ڷ������	
			}

//			int allopos=manager.allocate();
//			if(allopos==-1) {
//				TempInterval a=active.last();
//				if(a.end>temp.end) { 
//					//spill a
//					int replaced=prevnode.regs.get(a.temp_num).regnum;
//					prevnode.addnode(new temp2reg(a.temp_num,false,stackpos++));
//					prevnode.regs.remove(a.temp_num);
//					active.remove(a);
//					prevnode.addnode(new temp2reg(temp.temp_num,true,replaced));
//					active.add(temp);
//				}
//				else {
//					//spill temp
//					prevnode.addnode(new temp2reg(temp.temp_num,false,stackpos++));
//					
//				}
//			}
//			else {
//				active.add(temp);
//				temp2reg newTemp=new temp2reg(temp.temp_num,true,allopos);
//				prevnode.regs.put(temp.temp_num,newTemp);
//				boolean flag=false;
//				for(int i=0;i<usedRegNum;i++) {
//					if(usedReg[i]==allopos) {
//						flag=true;
//						break;
//					}
//				}
//				if(flag==false) usedReg[usedRegNum++]=allopos;
//			}
			if(add==true) {
				tables.add(prevnode);
			}
		}
		stackpos+=usedRegNum;
		return;
	}
	
	void refresh(TempInterval t, Table prevnode) {
		int stage=t.beg;
		Iterator<TempInterval> itr=active.iterator();
		while(itr.hasNext()) {
			TempInterval a=itr.next();
			if(a.end<stage) {
				itr.remove();
				if (prevnode.regs.get(a.temp_num).isreg)
					manager.free(prevnode.regs.get(a.temp_num).regnum);
				prevnode.regs.remove(a.temp_num);
			}
			else return;
		}
		return;
	}
	
	
}
