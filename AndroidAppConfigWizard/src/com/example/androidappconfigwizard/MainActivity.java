package com.example.androidappconfigwizard;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class MainActivity extends Activity implements OnClickListener {

    public static final int RETURN_CODE = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);

	SharedPreferences preferences = PreferenceManager
		.getDefaultSharedPreferences(this);
	Boolean configWizardCompleted = preferences.getBoolean(
		"configWizardCompleted", false);

	if (!configWizardCompleted) {
	    launchConfigWizard();
	}

	setContentView(R.layout.activity_main);
	updateTextView();

    }

    @Override
    public void onClick(View v) {

	erasePreferences();
	launchConfigWizard();

    }

    private void launchConfigWizard() {

	Intent intent = new Intent(this, ConfigWizard.class);
	startActivityForResult(intent, RETURN_CODE);

    }

    private void erasePreferences() {

	SharedPreferences preferences = PreferenceManager
		.getDefaultSharedPreferences(this);
	SharedPreferences.Editor editor = preferences.edit();
	editor.clear();
	editor.commit();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

	if (requestCode == RETURN_CODE && resultCode == RESULT_CANCELED) {
	    finish();
	} else {
	    updateTextView();
	}

    }

    private void updateTextView() {

	SharedPreferences preferences = PreferenceManager
		.getDefaultSharedPreferences(this);
	TextView preference1 = (TextView) findViewById(R.id.resultPreference1);
	TextView preference2 = (TextView) findViewById(R.id.resultPreference2);
	TextView preference3 = (TextView) findViewById(R.id.resultPreference3);
	preference1.setText(getResources().getString(R.string.pref1) + ": "
		+ preferences.getString("preference1", ""));
	preference2.setText(getResources().getString(R.string.pref2) + ": "
		+ preferences.getString("preference2", ""));
	preference3.setText(getResources().getString(R.string.pref3) + ": "
		+ preferences.getString("preference3", ""));

    }

}
