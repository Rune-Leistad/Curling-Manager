import java.util.Stack;


public class Game {
	private Team teamRed;
	private Team teamYellow;
	private Round[] rounds;
	private int currentRound;
	private int rocksSent;
	private boolean redGoesFirst;
	// stacks to allow going backwards and forwards in the game
	private Stack<SentRock> sentRocks;
	private Stack<SentRock> alreadySent;
	
	public Game() {
		currentRound = 1;
		rocksSent = 0;
		//rounds = new Round[nrOfRounds];
		sentRocks = new Stack<SentRock>();
		sentRocks.push(new SentRock()); //Makes sure you can backtrack to where no rocks are played
		alreadySent = new Stack<SentRock>();
	}
	
	public Player getCurrentPlayer() {
		if(redGoesFirst) {
			if(rocksSent%2 != 0) 
				return teamRed.getPlayer(((rocksSent)/2)/2);
			else
				return teamYellow.getPlayer(((rocksSent)/2)/2);
		}
		else {
			if(rocksSent%2 != 0)
				return teamYellow.getPlayer(((rocksSent)/2)/2);
			else
				return teamRed.getPlayer(((rocksSent)/2)/2);
		}
		
	}
	
	public void setRounds(int rounds) {
		this.rounds = new Round[rounds];
	}
	
	public void setStartTeam(boolean redFirst) {
		redGoesFirst = redFirst;
	}
	
	public void setTeamRed(Player[] redPlayers) {
		teamRed = new Team(redPlayers);
	}
	
	public void setTeamYellow(Player[] yellowPlayers) {
		teamYellow = new Team(yellowPlayers);
	}
	
	public String getTeamRed() {
		return teamRed.toString();
	}
	
	public void moveRock() {
		// graphical stuff
	}
	
	public Team getTeam(boolean red) {
		if(red)
			return teamRed;
		return teamYellow;
	}
	
	public void setRock(boolean inhand, int type, int score, String comment) {
		Player currentPlayer = getCurrentPlayer();
		SentRock sr = new SentRock(inhand, type, score, comment);
		currentPlayer.sendRock(sr);
		rocksSent++;
		
	}
	
	private void newRound() {
		rounds[currentRound-1] = new Round(currentRound);
		rounds[currentRound-1].setRoundScore(teamRed, teamYellow);
		currentRound++;
		// if current round >= max rounds finish the game
	}
	
	public void roundOverCheck() {
		if(rocksSent > 15)
			newRound();
	}
	
	public String getTeamYellow() {
		return teamRed.toString();
	}
}
