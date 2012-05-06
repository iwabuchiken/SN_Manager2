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
          	
    	// 繝ｭ繧?�繝ｼ繧?�繝ｧ繝ｳ繝槭ロ繝ｼ繧?�繝｣縺?�繧?�繝ｳ繧?�繧?�繝ｳ繧?�繧貞叙蠕励�?��?�    	
    	locationManager_ = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
    	
    	// 繝励Ο繝�?�?���↓GPS繧定ｨ?�螳?�    	
    	locationProvider_ = locationManager_.getProvider(LocationManager.GPS_PROVIDER);
    	
    	if (null == locationProvider_) {
            // GPS縺御?�?�縺医↑縺?�?�蜷医?�繝阪ャ繝医Ρ繝ｼ繧?�縺九ｉ縺?�菴咲?�?��?��?�蜿�?��励?�險?�螳壹�?��?�            
    		locationProvider_ = locationManager_.getProvider(LocationManager.NETWORK_PROVIDER);
            if (null == locationProvider_) {
            	return;
            } else {
           		providerLabel.setText("NETWORK_PROVIDER");            	
            }
    	} else {
    		providerLabel.setText("GPS_PROVIDER");
    	}
    	
    	// 菴咲?�?��?��?�縺?�譖ｴ�?��繧貞女縺大叙繧九ｈ縺?�↓險?�螳?�    	
    	locationManager_.requestLocationUpdates(locationProvider_.getName(),
    											0,
    											0,
    											this);
    }
    
    @Override
    public void onStop() {
    	super.onStop();
    	
    	// 菴咲?�?��?��?�縺?�譖ｴ�?��繧呈ｭ?�繧√�?
    	locationManager_.removeUpdates(this);
    }

    public void onLocationChanged(Location location) {
    	// 菴咲?�?��?��?�繧探extView縺?�險?�螳壹�?��?�    	
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
