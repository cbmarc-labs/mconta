/**
 * 
 */
package mconta.web.client.view;

import mconta.core.persistence.Record;
import mconta.web.client.presenter.Presenter;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.cellview.client.DataGrid;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author Marc
 *
 */
public class MainViewImpl extends Composite implements MainView {

	private static MainUiBinder uiBinder = GWT.create(MainUiBinder.class);
	
	Presenter presenter;

	interface MainUiBinder extends UiBinder<Widget, MainViewImpl> {
	}
	
	public MainViewImpl() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	@UiField TextBox titleField;
	@UiField Button acceptButton;
	@UiField HTMLPanel listGrid;
	//@UiField(provided = true) DataGrid<Record> contactList;

	public MainViewImpl(String firstName) {
		initWidget(uiBinder.createAndBindUi(this));
	}
	
	@UiHandler("acceptButton")
	void handleClick(ClickEvent e) {
		presenter.onButtonClicked();
	}

	public void setData(String data) {
		listGrid.clear();
		listGrid.add(new HTMLPanel(data));
		
	}

	public void setPresenter(Presenter presenter) {
		this.presenter = presenter;
		
	}

	public TextBox getTextField() {
		return titleField;
	}

}
