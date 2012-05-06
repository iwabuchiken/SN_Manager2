package sn2.sn1;

import java.text.NumberFormat;

import sn2.main.R;
//import sn.lib.LocationUtils;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

//import sn.main.R;

public class SN2_1_GetLocation extends Activity implements LocationListener {

	/* Fields
	 * (�?Javadoc)
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	// LocationManager
	private LocationManager locationManager_;

	// LocationProvider
	private LocationProvider locationProvider_;
		
	// Longitude
	Double longitude;
	
	// Longitude
	Double latitude;

	// Preference instance
	SharedPreferences preference;

	// Data is shown?
	boolean isDataShown = false;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
		// super
		super.onCreate(savedInstanceState);
		
		// Set the content
		setContentView(R.layout.sn2_1);
		
		// Initialize preference
		preference = getSharedPreferences("SN_1", MODE_PRIVATE);
		
		/* Set listeners to the buttons
		 * 
		 */
		// Button: Show
		Button btn_show = (Button) findViewById(R.id.btn_show);
		btn_show.setTag("show");
		btn_show.setOnClickListener(new ButtonClickListener());
		
		// Button: Save
		Button btn_save = (Button) findViewById(R.id.btn_save);
		btn_save.setTag("save");
		btn_save.setOnClickListener(new ButtonClickListener());
		
		// Button: Save
		Button btn_get_data = (Button) findViewById(R.id.btn_get_data);
		btn_get_data.setTag("getData");
		btn_get_data.setOnClickListener(new ButtonClickListener());
		
	}//public void onCreate(Bundle savedInstanceState)
	
	public void onStart() {
		super.onStart();
		
		// 繝ｭ繧?�繝ｼ繧?�繝ｧ繝ｳ繝槭ロ繝ｼ繧?�繝｣縺?�繧?�繝ｳ繧?�繧?�繝ｳ繧?�繧貞叙蠕励�?��?�    	
    	locationManager_ = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
    	
    	// 繝励Ο繝�?�?���↓GPS繧定ｨ?�螳?�    	
    	locationProvider_ = locationManager_.getProvider(LocationManager.GPS_PROVIDER);
    	
    	if (null == locationProvider_) {
            // GPS縺御?�?�縺医↑縺?�?�蜷医?�繝阪ャ繝医Ρ繝ｼ繧?�縺九ｉ縺?�菴咲?�?��?��?�蜿�?��励?�險?�螳壹�?��?�            
    		locationProvider_ = locationManager_.getProvider(LocationManager.NETWORK_PROVIDER);
//            if (null == locationProvider_) {
//            	return;
//            } else {
//           		providerLabel.setText("NETWORK_PROVIDER");            	
//            }
    	} else {
//    		providerLabel.setText("GPS_PROVIDER");
    	}
    	
    	// 菴咲?�?��?��?�縺?�譖ｴ�?��繧貞女縺大叙繧九ｈ縺?�↓險?�螳?�    	
    	locationManager_.requestLocationUpdates(locationProvider_.getName(),
    											0,
    											0,
    											this);
	}//public void onStart()
	
	// Inner class: ButtonClickListener
	class ButtonClickListener implements OnClickListener{

		public void onClick(View v) {
			// TODO 自動生成されたメソ�?��・スタ�?
			// tag+
			String buttonTag;// = (String) v.getTag();
			
			// If getTag() is not null
			if (v.getTag() != null) {
				buttonTag = (String) v.getTag();
			} else {//if (v.getTag() != null)
				buttonTag = "show";
			}//if (v.getTag() != null)
			
			// Switching
			if (buttonTag == "show") {
				showData();
			} else if (buttonTag == "save") {//if (buttonTag)
				saveData();
//				// debug
//				Toast.makeText(SN2_1_GetLocation.this, 
//									buttonTag, 
//									Toast.LENGTH_SHORT).show();
			} else if (buttonTag == "getData") {//if (buttonTag)
				getData();
				
			}//if (buttonTag)
			
			
//			// debug
//			Toast.makeText(SN2_1_GetLocation.this, 
//								buttonTag, 
//								Toast.LENGTH_SHORT).show();
			
//			//debug
//			Toast.makeText(SN2_1_GetLocation.this, v.toString(), 1).show();
			
		}//public void onClick(View arg0)

		private void getData() {
			// Is data already shown?
			if (isDataShown != true) {
				//debug
				Toast.makeText(SN2_1_GetLocation.this,
							"Data is not yet shown on the screen." +
							"\n" + "Please first tap \"Show\"", 
							Toast.LENGTH_SHORT).show();
			}//if (isDataShown)
			
			// TODO 自動生成されたメソ�?��・スタ�?
			//debug
			Toast.makeText(SN2_1_GetLocation.this,
						"getData", Toast.LENGTH_SHORT).show();
			
//			// Get a Preference object
//			SharedPreferences preference = 
//								getSharedPreferences("SN_1", MODE_PRIVATE);
			
			// Get data
			String longitudeData = preference.getString("LONGITUDE", "NO DATA!");
			String latitudeData = preference.getString("LATITUDE", "NO DATA!");
			
			// Get the text view
			TextView tv_long = (TextView) findViewById(R.id.tv_long);
			TextView tv_lat = (TextView) findViewById(R.id.tv_lat);
			
			// Set data
			if (longitudeData != null || latitudeData != null) {
				tv_long.setText(longitudeData);
				
				// debug
				Toast.makeText(SN2_1_GetLocation.this, 
						longitudeData, 
						Toast.LENGTH_SHORT).show();
			} else {//if (longitudeData != null)
				// debug
				Toast.makeText(SN2_1_GetLocation.this, 
						"longitudeData=" + longitudeData +
						"\n" + "latitudeData=" + latitudeData, 
						Toast.LENGTH_SHORT).show();
			}//if (longitudeData != null)
			
		}

		private void saveData() {
			// TODO 自動生成されたメソ�?��・スタ�?
			// Get a Preference object
//			SharedPreferences preference = 
//			preference = getSharedPreferences("SN_1", MODE_PRIVATE);
			
			// Get an editor object
			SharedPreferences.Editor editor = preference.edit();
			
			/* Prepare data
			 * 
			 */
			// Set the format
			NumberFormat format = NumberFormat.getInstance();
			
			// Set the number of floating digits => 10
			format.setMaximumFractionDigits(10);
			
			// Put data to preference
			editor.putString("LONGITUDE", String.valueOf(format.format(longitude)));
			editor.putString("LATITUDE", String.valueOf(format.format(latitude)));
			
			// Commit data to preference
			editor.commit();
			
			// debug
			Toast.makeText(SN2_1_GetLocation.this, 
								"Data saved", 
								Toast.LENGTH_SHORT).show();
		}

		private void showData() {
			// TODO 自動生成されたメソ�?��・スタ�?
			// Valid data obtained?
			if (longitude == null || latitude == null) {
				Toast.makeText(SN2_1_GetLocation.this, 
						"Sorry. Data is null, yet", 
						Toast.LENGTH_SHORT).show();
				return;
//			} else if (longitude.intValue() == 999) {
//				// debug
//				Toast.makeText(SN2_1_GetLocation.this, 
//									"Sorry. Longitude data not yet ready yet", 
//									Toast.LENGTH_SHORT).show();
			} else {//if ((int) longitude == 999)
				
				/* Show the longitude
				 * 
				 */
				// Get the text view
				TextView tv_long = (TextView) findViewById(R.id.tv_long);
				TextView tv_lat = (TextView) findViewById(R.id.tv_lat);
				
				/* Modify the double value
				 * 
				 */
				// Set the format
				NumberFormat format = NumberFormat.getInstance();
				// Set the number of floating digits => 10
				format.setMaximumFractionDigits(10);
				
				// Set the text
				tv_long.setText(String.valueOf(format.format(longitude)));
				tv_lat.setText(String.valueOf(format.format(latitude)));
//				tv_show.setText(String.valueOf(longitude));
				
				// Set the variable to true
				isDataShown = true;
				
//				// debug
//				Toast.makeText(SN2_1_GetLocation.this, 
//									String.valueOf(longitude), 
//									Toast.LENGTH_SHORT).show();
			}//if ((int) longitude == 999)
			
			
//			// debug
//			Toast.makeText(SN2_1_GetLocation.this, 
//								String.valueOf(longitude), 
//								Toast.LENGTH_LONG).show();
		}//private void showData()
		
	}//class ButtonClickListener implements OnClickListener

	public void onLocationChanged(Location location) {
		// TODO 自動生成されたメソ�?��・スタ�?
		longitude = location.getLongitude();
		latitude = location.getLatitude();
	}

	public void onProviderDisabled(String provider) {
		// TODO 自動生成されたメソ�?��・スタ�?
		
	}

	public void onProviderEnabled(String provider) {
		// TODO 自動生成されたメソ�?��・スタ�?
		
	}

	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO 自動生成されたメソ�?��・スタ�?
		
	}
}
