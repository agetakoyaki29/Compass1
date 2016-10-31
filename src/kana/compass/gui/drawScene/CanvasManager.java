package kana.compass.gui.drawScene;

import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.transform.Affine;
import kana.compass.geometry.Geo;
import kana.compass.logic.HotPaper;
import kana.compass.logic.Paper;
import kana.compass.util.MyRuntimeException;


public class CanvasManager {

	private final Canvas canvas;
	private final Canvas hotCanvas;

	private final Affine coord = new Affine();
	private double scale = 1;

	private final CanvasMouseHandler handler;
	private final Paper paper;
	private final HotPaper hotPaper;

	public CanvasManager(Canvas canvas, Canvas hotCanvas) {
		this.canvas = canvas;
		this.hotCanvas = hotCanvas;
		handler = new CanvasMouseHandler(hotCanvas, coord);
		paper = new Paper(canvas.getGraphicsContext2D(), coord);
		hotPaper = new HotPaper(hotCanvas.getGraphicsContext2D(), coord);

		final double scrollBase = 0.001;
		handler.setOnScroll(event -> {
			if(event.getDeltaY() == 0) return;

			double scale = Math.exp(event.getDeltaY() * scrollBase);
			Point2D pivot = Geo.newPoint2D(event);
			pivot = Geo.inverseTransform(coord, pivot);
			appendScale(scale, pivot);
			paper.repaint();

			event.consume();
		});


		paper.repaint();
		hotPaper.repaint();
	}

	public Paper getPaper() { return paper; }
	public HotPaper getHotPaper() { return hotPaper; }
	public CanvasMouseHandler getHandler() { return handler; }

	public void appendTranslation(double tx, double ty) {
		coord.appendTranslation(tx, ty);
	}
	public void appendScale(double s, Point2D pivot) {
		if(s <= 0) throw new MyRuntimeException("d>0, plz");
		scale *= s;
		coord.appendScale(s, s, pivot);
	}
	public void resetScale(Point2D pivot) {
		appendScale(1/scale, pivot);
	}

}
