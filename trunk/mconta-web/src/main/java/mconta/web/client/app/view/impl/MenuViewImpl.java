/**
 * 
 */
package mconta.web.client.app.view.impl;

import mconta.web.client.app.view.View;
import mconta.web.client.common.event.ChangePageEvent;
import mconta.web.client.common.event.EventBus;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author Marc
 *
 */
public class MenuViewImpl extends Composite implements View {

	private static MenuUiBinder uiBinder = GWT.create(MenuUiBinder.class);
	interface MenuUiBinder extends UiBinder<Widget, MenuViewImpl> {}
	
	@UiField Anchor menu1;
	
	public MenuViewImpl() {		
		initWidget(uiBinder.createAndBindUi(this));
	}
	
	@UiHandler("menu1")
	void handleClick(ClickEvent e) {
		EventBus.getEventBus().fireEvent(ChangePageEvent.list());
	  }

	@Override
	public void go(HasWidgets widget) {
		widget.clear();
		widget.add(this);
		
	}

}
