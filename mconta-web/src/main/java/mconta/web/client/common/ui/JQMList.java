/**
 * 
 */
package mconta.web.client.common.ui;

import mconta.web.client.common.event.JQMListEvent;
import mconta.web.client.common.event.JQMListHandler;

import com.google.gwt.dom.client.Document;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.ComplexPanel;
import com.google.gwt.user.client.ui.InsertPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author marc
 *
 */
public class JQMList extends ComplexPanel implements InsertPanel {
	
	private HandlerManager manager = new HandlerManager(this);
	
	public JQMList() {		
		setElement(Document.get().createULElement());
		
		getElement().setId(DOM.createUniqueId());
		getElement().setAttribute("data-role", "listview");
		getElement().setAttribute("data-inset", "true");
				
	}
	
	public HandlerRegistration addClickHandler(JQMListHandler handler) {
		return manager.addHandler(JQMListEvent.getType(), handler);
		
	} 
	
	public void add(JQMListItem child) {
		child.setList(this);
		add(child, getElement());
		
	}
	
	public void onLeftAnchorClick(JQMListItem item) {
		int index = getWidgetIndex(item);
		manager.fireEvent(JQMListEvent.left(index));
	}
	
	public void onRightAnchorClick(JQMListItem item) {
		int index = getWidgetIndex(item);
		manager.fireEvent(JQMListEvent.right(index));
	}
	
	public void refresh() {
        refresh(getElement().getId());
        
	}
	
	private static native void refresh(String id) /*-{
	
    	$wnd.$("#" + id).listview('refresh');
    	
    }-*/;

	@Override
	public void insert(Widget w, int beforeIndex) {
		insert(w, getElement(), beforeIndex, true);
		
	}

}
