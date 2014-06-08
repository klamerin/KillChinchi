package chinchillasGame.ProjectFinal;

import java.io.File;
import java.util.Random;

import javax.swing.*;

public class GamePanel extends JLayeredPane {

	private static final long serialVersionUID = 859073071982973065L;
	public JLabel mainbkg = new JLabel();
	public Bricks[][] chinchillas;
	public int bricksLeft;
	public int ballsLeft;
	public boolean gameOver;
	public static final int WIDTH = 1050;
	public static final int HEIGHT = 700;
	public String img;
	public Pill pill;

	public void SetBackground(GamePanel panel, String img) {
		panel.mainbkg.setIcon(new ImageIcon(System.getProperty("user.dir")
				+ File.separator + "src" + File.separator + "exercises"
				+ File.separator + "img" + File.separator + img));
		panel.mainbkg.setBounds(0, 0, WIDTH, HEIGHT);
		panel.mainbkg.setOpaque(false);
		panel.add(panel.mainbkg, new Integer(0));
		panel.moveToBack(this.mainbkg);
	}

	public GamePanel() {
		super();
		bricksLeft = 126;
		ballsLeft = 4;
		gameOver = false;
	}

	public Bricks[][] Wall() {
		int hits = 1;
		chinchillas = new Bricks[14][10];
		for (int i = 0; i < 14; i++) {
			for (int j = 0; j < 9; j++) {
				boolean hasPill = false;
				Random rn = new Random();
				int p = rn.nextInt(3);
				if (p == 0) {
					img = "default.png";
					hits = 1;
				} else if (p == 1) {
					img = "default2.png";
					hits = 2;
				} else if (p == 2) {
					img = "default3.png";
					hits = 2;
					hasPill = true;
				}
				chinchillas[i][j] = new Bricks(35 + (i) * 70,
						30 + (j + 1) * 40, img, hits, hasPill);
				add(chinchillas[i][j], new Integer(10));
				if (chinchillas[i][j].hasPill == true) {
					pill = new Pill(chinchillas[i][j].positionX,
							chinchillas[i][j].positionY, chinchillas[i][j]);
					moveToBack(pill);
					add(pill);
				}

			}
		}
		return chinchillas;

	}

}
