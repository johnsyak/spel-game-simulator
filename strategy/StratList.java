package strategy;

public class StratList {

	private StratList() {
	}
	
	public static CoinToss coinToss = new CoinToss();
	public static ThresholdGold waitGold2 = new ThresholdGold(2);
	public static ThresholdGold waitGold5 = new ThresholdGold(5);
	public static OneUpAndOut oneUp = new OneUpAndOut();
	public static SmartBot smartBot = new SmartBot();

}
