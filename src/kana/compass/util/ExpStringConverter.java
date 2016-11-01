package kana.compass.util;

import javafx.util.StringConverter;

public class ExpStringConverter extends StringConverter<String> {

	private final String format;

	public ExpStringConverter() {
		this(3, 1);
		// format = "%.2fe%+02d";
	}
	public ExpStringConverter(int mantissaDigit, int exponentDigit) {
		mantissaDigit -= 1;
		exponentDigit += 1;
		format = "%."+mantissaDigit+"fe%+0"+exponentDigit+"d";
	}

	@Override
	public String toString(String object) {
        if (object == null) return "";

        object = object.trim();

        if (object.length() < 1) return "";

        Double d = Double.valueOf(object);
        int digit = (int) Math.floor( Math.log10(d) );

//        return String.format("%6e", d);
        return String.format(format, d/Math.pow(10, digit), digit);
	}

	@Override
	public String fromString(String string) {
        return string;
	}


}
