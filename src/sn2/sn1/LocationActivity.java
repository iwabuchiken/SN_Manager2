package sn2.sn1;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.location.Location;
import android.location.LocationManager;
import android.location.LocationListener;
import android.location.LocationProvider;
import android.util.Log;
import android.widget.TextView;

import sn2.main.R;

public class LocationActivity extends Activity implements LocationListener {
	private LocationManager locationManager_;
	private LocationProvider locationProvider_;
	 
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.main);
        setContentView(R.layout.location_activity);
    }
    
    public void onStart() {
    	super.onStart();

    	TextView providerLabel = (TextView)findViewById(R.id.ProviderLabel);
          	
    	// ç¹ï½­ç¹§?±ç¹ï½¼ç¹§?·ç¹ï½§ç¹ï½³ç¹æ§­ãƒ­ç¹ï½¼ç¹§?¸ç¹ï½£ç¸º?®ç¹§?¤ç¹ï½³ç¹§?¹ç¹§?¿ç¹ï½³ç¹§?¹ç¹§è²å™è •åŠ±â˜?¹§?½    	
    	locationManager_ = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
    	
    	// ç¹åŠ±ÎŸç¹è?ã?¹ï¿½â†“GPSç¹§å®šï½¨?­è³?½    	
    	locationProvider_ = locationManager_.getProvider(LocationManager.GPS_PROVIDER);
    	
    	if (null == locationProvider_) {
            // GPSç¸ºå¾¡?½?¿ç¸ºåŒ»â†‘ç¸º?½?½èœ·åŒ»?½ç¹é˜ªãƒ£ç¹åŒ»Î¡ç¹ï½¼ç¹§?¯ç¸ºä¹ï½‰ç¸º?®è´å’²?½?®è«?¿½?½èœ¿é–?½¾åŠ±?’éšª?­è³å£¹â˜?¹§?½            
    		locationProvider_ = locationManager_.getProvider(LocationManager.NETWORK_PROVIDER);
            if (null == locationProvider_) {
            	return;
            } else {
           		providerLabel.setText("NETWORK_PROVIDER");            	
            }
    	} else {
    		providerLabel.setText("GPS_PROVIDER");
    	}
    	
    	// è´å’²?½?®è«?¿½?½ç¸º?®è­–ï½´è­?½°ç¹§è²å¥³ç¸ºå¤§å™ç¹§ä¹ï½ˆç¸º?½â†“éšª?­è³?½    	
    	locationManager_.requestLocationUpdates(locationProvider_.getName(),
    											0,
    											0,
    											this);
    }
    
    @Override
    public void onStop() {
    	super.onStop();
    	
    	// è´å’²?½?®è«?¿½?½ç¸º?®è­–ï½´è­?½°ç¹§å‘ˆï½­?¢ç¹§âˆšï½?
    	locationManager_.removeUpdates(this);
    }

    public void onLocationChanged(Location location) {
    	// è´å’²?½?®è«?¿½?½ç¹§æ¢extViewç¸º?«éšª?­è³å£¹â˜?¹§?½    	
    	TextView latitudeLabel = (TextView)findViewById(R.id.LatitudeLabel);
    	TextView LongitudeLabel = (TextView)findViewById(R.id.LongitudeLabel);
          	
    	latitudeLabel.setText(Double.toString(location.getLatitude()));
    	LongitudeLabel.setText(Double.toString(location.getLongitude()));
    }
    
    public void onProviderEnabled(String provider) {
    	Log.d("LocationActivity","onProviderEnabled");
    }
    
    public void onProviderDisabled(String provider) {   
    	Log.d("LocationActivity","onProviderDisabled");
    }
    public void onStatusChanged(String provider, int status, Bundle extras) {
       	Log.d("LocationActivity","onStatusChanged");   	
    }
}//public class LocationActivity
