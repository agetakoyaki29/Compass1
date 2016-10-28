package kana.main;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.util.Duration;


public class Main {

	public static void main(String[] args) {
		// launch
		Application.launch(MainApp.class, args);
	}

	public static BackgroundFill getSimpleBackgroudFill(Color color) {
		return new BackgroundFill(color, CornerRadii.EMPTY, Insets.EMPTY);
	}

	public static Background getSimpleBackgroud(Color color) {
		return new Background(
				new BackgroundFill(color, CornerRadii.EMPTY, Insets.EMPTY));
	}

	public static Animation getCallbackAnimation(EventHandler<ActionEvent> handler) {
		return new Timeline(new KeyFrame(Duration.ONE, handler));
	}

}

