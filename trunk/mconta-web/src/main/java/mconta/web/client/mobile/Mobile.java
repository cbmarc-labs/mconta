package mconta.web.client.mobile;

import mconta.web.client.common.event.ChangePageEvent;
import mconta.web.client.common.event.ChangePageHandler;
import mconta.web.client.common.event.EventBus;
import mconta.web.client.mobile.view.ListProductsViewImpl;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * Mobile is the main mobile program
 * 
 * @author marc
 */
public class Mobile implements EntryPoint {//, ChangePageHandler {
	
	ListProductsViewImpl listProducts = new ListProductsViewImpl();
	
	/**
	 * onModuleLoad()
	 */
	public void onModuleLoad() {
		//EventBus.getEventBus().addHandler(ChangePageEvent.getType(), this);
		
		RootPanel.get().add(listProducts);
		listProducts.go();
	}

	/*@Override
	public void onChangePage(String page) {
		JQMChangePage(page);
		
	}*/
	
	/**
	 * jqmChangePage() is the native method for change page in jQuery Mobile
	 * 
	 * @param page
	 */
	native void JQMChangePage(String page) /*-{
		
		$wnd.$.mobile.changePage("#" + page, "pop", false, true);
		
	}-*/;
  
}
