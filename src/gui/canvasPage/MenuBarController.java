package gui.canvasPage;

import javafx.fxml.FXMLLoader;
import screenTransition.Controller;
import screenTransition.Launcher;

public class MenuBarController implements Controller {

	private CanvasPageController canvasPageController;

	public MenuBarController(CanvasPageController canvasPageController) {
		this.canvasPageController = canvasPageController;

		FXMLLoader loader = Launcher.getFXMLLoader(this.getClass()).get();
		loader.setController(this);

		System.out.println(loader.getRoot());
	}

}
