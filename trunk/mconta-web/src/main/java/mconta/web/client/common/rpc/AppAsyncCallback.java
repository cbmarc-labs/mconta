package mconta.web.client.common.rpc;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;

public abstract class AppAsyncCallback<T> implements AsyncCallback<T> {
	
	public void onFailure(Throwable caught) {
		Window.alert("Server error: " + caught.toString());
		
	}
	
}
