package org.neige.enumresource.tests;

import android.content.Context;
import android.content.res.Configuration;
import android.test.InstrumentationTestCase;

import org.neige.enumresource.EnumResourceHelper;

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

	private String FR_BURMA = "Myanmar";
	private String FR_ICELAND = "Islande";
	private String FR_TURKEY = "Turquie";
	private String FR_AUSTRALIA = "Australie";

	private String EN_BURMA = "Burma";
	private String EN_ICELAND = "Iceland";
	private String EN_TURKEY = "Turkey";
	private String EN_AUSTRALIA = "Australia";

	private String ISO_BURMA = "MM";
	private String ISO_ICELAND = "IS";
	private String ISO_TURKEY = "TR";
	private String ISO_AUSTRALIA = "AU";


	private Context mContext;
	private EnumResourceHelper<Country> countryEnumResourceHelper, countryIsoEnumResourceHelper;

	@Override
	protected void setUp() throws Exception {
		super.setUp();

		mContext = getInstrumentation().getContext();
		countryEnumResourceHelper = new EnumResourceHelper<>(Country.class, EnumResourceHelper.ResourceType.STRING, mContext, null, "_STRING");
		countryIsoEnumResourceHelper = new EnumResourceHelper<>(Country.class, EnumResourceHelper.ResourceType.STRING, mContext, "ISO_", "_STRING");
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
		countryEnumResourceHelper.changeContext(mContext);
		countryIsoEnumResourceHelper.changeContext(mContext);
	}

	private void changeContextToDefault(){
		changeContext(Locale.JAPAN);
		countryEnumResourceHelper.changeContext(mContext);
		countryIsoEnumResourceHelper.changeContext(mContext);
	}

	public void testFrenchStringFromEnum(){
		changeContextToFrench();
		assertEquals(FR_BURMA, countryEnumResourceHelper.getResourceString(Country.BURMA));
		assertEquals(FR_ICELAND, countryEnumResourceHelper.getResourceString(Country.ICELAND));
		assertEquals(FR_TURKEY, countryEnumResourceHelper.getResourceString(Country.TURKEY));
		assertEquals(FR_AUSTRALIA, countryEnumResourceHelper.getResourceString(Country.AUSTRALIA));

		assertEquals(ISO_BURMA,countryIsoEnumResourceHelper.getResourceString(Country.BURMA));
		assertEquals(ISO_ICELAND,countryIsoEnumResourceHelper.getResourceString(Country.ICELAND));
		assertEquals(ISO_TURKEY,countryIsoEnumResourceHelper.getResourceString(Country.TURKEY));
		assertEquals(ISO_AUSTRALIA,countryIsoEnumResourceHelper.getResourceString(Country.AUSTRALIA));

		List<String> country = Arrays.asList(FR_BURMA,FR_ICELAND,FR_TURKEY, FR_AUSTRALIA);
		java.util.List<String> localizationString = countryEnumResourceHelper.getResourceString();
		for (int i = 0; i < localizationString.size(); i++) {
			assertEquals(country.get(i),localizationString.get(i));
		}
	}

	public void testFrenchStringToEnum(){
		changeContextToFrench();
		assertEquals(Country.BURMA,countryEnumResourceHelper.getEnumValue(FR_BURMA));
		assertEquals(Country.ICELAND,countryEnumResourceHelper.getEnumValue(FR_ICELAND));
		assertEquals(Country.TURKEY, countryEnumResourceHelper.getEnumValue(FR_TURKEY));
		assertEquals(Country.AUSTRALIA,countryEnumResourceHelper.getEnumValue(FR_AUSTRALIA));

		assertEquals(Country.BURMA,countryIsoEnumResourceHelper.getEnumValue(ISO_BURMA));
		assertEquals(Country.ICELAND,countryIsoEnumResourceHelper.getEnumValue(ISO_ICELAND));
		assertEquals(Country.TURKEY,countryIsoEnumResourceHelper.getEnumValue(ISO_TURKEY));
		assertEquals(Country.AUSTRALIA,countryIsoEnumResourceHelper.getEnumValue(ISO_AUSTRALIA));
	}

	public void testDefaultStringFromEnum(){
		changeContextToDefault();
		assertEquals(EN_BURMA,countryEnumResourceHelper.getResourceString(Country.BURMA));
		assertEquals(EN_ICELAND,countryEnumResourceHelper.getResourceString(Country.ICELAND));
		assertEquals(EN_TURKEY,countryEnumResourceHelper.getResourceString(Country.TURKEY));
		assertEquals(EN_AUSTRALIA,countryEnumResourceHelper.getResourceString(Country.AUSTRALIA));

		assertEquals(ISO_BURMA,countryIsoEnumResourceHelper.getResourceString(Country.BURMA));
		assertEquals(ISO_ICELAND,countryIsoEnumResourceHelper.getResourceString(Country.ICELAND));
		assertEquals(ISO_TURKEY,countryIsoEnumResourceHelper.getResourceString(Country.TURKEY));
		assertEquals(ISO_AUSTRALIA,countryIsoEnumResourceHelper.getResourceString(Country.AUSTRALIA));

	}

	public void testDefaultStringToEnum(){
		changeContextToDefault();
		assertEquals(Country.BURMA,countryEnumResourceHelper.getEnumValue(EN_BURMA));
		assertEquals(Country.ICELAND,countryEnumResourceHelper.getEnumValue(EN_ICELAND));
		assertEquals(Country.TURKEY,countryEnumResourceHelper.getEnumValue(EN_TURKEY));
		assertEquals(Country.AUSTRALIA,countryEnumResourceHelper.getEnumValue(EN_AUSTRALIA));

		assertEquals(Country.BURMA,countryIsoEnumResourceHelper.getEnumValue(ISO_BURMA));
		assertEquals(Country.ICELAND,countryIsoEnumResourceHelper.getEnumValue(ISO_ICELAND));
		assertEquals(Country.TURKEY,countryIsoEnumResourceHelper.getEnumValue(ISO_TURKEY));
		assertEquals(Country.AUSTRALIA,countryIsoEnumResourceHelper.getEnumValue(ISO_AUSTRALIA));
	}

}
