package kana.logic;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;


public abstract class AbstractPaper {

	protected GraphicsContext gc;

	public AbstractPaper(GraphicsContext gc) {
		this.gc = gc;
	}

	protected void clearWhole() {
		Canvas canvas = gc.getCanvas();
		gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
	}

	protected void fillWhole() {
		Canvas canvas = gc.getCanvas();
		gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
	}

	public abstract void repaint();

}
