package mconta.web.client.mobile.view.old;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.DivElement;
import com.google.gwt.dom.client.Element;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.UIObject;

/**
 * EditProductViewImpl class
 * 
 * @author marc
 */
public class EditProductViewImpl extends UIObject {

	private static MyUiBinder uiBinder = GWT.create(MyUiBinder.class);
	interface MyUiBinder extends UiBinder<Element, EditProductViewImpl> {}
	
	static String pageid = "editproduct";
	
	@UiField DivElement page;

	public EditProductViewImpl() {
		setElement(uiBinder.createAndBindUi(this));
		
		page.setId(pageid);
		
		jqmInit(pageid);
	}
	
	public native void jqmInit(String pageid) /*-{
		
		$wnd.$("#" + pageid).page();
		
	}-*/;

}
