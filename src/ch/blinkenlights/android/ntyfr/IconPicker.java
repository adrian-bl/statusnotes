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
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import android.widget.AdapterView;

import android.util.Log;


public class IconPicker extends Activity {
	
	GridView this_grid;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.iconpicker);
		this_grid = (GridView) findViewById(R.id.gridview);
		this_grid.setAdapter(new IconSelection());
		this_grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				setResult( position, new Intent() );
				finish();
			}
		});
	}
	
	
	public class IconSelection extends BaseAdapter {
		public IconSelection() {
		}
		
		public View getView(int pos, View conv_view, ViewGroup parent) {
			ImageView i;
			
			if (conv_view == null) {
				i = new ImageView(IconPicker.this);
				i.setScaleType(ImageView.ScaleType.FIT_CENTER);
				i.setLayoutParams(new GridView.LayoutParams(32,32));
			}
			else {
				i = (ImageView) conv_view;
			}
			
			i.setImageResource(R.drawable.x000 + pos);
			
			return i;
		}
		
		
		public final int getCount() {
			return 68;
		}
		
		public final Object getItem(int pos) {
			return pos;
		}
		
		public final long getItemId(int pos) {
			return pos;
		}
	}

}
