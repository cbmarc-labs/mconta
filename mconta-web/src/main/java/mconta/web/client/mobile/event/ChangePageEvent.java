package mconta.web.client.mobile.event;

import com.google.gwt.event.shared.GwtEvent;

/**
 * MessageEvent class
 * 
 * @author marc
 *
 */
public class ChangePageEvent extends GwtEvent<ChangePageHandler> {
	
	private static Type<ChangePageHandler> TYPE = new Type<ChangePageHandler>();
	private String page;
	
	public ChangePageEvent(String page) {
		this.page = page;
	}
	
	public static Type<ChangePageHandler> getType() {	
		return TYPE;
	}

	@SuppressWarnings({"unchecked", "rawtypes"})
	@Override
	public final Type<ChangePageHandler> getAssociatedType() {
		return (Type) TYPE;
	}

	@Override
	protected void dispatch(ChangePageHandler handler) {
		handler.onChangePage(page);
	}

}
