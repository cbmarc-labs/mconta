package mconta.web.client.common.event;

import com.google.gwt.event.shared.GwtEvent;

/**
 * MessageEvent class
 * 
 * @author marc
 *
 */
public class JQMListEvent extends GwtEvent<JQMListHandler> {
	
	private static Type<JQMListHandler> TYPE = new Type<JQMListHandler>();
	private enum Operation {LEFT, RIGHT};	
	private Operation operation;
	private int index;
	
	private JQMListEvent(Operation operation, int index) {
		this.operation = operation;
		this.index = index;
	}
	
	public static JQMListEvent left(int index) {
		return new JQMListEvent(Operation.LEFT, index);
	}
	
	public static JQMListEvent right(int index) {
		return new JQMListEvent(Operation.RIGHT, index);
	}
	
	public static Type<JQMListHandler> getType() {	
		return TYPE;
	}

	@SuppressWarnings({"unchecked", "rawtypes"})
	@Override
	public final Type<JQMListHandler> getAssociatedType() {
		return (Type) TYPE;
	}

	@Override
	protected void dispatch(JQMListHandler handler) {
		switch(operation) {
		case LEFT:
			handler.onLeftClick(this, index);
			break;
		case RIGHT:
			handler.onRightClick(this, index);
			break;
		}
	}

}
