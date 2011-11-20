package mconta.web.client.event;

import com.google.gwt.event.shared.EventHandler;

public interface AppErrorHandler extends EventHandler {
	public void onError(String msg);
}
