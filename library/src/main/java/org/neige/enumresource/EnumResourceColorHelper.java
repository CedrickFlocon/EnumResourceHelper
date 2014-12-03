package org.neige.enumresource;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Helper to localize enum
 * The name of resource for the label must match the name of the enumeration with prefix and suffix
 * value. For example, for enum value 'ENUM1' the resource would be defined as 'R.color.ENUM1'.
 * If no label has been defined, then the color is {@link Color#WHITE}.
 * @param <T> Enum Type
 */
public class EnumResourceColorHelper<T extends Enum<T>> extends EnumResourceHelper<T, Integer> {

	/**
	 * Constructor
	 * @param enumType enumeration type
	 * @param context application context needed to get resource from enum value
	 * @param resourcePrefix prefix of the enum in the resource file
	 * @param resourceSuffix suffix of the enum in the resource file
	 */
	public EnumResourceColorHelper(@NonNull Class<T> enumType, @NonNull Context context, @Nullable String resourcePrefix,
								   @Nullable String resourceSuffix) {
		super(enumType, context, resourcePrefix, resourceSuffix);
	}

	@Override
	protected void initMap(Context context) {
		for (T value : enumType.getEnumConstants()) {
			int resourceId = context.getResources().getIdentifier(getResourceName(value), "color", context.getPackageName());
			if (resourceId != 0) {
				enumMap.put(value, context.getResources().getColor(resourceId));
			} else {
				enumMap.put(value, Color.WHITE);
				Log.i(LOG_TAG, "No color found with name : \"" + getResourceName(value) + "\" default color use : \"0x" +
						String.format("%6x", Color.WHITE).toUpperCase() + "\"");
			}
		}
	}
}
