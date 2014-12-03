package org.neige.enumresource;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

/**
 * Helper to localize enum
 * @param <T> Enum Type
 * @param <U> Resource Type
 */
abstract class EnumResourceHelper<T extends Enum<T>, U> {

	protected final String LOG_TAG = "EnumResourceHelper";

	protected Class<T> enumType;
	protected EnumMap<T, U> enumMap;
	private String enumPrefix, enumSuffix;

	/**
	 * Constructor
	 * @param enumType enumeration type
	 * @param context application context needed to get resource from enum value
	 * @param resourcePrefix prefix of the enum in the resource file
	 * @param resourceSuffix suffix of the enum in the resource file
	 */
	public EnumResourceHelper(@NonNull Class<T> enumType, @NonNull Context context, @Nullable String resourcePrefix,
							  @Nullable String resourceSuffix) {
		this.enumType = enumType;
		this.enumMap = new EnumMap<>(enumType);
		this.enumPrefix = resourcePrefix == null ? "" : resourcePrefix;
		this.enumSuffix = resourceSuffix == null ? "" : resourceSuffix;

		changeContext(context);
	}

	/**
	 * Change the context used to get resource information
	 * @param context to be used
	 */
	public void changeContext(Context context) {
		initMap(context);
	}

	/**
	 * Get the resource name regarding to prefix, suffix, and enum.name()
	 * @param value value of the enum
	 * @return string of the resource
	 */
	protected String getResourceName(T value) {
		return enumPrefix + value.name() + enumSuffix;
	}

	/**
	 * Get resource for an enum
	 * @param enumValue enum value
	 * @return list of resource
	 */
	public U getResource(T enumValue) {
		return enumMap.get(enumValue);
	}

	/**
	 * Get resource for enumValues
	 * @param enumValues values of enum
	 * @return list of resource
	 */
	public List<U> getResource(T... enumValues) {
		List<U> localizedString = new ArrayList<>();
		for (T enumValue : enumValues) {
			localizedString.add(enumMap.get(enumValue));
		}
		return localizedString;
	}

	/**
	 * Get all the resource of the enum
	 * @return list of all localized string
	 */
	public List<U> getResource() {
		return getResource(enumType.getEnumConstants());
	}

	/**
	 * Get enum value from resource value (take the first enum value match regarding EnumClass.getEnumConstants() order)
	 * @param resourceValue resource value
	 * @return enum value found otherwise null
	 */
	public T getEnum(U resourceValue) {
		if (enumMap.containsValue(resourceValue)) {
			for (Map.Entry<T, U> map : enumMap.entrySet()) {
				if (map.getValue().equals(resourceValue)) {
					return map.getKey();
				}
			}
		}
		return null;
	}

	/**
	 * Init enumMap with key as enum value and U as resource value
	 * @param context context needed to get resource
	 */
	protected abstract void initMap(Context context);
}