package gui;

import main.MainApp;
import screenTransition.PageController;

public class CanvasPageController implements PageController {

	@Override
	public void init() {
		MainApp.getInstance().get().getStage().setMaximized(true);


	}

}
