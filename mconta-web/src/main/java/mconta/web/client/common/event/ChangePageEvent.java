package mconta.web.client.common.event;

import mconta.domain.model.Model;

import com.google.gwt.event.shared.GwtEvent;

/**
 * MessageEvent class
 * 
 * @author marc
 *
 */
public class ChangePageEvent extends GwtEvent<ChangePageHandler> {
	
	private static Type<ChangePageHandler> TYPE = new Type<ChangePageHandler>();
	private enum Operation {LIST, EDIT};
	
	private Operation operation;	
	private Model model;
	
	private ChangePageEvent(Operation operation, Model model) {
		this.model = model;
		this.operation = operation;
	}
	
	public static ChangePageEvent list() {
		return new ChangePageEvent(Operation.LIST, null);
	}
	
	public static ChangePageEvent edit(Model model) {
		return new ChangePageEvent(Operation.EDIT, model);
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
		switch(operation) {
		case LIST:
			handler.onList(this);
			break;
		case EDIT:
			handler.onEdit(this, model);
			break;
		}
	}

}
