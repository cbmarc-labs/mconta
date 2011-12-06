/**
 * 
 */
package mconta.web.client.view;

import java.util.List;

import mconta.core.persistence.Record;
import mconta.web.client.presenter.Presenter;
import mconta.web.client.ui.MyPaginationDataGrid;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
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
		
		initTable();
	}
	
	MyPaginationDataGrid<Record> grid;

	@UiField TextBox titleField;
	@UiField Button acceptButton;
	@UiField HTMLPanel listGrid;
		
	private void initTable() {
		grid = new MyPaginationDataGrid<Record>();
		grid.setHeight("300px");
		
	}

	@UiHandler("acceptButton")
	void handleClick(ClickEvent e) {
		presenter.onButtonClicked();
	}

	public void setData(List<Record> data) {
		listGrid.clear();
		
		grid.setDataList(data);
		
		listGrid.add(grid);
	}

	public void setPresenter(Presenter presenter) {
		this.presenter = presenter;
		
	}

	public TextBox getTextField() {
		return titleField;
	}

}
