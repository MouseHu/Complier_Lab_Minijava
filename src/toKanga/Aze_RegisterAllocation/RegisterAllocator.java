package toKanga.Aze_RegisterAllocation;
import java.util.Comparator;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.TreeSet;

import toKanga.Gen_interval.*;
public class RegisterAllocator{//�Եõ��Ļ��������������ɨ�裬����Ĵ���
	TreeSet<EachTemp_interval>liveInterval;
	
	TreeSet<EachTemp_interval>active = new TreeSet<EachTemp_interval>(//����ɨ��
	new Comparator<EachTemp_interval>()
	{
		public int compare(EachTemp_interval a, EachTemp_interval b)
		{
			if(a==b)
				return 0;
			if( a.liveInterval.end == b.liveInterval.end)
				return 1;
			else
				return a.liveInterval.end - b.liveInterval.end;
		}
	});
	
	Hashtable<Integer, Temp_location>RegAllocTable;
	//�Ĵ��������
	public int max_args=0;//������
	public int usedRegNum=0;
	public int stackPos=0;//ջָ���λ��
	int position=0;//��ǰ����λ��
	
	
	public LinkedList<AllocateTable> tables = new LinkedList<AllocateTable>();
	//�������ɵ�����
	RegisterManager regMan = new RegisterManager();
	//�Ĵ�������
	public int[] usedRegsArray=new int[24];
	
	
	final static int REGTOTAL = 24;
	final static int GENERALREG = 18;
	
	void initialize(int formalParam)
	{
		AllocateTable a = new AllocateTable(0);
		int i;
		for(i=4;i<formalParam;i++)
		{//��temp4��formalParam���βθ���������λ�����ڴ���
			stackPos++;
			Temp_location l = new Temp_location(false, i-4);
			a.regTable.put(new Integer(i), l);
		}
		tables.add(a);
		return;		
	}
	
	public RegisterAllocator(int formalParam, TreeSet<EachTemp_interval>t, int Funcmaxarg)
	{//��������Ϊ �ù��̵Ĳ�������----- �ù��̵�ÿһ�������Ļ�������  ------�ù��̵��õ�����������
		max_args=Funcmaxarg;
		liveInterval = t;
		stackPos = 0;
		initialize(formalParam);
	}
	
	//ÿһ�仰�еı�������һ��������У����еķ������һ���������
	public void RegisterAllocation()
	{//���мĴ����������Ҫ������ÿһ�������������һ������
		active.clear();//���
		Iterator<EachTemp_interval> itr1 = liveInterval.iterator();//��������ĵ�����
		AllocateTable newt = tables.getFirst();//tables��ΪallocateTable����ʼλ�õ���˳����ɵ�����
		while(itr1.hasNext())
		{//��liveInterval��ÿ����Ծ����
			EachTemp_interval a = itr1.next(); 
			boolean flag = false;
			if(newt.start < a.liveInterval.begin)
			{//�������a�Ŀ�ʼλ�ô����˱�newt�Ŀ�ʼλ�ã�����Ҫһ���±��ˡ��±�Ŀ�ʼλ��Ϊa�Ŀ�ʼλ�ã��±�ı���������Ϣֱ�ӴӾɱ���
				newt = new AllocateTable(a.liveInterval.begin, tables.getLast().regTable, tables.getLast().spillToMem);
				flag = true;
			}
			//-----------------------------------------------------
			ExpireOldIntervals(a, newt);//��̭ʧ��ı���
			if(active.size() == GENERALREG)//����Ĵ���������ѡ��ĳ����Ծ����������ڴ���
				SpillAtInterval(a, newt);
			else//�������һ���Ĵ���
				DistributeReg(a, newt);
			if(flag == true)//��������±�������뵽tables��
				tables.add(newt);
			//-----------------------------------------------------
		}	
		stackPos+=(usedRegNum);
		return;
	}
	void DistributeReg(EachTemp_interval a, AllocateTable newt)
	{//Ϊ����a����һ���ռĴ���
		int regNum = regMan.distributeGeneral(a.tempnum);
		int i;
		for(i=0;i<usedRegNum;i++)//������Ĵ�������Ŀ
		{
			if(usedRegsArray[i]==regNum)
				break;
		}
		if(i == usedRegNum)
		{
			usedRegsArray[usedRegNum++]=regNum;//usedRegsArrayΪ����Ĵ�������
		}
		active.add(a);//��ǰa�������˼Ĵ���
		newt.regTable.put(new Integer(a.tempnum), new Temp_location(true, regNum));//������Ϣ�ڷ������
		return;		
	}
	
	void SpillAtInterval(EachTemp_interval a, AllocateTable newt)
	{//ѡ��һ����Ծ����������ڴ棨�ѻ�Ծ���ڽ���ʱ��������Ǹ���Ծ����������ڴ棩
		EachTemp_interval b = active.last();
		if(b.liveInterval.end > a.liveInterval.end)
		{
			int regLoc = newt.regTable.get(new Integer(b.tempnum)).loc;	
			regMan.assignReg(a.tempnum, regLoc);
			
			int t = stackPos;
			newt.regTable.get(new Integer(b.tempnum)).setState(false, t);
			newt.spillToMem.put(new Integer(b.tempnum), new Temp_location(false, t));
			stackPos++;//��b���
			
			newt.regTable.put(new Integer(a.tempnum), new Temp_location(true, regLoc));
			//��a����Ĵ���
			active.remove(b);
			active.add(a);
		}
		else//�����a���
		{
			newt.regTable.put(new Integer(a.tempnum), new Temp_location(false, stackPos++));
		}		
	}
	
	void ExpireOldIntervals(EachTemp_interval a, AllocateTable newt)
	{//��̭һ��ʧ��ı�����
	//active�ǰ������������˳���С�������еģ����ĳ��active�л�������Ľ���λ�ô�����a�Ŀ�ʼλ�ã��Ϳ���ֱ���˳�
		Iterator<EachTemp_interval> itr1 = active.iterator();
		while(itr1.hasNext())
		{
			EachTemp_interval b = itr1.next();
			if(b.liveInterval.end >= a.liveInterval.begin)
			{
				return;
			}
			else
			{		//����b�Ļ��������Ѿ�����		
				if(!newt.regTable.containsKey(new Integer(b.tempnum)))
				{
					System.out.println("error in expireoldintervals");
					System.out.println(b.tempnum);
					System.exit(1);
				}
				itr1.remove();
				regMan.freeGeneral(newt.regTable.get(b.tempnum).loc);
				newt.regTable.remove(new Integer(b.tempnum));
			}
		}
	}
	
	

}
