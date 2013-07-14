package com.example.androidappconfigwizard;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.ViewFlipper;

public class ConfigWizard extends Activity implements OnClickListener {

    ViewFlipper viewFlipper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.config_wizard);

	viewFlipper = (ViewFlipper) findViewById(R.id.viewflipper);

    }

    @Override
    public void onClick(View v) {

	switch (v.getId()) {
	case R.id.ButtonNextV1:
	case R.id.ButtonNextV2:
	    viewFlipper.showNext();
	    break;
	case R.id.ButtonPrevV2:
	case R.id.ButtonPrevV3:
	    viewFlipper.showPrevious();
	    break;
	case R.id.ButtonFinish:
	    submitPreferences();
	    break;
	}

    }

    private void submitPreferences() {

	EditText editTextPreference1 = (EditText) findViewById(R.id.editTextPreference1);
	RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
	Spinner spinner = (Spinner) findViewById(R.id.spinner1);

	int id = radioGroup.getCheckedRadioButtonId();
	View radioButton = radioGroup.findViewById(id);
	int radioId = radioGroup.indexOfChild(radioButton);
	RadioButton btn = (RadioButton) radioGroup.getChildAt(radioId);

	SharedPreferences preferences = PreferenceManager
		.getDefaultSharedPreferences(this);
	SharedPreferences.Editor editor = preferences.edit();
	editor.putString("preference1", editTextPreference1.getText()
		.toString());
	editor.putString("preference2", btn.getText().toString());
	editor.putString("preference3", spinner.getSelectedItem().toString());
	editor.putBoolean("configWizardCompleted", true);
	editor.commit();

	Intent intent = new Intent();
	setResult(RESULT_OK, intent);

	finish();

    }
}
