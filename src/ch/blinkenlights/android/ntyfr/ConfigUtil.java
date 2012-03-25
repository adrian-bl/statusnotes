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
import android.app.Notification;
import android.app.NotificationManager;
import android.content.Intent;
import android.app.PendingIntent;
import android.widget.RemoteViews;


public class ConfigUtil {
	private static int SHORTCUT_NID = -1;
	private Context ctx;
	
	public ConfigUtil(Context c) {
		ctx = c;
	}
	
	public boolean ShowNoteShortcut() {
		return true;
	}
	
	public boolean StartAtBoot() {
		return true;
	}
	
	
	public void UpdateNewNoteShortcut() {
		NotificationManager notify_manager = (NotificationManager) ctx.getSystemService(ctx.NOTIFICATION_SERVICE);
		
		notify_manager.cancel(SHORTCUT_NID);
		
		if( ShowNoteShortcut() == false ) {
			return; /* early return */
		}
		
		long fake_time    = android.os.Build.VERSION.SDK_INT >= 9 ? -Long.MAX_VALUE : Long.MAX_VALUE;
		
		RemoteViews rview = new RemoteViews(ctx.getPackageName(), R.layout.shortcut_notification);
		Notification n    = new Notification(R.drawable.trans, null, 0);
		Intent xintent    = new Intent(ctx, NewNoteDialog.class);
		PendingIntent xpi = PendingIntent.getActivity(ctx, 0, xintent, 0);
		
		rview.setImageViewResource(R.id.image, R.drawable.plus);
		rview.setTextViewText(R.id.title, "New note");
		rview.setTextViewText(R.id.text, "Click here to create a new note.");
		
		n.contentView   = rview;
		n.contentIntent = xpi;
		n.when          = fake_time;  // pushes notification to the right corner
		n.flags        |= Notification.FLAG_ONGOING_EVENT | Notification.FLAG_NO_CLEAR;
		
		notify_manager.notify(SHORTCUT_NID, n);
		
	}
	
	
}
