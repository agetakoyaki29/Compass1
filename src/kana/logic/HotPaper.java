package kana.logic;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import kana.drawn.hot.HotDrawn;

public class HotPaper extends AbstractPaper {

	public HotDrawn drawing = null;
	// TODO public HotDrawn select = null;

	public HotPaper(GraphicsContext gc) {
		super(gc);

		gc.setFill(null);
		gc.setStroke(Color.RED);
		clearWhole();
	}

	@Override
	public void repaint() {
		clearWhole();

		if(drawing != null) drawing.draw(gc);
	}

}
