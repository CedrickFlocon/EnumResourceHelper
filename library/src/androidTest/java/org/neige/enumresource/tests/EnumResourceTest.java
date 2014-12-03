package org.neige.enumresource.tests;

import android.content.Context;
import android.content.res.Configuration;
import android.test.InstrumentationTestCase;

import org.neige.enumresource.EnumResourceColorHelper;
import org.neige.enumresource.EnumResourceDrawableHelper;
import org.neige.enumresource.EnumResourceStringHelper;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class EnumResourceTest extends InstrumentationTestCase {

	private enum Country{
		BURMA,
		ICELAND,
		TURKEY,
		AUSTRALIA
	}

	private final String FR_BURMA = "Myanmar";
	private final String FR_ICELAND = "Islande";
	private final String FR_TURKEY = "Turquie";
	private final String FR_AUSTRALIA = "Australie";

	private final String EN_BURMA = "Burma";
	private final String EN_ICELAND = "Iceland";
	private final String EN_TURKEY = "Turkey";
	private final String EN_AUSTRALIA = "Australia";

	private final String ISO_BURMA = "MM";
	private final String ISO_ICELAND = "IS";
	private final String ISO_TURKEY = "TR";
	private final String ISO_AUSTRALIA = "AU";

	private final int BURMA_COLOR = 0xFF00FF00;
	private final int ICELAND_COLOR = 0xFF0000FF;
	private final int TURKEY_COLOR = 0xFFFF0000;
	private final int AUSTRALIA_COLOR = 0xFFFFFF00;

	private Context mContext;
	private EnumResourceStringHelper<Country> countryEnumResourceStringHelper, countryIsoEnumResourceStringHelper;
	private EnumResourceColorHelper<Country> countryEnumResourceColorHelper;
	private EnumResourceDrawableHelper<Country> countryEnumResourceDrawableHelper;


	@Override
	protected void setUp() throws Exception {
		super.setUp();

		mContext = getInstrumentation().getContext();
		countryEnumResourceStringHelper = new EnumResourceStringHelper<>(Country.class, mContext, null, "_STRING");
		countryIsoEnumResourceStringHelper = new EnumResourceStringHelper<>(Country.class, mContext, "ISO_", "_STRING");
		countryEnumResourceColorHelper = new EnumResourceColorHelper<>(Country.class, mContext, null, "_COLOR");
		countryEnumResourceDrawableHelper  = new EnumResourceDrawableHelper<>(Country.class, mContext, null, null);

		new EnumResourceColorHelper<>(Country.class, mContext, null, "gbdfjgbsgns");
	}

	public void testInit() throws Exception {
		assertNotNull("Context not nul",mContext);
	}

	private void changeContext(Locale locale){
		Locale.setDefault(locale);
		Configuration config = new Configuration();
		config.locale = locale;
		mContext.getApplicationContext().getResources().updateConfiguration(config, null);
	}

	private void changeContextToFrench(){
		changeContext(Locale.FRENCH);
		countryEnumResourceStringHelper.changeContext(mContext);
		countryIsoEnumResourceStringHelper.changeContext(mContext);
	}

	private void changeContextToDefault(){
		changeContext(Locale.JAPAN);
		countryEnumResourceStringHelper.changeContext(mContext);
		countryIsoEnumResourceStringHelper.changeContext(mContext);
	}

	public void testFrenchStringFromEnum(){
		changeContextToFrench();
		assertEquals(FR_BURMA, countryEnumResourceStringHelper.getResource(Country.BURMA));
		assertEquals(FR_ICELAND, countryEnumResourceStringHelper.getResource(Country.ICELAND));
		assertEquals(FR_TURKEY, countryEnumResourceStringHelper.getResource(Country.TURKEY));
		assertEquals(FR_AUSTRALIA, countryEnumResourceStringHelper.getResource(Country.AUSTRALIA));

		assertEquals(ISO_BURMA, countryIsoEnumResourceStringHelper.getResource(Country.BURMA));
		assertEquals(ISO_ICELAND, countryIsoEnumResourceStringHelper.getResource(Country.ICELAND));
		assertEquals(ISO_TURKEY, countryIsoEnumResourceStringHelper.getResource(Country.TURKEY));
		assertEquals(ISO_AUSTRALIA, countryIsoEnumResourceStringHelper.getResource(Country.AUSTRALIA));

		List<String> country = Arrays.asList(FR_BURMA,FR_ICELAND,FR_TURKEY, FR_AUSTRALIA);
		java.util.List<String> localizationString = countryEnumResourceStringHelper.getResource();
		for (int i = 0; i < localizationString.size(); i++) {
			assertEquals(country.get(i),localizationString.get(i));
		}
	}

	public void testFrenchStringToEnum(){
		changeContextToFrench();
		assertEquals(Country.BURMA, countryEnumResourceStringHelper.getEnum(FR_BURMA));
		assertEquals(Country.ICELAND, countryEnumResourceStringHelper.getEnum(FR_ICELAND));
		assertEquals(Country.TURKEY, countryEnumResourceStringHelper.getEnum(FR_TURKEY));
		assertEquals(Country.AUSTRALIA, countryEnumResourceStringHelper.getEnum(FR_AUSTRALIA));

		assertEquals(Country.BURMA, countryIsoEnumResourceStringHelper.getEnum(ISO_BURMA));
		assertEquals(Country.ICELAND, countryIsoEnumResourceStringHelper.getEnum(ISO_ICELAND));
		assertEquals(Country.TURKEY, countryIsoEnumResourceStringHelper.getEnum(ISO_TURKEY));
		assertEquals(Country.AUSTRALIA, countryIsoEnumResourceStringHelper.getEnum(ISO_AUSTRALIA));
	}

	public void testDefaultStringFromEnum(){
		changeContextToDefault();
		assertEquals(EN_BURMA, countryEnumResourceStringHelper.getResource(Country.BURMA));
		assertEquals(EN_ICELAND, countryEnumResourceStringHelper.getResource(Country.ICELAND));
		assertEquals(EN_TURKEY, countryEnumResourceStringHelper.getResource(Country.TURKEY));
		assertEquals(EN_AUSTRALIA, countryEnumResourceStringHelper.getResource(Country.AUSTRALIA));

		assertEquals(ISO_BURMA, countryIsoEnumResourceStringHelper.getResource(Country.BURMA));
		assertEquals(ISO_ICELAND, countryIsoEnumResourceStringHelper.getResource(Country.ICELAND));
		assertEquals(ISO_TURKEY, countryIsoEnumResourceStringHelper.getResource(Country.TURKEY));
		assertEquals(ISO_AUSTRALIA, countryIsoEnumResourceStringHelper.getResource(Country.AUSTRALIA));

	}

	public void testDefaultStringToEnum(){
		changeContextToDefault();
		assertEquals(Country.BURMA, countryEnumResourceStringHelper.getEnum(EN_BURMA));
		assertEquals(Country.ICELAND, countryEnumResourceStringHelper.getEnum(EN_ICELAND));
		assertEquals(Country.TURKEY, countryEnumResourceStringHelper.getEnum(EN_TURKEY));
		assertEquals(Country.AUSTRALIA, countryEnumResourceStringHelper.getEnum(EN_AUSTRALIA));

		assertEquals(Country.BURMA, countryIsoEnumResourceStringHelper.getEnum(ISO_BURMA));
		assertEquals(Country.ICELAND, countryIsoEnumResourceStringHelper.getEnum(ISO_ICELAND));
		assertEquals(Country.TURKEY, countryIsoEnumResourceStringHelper.getEnum(ISO_TURKEY));
		assertEquals(Country.AUSTRALIA, countryIsoEnumResourceStringHelper.getEnum(ISO_AUSTRALIA));
	}

	public void testColorFromEnum(){
		assertEquals(BURMA_COLOR, (int)countryEnumResourceColorHelper.getResource(Country.BURMA));
		assertEquals(ICELAND_COLOR, (int)countryEnumResourceColorHelper.getResource(Country.ICELAND));
		assertEquals(TURKEY_COLOR, (int)countryEnumResourceColorHelper.getResource(Country.TURKEY));
		assertEquals(AUSTRALIA_COLOR, (int)countryEnumResourceColorHelper.getResource(Country.AUSTRALIA));
	}

	public void testColorToEnum(){
		assertEquals(Country.BURMA, countryEnumResourceColorHelper.getEnum(BURMA_COLOR));
		assertEquals(Country.ICELAND, countryEnumResourceColorHelper.getEnum(ICELAND_COLOR));
		assertEquals(Country.TURKEY, countryEnumResourceColorHelper.getEnum(TURKEY_COLOR));
		assertEquals(Country.AUSTRALIA, countryEnumResourceColorHelper.getEnum(AUSTRALIA_COLOR));
	}

	public void testDrawable(){
		assertEquals(Country.BURMA, countryEnumResourceDrawableHelper.getEnum(countryEnumResourceDrawableHelper.getResource(Country.BURMA)));
		assertEquals(Country.ICELAND, countryEnumResourceDrawableHelper.getEnum(countryEnumResourceDrawableHelper.getResource(Country.ICELAND)));
		assertEquals(Country.TURKEY, countryEnumResourceDrawableHelper.getEnum(countryEnumResourceDrawableHelper.getResource(Country.TURKEY)));
		assertEquals(Country.AUSTRALIA, countryEnumResourceDrawableHelper.getEnum(countryEnumResourceDrawableHelper.getResource(Country.AUSTRALIA)));
	}
}
