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


import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.DataInputStream;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.File;
import java.io.InputStream;

import java.lang.Integer;
import java.util.ArrayList;

import android.content.Context;
import android.util.Log;


public class NoteUtil {
	
	private Context ctx;
	public NoteUtil(Context c) {
		ctx = c;
	}
	
	/* creates/saves a new note, returns the new noteid */
	public int CreateNote(int icon, String title, String text) {
		int fileid = (int)( System.currentTimeMillis() / 1000 );
		try {
			String outdata           = icon      +"\n"+
			                           title     +"\n"+
			                           text           ;
			
			FileOutputStream fos     = ctx.openFileOutput(""+fileid, ctx.MODE_PRIVATE);
			BufferedOutputStream bos = new BufferedOutputStream(fos, 256);
			
			bos.write(outdata.getBytes());
			
			bos.close();
			fos.close();
		} catch(Exception e) { fileid = 0; }
		
		return fileid;
	}
	
	
	/* Loads given note id, returns an ArrayList with:
	 *
	 * [STATUS,ICONID,TITLE,BODY]
	 *
	 */
	public ArrayList LoadNoteById(int nid) {
		
		ArrayList result = new ArrayList();
		
		/* [ result, icon, title_txt, body_txt ] */
		result.add(-1); result.add(0); result.add(""); result.add("");
		
		try {
			String              utfbytes = "";
			String[]            splitted ;
			byte[]              bytes    = new byte[1024];
			
			FileInputStream     fis      = ctx.openFileInput(""+nid);
			BufferedInputStream bis      = new BufferedInputStream(fis, 1024);
			DataInputStream     dis      = new DataInputStream(bis);
			
			dis.read(bytes);
			
			dis.close();
			bis.close();
			fis.close();
			
			
			utfbytes = (new String(bytes, "UTF-8"));
			splitted = utfbytes.split("\n", 3);
			
			if(splitted.length == 3) {
				result.set(1, Integer.parseInt(splitted[0]));
				result.set(2, splitted[1].trim());
				result.set(3, splitted[2].trim());
				result.set(0, 0);
			}
			else {
				result.set(2, "corrupted note:");
				result.set(3, utfbytes.trim() );
			}
			
		} catch(Exception e) { result.set(2, "error "+e); }
		
		return result;
	}
	
	/* Removes given noteid from stable storage */
	public boolean RemoveNoteById(int nid) {
		return (new File(ctx.getFilesDir()+"/"+nid)).delete();
	}
	
	/* Returns an ArrayList with all available note ids */
	public ArrayList<Integer> GetAllNoteIds() {
		int i, y;
		ArrayList noteslist = new ArrayList<Integer>();
		File dir            = new File( ""+ctx.getFilesDir() );
		String dirents[]    = dir.list();
		
		for(i=0; i<dirents.length;i++) {
			try {
				y = java.lang.Integer.parseInt(dirents[i]);
				noteslist.add(y);
			}
			catch(NumberFormatException e) {
				// Log.v("GetAllNoteIDs", "skipping invalid note with name "+dirents[i]);
				/* There isn't much we can do apart from deleting the file */
			}
		}
		
		return noteslist;
	}
	
	
	
}
