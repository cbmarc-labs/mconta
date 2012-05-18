package mconta.web.client.mobile.view;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.DivElement;
import com.google.gwt.dom.client.Element;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.UIObject;

public class JQMPage extends UIObject {

	private static JQMPageUiBinder uiBinder = GWT.create(JQMPageUiBinder.class);

	interface JQMPageUiBinder extends UiBinder<Element, JQMPage> {
	}
	
	static String pageid = "page1";
	
	@UiField DivElement page;
	@UiField DivElement jqmpagecontent;

	public JQMPage() {
		setElement(uiBinder.createAndBindUi(this));
		
		page.setId(pageid);
		jqmpagecontent.setInnerHTML("<a href=\"#page2\" data-role=\"button\" data-icon=\"star\">Star button</a>");
		
		jqmInit(pageid);
	}
	
	public native void jqmInit(String pageid) /*-{
		
		$wnd.$("#" + pageid).page();
		
	}-*/;

}
