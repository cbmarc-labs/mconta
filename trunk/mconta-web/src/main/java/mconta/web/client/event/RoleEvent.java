package mconta.web.client.event;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;

public class RoleEvent extends GwtEvent<RoleEvent.RoleHandler> {
	
	public interface RoleHandler extends EventHandler {
		public void onEvent();
	}
	
	private static Type<RoleHandler> TYPE = new Type<RoleHandler>();
		
	public static Type<RoleHandler> getType() {	
		return TYPE;
	}

	@SuppressWarnings({"unchecked", "rawtypes"})
	@Override
	public final Type<RoleHandler> getAssociatedType() {
		return (Type) TYPE;
	}

	@Override
	protected void dispatch(RoleHandler handler) {
		handler.onEvent();
	}

}
