package mconta.web.client.app.event;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;

public class AppErrorEvent extends GwtEvent<AppErrorEvent.AppErrorHandler> {
	
	public interface AppErrorHandler extends EventHandler {
		public void onError(String msg);
	}
	
	private static Type<AppErrorHandler> TYPE = new Type<AppErrorHandler>();
	private String msg;
	
	public AppErrorEvent(String msg) {
		this.msg = msg;
	}
	
	public static Type<AppErrorHandler> getType() {	
		return TYPE;
	}

	@SuppressWarnings({"unchecked", "rawtypes"})
	@Override
	public final Type<AppErrorHandler> getAssociatedType() {
		return (Type) TYPE;
	}

	@Override
	protected void dispatch(AppErrorHandler handler) {
		handler.onError(this.msg);
	}

}
