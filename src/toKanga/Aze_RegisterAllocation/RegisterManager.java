package toKanga.Aze_RegisterAllocation;
import toKanga.Regs;
public class RegisterManager {
	boolean[] regUsed = new boolean[24];//��ǼĴ����Ƿ�ռ��
	int [] reg_var = new int[24];//�Ĵ���i������ĸ�����
	boolean isFull=false;//�Ĵ����Ƿ�����
	
	public RegisterManager()
	{
		for(int i=0;i<24;i++)
		{
			reg_var[i] = -1;
			regUsed[i] = false;
		}
	}
		
	public void clear()
	{
		for(int i=0;i<24;i++)
		{
			reg_var[i] = -1;
			regUsed[i] = false;
		}
		isFull = false;
	}
	
	int VarInReg(int tempnum)
	{
		for(int i=0; i<24; i++)
		{
			if(reg_var[i] == tempnum)
			{
				return i; 
			}
		}
		return -1;
	}
		
	public int getVar(int i)
	{
		return reg_var[i];
	}
	
	public String getRegName(int i)
	{
		return Regs.REGS[i];
	}
	
	void freeGeneral(int i)
	{
		if(i>3&&i<22)
		{
			regUsed[i] = false;
			reg_var[i] = -1;
			isFull = false;
		}
		else
		{
			System.out.println("error in freeGeneral");
			System.exit(1);
		}
		return;
	}
	
	int distributeGeneral(int tempnum)//ѭ�����ҿ��ĸ��Ĵ�����û���õ�
	{
		for(int i=4;i<22;i++)
		{
			if(regUsed[i] == false)
			{
				regUsed[i]=true;
				reg_var[i]=tempnum;
				return i;
			}
		}
		isFull=true;
		return -1;
	}
	
	public int assignReg(int tempnum, int i)
	{
		if(regUsed[i] == false)
		{
			reg_var[i]=tempnum;
			regUsed[i]=true;
			return -1;
		}
		else
		{
			int t = reg_var[i];//�Ĵ����е�ֵ
			reg_var[i]=tempnum;
			regUsed[i]=true;
			return t;
		}
	}
}
