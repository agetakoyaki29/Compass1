package kana.compass.logic;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.transform.Affine;


public abstract class AbstractPaper {

	private final GraphicsContext gc;
	private final Pen pen;

	public AbstractPaper(GraphicsContext gc, Affine coord) {
		this.gc = gc;
		this.pen = new Pen(gc, coord);
	}

	public final Pen getPen() { return pen; }

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
