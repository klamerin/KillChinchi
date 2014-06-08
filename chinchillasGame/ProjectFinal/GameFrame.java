package chinchillasGame.ProjectFinal;

import java.awt.*;

import javax.swing.*;

public class GameFrame extends JFrame {
	private static final long serialVersionUID = -4983768601010081909L;

	public GameFrame() throws HeadlessException {
		super();
	}

	public GameFrame(GraphicsConfiguration gc) {
		super(gc);
	}

	public GameFrame(String title, GraphicsConfiguration gc) {
		super(title, gc);
	}

	public GameFrame(String title) throws HeadlessException {
		super(title);
	}

}
