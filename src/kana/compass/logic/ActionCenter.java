package kana.compass.logic;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javafx.geometry.Point2D;
import kana.compass.drawn.CenterDot;
import kana.compass.drawn.Cercle;
import kana.compass.drawn.Dot;
import kana.compass.drawn.Drawn;
import kana.compass.drawn.FreeDot;
import kana.compass.drawn.hot.HotCercle;
import kana.compass.drawn.hot.HotLine;
import kana.compass.geometry.Geo;
import kana.compass.gui.drawScene.CanvasManager;
import kana.compass.gui.drawScene.CanvasMouseHandler;
import kana.compass.gui.drawScene.DrawSceneCtrl;
import kana.compass.gui.drawScene.actToolBar.ActToolBarCtrl;
import kana.compass.gui.drawScene.actToolBar.DrawCercleToolBarCtrl;
import kana.compass.gui.drawScene.actToolBar.DrawLineToolBarCtrl;
import kana.compass.util.MyRuntimeException;


public class ActionCenter {

	private final DrawSceneCtrl ctrl;
	private final CanvasMouseHandler handler;
	private final CanvasManager manager;
	private final Paper paper;
	private final HotPaper hotPaper;
	private final DrawnPool pool;

	private HotDrawn hot = null;
	private ActToolBarCtrl actToolBarCtrl = null;

	public ActionCenter(DrawSceneCtrl ctrl, CanvasManager manager) {
		this.ctrl = ctrl;
		this.manager = manager;
		this.handler = manager.getHandler();
		this.paper = manager.getPaper();
		this.hotPaper = manager.getHotPaper();
		this.pool = paper.getPool();
	}

	public void setHotDrawn(Class<? extends HotDrawn> clazz) {
		clearOns();
		clearDrawing();

		hot = instantiate(clazz);
		ctrl.setActToolBar(hot.getActToolBarCtrl());	// TODO 繰り返しで初期化したくない
		hot.begin();

		setOnLeftClicked4FreeDot(pt -> push("freeDot", pt));
		setOnRightClicked4ReadDot(pt -> push("readDot", pt));
	}

	private HotDrawn instantiate(Class<? extends HotDrawn> clazz) {
		Constructor<? extends HotDrawn> constructor;
		try {
			constructor = clazz.getConstructor(ActionCenter.class);
		} catch (SecurityException | NoSuchMethodException e) {
			throw new MyRuntimeException(e);
		}

		HotDrawn obj;
		try {
			obj = constructor.newInstance(this);
		} catch (IllegalArgumentException | ReflectiveOperationException e) {
			throw new MyRuntimeException(e);
		}

		return obj;
	}

	public void push(String name, Object obj) {
		if(hot == null) return;
		hot.push(name, obj);
	}

	public void clearOns() {
		handler.clearOnMoved();
		handler.clearOnLeftClicked();
		handler.clearOnRightClicked();
	}

	// ---- hot drawings ----

	public void clearDrawing() {
		hotPaper.clearDrawing();
	}

	// ---- text ----

	public void setStatusText(String text) {
		ctrl.setStatusText(text);
	}
	public void showPopup(String text) {
		ctrl.showSimplePopup(text);
	}

	// ---- higher-order function ----

	private void setOnLeftClicked4FreeDot(Consumer<Dot> consumer) {
		handler.setLeftPoint(pt -> {
			consumer.accept(new FreeDot(pt));
		});
	}

	private void setOnRightClicked4ReadDot(Consumer<Dot> consumer) {
		handler.setRightPoint(pt -> {
			// TODO too heavy
			double r = 5*5;
			Dot t = null;
			double distance = r;

			for (Dot dot : pool.getDots()) {
				Point2D v = dot.getPt();
				double d = Geo.sqrDistance(pt, v);
				if(d > r) continue;
				if(d > distance) continue;
				distance = d;
				t = dot;
			}

			if(t == null) {
				ctrl.showSimplePopup("点が見つかりません");
				return;
			}
			consumer.accept(t);
		});
	}

	// ---- class ----

	public abstract class Action {
		// TODO 必要

		public abstract ActToolBarCtrl getActToolBarCtrl();
	}



	public abstract class HotDrawn extends Action {

		private boolean preDraw = false;

		protected ActionCenter outer() {
			return ActionCenter.this;
		}

		public final void push(String name, Object param) {
			String methodName = "push" + name.substring(0, 1).toUpperCase() + name.substring(1);

			List<Method> methods = Stream.of( this.getClass().getMethods() )
				.filter(m -> m.getParameterCount() == 1)
				.filter(m -> m.getName().equals(methodName))
				.collect(Collectors.toList())
				;

			for (Method method : methods) {
				try {
					method.invoke(this, param);
				} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
					continue;
				}
				break;
			}
		}

		private final void begin() {
			began();
			// TODO endと合わせて作ったけど、なにしよう
		}

		protected abstract void began();

		public abstract void draw(Pen pen);

		public final boolean isPreDraw() { return preDraw; };

		protected final void beginPreDraw() {
			handler.setMovePoint(pt -> {
				prePushDot(new FreeDot(pt));
				hotPaper.repaint();
			});
//			hotPaper.setDrawing(this); TODO
			preDraw  = true;
		}

		public abstract void prePushDot(Dot pt);

		public final void end() {
			Set<Drawn> drawns = this.makeColds();
			paper.addAll(drawns);

			// end of pre draw
			clearDrawing();
			handler.clearOnMoved();

			// TODO how 繰り返し
			hot = null;
		}

		protected abstract Set<Drawn> makeColds();

		// ---- default point ----

		public abstract void pushDot(Dot point);

		public void pushFreeDot(FreeDot freeDot) {
			pushDot(freeDot);
		}

		public void pushReadDot(Dot readDot) {
			pushDot(readDot);
		}

	}


	// TODO 以下テスト

	public void drawLine() {
		clearOns();
		clearDrawing();
		actToolBarCtrl = new DrawLineToolBarCtrl();
		ctrl.setActToolBar(actToolBarCtrl);

		tempLine();
	}
	public void drawCercle() {
		clearOns();
		clearDrawing();
		actToolBarCtrl = new DrawCercleToolBarCtrl();
		ctrl.setActToolBar(actToolBarCtrl);

		tempCercle();
	}

	private void tempLine() {
		HotLine cur = new HotLine();
		hotPaper.setDrawing(cur);

		Consumer<Point2D> whenPt2 = pt -> {
			cur.pt2 = new FreeDot(pt);
			hotPaper.repaint();
		};
		Consumer<Dot> getPt2 = pt -> {
			cur.pt2 = pt;
			if(cur.pt1.equals(cur.pt2)) {
				ctrl.showSimplePopup("同一点です");
				return;
			}

			paper.add(cur.makeCold());

			hotPaper.clearDrawing();
			handler.clearOnMoved();
			tempLine();
		};
		Consumer<Dot> getPt1 = pt -> {
			cur.pt1 = pt;

			handler.setMovePoint(whenPt2);
			setOnLeftClicked4FreeDot(getPt2);
			setOnRightClicked4ReadDot(getPt2);

			setStatusText("終点を");
		};

		setOnLeftClicked4FreeDot(getPt1);
		setOnRightClicked4ReadDot(getPt1);

		setStatusText("始点を");
	}


	private void tempCercle() {
		HotCercle cur = new HotCercle();
		hotPaper.setDrawing(cur);

		Consumer<Point2D> whenPt2 = pt -> {
			cur.pt2 = new FreeDot(pt);
			hotPaper.repaint();
		};
		Consumer<Dot> getPt2 = pt -> {
			cur.pt2 = pt;
			if(cur.pt1.equals(cur.pt2)) {
				ctrl.showSimplePopup("同一点です");
				return;
			}
			hotPaper.clearDrawing();
			Drawn cercle = cur.makeCold();
			paper.add(cercle);
			paper.add(new CenterDot((Cercle) cercle));
			hotPaper.repaint();

			handler.clearOnMoved();
			tempCercle();
		};
		Consumer<Dot> getPt1 = pt -> {
			cur.pt1 = pt;

			handler.setMovePoint(whenPt2);
			setOnLeftClicked4FreeDot(getPt2);
			setOnRightClicked4ReadDot(getPt2);

			setStatusText("1点目を");
		};

		setOnLeftClicked4FreeDot(getPt1);
		setOnRightClicked4ReadDot(getPt1);

		setStatusText("2点目を");
	}

}
