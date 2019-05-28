package toKanga.Aze_RegisterAllocation;

import toKanga.Regs;

public class Temp_location 
{//记录变量的位置	
	public boolean isReg;//是否在寄存器中
	public int stackLoc;//若在内存中，在栈中的位置
	public int loc;//若在寄存器中，在哪个寄存器
		
	public String getreg()
	{
		return Regs.REGS[loc];
	}
	
	Temp_location(boolean isreg, int p)
	{
		isReg = isreg;
		if(isreg == true)
		{
			loc = p;
			stackLoc = -1;
		}
		else
		{
			stackLoc = p;
			loc = -1;
		}
	}
	
	public void setState(boolean isreg, int p)
	{
		isReg = isreg;
		if(isreg == true)
			loc = p;
		else
			stackLoc = p;
	}
	
	public Temp_location(){}
}