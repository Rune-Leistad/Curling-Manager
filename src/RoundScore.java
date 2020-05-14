import java.awt.Choice;
import java.awt.GridLayout;

import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;


public class RoundScore {
	
	public RoundScore() {
		// empty atm
	}
	
	public void setScore(Team redTeam, Team yellowTeam) {
		
		JPanel optionPanePanel = new JPanel();
		JPanel roundScorePanel = new JPanel();
		Choice pointChoice = new Choice();
		final ButtonGroup scoringTeam = new ButtonGroup();
		
		//JLabel redTeam = new JLabel("Red team");
		//JLabel yellowTeam = new JLabel("Yellow team");
		
		JRadioButton redPoint = new JRadioButton("Red point");
		redPoint.setActionCommand("0");
		JRadioButton yellowPoint = new JRadioButton("Yellow point");
		yellowPoint.setActionCommand("1");
		
		scoringTeam.add(redPoint);
		scoringTeam.add(yellowPoint);
		
		pointChoice.addItem("0");
		pointChoice.addItem("1");
		pointChoice.addItem("2");
		pointChoice.addItem("3");
		pointChoice.addItem("4");
		pointChoice.addItem("5");
		pointChoice.addItem("6");
		pointChoice.addItem("7");
		pointChoice.addItem("8");
		
		roundScorePanel.add(pointChoice);
		
		optionPanePanel.setLayout(new GridLayout(2,1));
		optionPanePanel.add(redPoint);
		optionPanePanel.add(yellowPoint);
		optionPanePanel.add(roundScorePanel);
		
		int result = JOptionPane.showConfirmDialog(null, optionPanePanel, "Round score", JOptionPane.OK_CANCEL_OPTION);
		if(result == JOptionPane.OK_OPTION) {
			Team team;
			int score = 0;
			
			if(scoringTeam.getSelection().getActionCommand().equals("0")) team = redTeam;
			else team = yellowTeam;
			
			if(pointChoice.getSelectedIndex() < 0) score = 0;
			else score = pointChoice.getSelectedIndex();
			
			team.setNewScore(score);
		}
		
	}
}
