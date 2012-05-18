package mconta.web.client.mobile.view;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.UIObject;

public class EditViewImpl extends UIObject {

	private static EditViewImplUiBinder uiBinder = GWT
			.create(EditViewImplUiBinder.class);

	interface EditViewImplUiBinder extends UiBinder<Element, EditViewImpl> {}

	public EditViewImpl() {
		setElement(uiBinder.createAndBindUi(this));
		
		jqmInit("edit");
	}
	
	public native void jqmInit(String pageid) /*-{
		
		$wnd.$("#" + pageid).page();
		
	}-*/;

}
