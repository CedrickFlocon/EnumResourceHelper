package org.neige.enumresource;

import android.content.Context;
import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

/**
 * Help to localize enum.
 * 
 * The name of resource for the label must match the name of the enumeration with suffix and prefix
 * value. For example, for enum value 'ENUM1' the resource would be defined as 'R.string.ENUM1'.
 * If no label has been defined, then the string is {@link Enum#name()}.
 * 
 */
public class EnumResourceHelper<T extends Enum<T>> {

	public enum ResourceType {
		STRING("string"),
		COLOR("color");

		private String resourceTypeString;

		private ResourceType(String resourceTypeString) {
			this.resourceTypeString = resourceTypeString;
		}
	}

	private Context context;
	private ResourceType resourceType;
	private Class<T> enumType;
	private EnumMap<T, String> enumMap;
	private String enumPrefix = "", enumSuffix = "";

	 /**
	 * Constructor
	 * @param enumType enumeration type
	 * @param context application context needed for get string from resource
	 * @param resourceType type of resource like String Color ...
	 * @param enumSuffix suffix of the enum in the resource file
	 * @param enumPrefix prefix of the enum in the resource file
	 */
	public EnumResourceHelper(Class<T> enumType, ResourceType resourceType, Context context, @Nullable String enumSuffix, @Nullable String enumPrefix) {
		this.enumType = enumType;
		this.enumMap = new EnumMap<>(enumType);
		this.enumPrefix = enumPrefix == null ? "" : enumPrefix;
		this.enumSuffix = enumSuffix == null ? "" : enumSuffix;
		this.context = context;
		this.resourceType = resourceType;

		changeContext(context);
	}

	/**
	 * Change the context used to get resource information
	 * @param context to be used
	 */
	public void changeContext(Context context){
		for (T value : enumType.getEnumConstants()) {
			int resourceId = context.getResources().getIdentifier(enumSuffix + value.name() + enumPrefix, resourceType.resourceTypeString, context.getPackageName());
			if (resourceId != 0) {
				enumMap.put(value, context.getResources().getString(resourceId));
			} else {
				enumMap.put(value, value.name());
			}
		}
	}

	/**
	 * Get all Localized string from enumType
	 * @return list of all localized string
	 */
	public List<String> getResourceString() {
		return getResourceString(enumType.getEnumConstants());
	}

	/**
	 * Get localized string for enumValue
	 * @param enumValue enum to be localized
	 * @return String of the enumValue
	 */
	public String getResourceString(T enumValue) {
		return enumMap.get(enumValue);
	}

	/**
	 * Get localized string for enumValues
	 * @param enumValues enum to be localized
	 * @return list of localized enumValues
	 */
	public List<String> getResourceString(T... enumValues) {
		List<String> localizedString = new ArrayList<>();
		for (T enumValue : enumValues) {
			localizedString.add(enumMap.get(enumValue));
		}
		return localizedString;
	}

	/**
	 * Get enum value from localized string (take the first enum value match regarding EnumClass.getEnumConstants() order)
	 * @param enumString localized string
	 * @return enum value
	 */
	public T getEnumValue(String enumString) {
		if (enumMap.containsValue(enumString)) {
			for (Map.Entry<T, String> map : enumMap.entrySet()) {
				if (map.getValue().equals(enumString)) {
					return map.getKey();
				}
			}
		}
		return null;
	}

}
