package com.example.countrylist;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

/**
 *  Country List
 *  
 *  Simple application to display a list of Countries showing their flag and name.
 *  
 *  Uses custom Adapter and ViewHolder.
 *  List country names and flag images are currently loaded from two arrays for the purposes 
 *  of this demo. This can be changed to read from a db or service in the future. 
 *   
 * @author Rob McBryde
 * @date July 2013
 *
 */
public class MainActivity extends Activity {

	ListView list;
	String[] countryNames;
	int[] flagImages = {R.drawable.argentina, R.drawable.belgium, R.drawable.brazil, R.drawable.canada, R.drawable.china, R.drawable.croatia,
			   			R.drawable.denmark, R.drawable.england, R.drawable.germany, R.drawable.italy, R.drawable.jamaica, R.drawable.japan,
			   			R.drawable.netherlands, R.drawable.portugal, R.drawable.romania, R.drawable.scotland, R.drawable.south_korea, R.drawable.spain,
			   			R.drawable.sweden, R.drawable.switzerland};
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		// set the layout 
		setContentView(R.layout.activity_main);
		Resources res = getResources();
		
		//populate the list array of country names from strings.xml
		countryNames = res.getStringArray(R.array.countryNames);
		
		//instanciate the list and custom FlagAdaptor
		list = (ListView) findViewById(R.id.listView);
		FlagAdapter adapter = new FlagAdapter(this, countryNames, flagImages);
		list.setAdapter(adapter);
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}


}

/**
 *  Custom FlagAdaptor
 *  
 *  This contains a custom ViewHolder
 *  
 * @author Rob
 * @date July 2013
 *
 */
class FlagAdapter extends ArrayAdapter<String>
{
	Context context;
	int[] flags;
	String[] names;
	
	public FlagAdapter(Context context, String[] countryNames, int flags[]) {
		super(context, R.layout.single_row, R.id.textView1, countryNames);
		this.context = context;
		this.flags = flags;
		this.names = countryNames;
		
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent){
		
		// Keeps reference to avoid future findViewById()
		CountryViewHolder viewHolder;
	
		if (convertView == null) {
            
		LayoutInflater inflator = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		convertView = inflator.inflate(R.layout.single_row, parent, false);
		
		viewHolder = new CountryViewHolder();
        viewHolder.flagImageHolder = (ImageView) convertView.findViewById(R.id.imageView1);
        viewHolder.countryNameHolder = (TextView) convertView.findViewById(R.id.textView1);
        

        convertView.setTag(viewHolder);
        
		} else {
            viewHolder = (CountryViewHolder) convertView.getTag();
        }
		
            viewHolder.flagImageHolder.setImageResource(flags[position]);
            viewHolder.countryNameHolder.setText(names[position]);
        
		return convertView;
	}
	
	// COuntryViewHolder to prevent multiple calls to finsViewById in the Adapter
	static class CountryViewHolder{
		ImageView flagImageHolder;
		TextView countryNameHolder;
	}
	
}
