package mconta.web.client.rpc;

import mconta.web.client.event.AppErrorEvent;
import mconta.web.client.event.AppEventBus;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;

public abstract class AppAsyncCallback<T> implements AsyncCallback<T> {
	
	private AppEventBus eventBus = AppEventBus.getAppEventBus();
	
	public void onFailure(Throwable caught) {
		System.out.println("Server error: " + caught.getMessage());
		Window.alert("Server error: " + caught.getMessage());
		
		eventBus.fireEvent(new AppErrorEvent(caught.getMessage()));
	}
	
}