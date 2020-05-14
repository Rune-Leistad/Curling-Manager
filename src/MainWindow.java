import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.factories.FormFactory;

import javax.swing.border.EmptyBorder;


public class MainWindow {

	
	
	private JFrame frame;
	private JPanel playerPanel;
	private JPanel rinkPanel;
	private JSplitPane splitPane;
	private Game game;
	
	private JLabel currentPlayer;
	
	private final ButtonGroup radioTurn = new ButtonGroup();
	private final ButtonGroup radioScore = new ButtonGroup();
	private final ButtonGroup inOut = new ButtonGroup();
	
	private JRadioButton inhand;
	private JRadioButton outhand;
	
	private JRadioButton zero;
	private JRadioButton one;
	private JRadioButton two;
	private JRadioButton three;
	private JRadioButton four;
	private JRadioButton invalid;

	private JTextField comment;
	private JPanel scorePanel;
	private JPanel typePanel;
	private Choice rockType;
	private JMenu menuStatistics;
	private JMenuItem uMenuRedTeam;
	private JMenuItem uMenuYellowTeam;
	private JLabel lblComment;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow window = new MainWindow();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainWindow() {
		initialize();
	}
	
	

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		// setting up frame
		frame = new JFrame();
		frame.setTitle("Curling Manager");
		frame.setBounds(100, 10, 1218, 950);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// menu
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		JMenu menuFile = new JMenu("File");
		menuBar.add(menuFile);
		
		menuStatistics = new JMenu("Statistics");
		menuBar.add(menuStatistics);
		uMenuRedTeam = new JMenuItem("Teams statistics");
		menuStatistics.add(uMenuRedTeam);
		uMenuRedTeam.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				EventQueue.invokeLater(new Runnable() {
					@Override
					public void run() {
						try {
							ScoreOutput so = new ScoreOutput(game.getTeam(true), game.getTeam(false));
							so.setVisible(true);
						}
						catch(Exception e) {
							e.printStackTrace();
						}
					}
					
				});
			}
			
		});

		
		currentPlayer = new JLabel("Player");
		
		JMenuItem uMenuNewGame = new JMenuItem("New Game");
		uMenuNewGame.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				try{
					newGame();
					Player cp = game.getCurrentPlayer();
					currentPlayer.setText(cp.toString());
				}
				catch(Exception e) {
					System.err.print("New game cancelled");
				}
			}
			
		});
		menuFile.add(uMenuNewGame);
		
		splitPane = new JSplitPane();
		rinkPanel = new RinkPanel();
		splitPane.setResizeWeight(0.3);
		frame.getContentPane().add(splitPane, BorderLayout.CENTER);
		
		playerPanel = new JPanel();
		splitPane.setLeftComponent(playerPanel);
		
		playerPanel.setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,},
			new RowSpec[] {
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("max(25dlu;default)"),
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("max(95dlu;default):grow"),
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,}));
		
		
		currentPlayer.setFont(new Font("Tahoma", Font.PLAIN, 16));
		playerPanel.add(currentPlayer, "4, 4");
		
		
		
		inhand = new JRadioButton("inhand");
		inhand.setActionCommand("1");
		playerPanel.add(inhand, "4, 8");
		radioTurn.add(inhand);
		
		outhand = new JRadioButton("outhand");
		outhand.setActionCommand("0");
		playerPanel.add(outhand, "4, 10");
		radioTurn.add(outhand);
		
		typePanel = new JPanel();
		typePanel.setBorder(null);
		playerPanel.add(typePanel, "4, 14, fill, bottom");
		
		rockType = new Choice();
		rockType.addItem("Draw");
		rockType.addItem("Guard");
		rockType.addItem("Front");
		rockType.addItem("Freeze");
		rockType.addItem("Raise/tap back");
		rockType.addItem("Takeout");
		rockType.addItem("Peel/clearing");
		rockType.addItem("Hit & roll");
		rockType.addItem("Promotion takeout");
		rockType.addItem("Softpeel/wick");
		typePanel.add(rockType);
		
		scorePanel = new JPanel();
		scorePanel.setBorder(new EmptyBorder(0, 0, 0, 0));
		scorePanel.setLayout(new GridLayout(1,6));
		scorePanel.setSize(new Dimension(20, 100));
		playerPanel.add(scorePanel, "4, 16, fill, top");
		
		zero = new JRadioButton("0");
		scorePanel.add(zero);
		zero.setActionCommand("0");
		radioScore.add(zero);
		
		one = new JRadioButton("1");
		scorePanel.add(one);
		one.setActionCommand("1");
		radioScore.add(one);
		
		two = new JRadioButton("2");
		scorePanel.add(two);
		two.setActionCommand("2");
		radioScore.add(two);
		
		three = new JRadioButton("3");
		scorePanel.add(three);
		three.setActionCommand("3");
		radioScore.add(three);
		
		four = new JRadioButton("4");
		scorePanel.add(four);
		four.setActionCommand("4");
		radioScore.add(four);
		
		invalid = new JRadioButton("x");
		scorePanel.add(invalid);
		invalid.setActionCommand("-1");
		radioScore.add(invalid);
		
		lblComment = new JLabel("Comment:");
		playerPanel.add(lblComment, "4, 18");
		
		comment = new JTextField();
		playerPanel.add(comment, "4, 20, fill, top");
		comment.setColumns(10);
		
		// the next button!
		Button nextBtn = new Button("Next");
		nextBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(game != null) {
					String errorMessage = "No turn chosen";
					try {
						boolean isInhand = ("1".equals(radioTurn.getSelection().getActionCommand()));
						errorMessage = "No stone type selected.\nPlease select draw, take-out etc.";
						int type = rockType.getSelectedIndex();
						errorMessage = "No score selected.\nSelect \"x\" for invalid stone";
						int score = Integer.parseInt(radioScore.getSelection().getActionCommand());
						game.setRock(isInhand, type, score, comment.getText());
						Player cp = game.getCurrentPlayer();
						/*if(player.equals(null)) {
							JOptionPane.showInputDialog(arg0);
							
						}*/
						currentPlayer.setText(cp.toString());
						errorMessage = "Game not created";
					}
					catch(NullPointerException npe) {
						JOptionPane.showMessageDialog(null, errorMessage);
						System.err.println(npe.getMessage());
					}
					game.roundOverCheck();
				}
				else {
					JOptionPane.showMessageDialog(null, "You have to start a new game first");
				}
			}
		});
		
		
		playerPanel.add(nextBtn, "8, 20");
		
		
		rinkPanel.setBackground(UIManager.getColor("Button.background"));
		splitPane.setRightComponent(rinkPanel);
		
		JLayeredPane layeredPane = new JLayeredPane();
		rinkPanel.add(layeredPane);
	}
	
	private void newGame() {
		game = new Game();
		
		JPanel optionPane = new JPanel();
		
		JPanel teams = new JPanel();
		JPanel redTeam = new JPanel();
		JPanel yellowTeam = new JPanel();
		JPanel hammer = new JPanel();
		JPanel roundChoice = new JPanel();
		
		
		JTextField p1red = new JTextField("Red 1");
		JTextField p2red = new JTextField("Red 2");
		JTextField p3red = new JTextField("Red 3");
		JTextField p4red = new JTextField("Red 4");
		
		JTextField p1yellow = new JTextField("Yellow 1");
		JTextField p2yellow = new JTextField("Yellow 2");
		JTextField p3yellow = new JTextField("Yellow 3");
		JTextField p4yellow = new JTextField("Yellow 4");
		
		JRadioButton redHammer = new JRadioButton("Has hammer");
		redHammer.setActionCommand("0");
		JRadioButton yellowHammer = new JRadioButton("Has hammer");
		yellowHammer.setActionCommand("1");
		JLabel roundCountLab = new JLabel("Number of rounds");
		JTextField rCountText = new JTextField(2);
		
		inOut.add(redHammer);
		inOut.add(yellowHammer);
		
		Choice rounds = new Choice();
		JLabel roundLabel = new JLabel("Number of rounds:");
		
		rounds.addItem("0");
		rounds.addItem("1");
		rounds.addItem("2");
		rounds.addItem("3");
		rounds.addItem("4");
		rounds.addItem("5");
		rounds.addItem("6");
		rounds.addItem("7");
		rounds.addItem("8");
		rounds.addItem("9");
		rounds.addItem("10");
	
		roundChoice.add(roundLabel);
		roundChoice.add(rounds);
		
		roundChoice.setLayout(new GridLayout(1,2));
		
		redTeam.add(new JLabel("Red team"));
		redTeam.add(p1red);
		redTeam.add(p2red);
		redTeam.add(p3red);
		redTeam.add(p4red);
		
		yellowTeam.add(new JLabel("Yellow team"));
		yellowTeam.add(p1yellow);
		yellowTeam.add(p2yellow);
		yellowTeam.add(p3yellow);
		yellowTeam.add(p4yellow);
		
		redTeam.setLayout(new GridLayout(5,1));
		yellowTeam.setLayout(new GridLayout(5,1));
		
		teams.add(redTeam);
		teams.add(yellowTeam);
		teams.add(redHammer);
		teams.add(yellowHammer);
		
		teams.setLayout(new GridLayout(2,2));
		
		optionPane.add(teams);
		optionPane.add(roundChoice);
		
		optionPane.setLayout(new GridLayout(2,1));
		
		int result = JOptionPane.showConfirmDialog(null, optionPane, "Game name", JOptionPane.OK_CANCEL_OPTION);
		if(result == JOptionPane.OK_OPTION) {
			Player[] reds = {new Player(p1red.getText()),
					new Player(p2red.getText()),
					new Player(p3red.getText()),
					new Player(p4red.getText())};
			Player[] yellows = {new Player(p1yellow.getText()),
					new Player(p2yellow.getText()),
					new Player(p3yellow.getText()),
					new Player(p4yellow.getText())};
			
			game.setTeamRed(reds);
			game.setTeamYellow(yellows);
			game.setStartTeam(inOut.getSelection().getActionCommand().equals("0"));
			game.setRounds(rounds.getSelectedIndex());
			currentPlayer.setText(inOut.getSelection().getActionCommand());
		}
		else ; // dont do anything
	}
	
	private class SwingAction extends AbstractAction {
		public SwingAction() {
			putValue(NAME, "SwingAction");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {
		}
	}
}
