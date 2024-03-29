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
	 * (é?Javadoc)
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
		
		// ç¹ï½­ç¹§?±ç¹ï½¼ç¹§?·ç¹ï½§ç¹ï½³ç¹æ§­ã­ç¹ï½¼ç¹§?¸ç¹ï½£ç¸º?®ç¹§?¤ç¹ï½³ç¹§?¹ç¹§?¿ç¹ï½³ç¹§?¹ç¹§è²åè å±â?¹§?½    	
    	locationManager_ = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
    	
    	// ç¹å±Îç¹è?ã?¹ï¿½âGPSç¹§å®ï½¨?­è³?½    	
    	locationProvider_ = locationManager_.getProvider(LocationManager.GPS_PROVIDER);
    	
    	if (null == locationProvider_) {
            // GPSç¸ºå¾¡?½?¿ç¸ºå»âç¸º?½?½è·å»?½ç¹éªã£ç¹å»Î¡ç¹ï½¼ç¹§?¯ç¸ºä¹ï½ç¸º?®è´å²?½?®è«?¿½?½è¿é?½¾å±?éª?­è³å£¹â?¹§?½            
    		locationProvider_ = locationManager_.getProvider(LocationManager.NETWORK_PROVIDER);
//            if (null == locationProvider_) {
//            	return;
//            } else {
//           		providerLabel.setText("NETWORK_PROVIDER");            	
//            }
    	} else {
//    		providerLabel.setText("GPS_PROVIDER");
    	}
    	
    	// è´å²?½?®è«?¿½?½ç¸º?®è­ï½´è­?½°ç¹§è²å¥³ç¸ºå¤§åç¹§ä¹ï½ç¸º?½âéª?­è³?½    	
    	locationManager_.requestLocationUpdates(locationProvider_.getName(),
    											0,
    											0,
    											this);
	}//public void onStart()
	
	// Inner class: ButtonClickListener
	class ButtonClickListener implements OnClickListener{

		public void onClick(View v) {
			// TODO èªåçæãããã¡ã½ã?ã»ã¹ã¿ã?
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
			
			// TODO èªåçæãããã¡ã½ã?ã»ã¹ã¿ã?
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
			// TODO èªåçæãããã¡ã½ã?ã»ã¹ã¿ã?
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
			// TODO èªåçæãããã¡ã½ã?ã»ã¹ã¿ã?
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
		// TODO èªåçæãããã¡ã½ã?ã»ã¹ã¿ã?
		longitude = location.getLongitude();
		latitude = location.getLatitude();
	}

	public void onProviderDisabled(String provider) {
		// TODO èªåçæãããã¡ã½ã?ã»ã¹ã¿ã?
		
	}

	public void onProviderEnabled(String provider) {
		// TODO èªåçæãããã¡ã½ã?ã»ã¹ã¿ã?
		
	}

	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO èªåçæãããã¡ã½ã?ã»ã¹ã¿ã?
		
	}
}
