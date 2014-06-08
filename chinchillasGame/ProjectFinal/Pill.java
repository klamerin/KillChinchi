package chinchillasGame.ProjectFinal;

import java.io.File;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Pill extends JLabel {
	private static final long serialVersionUID = 626528000549314634L;

	Bricks brick;
	int width = 50;
	int height = 20;
	int type;
	int positionX;
	int positionY;
	String img;

	public Pill(int positionX, int positionY, Bricks brick) {
		this.brick = brick;
		this.positionX = brick.positionY;
		this.positionY = brick.positionY;
		Random rn = new Random();
		type = rn.nextInt(2);
		if (type == 0) {
			img = "pill1.jpg";
		} else {
			img = "pill2.jpg";
		}
		setIcon(new ImageIcon(System.getProperty("user.dir") + File.separator
				+ "src" + File.separator + "exercises" + File.separator + "img"
				+ File.separator + img));
		setOpaque(false);
		setBounds(positionX, positionY, width, height);
		setVisible(false);
	}

	public void drop(Bricks brick) {
		while (positionY <= 700) {
			positionY = +1;
			setLocation(positionX, positionY);
			if (positionY > (brick.positionY + brick.height)) {
				WorkSpace.mainGamePanel.moveToFront(this);
				WorkSpace.mainGamePanel.revalidate();
				WorkSpace.mainGamePanel.repaint();
				setVisible(true);
			}
		}
	}
}
