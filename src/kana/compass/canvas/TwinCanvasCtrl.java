package kana.compass.canvas;

import javafx.beans.binding.ObjectBinding;
import javafx.beans.property.ReadOnlyDoubleProperty;
import javafx.fxml.FXML;
import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.ScrollPane;
import javafx.stage.Screen;
import kana.compass.geometry.Geo;
import kana.compass.stage.transition.Ctrl;
import lombok.Getter;


public class TwinCanvasCtrl extends Ctrl {
	
	@FXML private Canvas canvas;
	@FXML private Canvas hotCanvas;
	
	@Getter
	private final ScopeTransform scope;
	@Getter
	private final CanvasMouseHandler handler;
	@Getter
	private final Paper paper;
	@Getter
	private final HotPaper hotPaper;
	
	public TwinCanvasCtrl() {
		// canvas full screen
		Rectangle2D screen = Screen.getPrimary().getVisualBounds();
		canvas.setWidth(screen.getWidth());
		canvas.setHeight(screen.getHeight());
		hotCanvas.setWidth(canvas.getWidth());
		hotCanvas.setHeight(canvas.getHeight());
		
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

	@Override
	public ScrollPane getRoot() {
		return (ScrollPane) super.getRoot();
	}

	public void repaint() {
		paper.repaint();
		hotPaper.repaint();
	}

	private ObjectBinding<Point2D> makeCenterProperty() {
		ReadOnlyDoubleProperty width = getRoot().widthProperty();
		ReadOnlyDoubleProperty height = getRoot().heightProperty();

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
