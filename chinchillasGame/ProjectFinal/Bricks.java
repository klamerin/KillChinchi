package chinchillasGame.ProjectFinal;

import java.io.File;
import javax.swing.*;

public class Bricks extends JLabel {

	public static final long serialVersionUID = 5089547125457248013L;
	public int positionX;
	public int positionY;
	public JLabel bkg = new JLabel();
	public String img;
	public boolean hasPill;
	public boolean isDestroyed;
	public int width = 60;
	public int height = 30;
	// public Pill pill;
	public int points = 100;
	public int hits;

	public Bricks(int positionX, int positionY, String img, int hits,
			boolean hasPill) {
		super();
		this.hits = hits;
		this.positionX = positionX;
		this.positionY = positionY;
		this.setLayout(null);
		this.setOpaque(false);

		bkg.setIcon(new ImageIcon(System.getProperty("user.dir")
				+ File.separator + "src" + File.separator + "exercises"
				+ File.separator + "img" + File.separator + img));
		bkg.setBounds(0, 0, width, height);
		bkg.setOpaque(false);

		this.add(bkg);
		this.setBounds(positionX, positionY, width, height);
		isDestroyed = false;
	}

	public void die() {
		if (isDestroyed == true) {
			WorkSpace.mainGamePanel.remove(this);
			WorkSpace.mainGamePanel.revalidate();
			WorkSpace.mainGamePanel.repaint();
			if (hasPill == true) {
				WorkSpace.mainGamePanel.pill.drop(this);
			}
			WorkSpace.Score += this.points;
			if (WorkSpace.Score % 1000 == 0) {
				WorkSpace.mainGamePanel.ballsLeft++;
			}
		}
	}

}
