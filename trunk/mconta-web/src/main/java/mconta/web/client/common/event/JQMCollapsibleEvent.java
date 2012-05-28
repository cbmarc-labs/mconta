package mconta.web.client.common.event;

import com.google.gwt.event.shared.GwtEvent;

/**
 * MessageEvent class
 * 
 * @author marc
 *
 */
public class JQMCollapsibleEvent extends GwtEvent<JQMCollapsibleHandler> {
	
	private static Type<JQMCollapsibleHandler> TYPE = new Type<JQMCollapsibleHandler>();
	private enum Operation {EXPAND, COLLAPSE};
	
	private Operation operation;
	
	private JQMCollapsibleEvent(Operation operation) {
		this.operation = operation;
	}
	
	public static JQMCollapsibleEvent expand() {
		return new JQMCollapsibleEvent(Operation.EXPAND);
	}
	
	public static JQMCollapsibleEvent collapse() {
		return new JQMCollapsibleEvent(Operation.COLLAPSE);
	}
	
	public static Type<JQMCollapsibleHandler> getType() {	
		return TYPE;
	}

	@SuppressWarnings({"unchecked", "rawtypes"})
	@Override
	public final Type<JQMCollapsibleHandler> getAssociatedType() {
		return (Type) TYPE;
	}

	@Override
	protected void dispatch(JQMCollapsibleHandler handler) {
		switch(operation) {
		case EXPAND:
			handler.onExpand(this);
			break;
		case COLLAPSE:
			handler.onCollapse(this);
			break;
		}
	}

}
