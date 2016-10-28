package kana.logic;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import kana.drawn.hot.OldHotDrawn;

public class HotPaper extends AbstractPaper {

	private OldHotDrawn drawing = null;
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

		if(drawing == null) return;
		drawing.draw(gc);
	}

	public void clearDrawing() {
		drawing = null;
		repaint();
	}

	public void setDrawing(OldHotDrawn drawn) {
		drawing = drawn;
	}
//	public void setDrawings(OldHotDrawn... drawns) {
//		setDrawings(Arrays.asList(drawns));
//	}
//	public void setDrawings(Collection<? extends OldHotDrawn> drawns) {
//		drawings.clear();
//		drawings.addAll(drawns);
//	}

}
