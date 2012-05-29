/**
 * 
 */
package mconta.web.client.common.ui;

import mconta.web.client.common.event.JQMCollapsibleEvent;
import mconta.web.client.common.event.JQMCollapsibleHandler;

import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Element;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.uibinder.client.UiConstructor;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.ComplexPanel;
import com.google.gwt.user.client.ui.InsertPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * JQMPanel class
 * 
 * @author marc
 *
 */
public class JQMCollapsible extends ComplexPanel implements InsertPanel {
	
	private HandlerManager manager = new HandlerManager(this);
	private Boolean triggered = false;
	
	private Element text;
	
	public @UiConstructor JQMCollapsible(String text) {
		setElement(Document.get().createDivElement());
		this.text = Document.get().createHElement(3);
		
		getElement().setId(DOM.createUniqueId());
		getElement().setAttribute("data-role", "collapsible");
		getElement().appendChild(this.text);
		
		this.text.setInnerText(text);

	}
	
	/* (non-Javadoc)
	 * @see com.google.gwt.user.client.ui.Widget#onAttach()
	 */
	@Override
	protected void onAttach() {
		super.onAttach();
		
		// TODO: Really total hack! There's gotta be a better way...
		if(!triggered)
			trigger(this, getElement().getId());
		triggered = true;
		
	}
	
	private void onCollapse() {
		manager.fireEvent(JQMCollapsibleEvent.collapse());
		
	}
	
	private void onExpand() {
		manager.fireEvent(JQMCollapsibleEvent.expand());
		
	}
	
	public void collapse() {
		nativeCollapse(getElement().getId());
	}
	
	public native static void nativeCollapse(String id) /*-{
	
		$wnd.$('#' + id).trigger('collapse');
	
	}-*/;

	private native static void trigger(JQMCollapsible x, String id) /*-{
		
		$wnd.$('#' + id).bind('expand', function () {
			x.@mconta.web.client.common.ui.JQMCollapsible::onExpand()();
		}).bind('collapse', function () {
    		x.@mconta.web.client.common.ui.JQMCollapsible::onCollapse()();
		});
		
	}-*/;

	public HandlerRegistration addClickHandler(JQMCollapsibleHandler handler) {
		return manager.addHandler(JQMCollapsibleEvent.getType(), handler);
		
	} 

	/* (non-Javadoc)
	 * @see com.google.gwt.user.client.ui.Panel#add(com.google.gwt.user.client.ui.Widget)
	 */
	@Override
	public void add(Widget child) {
		add(child, getElement());
	}
	

	@Override
	public void insert(Widget w, int beforeIndex) {
		insert(w, getElement(), beforeIndex, true);
		
	}

}
