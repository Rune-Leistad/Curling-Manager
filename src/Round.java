
public class Round {
	private Team scoringTeam;
	private RoundScore roundScore;
	private int roundNumber;
	
	public Round(int number) {
		roundNumber = number;
		roundScore = new RoundScore();
	}
	
	public void setRoundScore(Team red, Team yellow) {
		roundScore.setScore(red, yellow);
	}
}
