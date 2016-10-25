package kana.sceneTransition;

import java.io.IOException;
import java.net.URL;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import kana.main.MyRuntimeException;


public abstract class Ctrl {

	private final Parent root;

	public Ctrl() {
		FXMLLoader loader = new FXMLLoader( getFXMLURL() );
		loader.setController(this);

		try {
			loader.load();
		} catch (IOException e) {
			throw new MyRuntimeException(e);
		}

		root = loader.getRoot();
	}

	/**
	 * 同階層かつ、正しい命名則のときだけ
	 */
	protected URL getFXMLURL() {
		String className = getClass().getSimpleName();
		int last = className.lastIndexOf("C");
		String fileName = className.substring(0, last).concat(".fxml");
		return getClass().getResource(fileName);
	}

	public Parent getRoot() { return root; }

}
