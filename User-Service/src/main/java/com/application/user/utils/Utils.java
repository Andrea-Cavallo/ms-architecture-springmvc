package com.application.user.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Utils {

	private Utils() {
	}

	/**
	 * Converts an object to a Long value. If the object is null, null is returned.
	 * If the object is already a Long value, it is returned as is. If the object is
	 * an Integer value, it is converted to a Long value. If the object is a String,
	 * it is parsed as a Long value and returned.
	 *
	 * @param o the object to convert
	 * @return the Long value of the object, or null if the object is null
	 * @throws NumberFormatException if the object is a String that cannot be parsed
	 *                               as a Long value
	 */
	public static Long longValue(Object o) {
		if (Objects.isNull(o))
			return null;
		if (o instanceof Long) {
			return (Long) o;
		}
		if (o instanceof Integer) {
			Integer i = (Integer) o;
			return i.longValue();
		}
		var s = o.toString();
		return Long.valueOf(s);
	}

	/**
	 * Converts an object to a String. If the object is null, null is returned. If
	 * the object is already a String value, it is returned as is.
	 *
	 * @param o the object to convert
	 * @return the String value of the object, or null if the object is null
	 */
	public static String stringValue(Object o) {
		return Objects.isNull(o) ? null : o.toString();
	}

	static Map<String, Object> mapValue(Object o) {
		if (Objects.isNull(o))
			return new HashMap<>();
		return (Map<String, Object>) o;
	}

}
