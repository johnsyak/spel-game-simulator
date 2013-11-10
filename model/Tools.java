package model;

public class Tools {

	private Tools() {
	}

	public static boolean d20Check(int dc) {
		return (Math.ceil(Math.random() * 20) >= dc);
	}

}
