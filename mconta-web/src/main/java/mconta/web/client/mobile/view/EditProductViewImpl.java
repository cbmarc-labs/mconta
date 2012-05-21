/**
 * 
 */
package mconta.web.client.mobile.view;

import mconta.web.client.mobile.ui.JQMPage;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

/**
 * EditProductViewImpl class
 * 
 * @author marc
 *
 */
public class EditProductViewImpl extends Composite {

	private static MyUiBinder uiBinder = GWT.create(MyUiBinder.class);
	interface MyUiBinder extends UiBinder<Widget, EditProductViewImpl> {}
	
	@UiField JQMPage page;
	@UiField Element quantity;
	
	Integer counter = 1;
	
	public EditProductViewImpl() {
		initWidget(uiBinder.createAndBindUi(this));
	}
	
	@UiHandler("button_minus")
	void minusClick(ClickEvent e) {
		if(counter > 0 ) counter --;
		quantity.setInnerText(counter.toString());
	}
	
	@UiHandler("button_plus")
	void plusClick(ClickEvent e) {
		if(counter < 50) counter ++;
		quantity.setInnerText(counter.toString());
	}

}
