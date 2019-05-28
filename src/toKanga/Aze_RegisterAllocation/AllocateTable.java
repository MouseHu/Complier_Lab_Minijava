package toKanga.Aze_RegisterAllocation;

import java.util.Hashtable;



public class AllocateTable {
	public int start;//这张AllocateTable生效的开始位置
	public Hashtable<Integer,Temp_location>spillToMem;//溢出到内存的变量的表
	public Hashtable<Integer,Temp_location>regTable;//寄存器中变量的表
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
