package chinchillasGame.ProjectFinal;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Ball extends Bricks {

	private static final long serialVersionUID = 4641443129860641699L;

	public Ball(int positionX, int positionY, String img, int hits,
			boolean hasPill) {
		super(positionX, positionY, img, hits, hasPill);
		this.setBounds(positionX, positionY, size, size);
		bkg.setBounds(0, 0, size, size);
	}

	int difficulty = 1;
	public int size = 20;
	public int stepX = difficulty;
	public int stepY = -difficulty;
	public String soundName;
	public void resetState() {
		positionX = 525;
		positionY = 590;
	}

	public void playSound2(String soundName) {
		String file = System.getProperty("user.dir")
				+ File.separator + "src" + File.separator + "exercises"
				+ File.separator + "img" + File.separator + soundName;
		File soundFile;
		Clip clip;
		AudioInputStream audioIn;
		soundFile = new File(file);
		try {
			audioIn = AudioSystem.getAudioInputStream(soundFile);
			clip = AudioSystem.getClip();
			clip.open(audioIn);
			clip.start();
		} catch (UnsupportedAudioFileException | IOException e) {
			e.printStackTrace();
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		}
	}

	public void winGame() {
		if (WorkSpace.mainGamePanel.bricksLeft == 0) {
			playSound2("C:\\Users\\Klamerin\\workspace\\klm\\klm1\\src\\exercises\\img\\win.wav");
			WorkSpace.mainGamePanel.gameOver = true;
			WorkSpace.mainGamePanel.setVisible(false);
			WorkSpace.gameStarted = false;
			WorkSpace.endpane.setVisible(true);
			WorkSpace.endpane.button.setVisible(true);
			WorkSpace.endpane.exitButton.setVisible(true);
			WorkSpace.endpane.winGame.setVisible(true);
		}
	}

	public void moveBall(Slide slide, Bricks[][] chinchillas) {
		setLocation(positionX, positionY);
		positionX += stepX;
		positionY += stepY;

		if (positionX + size >= GamePanel.WIDTH) {
			stepX = -difficulty;
//			playSound2("C:\\Users\\Klamerin\\workspace\\klm\\klm1\\src\\exercises\\img\\trent.wav");
			playSound2("trent.wav");
		}

		if (positionX <= 0) {
			stepX = difficulty;
//			playSound2("C:\\Users\\Klamerin\\workspace\\klm\\klm1\\src\\exercises\\img\\trent.wav");
			playSound2("trent.wav");
		}

		if (positionY + size >= GamePanel.HEIGHT) {
//			playSound2("C:\\Users\\Klamerin\\workspace\\klm\\klm1\\src\\exercises\\img\\fall.wav");
			playSound2("fall.wav");
			try {
				Thread.sleep(1700);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			WorkSpace.mainGamePanel.ballsLeft--;
			WorkSpace.gameStarted = false;
			resetState();
			setLocation(525, 590);
			WorkSpace.slide.resetState();
			WorkSpace.slide.setLocation(475, 600);

			if (WorkSpace.gameStarted) {
				moveBall(slide, chinchillas);
			}

			if (WorkSpace.mainGamePanel.ballsLeft < 0) {
//				playSound2("C:\\Users\\Klamerin\\workspace\\klm\\klm1\\src\\exercises\\img\\lose.wav");
				playSound2("lose.wav");
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				WorkSpace.mainGamePanel.gameOver = true;
				WorkSpace.mainGamePanel.setVisible(false);
				WorkSpace.gameStarted = false;
				WorkSpace.endpane.setVisible(true);
				WorkSpace.endpane.button.setVisible(true);
				WorkSpace.endpane.exitButton.setVisible(true);
				WorkSpace.endpane.loseGame.setVisible(true);
				WorkSpace.mainGamePanel.ballsLeft = 4;
				WorkSpace.Score = 0;
			}
		}

		if (positionY <= 0) {
			stepY = difficulty;
//			playSound2("C:\\Users\\Klamerin\\workspace\\klm\\klm1\\src\\exercises\\img\\trent.wav");
			playSound2("trent.wav");
		}

		// slider
		if ((positionY + size) >= slide.positionY
				&& positionY <= slide.positionY
				&& (positionX + size) > slide.positionX
				&& positionX < (slide.positionX + slide.width)) {
			stepY = -difficulty;
//			playSound2("C:\\Users\\Klamerin\\workspace\\klm\\klm1\\src\\exercises\\img\\trent.wav");
			playSound2("trent.wav");
		}

		// bricks
		for (int i = 0; i < 14; i++) {
			for (int j = 0; j < 9; j++) {
				if (chinchillas[i][j] != null) {
					// top
					if (chinchillas[i][j] != null) {
						if ((positionY + size) >= chinchillas[i][j].positionY
								&& positionY <= chinchillas[i][j].positionY
								&& (positionX + (size / 2)) >= chinchillas[i][j].positionX
								&& (positionX + (size / 2)) <= (chinchillas[i][j].positionX + chinchillas[i][j].width)) {
							stepY = -difficulty;
//							playSound2("C:\\Users\\Klamerin\\workspace\\klm\\klm1\\src\\exercises\\img\\die.wav");
							playSound2("die.wav");
							chinchillas[i][j].hits--;
							if (chinchillas[i][j].hits == 0) {
								WorkSpace.mainGamePanel.bricksLeft--;
								chinchillas[i][j].isDestroyed = true;
								chinchillas[i][j].die();
								chinchillas[i][j] = null;
								winGame();
							}
						}
					}

					// bottom
					if (chinchillas[i][j] != null) {
						if (positionY <= (chinchillas[i][j].positionY + chinchillas[i][j].height)
								&& positionY >= chinchillas[i][j].positionY
								&& (positionX + (size / 2)) >= chinchillas[i][j].positionX
								&& (positionX + (size / 2)) <= (chinchillas[i][j].positionX + chinchillas[i][j].width)) {
							stepY = difficulty;
//							playSound2("C:\\Users\\Klamerin\\workspace\\klm\\klm1\\src\\exercises\\img\\die.wav");
							playSound2("die.wav");
							chinchillas[i][j].hits--;
							if (chinchillas[i][j].hits == 0) {
								WorkSpace.mainGamePanel.bricksLeft--;
								chinchillas[i][j].isDestroyed = true;
								chinchillas[i][j].die();
								chinchillas[i][j] = null;
								winGame();
							}
						}
					}
					// left
					if (chinchillas[i][j] != null) {
						if ((positionX + size) >= chinchillas[i][j].positionX
								&& positionX <= chinchillas[i][j].positionX
								&& (positionY + (size / 2)) >= chinchillas[i][j].positionY
								&& (positionY + (size / 2)) <= (chinchillas[i][j].positionY + chinchillas[i][j].height)) {
							stepX = -difficulty;
//							playSound2("C:\\Users\\Klamerin\\workspace\\klm\\klm1\\src\\exercises\\img\\die.wav");
							playSound2("die.wav");
							chinchillas[i][j].hits--;
							if (chinchillas[i][j].hits == 0) {
								WorkSpace.mainGamePanel.bricksLeft--;
								chinchillas[i][j].isDestroyed = true;
								chinchillas[i][j].die();
								chinchillas[i][j] = null;
								winGame();
							}
						}
					}

					// right
					if (chinchillas[i][j] != null) {
						if (positionX <= (chinchillas[i][j].positionX + chinchillas[i][j].width)
								&& (positionX + size) >= (chinchillas[i][j].positionX + chinchillas[i][j].width)
								&& (positionY + (size / 2)) >= chinchillas[i][j].positionY
								&& (positionY + (size / 2)) <= (chinchillas[i][j].positionY + chinchillas[i][j].height)) {
							stepX = difficulty;
//							playSound2("C:\\Users\\Klamerin\\workspace\\klm\\klm1\\src\\exercises\\img\\die.wav");
							playSound2("die.wav");
							chinchillas[i][j].hits--;
							if (chinchillas[i][j].hits == 0) {
								WorkSpace.mainGamePanel.bricksLeft--;
								chinchillas[i][j].isDestroyed = true;
								chinchillas[i][j].die();
								chinchillas[i][j] = null;
								winGame();
							}
						}
					}
				} else {
					// System.out.println("null");
				}
			}
		}

	}
}
