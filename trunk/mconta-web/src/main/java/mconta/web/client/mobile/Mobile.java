package mconta.web.client.mobile;

import mconta.web.client.mobile.event.MessageHandler;
import mconta.web.client.mobile.view.EditProductViewImpl;
import mconta.web.client.mobile.view.ListProductsViewImpl;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * Mobile is the main mobile program
 * 
 * @author marc
 */
public class Mobile implements EntryPoint {
	
	ListProductsViewImpl listProducts = new ListProductsViewImpl();
	Widget editProduct = new EditProductViewImpl();
	
	/**
	 * onModuleLoad()
	 */
	public void onModuleLoad() {		
		RootPanel.get().add(listProducts);
		RootPanel.get().add(editProduct);
		
		String location = Window.Location.getHash();
		if(location.isEmpty())
			location = listProducts.getElement().getId();
		
		JQMChangePage(location);
		
		bind();
	}
	
	public void bind() {
		listProducts.addClickHandler(new MessageHandler(){

			@Override
			public void onClick(String msg) {
				JQMChangePage(editProduct.getElement().getId());
				
			}});
	}
	
	/**
	 * jqmChangePage() is the native method for change page in jQuery Mobile
	 * 
	 * @param page
	 */
	native void JQMChangePage(String page) /*-{
		
		$wnd.$.mobile.changePage("#" + page, "pop", false, true);
		
	}-*/;
  
}
