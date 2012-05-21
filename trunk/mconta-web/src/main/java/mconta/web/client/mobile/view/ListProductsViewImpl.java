/**
 * 
 */
package mconta.web.client.mobile.view;

import mconta.web.client.mobile.event.MessageEvent;
import mconta.web.client.mobile.event.MessageHandler;
import mconta.web.client.mobile.ui.JQMPage;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.UListElement;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.event.shared.HasHandlers;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

/**
 * EditProductViewImpl class
 * 
 * @author marc
 *
 */
public class ListProductsViewImpl extends Composite implements HasHandlers {
	
	private HandlerManager handlerManager;

	private static MyUiBinder uiBinder = GWT.create(MyUiBinder.class);
	interface MyUiBinder extends UiBinder<Widget, ListProductsViewImpl> {}
	
	@UiField JQMPage page;
	@UiField UListElement list;
	
	public ListProductsViewImpl() {
		initWidget(uiBinder.createAndBindUi(this));
		
		handlerManager = new HandlerManager(this);
		
		String product = "prod1";
		
		StringBuilder build = new StringBuilder();
		
		build.append("<li>");
		build.append("<a href=\"javascript:onClick('" + product + "')\">");
		build.append("<img src=\"img/album-hc.jpg\" /><h3>Warning</h3><p>Hot Chip</p>");
		build.append("<span class=\"ui-li-count\">0</span>");
		build.append("</a>");
		build.append("</li>");
		
		list.setInnerHTML(build.toString());
		setTrigger(this);
	}
	
	public void onClick(String data) {
		handlerManager.fireEvent(new MessageEvent(data));
	}
	
	/**
	 * addClickHandler
	 * 
	 * @param handler
	 * @return
	 */
	public HandlerRegistration addClickHandler(MessageHandler handler) {
		return handlerManager.addHandler(MessageEvent.getType(), handler);
	}
	
	/**
	 * setTrigguer()
	 * 
	 * @param x
	 */
	public native void setTrigger(ListProductsViewImpl x)/*-{
		
		$wnd.onClick = function (data) {
			x.@mconta.web.client.mobile.view.ListProductsViewImpl::onClick(Ljava/lang/String;)(data);
		};

	}-*/;

}
