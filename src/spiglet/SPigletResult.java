package spiglet;

public class SPigletResult extends AbstractSPigletResult{
	private String result;
	private boolean simple;
	public SPigletResult(String result,boolean simple) {
		super();
		this.result=result;
		this.simple=simple;
	}
	public String toString() {
		return result;
	}
	public boolean isTemp() {
		return result.startsWith("TEMP");
	}
	public boolean isSimple() {
		return simple;
	}
}
