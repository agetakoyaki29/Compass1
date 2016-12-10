package kana.compass.logic;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javafx.geometry.Point2D;
import javafx.scene.Node;
import kana.compass.canvas.CanvasManager;
import kana.compass.canvas.CanvasMouseHandler;
import kana.compass.canvas.HotPaper;
import kana.compass.canvas.Paper;
import kana.compass.drawn.Drawn;
import kana.compass.drawn.hot.HotCercle;
import kana.compass.drawn.hot.HotLine;
import kana.compass.geometry.Geo;
import kana.compass.gui.drawScene.DrawSceneCtrl;
import kana.compass.gui.drawScene.opTBar.OpTBarCtrl;
import kana.compass.gui.drawScene.opTBar.OpTBarFactory;
import kana.compass.util.Util;


public class OpCentral {

	private final OpTBarFactory factory = new OpTBarFactory(this);

	private final DrawSceneCtrl ctrl;
	private final CanvasManager manager;
	private final CanvasMouseHandler handler;
	private final ScopeTransform scope;
	private final Paper paper;
	private final HotPaper hotPaper;
	private final DrawnPool pool;

	private Operation op = null;

	public OpCentral(DrawSceneCtrl ctrl, CanvasManager manager) {
		this.ctrl = ctrl;
		this.manager = manager;
		this.handler = manager.getHandler();
		this.scope = manager.getScope();
		this.paper = manager.getPaper();
		this.hotPaper = manager.getHotPaper();
		this.pool = paper.getPool();

		setOnLeftClicked4FreePoint(pt -> push("freePoint", pt));
		setOnRightClicked4ReadPoint(pt -> push("readPoint", pt));
	}

	public void setHotDrawn(Class<? extends HotDrawn> clazz) {
		clearOns();
		clearDrawing();

		op = Util.instantiate(clazz, this);
		factory.repush();
		op.begin();
	}

	public void push(String name, Object obj) {
		op.push(name, obj);
	}

	public void clearOns() {
		handler.clearOnMoved();
//		handler.clearOnClickedLeft();
//		handler.clearOnClickedRight();
	}

	public void setOpTBar(Class<? extends OpTBarCtrl> clazz) {
		Node opTBar = factory.makeOpTBar(clazz);
		ctrl.setOpTB(opTBar);
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

	private void setOnLeftClicked4FreePoint(Consumer<Point2D> consumer) {
		handler.setClickedLeftPoint(pt -> {
			consumer.accept(pt);
		});
	}

	private void setOnRightClicked4ReadPoint(Consumer<Point2D> consumer) {
		handler.setClickedRightPoint(pt -> {
			// TODO too heavy
			double min = 8 * scope.get1Pixel();
			min *= min;
			Point2D near = null;

			for (Point2D v : pool.getPts()) {
				double d = Geo.sqrDistance(pt, v);
				if(d > min) continue;
				min = d;
				near = v;
			}

			if(near == null) {
				ctrl.showSimplePopup("点が見つかりません");
				return;
			}
			consumer.accept(near);
		});
	}

	// ---- class ----

	public abstract class Operation {

		protected Operation(OpCentral outer) {
			Class<? extends OpTBarCtrl> clazz = getDefaultOpTBarCtrl();
			if(!clazz.equals(OpTBarFactory.NON)) setOpTBar(clazz);
		}

		public abstract Class<? extends OpTBarCtrl> getDefaultOpTBarCtrl();

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

		protected OpCentral outer() {
			return OpCentral.this;
		}

		protected final void begin() {
			began();
			// TODO finishと合わせて作ったけど、なにしよう
		}

		protected abstract void began();

		protected abstract void finish();

		// ---- default push point ----

		public abstract void pushPoint(Point2D pt);

		public void pushFreePoint(Point2D pt) {
			pushPoint(pt);
		}

		public void pushReadPoint(Point2D pt) {
			pushPoint(pt);
		}

	}



	public abstract class HotDrawn extends Operation {

		protected HotDrawn(OpCentral outer) {
			super(outer);
		}

		private boolean preDraw = false;

		public abstract void preDraw(Pen pen);

		public final boolean isPreDraw() { return preDraw; };

		protected final void beginPreDraw() {
			handler.setMovePoint(pt -> {
				prePushPoint(pt);
				hotPaper.repaint();
			});
			hotPaper.setDrawing(this);
			preDraw  = true;
		}

		public abstract void prePushPoint(Point2D pt);

		@Override
		protected final void finish() {
			Set<Drawn> drawns = this.makeColds();
			paper.addAll(drawns);

			// end of pre draw
			clearDrawing();
			handler.clearOnMoved();
			preDraw = false;

			begin();
		}

		protected abstract Set<Drawn> makeColds();

	}



	public void drawLine() {
		setHotDrawn(HotLine.class);
	}

	public void drawCercle() {
		setHotDrawn(HotCercle.class);
	}

}

