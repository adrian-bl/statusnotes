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

import android.content.Context;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;


public class ConfigUtil {
	private Context ctx;
	private SharedPreferences prefs;
	
	public ConfigUtil(Context c) {
		ctx = c;
		prefs = PreferenceManager.getDefaultSharedPreferences(ctx);
	}
	
	public boolean ShowNoteShortcut() {
		return prefs.getBoolean("showShortcut", false);
	}
	
	public boolean ShowRandomIcon() {
		return prefs.getBoolean("selectRandomAll", false);
	}
	
	
}
