package kana.compass.gui.drawScene;

import java.util.function.Consumer;

import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.transform.Affine;
import kana.compass.geometry.Geo;


public class CanvasMouseHandler {

	private static final EventHandler<MouseEvent> DONO = e->{};

	private final Affine coord;

	private EventHandler<MouseEvent> onLeftClicked = DONO;
	private EventHandler<MouseEvent> onRightClicked = DONO;
	private EventHandler<MouseEvent> onMoved         = DONO;

	private EventHandler<ScrollEvent> onScroll = e->{};


	public CanvasMouseHandler(Canvas hotCanvas, Affine coord) {
		this.coord = coord;

		hotCanvas.setOnMouseClicked(this::onClicked);
		hotCanvas.setOnMouseMoved(this::onMoved);
		hotCanvas.setOnScroll(this::onScroll);
	}

	// ----

	private void onClicked(MouseEvent event) {
		switch (event.getButton()) {
		case PRIMARY:
			onLeftClicked.handle(event);
			break;
		case SECONDARY:
			onRightClicked.handle(event);
			break;
		default:
			break;
		}
	}

	private void onMoved(MouseEvent event) {
		onMoved.handle(event);
	}

	private void onScroll(ScrollEvent event) {
		onScroll.handle(event);
	}

	// ----

	public void setLeftPoint(Consumer<Point2D> con) {
		onLeftClicked = ptc2meh(con);
	}
	public void setRightPoint(Consumer<Point2D> con) {
		onRightClicked = ptc2meh(con);
	}
	public void setMovePoint(Consumer<Point2D> con) {
		onMoved = ptc2meh(con);
	}

	public void clearOnLeftClicked() {
		onLeftClicked = DONO;
	}
	public void clearOnRightClicked() {
		onRightClicked = DONO;
	}
	public void clearOnMoved() {
		onMoved = DONO;
	}

	public void setOnScroll(EventHandler<ScrollEvent> handler) {
		onScroll  = handler;
	}

	// ----

	private EventHandler<MouseEvent> ptc2meh(Consumer<Point2D> ptc) {
		return event -> {
			Point2D pt = Geo.newPoint2D(event);
			pt = Geo.inverseTransform(coord, pt);
			ptc.accept(pt);
		};
	}

}
