package toKanga.Aze_RegisterAllocation;

import java.util.Hashtable;



public class AllocateTable {
	public int start;//����AllocateTable��Ч�Ŀ�ʼλ��
	public Hashtable<Integer,Temp_location>spillToMem;//������ڴ�ı����ı�
	public Hashtable<Integer,Temp_location>regTable;//�Ĵ����б����ı�
	AllocateTable(int s, Hashtable<Integer,Temp_location>h, Hashtable<Integer, Temp_location>spill)
	{
		start = s;
		regTable = new Hashtable<Integer,Temp_location>(h);
		spillToMem = new Hashtable<Integer,Temp_location>(spill);
	}
	AllocateTable(int s)
	{
		start = s;
		regTable = new Hashtable<Integer, Temp_location>();
		spillToMem = new Hashtable<Integer, Temp_location>();
	}
	

}
