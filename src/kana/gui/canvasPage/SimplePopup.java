package kana.gui.canvasPage;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.PauseTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.Timeline;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.stage.Popup;
import javafx.util.Duration;
import kana.drawn.geometry.Vector;


public class SimplePopup extends Popup {
	private final Label label;

	public SimplePopup(String text) {
		label = new Label(text);
		label.getStyleClass().setAll("tooltip");

		this.getContent().add(label);
	}

	public void show(Node ownerNode, Vector nodeAnchor) {
//		Window window = ownerNode.getScene().getWindow();
		Point2D anchor = ownerNode.localToScreen(nodeAnchor.x, nodeAnchor.y);
		this.show(ownerNode, anchor.getX(), anchor.getY());

		getTimeLine().play();
	}
	public void show(Node ownerNode) {
		show(ownerNode, Vector.zero);
	}

	private static final Duration waitTime = Duration.seconds(2);
	private static final Duration faidTime = Duration.seconds(1);

	private Animation getTimeLine() {
		return new SequentialTransition(
			new PauseTransition(waitTime),
			new Timeline(
				new KeyFrame(faidTime,
					e -> this.hide(),
					new KeyValue(label.opacityProperty(), 0))
		));
	}

}
