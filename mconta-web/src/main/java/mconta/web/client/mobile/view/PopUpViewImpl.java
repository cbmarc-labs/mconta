package mconta.web.client.mobile.view;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.UIObject;

public class PopUpViewImpl extends UIObject {

	private static PopUpViewImplUiBinder uiBinder = GWT
			.create(PopUpViewImplUiBinder.class);

	interface PopUpViewImplUiBinder extends UiBinder<Element, PopUpViewImpl> {
	}

	public PopUpViewImpl() {
		setElement(uiBinder.createAndBindUi(this));
		
		jqmInit();
	}
	
	public native void jqmInit() /*-{
		$wnd.$("#popupviewimpl").page();
	}-*/;

}
