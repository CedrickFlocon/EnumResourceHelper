package org.neige.enumresource;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import org.neige.enumresource.sample.R;


public class MainActivity extends Activity implements AdapterView.OnItemSelectedListener {

	private enum Country{
		BURMA,
		ICELAND,
		TURKEY,
		AUSTRALIA
	}

	private Spinner spinnerCountry;
	private TextView textViewIsoCode;

	private EnumResourceHelper<Country> countryEnumResourceHelper, countryIsoEnumResourceHelper;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		countryEnumResourceHelper = new EnumResourceHelper<>(Country.class, EnumResourceHelper.ResourceType.STRING, this, null, "_STRING");
		countryIsoEnumResourceHelper = new EnumResourceHelper<>(Country.class, EnumResourceHelper.ResourceType.STRING, this, "ISO_", "_STRING");

		spinnerCountry = (Spinner) findViewById(R.id.spinnerCountry);
		textViewIsoCode = (TextView) findViewById(R.id.textViewIsoCode);

		ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, countryEnumResourceHelper.getResourceString());
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinnerCountry.setAdapter(adapter);

		spinnerCountry.setOnItemSelectedListener(this);
	}

	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
		Country country = countryEnumResourceHelper.getEnumValue((String) spinnerCountry.getSelectedItem());
		textViewIsoCode.setText(countryIsoEnumResourceHelper.getResourceString(country));
	}

	@Override
	public void onNothingSelected(AdapterView<?> parent) {

	}
}
