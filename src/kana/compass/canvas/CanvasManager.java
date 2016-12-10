package kana.compass.canvas;

import javafx.beans.binding.ObjectBinding;
import javafx.beans.property.ReadOnlyDoubleProperty;
import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.ScrollPane;
import kana.compass.geometry.Geo;
import kana.compass.logic.ScopeTransform;


public class CanvasManager {

	private final ScrollPane sp;
	private final Canvas canvas;
	private final Canvas hotCanvas;
	private final ScopeTransform scope;

	private final CanvasMouseHandler handler;
	private final Paper paper;
	private final HotPaper hotPaper;

	public CanvasManager(ScrollPane sp, Canvas canvas, Canvas hotCanvas) {
		this.sp = sp;
		this.canvas = canvas;
		this.hotCanvas = hotCanvas;
		ObjectBinding<Point2D> centerProperty = makeCenterProperty();
		this.scope = new ScopeTransform(centerProperty);
		handler = new CanvasMouseHandler(hotCanvas, scope);
		paper = new Paper(canvas.getGraphicsContext2D(), scope);
		hotPaper = new HotPaper(hotCanvas.getGraphicsContext2D(), scope);

		// handler
		final double scrollBase = 0.001;
		handler.setOnScroll(event -> {		// scroll => zoom
			if(event.getDeltaY() == 0) return;

			double scale = Math.exp(event.getDeltaY() * scrollBase);
			Point2D pivot = Geo.newPoint2D(event);
			scope.appendScale(scale, pivot);
			repaint();
		});
		handler.setDraggedMiddleDelta(delta -> {	// drag => scroll
			scope.appendTranslation(delta);
			repaint();
		});

		// repaint
		paper.repaint();
		hotPaper.repaint();
	}

	public Paper getPaper() { return paper; }
	public HotPaper getHotPaper() { return hotPaper; }
	public CanvasMouseHandler getHandler() { return handler; }
	public ScopeTransform getScope() { return scope; }

	public void repaint() {
		paper.repaint();
		hotPaper.repaint();
	}

	private ObjectBinding<Point2D> makeCenterProperty() {
		ReadOnlyDoubleProperty width = sp.widthProperty();
		ReadOnlyDoubleProperty height = sp.heightProperty();

		ObjectBinding<Point2D> centerProperty = new ObjectBinding<Point2D>() {
			{
				super.bind(width, height);
			}
			@Override protected Point2D computeValue() {
				return new Point2D(width.get()/2, height.get()/2);
			}
		};

		return centerProperty;
	}

}
