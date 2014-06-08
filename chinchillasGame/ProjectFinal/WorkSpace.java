package chinchillasGame.ProjectFinal;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.*;

public class WorkSpace {

	public static Slide slide;
	public static GamePanel mainGamePanel;
	public static Bricks[][] chinchillas;
	public static boolean gameStarted = false;
	public static GameFrame field;
	public static int Score;
	public static JLabel score;
	public static JLabel lives;
	public static StartPanel fake;
	public static EndPanel endpane;
	public static Ball ball;

	public static void main(String[] args) throws InterruptedException {
		field = new GameFrame("Kill Chinchillas");
		field.setSize(GamePanel.WIDTH, GamePanel.HEIGHT);
		field.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		field.setLayout(null);
		mainGamePanel = new GamePanel();
		mainGamePanel.setBounds(0, 0, GamePanel.WIDTH, GamePanel.HEIGHT);
		mainGamePanel.setLayout(null);
		mainGamePanel.SetBackground(mainGamePanel, "main_bkg.png");
		mainGamePanel.setVisible(false);

		score = new JLabel("Score: " + Score);
		score.setBounds(10, 10, 100, 50);
		score.setForeground(new Color(255, 255, 255));
		score.setOpaque(false);

		lives = new JLabel("Lives: " + mainGamePanel.ballsLeft);
		lives.setBounds(970, 10, 100, 50);
		lives.setForeground(new Color(255, 255, 255));
		lives.setOpaque(false);

		chinchillas = mainGamePanel.Wall();
		slide = new Slide(475, 600, "slide.png", 0, false);
		slide.setFocusable(true);
		slide.requestFocus();
		slide.addKeyListener(new SliderListener());
		mainGamePanel.add(slide, new Integer(10));
		ball = new Ball(525, 590, "ball.png", 0, false);
		mainGamePanel.add(ball, new Integer(10));
		ball.addKeyListener(new SliderListener());

		mainGamePanel.add(score);
		mainGamePanel.add(lives);
		mainGamePanel.moveToFront(score);
		mainGamePanel.moveToFront(lives);
		field.add(mainGamePanel);

		fake = new StartPanel();
		fake.setBounds(0, 0, 1050, 700);
		fake.setVisible(true);
		fake.setOpaque(false);
		mainGamePanel.SetBackground(fake, "startbkg.png");
		field.add(fake);

		endpane = new EndPanel();
		endpane.setBounds(0, 0, 1050, 700);
		mainGamePanel.SetBackground(endpane, "startbkg.png");
		field.add(endpane);
		field.setResizable(false);
		field.setVisible(true);

		while (true) {
			Thread.sleep(2);
			if (WorkSpace.gameStarted && WorkSpace.chinchillas != null) {
				ball.moveBall(slide, chinchillas);
				WorkSpace.RefreshData();
				mainGamePanel.repaint();
			}
		}
	}

	public static class SliderListener implements KeyListener {

		@Override
		public void keyTyped(KeyEvent e) {
		}

		@Override
		public void keyPressed(KeyEvent e) {
			if (e.getKeyCode() == KeyEvent.VK_LEFT) {
				if (slide.positionX <= 0) {
					slide.moveSlide(0);
				} else {
					slide.moveSlide(-28);
				}
				slide.setLocation(slide.positionX, slide.positionY);
			}
			if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
				if (slide.positionX + slide.width >= GamePanel.WIDTH) {
					slide.moveSlide(0);
				} else {
					slide.moveSlide(28);
				}
				slide.setLocation(slide.positionX, slide.positionY);
			}

			if (e.getKeyCode() == KeyEvent.VK_SPACE) {
				WorkSpace.gameStarted = true;
			}

		}

		@Override
		public void keyReleased(KeyEvent e) {
			if (e.getKeyCode() == KeyEvent.VK_RIGHT
					&& e.getKeyCode() == KeyEvent.VK_RIGHT) {
				slide.moveSlide(0);
			}

		}

	}

	public static void RefreshData() {
		WorkSpace.lives.setText("Lives: " + mainGamePanel.ballsLeft);
		WorkSpace.score.setText("Score: " + WorkSpace.Score);

	}

}
