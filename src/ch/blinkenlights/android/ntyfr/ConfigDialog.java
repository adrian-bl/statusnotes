/*******************************************************
 *
 * Part of ch.blinkenlights.android.ntyfr
 *
 * (C) 2012 Adrian Ulrich
 *
 * Licensed under the GPLv2 (only)
 *
 *******************************************************/

package ch.blinkenlights.android.ntyfr;



import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.Preference.OnPreferenceClickListener;
 
public class ConfigDialog extends PreferenceActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.xml.preferences);
		
		Preference shortcutCallback = (Preference) findPreference("showShortcut");
		
		shortcutCallback.setOnPreferenceClickListener(new OnPreferenceClickListener() {
			public boolean onPreferenceClick(Preference preference) {
				Stuff stuff = new Stuff(getApplicationContext());
				stuff.UpdateNewNoteShortcut();
				return true;
			}
		});
	
	}
}
