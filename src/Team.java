
public class Team {
	
	private Player[] players;
	private int pointScore;
	
	public Team(Player[] players) {
		this.players = players;
		pointScore = 0;
	}
	
	public Player getPlayer(int player) {
		if(player >= players.length)
			return null;
		return players[player];
	}
	
	public void setNewScore(int roundScore) {
		pointScore += roundScore;
	}

	public String toString() {
		String team = "";
		for(Player p : players) {
			team += p.toString() + "\n";
		}
		return team;
	}
}
