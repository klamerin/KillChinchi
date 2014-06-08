package chinchillasGame.ProjectFinal;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;

public class StartPanel extends GamePanel {

	private static final long serialVersionUID = 1L;
	JButton startButton;
	JButton exitButton;
	private JComboBox<?> encDecCombo;
	private String[] encDecValues = { "Slow", "Normal", "Fast", "Superfast" };

	public static final int WIDTH = 1050;
	public static final int HEIGHT = 700;

	public StartPanel() {
		super();
		setLayout(null);
		startButton = new JButton("START GAME");
		startButton.setBounds(700, 55, 220, 40);
		startButton.setForeground(Color.BLACK);
		startButton.setBackground(new Color(217, 0, 0));
		startButton.setFont(new Font("Old English Text MT", Font.BOLD, 22));
		startButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
//				WorkSpace.ball
//						.playSound2("C:\\Users\\Klamerin\\workspace\\klm\\klm1\\src\\exercises\\img\\start.wav");
				WorkSpace.ball.playSound2("start.wav");
				setVisible(false);
				WorkSpace.mainGamePanel.setVisible(true);
				WorkSpace.slide.requestFocus();
			}
		});
		exitButton = new JButton("EXIT");
		exitButton.setBounds(700, 95, 220, 40);
		exitButton.setForeground(Color.BLACK);
		exitButton.setBackground(new Color(63, 148, 170));
		exitButton.setFont(new Font("Old English Text MT", Font.BOLD, 22));
		exitButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				// WorkSpace.ball.playSound2("C:\\Users\\Klamerin\\workspace\\klm\\klm1\\src\\exercises\\img\\balloon.wav");
				System.exit(0);
			}
		});

		add(exitButton, new Integer(10));
		add(startButton, new Integer(10));
		encDecCombo = new JComboBox<Object>(encDecValues);
		encDecCombo.addActionListener(new ComboListener());
		encDecCombo.setBounds(550, 55, 100, 40);
		add(encDecCombo);

	}

	private class ComboListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			JComboBox<?> combo = (JComboBox<?>) e.getSource();
			WorkSpace.ball.difficulty = (combo.getSelectedIndex() + 1);
		}
	}

}
