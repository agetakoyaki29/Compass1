package gui;

import java.util.Optional;

import javafx.stage.Stage;
import screenTransition.Launcher;
import screenTransition.PageController;

public class MainApp extends Launcher{

	@Override
	public void start(Stage primaryStage) {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	protected Stage getStage() {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	/**
	 * 初期Page
	 */
	private static final Class<? extends PageController> START_PAGE = PlayPageController.class;

	// ---- static ----

	/**
	 * 自分自身
	 */
	private static Launcher instance;

	/**
	 * インスタンスを取得するよ。
	 * @return GUILauncherのインスタンス。
	 */
	public static Optional<Launcher> getInstance(){
		return Optional.ofNullable(instance);
	}

}
