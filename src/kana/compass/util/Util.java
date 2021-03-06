package kana.compass.util;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.ObjectBinding;
import javafx.beans.binding.StringBinding;
import javafx.beans.property.ObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.TextInputControl;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import javafx.util.StringConverter;


public class Util {

	private Util() {
		throw new MyRuntimeException("aaa");
	}

	public static <T> Set<T> GetOneSet(T ta) {
		return new HashSet<T>(Arrays.asList(ta));
	}

	public static Double parseDouble(String str) {
		try {
			return Double.valueOf(str);
		} catch (NumberFormatException e) {
			return null;
		}
	}

	// ---- ----

	public static BackgroundFill getSimpleBackgroudFill(Color color) {
		return new BackgroundFill(color, CornerRadii.EMPTY, Insets.EMPTY);
	}

	public static Background getSimpleBackgroud(Color color) {
		return new Background(
				new BackgroundFill(color, CornerRadii.EMPTY, Insets.EMPTY));
	}

	public static Animation getCallbackAnimation(EventHandler<ActionEvent> handler) {
		return new Timeline(new KeyFrame(Duration.ONE, handler));
	}

	// ---- property bind ----

	public static <T> StringBinding toStringBinding(StringConverter<T> converter, ObservableValue<T> observable) {
		return Bindings.createStringBinding(() -> converter.toString(observable.getValue()), observable);
	}

	public static <T> ObjectBinding<T> fromStringBinding(StringConverter<T> converter, ObservableValue<String> observable) {
		return Bindings.createObjectBinding(() -> converter.fromString(observable.getValue()), observable);
	}

	public static ObjectProperty<String> writtenProperty(TextInputControl tiCtrl) {
		// TODO これ、どうすりゃええねん
		TextFormatter<String> formatter = new TextFormatter<>(TextFormatter.IDENTITY_STRING_CONVERTER);
		tiCtrl.setTextFormatter(formatter);
		return formatter.valueProperty();
	}

	// ---- reflection ----

	public static <T> T instantiate(Class<? extends T> clazz, Object... args) {
		Constructor<? extends T> constructor;
		Class<?>[] argClassArray = Stream.of(args)
				.map(arg -> arg.getClass())
				.collect(Collectors.toList())
				.toArray(new Class<?>[0]);
		try {
			constructor = clazz.getConstructor(argClassArray);
		} catch (SecurityException | NoSuchMethodException e) {
			throw new MyRuntimeException(e);
		}
		try {
			return constructor.newInstance(args);
		} catch (IllegalArgumentException | ReflectiveOperationException e) {
			throw new MyRuntimeException(e);
		}
	}

	public static void callMethod(Object receiver, String name, Object param) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		String methodName = "push" + name.substring(0, 1).toUpperCase() + name.substring(1);

		Function<Method, Integer> f = m -> {
			return Util.howSuper(m.getParameterTypes()[0], param.getClass());
		};

		Method method = Stream.of( receiver.getClass().getMethods() )
			.filter(m -> m.getParameterCount() == 1)
			.filter(m -> m.getName().equals(methodName))
			.max((m1, m2) -> f.apply(m1) - f.apply(m2))
			.orElseThrow(()->new NoSuchMethodException())
			;

		method.invoke(receiver, param);
	}

	public static void callMethod(Object receiver, String methodName, Object... params) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Method[] allMethods = receiver.getClass().getMethods();

		List<Method> methods = Stream.of(allMethods)
			.filter(m -> m.getName().equals(methodName))
			.filter(m -> m.getParameterCount() == params.length)
//			.filter(m -> {
//				for (Class<?> param : m.getParameterTypes()) {
//
//				}
//			})
			.collect(Collectors.toList());

		for (Method method : methods) {
			// TODO
			method.invoke(receiver, params);
			break;
		}
	}

	public static int howSuper(Class<?> sup, Class<?> sub) {
		for(int i=0; ; i++) {
			if(sub.equals(sup)) return i;
			sub = sub.getSuperclass();
			if(sub.equals(Object.class)) return Integer.MAX_VALUE;
		}
	}

	public static Class<?> nSuper(Class<?> clazz, int n) {
		if(n == Integer.MAX_VALUE) return null;

		for(int i=0; i<n; i++) {
			if(clazz == null) return null;
			clazz = clazz.getSuperclass();
		}
		return clazz;
	}

}
