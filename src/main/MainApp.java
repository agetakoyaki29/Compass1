package main;

import gui.CanvasPageController;
import screenTransition.Launcher;
import screenTransition.PageController;

public class MainApp extends Launcher{

	/**
	 * 自分自身
	 */
	private static Launcher instance = null;

	@Override
	protected void setInstance(Launcher me) {
		instance = me;
	}

	/**
	 * インスタンスを取得するよ。
	 * @return GUILauncherのインスタンス。
	 */
	public static Launcher getInstance(){
		return instance;
	}

	private final String initTitle = "たいとる";

	@Override
	public String getInitTitle() {
		return initTitle;
	}

	/**
	 * 初期Page
	 */
	private final Class<? extends PageController> START_PAGE = CanvasPageController.class;

	@Override
	public Class<? extends PageController> getFirstPage() {
		return START_PAGE;
	}

}
