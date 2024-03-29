/**
 * 
 */
package mconta.web.client.app.view.impl;

import mconta.web.client.app.presenter.impl.ListProductsPresenter;
import mconta.web.client.common.event.ChangePageEvent;
import mconta.web.client.common.event.EventBus;
import mconta.web.client.common.ui.JQMList;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author Marc
 *
 */
public class ListProductsViewImpl extends Composite implements ListProductsPresenter.Display {

	private static MainUiBinder uiBinder = GWT.create(MainUiBinder.class);
	interface MainUiBinder extends UiBinder<Widget, ListProductsViewImpl> {}
	
	@UiField JQMList list;
	
	public ListProductsViewImpl() {		
		initWidget(uiBinder.createAndBindUi(this));
		
	}
	
	@UiHandler("newButton")
	public void newButtonClick(ClickEvent event) {
		EventBus.getEventBus().fireEvent(ChangePageEvent.edit(null));
		
	}

	@Override
	public JQMList getList() {
		return list;
	}

}
