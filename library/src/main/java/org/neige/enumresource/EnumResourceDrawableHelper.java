package org.neige.enumresource;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Helper to localize enum
 * The name of resource for the label must match the name of the enumeration with prefix and suffix
 * value. For example, for enum value 'ENUM1' the resource would be defined as 'R.drawable.enum1'.
 * If no label has been defined, then the drawable of a {@link Color#TRANSPARENT}.
 * @param <T> Enum Type
 */
public class EnumResourceDrawableHelper<T extends Enum<T>> extends EnumResourceHelper<T, Drawable> {

	/**
	 * Constructor
	 * @param enumType enumeration type
	 * @param context application context needed to get resource from enum value
	 * @param resourcePrefix prefix of the enum in the resource file
	 * @param resourceSuffix suffix of the enum in the resource file
	 */
	public EnumResourceDrawableHelper(@NonNull Class<T> enumType, @NonNull Context context, @Nullable String resourcePrefix,
									  @Nullable String resourceSuffix) {
		super(enumType, context, resourcePrefix, resourceSuffix);
	}

	@Override
	protected void initMap(Context context) {
		for (T value : enumType.getEnumConstants()) {
			int resourceId = context.getResources().getIdentifier(getResourceName(value).toLowerCase(), "drawable", context.getPackageName());
			if (resourceId != 0) {
				enumMap.put(value, context.getResources().getDrawable(resourceId));
			} else {
				enumMap.put(value, new ColorDrawable(Color.TRANSPARENT));
				Log.i(LOG_TAG, "No drawable found with name : \"" + getResourceName(value) + "\" default drawable use");
			}
		}
	}
}