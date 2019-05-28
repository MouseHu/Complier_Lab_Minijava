package toKanga.Gen_interval;
public class EachTemp_interval
{
	public int tempnum;
	public Interval liveInterval;
	EachTemp_interval(int a)
	{
		tempnum = a;		
	}
	EachTemp_interval(int a, int b, int c)
	{
		tempnum = a;
		liveInterval = new Interval(b,c);
	}
	
}