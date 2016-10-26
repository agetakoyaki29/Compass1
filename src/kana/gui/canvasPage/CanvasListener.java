package kana.gui.canvasPage;

import java.util.function.Consumer;

import javafx.scene.Node;
import javafx.scene.input.MouseEvent;


public class CanvasListener {
	public Consumer<MouseEvent> leftClicked = e->{};
	public Consumer<MouseEvent> rightClicked = e->{};
	public Consumer<MouseEvent> moved       = e->{};

	public CanvasListener(Node target) {
		target.setOnMouseClicked(event -> onClicked(event));
		target.setOnMouseMoved(event -> onMoved(event));
	}

	private void onClicked(MouseEvent event) {

		switch (event.getButton()) {
		case PRIMARY:
			leftClicked.accept(event);
			break;

		case MIDDLE:
			break;

		case SECONDARY:
			rightClicked.accept(event);
			break;

		default:
			break;
		}
	}

	private void onMoved(MouseEvent event) {
		moved.accept(event);
	}

}
