package mconta.web.client.app.event;

import com.google.gwt.event.shared.HandlerManager;

public class AppEventBus extends HandlerManager {
	
	private static AppEventBus appEventBus = new AppEventBus();

	private AppEventBus() {
		super(null);
	}
	
	public static AppEventBus getAppEventBus() {
		return appEventBus;
	}
	
	public static AppEventBus getEventBus() {
		return appEventBus;
		
	}

}
