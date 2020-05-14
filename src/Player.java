import java.util.ArrayList;


public class Player {
	private ArrayList<SentRock> rocks;
	private String name;
	/*
	 * Initializes attributes
	 */
	public Player(String name) {
		rocks = new ArrayList<SentRock>();
		this.name = name;
	}
	
	public void sendRock(SentRock sr) {
		rocks.add(sr);
	}
	
	public String toString() {
		return String.format("%s  %d%%", name, getTotalScore());
	}
	
	public int getTotalScore() {
		int score = 0;
		int validRocks = 0;
		for(SentRock sr : rocks) {
			int rockScore = sr.getScore();
			if(rockScore < 0)
				; // do nothing
			else {
				score += rockScore;
				validRocks++;
			}
		}
		if(validRocks == 0) return 0;
		return score * 100 / 4 / validRocks;
	}
	
	public int getDraw() {
		int score = 0;
		int validRocks = 0;
		for(SentRock sr : rocks) {
			if(sr.getScore() < 0)
				; // do nothing
			else if(sr.getType() < 5) {
				score += sr.getScore();
				validRocks++;
			}
		}
		if(validRocks == 0)
			return 0;
		return score * 100 / 4 / validRocks;
	}
	
	public int getTakeOut() {
		int score = 0;
		int validRocks = 0;
		for(SentRock sr : rocks) {
			if(sr.getScore() < 0)
				; // do nothing
			else if(sr.getType() > 4) {
				score += sr.getScore();
				validRocks++;
			}
		}
		if(validRocks == 0)
			return 0;
		return score * 100 / 4 / validRocks;
	}

	public String getName() {
		return name;
	}
}
