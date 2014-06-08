package chinchillasGame.ProjectFinal;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;

public class EndPanel extends GamePanel {

	private static final long serialVersionUID = -6568154998686284724L;
	JButton button;
	JButton exitButton;
	JLabel winGame;
	JLabel loseGame;

	public EndPanel() {
		super();
		setLayout(null);
		winGame = new JLabel("YOU WON, BUT YOU ARE STILL GOING TO DIE =)))",
				JLabel.CENTER);
		winGame.setBounds(20, 350, 1010, 50);
		winGame.setBackground(Color.BLACK);
		winGame.setOpaque(true);
		winGame.setForeground(Color.RED);
		winGame.setVisible(false);
		winGame.setFont(new Font("Algerian", Font.BOLD, 35));
		add(winGame);
		loseGame = new JLabel("YOU LOST =(((", JLabel.CENTER);
		loseGame.setBounds(470, 150, 570, 80);
		loseGame.setBackground(Color.BLACK);
		loseGame.setOpaque(true);
		loseGame.setForeground(Color.RED);
		loseGame.setVisible(false);
		loseGame.setFont(new Font("Algerian", Font.BOLD, 75));
		add(loseGame);
		exitButton = new JButton("EXIT");
		exitButton.setBounds(700, 95, 220, 40);
		exitButton.setForeground(Color.BLACK);
		exitButton.setBackground(new Color(63, 148, 170));
		exitButton.setFont(new Font("Chiller", Font.BOLD, 22));
		exitButton.setVisible(false);
		exitButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				// WorkSpace.ball.playSound2("C:\\Users\\Klamerin\\workspace\\klm\\klm1\\src\\exercises\\img\\balloon.wav");
				System.exit(0);
			}
		});

		button = new JButton("PLAY AGAIN");
		button.setBounds(700, 55, 220, 40);
		button.setForeground(Color.BLACK);
		button.setBackground(new Color(217, 0, 0));
		button.setFont(new Font("Chiller", Font.BOLD, 22));
		button.setVisible(false);
		button.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {

				setVisible(false);
//				WorkSpace.ball
//						.playSound2("C:\\Users\\Klamerin\\workspace\\klm\\klm1\\src\\exercises\\img\\start.wav");
				WorkSpace.ball.playSound2("start.wav");
				for (int i = 0; i < 14; i++) {
					for (int j = 0; j < 9; j++) {
						if (WorkSpace.chinchillas[i][j] != null) {
							WorkSpace.mainGamePanel
									.remove(WorkSpace.chinchillas[i][j]);
							WorkSpace.mainGamePanel.revalidate();
							WorkSpace.mainGamePanel.repaint();
							WorkSpace.chinchillas[i][j] = null;
						}
					}
				}
				WorkSpace.chinchillas = WorkSpace.mainGamePanel.Wall();
				WorkSpace.mainGamePanel.setVisible(true);
				WorkSpace.mainGamePanel.moveToFront(WorkSpace.ball);
				WorkSpace.slide.setFocusable(true);
				WorkSpace.slide.requestFocus();
				WorkSpace.ball.setVisible(true);
				WorkSpace.ball.resetState();
				WorkSpace.mainGamePanel.ballsLeft = 4;
				WorkSpace.Score = 0;
				WorkSpace.mainGamePanel.bricksLeft = 126;
			}
		});
		add(button);
		add(exitButton);
	}
}
