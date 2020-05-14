
public class SentRock {
	private String comment;
	private int rockType, score;
	private boolean inhand;
	
	public static final int NOT_SENT = -2;
	public static final int PICKED = -1;
	public static final int MISS = 0;
	public static final int POOR_SHOT = 1;
	public static final int OK_SHOT = 2;
	public static final int GOOD_SHOT = 3;
	public static final int PERFECT_SHOT = 4;
	
	/*
	 * Constructs a sent rock to represent where no rocks are sent
	 */
	public SentRock() {
		comment = "No rocks sent";
		rockType = -1;
		score = -1;
	}
	
	/*
	 * Constructs a sent rock with all its values
	 */
	public SentRock(boolean inhand, int type, int score, String com) {
		this.inhand = inhand;
		rockType = type;
		this.score = score;
		comment = com;
	}
	
	public int getScore() {
		return score;
	}
	
	public int getType() {
		return rockType;
	}
	
	public boolean inhand() {
		return inhand;
	}
}
