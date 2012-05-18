package mconta.web.client.mobile.view;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.UIObject;

public class LoginViewImpl extends UIObject {

	private static LoginViewImplUiBinder uiBinder = GWT
			.create(LoginViewImplUiBinder.class);

	interface LoginViewImplUiBinder extends UiBinder<Element, LoginViewImpl> {
	}

	public LoginViewImpl() {
		setElement(uiBinder.createAndBindUi(this));
		
		jqmInit();
	}
	
	public native void jqmInit() /*-{
		
		$wnd.$("#page2").page();
		
	}-*/;

}
