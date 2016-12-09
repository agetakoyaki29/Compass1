package kana.compass.stage.transition;

import java.io.IOException;
import java.net.URL;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Window;
import kana.compass.util.MyRuntimeException;


public abstract class Ctrl {

	private final Parent root;

	public Ctrl() {
		FXMLLoader loader = new FXMLLoader(getFXMLLocation());
		loader.setController(this);

		try {
			loader.load();
		} catch (IOException e) {
			throw new MyRuntimeException("loader can't load with file("+getFXMLLocation()+")", e);
		}

		root = loader.getRoot();
	}

	/**
	 * クラスの命名則に依存する
	 */
	protected String getFXMLName() {
		String prefix = "C";
		String className = getClass().getSimpleName();
		int lastIndex = className.lastIndexOf(prefix);
		if(lastIndex < 0) throw new MyRuntimeException("class name("+className+") doesn't include prefix("+prefix+").");
		String fileName = className.substring(0, lastIndex).concat(".fxml");
		return fileName;
	}

	/**
	 * このクラスのクラスローダを使う
	 */
	protected URL getFXMLLocation() {
		String fileName = getFXMLName();
		URL location = getClass().getResource(fileName);
		if(location == null) throw new MyRuntimeException("class("+getClass()+") can't find a file with name("+fileName+").");
		return location;
	}

	public Parent getRoot() { return root; }

	public Window getWindow() { return root.getScene().getWindow(); }

}
