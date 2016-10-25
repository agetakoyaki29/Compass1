package kana.screenTransition;

import java.io.IOException;
import java.util.Optional;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;


public abstract class Launcher extends Application {

	private Stage stage = null;
	private String title = "たいとる";
	private PageController pageCtrl;

	@Override
	public void start(Stage primaryStage) {
		// インスタンス
		setInstance(this);

		// set ステージ
		stage = primaryStage;

		// init stage
		setTitle(getInitTitle());

		stage.show();

		// 初期画面move
		movePage(getFirstPage());
	}

	/**
	 * @param clazz 移動先のクラス
	 * @return 移動先の初期化されたコントローラのインスタンス
	 */
	public void movePage(Class<? extends PageController> clazz) {
		// load
		FXMLLoader loader = getFXMLLoader(clazz).get();
		PageController ctrl = loader.getController();
		Scene scene = new Scene( loader.getRoot() );

		// set gui
		stage.setScene(scene);

		// init controller
		ctrl.init();

		pageCtrl = ctrl;
	}

	public final void setTitle(String title) {
		if(stage == null) return;

		this.title = title;
		stage.setTitle(title);
	}

	public final String getTitle() {
		return title;
	}

	public final Stage getStage() {
		//return Optional.ofNullable(stage);
		return stage;
	}

	public final PageController getPageController() { return pageCtrl; }

	// ---- abstract ----

	/**
	 * staticなinstenceにsetするために使う
	 */
	protected abstract void setInstance(Launcher me);

	public abstract Class<? extends PageController> getFirstPage();

	public abstract String getInitTitle();

	// ---- private ----

	/**
	 * loadされたFXMLLoaderを返す、便利関数。
	 * エラーもここで処理する。
	 *
	 * 帰ってくるloaderを使って、
	 * loader.getRoot()や、
	 * loader.getController()として使ってもらう予定。
	 *
	 * @param clazz Controllerの実装クラス
	 * @return 読み込み失敗時null
	 */
	public static Optional<FXMLLoader> getFXMLLoader(Class<? extends Controller> clazz) {
		String fxml = getFXMLFileName(clazz);

		FXMLLoader loader = new FXMLLoader( clazz.getResource(fxml) );
		try {
			loader.load();
		} catch (IOException e) {
			loader = null;
			e.printStackTrace();
		}

		return Optional.ofNullable(loader);
	}

	/**
	 * Controllerに対応するFXMLの名前を取得する。
	 * 命名則を前提としている。
	 * @param clazz Controllerの実装クラス
	 */
	private static String getFXMLFileName(Class<? extends Controller> clazz) {
		String str = clazz.getSimpleName();
		int last = str.lastIndexOf("Controller");
		return str.substring(0, last).concat(".fxml");
	}

}

