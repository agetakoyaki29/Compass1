package screenTransition;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;


public abstract class Launcher extends Application {

	@Override
	public abstract void start(Stage primaryStage);

	/**
	 * @param clazz 移動先のクラス
	 * @return 移動先の初期化されたコントローラのインスタンス
	 */
	public PageController movePage(Class<? extends PageController> clazz) {
		// load
		FXMLLoader loader = getFXMLLoader(clazz);
		PageController ctrl = loader.getController();
		Scene scene = new Scene( loader.getRoot() );

		// init controller
		ctrl.init();

		// set gui
		getStage().setScene(scene);

		return ctrl;
	}

	protected abstract Stage getStage();

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
	private FXMLLoader getFXMLLoader(Class<? extends Controller> clazz) {
		String fxml = getFXMLFileName(clazz);

		FXMLLoader loader = new FXMLLoader( clazz.getResource(fxml) );
		try {
			loader.load();
		} catch (IOException e) {
			loader = null;
			e.printStackTrace();
		}

		return loader;
	}

	/**
	 * Controllerに対応するFXMLの名前を取得する。
	 * 命名則を前提としている。
	 * @param clazz Controllerの実装クラス
	 */
	private String getFXMLFileName(Class<? extends Controller> clazz) {
		String str = clazz.getSimpleName();
		int last = str.lastIndexOf("Controller");
		return str.substring(0, last).concat(".fxml");
	}

}

