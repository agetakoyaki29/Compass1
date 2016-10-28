package kana.gui.canvasPage;

import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;


public class CanvasMouseHandler {
	public EventHandler<MouseEvent> onLeftClicked = DONO;
	public EventHandler<MouseEvent> onRightClicked = DONO;
	public EventHandler<MouseEvent> onMoved       = DONO;

	public CanvasMouseHandler(Node target) {
		target.setOnMouseClicked(this::onClicked);
		target.setOnMouseMoved(this::onMoved);
	}

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

	public void clearOnLeftClicked() {
		onLeftClicked = DONO;
	}
	public void clearOnRightClicked() {
		onRightClicked = DONO;
	}
	public void clearOnMoved() {
		onMoved = DONO;
	}

	private static final EventHandler<MouseEvent> DONO = e->{};

}
