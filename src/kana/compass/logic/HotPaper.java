package kana.compass.logic;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import kana.compass.logic.OpCentral.HotDrawn;

public class HotPaper extends AbstractPaper {

	private HotDrawn drawing = null;
	// TODO public HotDrawn select = null;

	public HotPaper(GraphicsContext gc, ScopeTransform scope) {
		super(gc, scope);

		gc.setFill(null);
		gc.setStroke(Color.RED);
		clearWhole();
	}

	@Override
	public void repaint() {
		clearWhole();

		if(drawing == null) return;
		drawing.preDraw(getPen());
	}

	public void clearDrawing() {
		drawing = null;
		repaint();
	}

	public void setDrawing(HotDrawn drawn) {
		drawing = drawn;
	}

}
