package mconta.web.client.app.rpc;

import mconta.web.client.app.event.AppErrorEvent;
import mconta.web.client.app.event.AppEventBus;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;

public abstract class AppAsyncCallback<T> implements AsyncCallback<T> {
	
	private AppEventBus eventBus = AppEventBus.getAppEventBus();
	
	public void onFailure(Throwable caught) {
		Window.alert("Server error: " + caught.toString());
		
		eventBus.fireEvent(new AppErrorEvent(caught.getMessage()));
	}
	
}
