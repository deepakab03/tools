package com.deepak.tools.misc.other;

import java.util.ArrayList;
import java.util.List;

public class TestUtils {

	public enum TLight {
		RED,YELLOW,GREEN
		}
	
	@SuppressWarnings("unchecked")
	public static <T extends Enum<T>> Object[] getEnumsApartFrom(T... enumsToBeExcluded) {
		Enum<T> firstEnum = enumsToBeExcluded[0];

		T[] allEnums = (T[]) firstEnum.getClass().getEnumConstants();
		final List<T> enumList = new ArrayList<>();
		for (T enumVal : allEnums) {
			boolean present = false;
			for (T enumToBeExcluded : enumsToBeExcluded) {
				if (enumToBeExcluded.equals(enumVal)) {
					present = true;
					break;
				}
			}
			if (!present) {
				enumList.add(enumVal);
			}
		}

		return enumList.toArray();
	}

	@SuppressWarnings("unchecked")
	public static <T extends Enum<T>> Object[] getEnumsApartFrom(List<T> enumsToBeExcluded) {
		T firstEnum = enumsToBeExcluded.get(0);

		T[] allEnums = (T[]) firstEnum.getClass().getEnumConstants();
		final List<T> enumList = new ArrayList<>();
		for (T enumVal : allEnums) {
			boolean present = false;
			for (T enumToBeExcluded : enumsToBeExcluded) {
				if (enumToBeExcluded.equals(enumVal)) {
					present = true;
					break;
				}
			}
			if (!present) {
				enumList.add(enumVal);
			}
		}


		return enumList.toArray();
	}
}
