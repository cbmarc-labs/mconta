package mconta.android;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * MainActivity class
 * 
 * @author marc
 *
 */
public class MainActivity extends Activity {
	
	/**
	 * MyWebViewClient class
	 * 
	 * @author marc
	 */
	// Control over where a clicked link load
	private class MyWebViewClient extends WebViewClient {

		@Override
		public boolean shouldOverrideUrlLoading(WebView view, String url) {
			view.loadUrl(url);
			return true;
		}
		
	}

	/**
	 * WebView is the embed browser 
	 */
    WebView webView;
    
    /**
     * customHtml is the default message for no variable definition
     */
    String customHtml = "<html><body><h1>Aplicaci&oacute;n no definida.</h1></body></html>";
    
    /**
     * onCreate is called when application starts
     * 
     * @author marc
     * @param savedInstanceState The previous instance state, type Bundle
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        webView = (WebView) findViewById(R.id.webview);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new MyWebViewClient());
        
        if(SettingsActivity.getUrl(this).equals(getString(R.string.app_url)))
        	webView.loadData(customHtml, "text/html", "UTF-8");
        else
        	webView.loadUrl(SettingsActivity.getUrl(this));
    }

    /**
     * onKeyDown() 
     * 
     * @author marc
     * @param keyCode
     * @param event
     * @return
     */
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		
		if ((keyCode == KeyEvent.KEYCODE_BACK) && webView.canGoBack()) {
			webView.goBack();
			
			return true;
		}
		
		return super.onKeyDown(keyCode, event);
	}

	/**
	 * onCreateOptionsMenu()
	 * 
	 * @author marc
	 * @param menu
	 * @return Boolean state of the menu
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
	    inflater.inflate(R.menu.menu, menu);
	    
	    return true;
	}

	/**
	 * onOptionsItemSelected() returns true if item selected or false if not
	 * 
	 * @author marc
	 * @param item 
	 * @return
	 */
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.reload:
			webView.clearCache(true);
			webView.loadUrl(SettingsActivity.getUrl(this));
			
			return true;
		case R.id.settings:
			startActivity(new Intent(this, SettingsActivity.class));
			
			return true;
		}
		
		return false;
	}

}

