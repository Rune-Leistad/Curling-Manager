import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSplitPane;


public class ScoreOutput extends JFrame {
	private JPanel redTeamPanel, yellowTeamPanel;
	private JSplitPane splitPane;
	private Team redTeam, yellowTeam;
	
	public ScoreOutput(Team teamR, Team teamY) {
		super();
		redTeam = teamR;
		yellowTeam = teamY;
		
		this.setBounds(200, 200, 600, 300);
		this.setTitle("Statistics");
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		redTeamPanel = new JPanel();
		yellowTeamPanel = new JPanel();
		splitPane = new JSplitPane();
		splitPane.setResizeWeight(0.5);
		this.getContentPane().add(splitPane, BorderLayout.CENTER);
		
		splitPane.setLeftComponent(redTeamPanel);
		splitPane.setRightComponent(yellowTeamPanel);
		
		setTeamStatistics(redTeam, redTeamPanel);
		setTeamStatistics(yellowTeam, yellowTeamPanel);
	}
	
	private void setTeamStatistics(Team team, JPanel teamPanel) {
		teamPanel.setLayout(new GridLayout(4,4));
		for(int i = 0; i < 4; i++) {
			Player p = team.getPlayer(i);
			JLabel name = new JLabel(p.getName());
			JLabel drawScore = new JLabel(p.getDraw() + "");
			JLabel toScore = new JLabel(p.getTakeOut() + "");
			JLabel score = new JLabel(p.getTotalScore() + "");
			teamPanel.add(name);
			teamPanel.add(drawScore);
			teamPanel.add(toScore);
			teamPanel.add(score);
		}
	}
}
