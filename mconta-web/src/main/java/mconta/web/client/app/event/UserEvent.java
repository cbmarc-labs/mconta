package mconta.web.client.app.event;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;

public class UserEvent extends GwtEvent<UserEvent.UserHandler> {
	
	public interface UserHandler extends EventHandler {
		public void onUserEvent();
	}
	
	private static Type<UserHandler> TYPE = new Type<UserHandler>();
		
	public static Type<UserHandler> getType() {	
		return TYPE;
	}

	@SuppressWarnings({"unchecked", "rawtypes"})
	@Override
	public final Type<UserHandler> getAssociatedType() {
		return (Type) TYPE;
	}

	@Override
	protected void dispatch(UserHandler handler) {
		handler.onUserEvent();
	}

}
