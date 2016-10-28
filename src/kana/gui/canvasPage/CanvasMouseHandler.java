package kana.gui.canvasPage;

import java.util.function.Consumer;

import javafx.scene.Node;
import javafx.scene.input.MouseEvent;


public class CanvasMouseHandler {
	public Consumer<MouseEvent> onLeftClicked = DONO;
	public Consumer<MouseEvent> onRightClicked = DONO;
	public Consumer<MouseEvent> onMoved       = DONO;

	public CanvasMouseHandler(Node target) {
		target.setOnMouseClicked(event -> onClicked(event));
		target.setOnMouseMoved(event -> onMoved(event));
	}

	private void onClicked(MouseEvent event) {
		switch (event.getButton()) {
		case PRIMARY:
			onLeftClicked.accept(event);
			break;

		case MIDDLE:
			break;

		case SECONDARY:
			onRightClicked.accept(event);
			break;

		default:
			break;
		}
	}

	private void onMoved(MouseEvent event) {
		onMoved.accept(event);
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

	private static final Consumer<MouseEvent> DONO = e->{};

}
