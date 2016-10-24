package screenTransition;

import java.io.IOException;
import java.util.Optional;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;


public abstract class Launcher extends Application {

	private Stage stage = null;

	@Override
	public void start(Stage primaryStage) {
		// インスタンス
		setInstance(this);

		// set ステージ
		stage = primaryStage;

		// init stage
		stage.setTitle(getTitle());

		// 初期画面move
		movePage(getFirstPage());

		stage.show();
	}

	/**
	 * @param clazz 移動先のクラス
	 * @return 移動先の初期化されたコントローラのインスタンス
	 */
	public PageController movePage(Class<? extends PageController> clazz) {
		// load
		FXMLLoader loader = getFXMLLoader(clazz).get();
		PageController ctrl = getPageController(loader).get();
		Scene scene = new Scene( loader.getRoot() );

		// init controller
		ctrl.init();

		// set gui
		getStage().get().setScene(scene);

		return ctrl;
	}

	protected final Optional<Stage> getStage() {
		return Optional.ofNullable(stage);
	}

	// ---- abstract ----

	/**
	 * staticなinstenceにsetするために使う
	 */
	protected abstract void setInstance(Launcher me);

	public abstract String getTitle();

	public abstract Class<? extends PageController> getFirstPage();

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
	private Optional<FXMLLoader> getFXMLLoader(Class<? extends Controller> clazz) {
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
	private String getFXMLFileName(Class<? extends Controller> clazz) {
		String str = clazz.getSimpleName();
		int last = str.lastIndexOf("Controller");
		return str.substring(0, last).concat(".fxml");
	}

	private Optional<PageController> getPageController(FXMLLoader loader) {
		PageController ctrl = loader.getController();
		return Optional.ofNullable(ctrl);
	}

}

