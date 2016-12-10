package kana.compass.canvas;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;


public abstract class AbstractPaper {

	private final GraphicsContext gc;
	private final Pen pen;

	public AbstractPaper(GraphicsContext gc, ScopeTransform scope) {
		this.gc = gc;
		this.pen = new Pen(gc, scope);
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
