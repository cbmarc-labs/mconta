/**
 * 
 */
package mconta.web.client.event;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;

/**
 * @author Marc
 *
 */
public class AppCellTableEvent 
		extends GwtEvent<AppCellTableEvent.AppCellTableHandler> {
	
	//marker for handler logic in registration method
	public interface AppCellTableHandler extends EventHandler {
		   void onDeleteButtonClicked();
		   void onAddNewButtonClicked();
	}
	
	private static final Type<AppCellTableHandler> TYPE = 
			new Type<AppCellTableHandler>();
	private enum Operation {ADDNEW, DELETE};
	private Operation operation;
	
	private AppCellTableEvent(Operation operation) {
		this.operation = operation;
		
	}
	
	public static AppCellTableEvent addnew() {
		return new AppCellTableEvent(Operation.ADDNEW);
	}
	
	public static AppCellTableEvent delete() {
		return new AppCellTableEvent(Operation.DELETE);
	}
	
	public static Type<AppCellTableHandler> getType() {
		return TYPE;
		
	}
	
	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<AppCellTableHandler> 
			getAssociatedType() {
		return getType();
		
	}

	@Override
	protected void dispatch(AppCellTableHandler handler) {
		switch(operation) {
		case ADDNEW:
			handler.onAddNewButtonClicked();
			break;
		case DELETE:
			handler.onDeleteButtonClicked();
			break;
		}
		
	}

}
