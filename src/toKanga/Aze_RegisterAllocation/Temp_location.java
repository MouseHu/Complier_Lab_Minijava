package toKanga.Aze_RegisterAllocation;

import toKanga.Regs;

public class Temp_location 
{//��¼������λ��	
	public boolean isReg;//�Ƿ��ڼĴ�����
	public int stackLoc;//�����ڴ��У���ջ�е�λ��
	public int loc;//���ڼĴ����У����ĸ��Ĵ���
		
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