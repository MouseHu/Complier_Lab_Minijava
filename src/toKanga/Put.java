package toKanga;
public class Put {
	static String Tab = "";
	static String filename;
	static String output = "";
	public static void con(String s){
		//output += Tab + s;
		System.out.print(Tab+s);
	}
	public static void file(String s){
		
	}
	public static String getoutput()
	{
		return output;
	}
	public void addTab()
	{
		Tab += "    ";
	}
	public void minusTab()
	{
		Tab = Tab.substring(0,Tab.length()-4) ;
	}
}
