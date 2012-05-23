package mconta.android;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;
import android.util.Log;

public class SettingsActivity extends PreferenceActivity {
	
    /** 
     * TAG defines the class for debuggin purposes
     * 
     * @see SettingsActivity
     */
    private static final String TAG = "SettingsActivity";
    
    /**
     * OPT_URL is the key for the url setting in xml/settings.xml
     * 
     * @see DeviceSettings
     */
    private static final String OPT_URL = "url";
    
    /**
     * OPT_URL_DEF is the default value for the URL setting in 
     * xml/settings.xml if the value cannot be found.
     * 
     * @see DeviceSettings
     */
    private static final String OPT_URL_DEF = "http://";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		try {
			Log.v(TAG,"Trying to create the SettingsActivity page");
			super.onCreate(savedInstanceState);
			addPreferencesFromResource(R.xml.settings);
			
		} catch (Exception e){
            Log.e(TAG, "There was an error creating SettingsActivity. Exiting. " + 
            		e.getMessage(),e.getCause());
            
            finish();
		}
	}
	
	public static String getUrl(Context context) {
		SharedPreferences prefs;
		
		try {
            Log.v(TAG, "Trying to get the URL address");
            prefs = PreferenceManager.getDefaultSharedPreferences(context);
            
            return prefs.getString(OPT_URL, OPT_URL_DEF);
            
		} catch (Exception e) {
            Log.e(TAG, "Error while trying to get URL address. Exiting. " + 
            		e.getMessage(),e.getCause());
            
            return "";
    }
	}

}
