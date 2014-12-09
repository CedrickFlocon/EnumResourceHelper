package org.neige.enumresource.sample.activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import org.neige.enumresource.EnumResourceDrawableHelper;
import org.neige.enumresource.EnumResourceStringHelper;
import org.neige.enumresource.sample.R;
import org.neige.enumresource.sample.model.Country;


public class MainActivity extends Activity implements AdapterView.OnItemSelectedListener {

	private Spinner spinnerCountry;
	private TextView textViewIsoCode;
	private ImageView imageView;

	private EnumResourceStringHelper<Country> countryEnumResourceHelper, countryIsoEnumResourceHelper;
	private EnumResourceDrawableHelper<Country> countryEnumResourceDrawableHelper;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		countryEnumResourceHelper = new EnumResourceStringHelper<>(Country.class, this, null, "_STRING");
		countryIsoEnumResourceHelper = new EnumResourceStringHelper<>(Country.class, this, "ISO_", "_STRING");
		countryEnumResourceDrawableHelper = new EnumResourceDrawableHelper<>(Country.class, this, null, null);

		spinnerCountry = (Spinner) findViewById(R.id.spinnerCountry);
		textViewIsoCode = (TextView) findViewById(R.id.textViewIsoCode);
		imageView = (ImageView) findViewById(R.id.imageViewFlag);

		ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, countryEnumResourceHelper.getResource());
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinnerCountry.setAdapter(adapter);

		spinnerCountry.setOnItemSelectedListener(this);
	}

	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
		Country country = countryEnumResourceHelper.getEnum((String) spinnerCountry.getSelectedItem());
		textViewIsoCode.setText(countryIsoEnumResourceHelper.getResource(country));

		imageView.setImageDrawable(countryEnumResourceDrawableHelper.getResource(country));
	}

	@Override
	public void onNothingSelected(AdapterView<?> parent) {

	}
}
