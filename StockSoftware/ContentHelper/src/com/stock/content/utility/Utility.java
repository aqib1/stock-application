package com.stock.content.utility;

import java.awt.Color;
import java.awt.Font;
import java.util.Objects;

public class Utility {

	public static final String [] paymentTypes = {"Cash","Card"};
	public static final String DEFAULT_COLOR = "#808080";
	public static final String YES = "Y";
	public static final String NO = "N";
	public static final String MALE = "M";
	public static final String FEMALE = "F";
	public static final String FONT_PLAIN = "Plain";
	public static final String FONT_ITALIC = "Italic";
	public static final String FONT_BOLD = "Bold";

	private Utility() {

	}

	public final static String toHexString(Color colour) throws NullPointerException {
		String hexColour = Integer.toHexString(colour.getRGB() & 0xffffff);
		if (hexColour.length() < 6) {
			hexColour = "000000".substring(0, 6 - hexColour.length()) + hexColour;
		}
		return "#" + hexColour;
	}

	public static int parseStyleStringToFont(String style) {
		if (FONT_BOLD.equalsIgnoreCase(style)) {
			return Font.BOLD;
		} else if (FONT_ITALIC.equalsIgnoreCase(style)) {
			return Font.ITALIC;
		} else
			return Font.PLAIN;
	}

	public static String parseStyleFontToString(int style) {
		if (Font.BOLD == style) {
			return FONT_BOLD;
		} else if (Font.ITALIC == style) {
			return FONT_ITALIC;
		} else
			return FONT_PLAIN;
	}

	/*********************** Utility methods *************************/
	public static boolean isNull(Object obj) {
		return obj == null;
	}

	public static boolean isNullOrEmptyString(String val) {
		return Objects.isNull(val) || val.isEmpty();
	}

	public static boolean isNullInteger(Integer i) {
		return Objects.isNull(i);
	}

	public static boolean isNullOrZeroInteger(Integer i) {
		return isNullInteger(i) || i == 0;
	}

}
