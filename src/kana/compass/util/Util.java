package kana.compass.util;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.util.Duration;


public class Util {

	private Util() {
		throw new MyRuntimeException("aaa");
	}

	public static <T> Set<T> GetOneSet(T ta) {
		return new HashSet<T>(Arrays.asList(ta));
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
