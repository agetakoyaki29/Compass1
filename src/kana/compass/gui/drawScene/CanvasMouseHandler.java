package kana.compass.gui.drawScene;

import java.util.function.Consumer;

import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import kana.compass.geometry.Geo;
import kana.compass.logic.ScopeTransform;


public class CanvasMouseHandler {

	private static final EventHandler<MouseEvent> DONO = e->{};

	private final ScopeTransform scope;

	private EventHandler<MouseEvent> onClickedLeft = DONO;
	private EventHandler<MouseEvent> onClickedRight = DONO;
	private EventHandler<MouseEvent> onMoved         = DONO;

	private EventHandler<ScrollEvent> onScroll = e->{};

	private Consumer<Point2D> draggedMiddleDelta = pt->{};

	private Point2D oldPt = null;


	public CanvasMouseHandler(Node target, ScopeTransform scope) {
		this.scope = scope;

		target.setOnMouseClicked(this::onClicked);
		target.setOnMousePressed(this::onPressed);
		target.setOnMouseMoved(this::onMoved);
		target.setOnMouseDragged(this::onDragged);

		target.setOnScroll(this::onScroll);
	}

	// ----

	private void onClicked(MouseEvent event) {
		switch (event.getButton()) {
		case PRIMARY:
			onClickedLeft.handle(event);
			break;
		case SECONDARY:
			onClickedRight.handle(event);
			break;
		default:
			break;
		}
	}
	private void onPressed(MouseEvent event) {
		oldPt = Geo.newPoint2D(event);
	}
	private void onMoved(MouseEvent event) {
		onMoved.handle(event);
	}
	private void onDragged(MouseEvent event) {
		if(! event.getButton().equals(MouseButton.MIDDLE)) return;
		Point2D newPt = Geo.newPoint2D(event);
		Point2D delta = newPt.subtract(oldPt);
		delta = scope.inverseDeltaTransform(delta);
		draggedMiddleDelta.accept(delta);
		oldPt = newPt;
	}

	private void onScroll(ScrollEvent event) {
		onScroll.handle(event);
		event.consume();
	}

	// ----

	public void setClickedLeftPoint(Consumer<Point2D> con) {
		onClickedLeft = ptc2meh(con);
	}
	public void setClickedRightPoint(Consumer<Point2D> con) {
		onClickedRight = ptc2meh(con);
	}
	public void setMovePoint(Consumer<Point2D> con) {
		onMoved = ptc2meh(con);
	}
	public void setDraggedMiddleDelta(Consumer<Point2D> con) {
		draggedMiddleDelta = con;
	}

	public void clearOnClickedLeft() {
		onClickedLeft = DONO;
	}
	public void clearOnClickedRight() {
		onClickedRight = DONO;
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
			pt = scope.inverseTransform(pt);
			ptc.accept(pt);
		};
	}

}
