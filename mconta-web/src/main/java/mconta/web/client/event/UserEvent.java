package mconta.web.client.event;

import com.google.gwt.event.shared.GwtEvent;

public class UserEvent extends GwtEvent<UserHandler> {
	
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
		handler.onEvent();
	}

}
