package kana.compass.util.strConv;

import javafx.util.StringConverter;

public class ExpStrConv extends StringConverter<Double> {

	private final String format;

	public ExpStrConv() {
		this(3, 1);
		// format = "%.2fe%+02d";
	}
	public ExpStrConv(int mantissaDigit, int exponentDigit) {
		mantissaDigit -= 1;
		exponentDigit += 1;
		format = "%."+mantissaDigit+"fe%+0"+exponentDigit+"d";
	}

	@Override
	public String toString(Double object) {
		double d = object;
        int digit = (int) Math.floor( Math.log10(d) );

//        return String.format("%6e", d);
        return String.format(format, d/Math.pow(10, digit), digit);
	}

	@Override
	public Double fromString(String string) {
        // If the specified value is null or zero-length, return null
        if (string == null) {
            return null;
        }

        string = string.trim();

        if (string.length() < 1) {
            return null;
        }

        return Double.valueOf(string);
	}


}
