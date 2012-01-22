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


import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;

/* Start service on boot */
public class OnBootReceiver extends BroadcastReceiver {
	@Override
	public void onReceive(Context context, Intent intent) {
		context.startActivity( new Intent(context, NewNoteDialog.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK).putExtra("nid", -1) );
	}
}
