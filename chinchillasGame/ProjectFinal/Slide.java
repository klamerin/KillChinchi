package chinchillasGame.ProjectFinal;

public class Slide extends Bricks {
	private static final long serialVersionUID = 519326454673268210L;

	public int width = 100;
	public int height = 60;
	public int positionX = 475;
	public int positionY = 600;
	Pill pill;

	public Slide(int positionX, int positionY, String img, int hits,
			boolean hasPill) {
		super(positionX, positionY, img, hits, hasPill);
		this.setBounds(positionX, positionY, width, height);
		bkg.setBounds(0, 0, width, height);

	}

	public void resetState() {
		positionX = 475;
		positionY = 600;
	}

	public void moveSlide(int x) {
		positionX += x;
	}

	public void absorbPill(Pill pill) {
		if ((positionY + height) >= pill.positionY
				&& positionY <= pill.positionY && positionX >= pill.positionX
				&& positionX <= (pill.positionX + pill.width)) {
			pill.setVisible(false);
			WorkSpace.mainGamePanel.ballsLeft++;
		}
	}

}
