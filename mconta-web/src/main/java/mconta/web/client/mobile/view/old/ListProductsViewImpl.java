package mconta.web.client.mobile.view.old;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;

/**
 * ListProductsViewImpl class
 * 
 * @author marc
 */
public class ListProductsViewImpl extends Composite {

	private static MyUiBinder uiBinder = GWT.create(MyUiBinder.class);
	interface MyUiBinder extends UiBinder<Element, ListProductsViewImpl> {}
	
	static String pageid = "listproducts";
	
	//@UiField DivElement page;
	//@UiField AnchorElement pepe;

	public ListProductsViewImpl() {
		setElement(uiBinder.createAndBindUi(this));
		
		getElement().setId(pageid);
		
		jqmInit(pageid);
	}
	
	public native void jqmInit(String pageid) /*-{
		
		$wnd.$("#" + pageid).page();
		
	}-*/;

}
