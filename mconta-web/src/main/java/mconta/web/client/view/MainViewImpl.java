/**
 * 
 */
package mconta.web.client.view;

import java.util.Comparator;
import java.util.List;

import mconta.core.persistence.Record;
import mconta.web.client.presenter.Presenter;
import mconta.web.client.ui.AppDataGrid;

import com.google.gwt.cell.client.TextCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
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

	@UiField TextBox titleField;
	@UiField Button acceptButton;
	@UiField AppDataGrid<Record> appDataGrid;
	
	public MainViewImpl() {		
		initWidget(uiBinder.createAndBindUi(this));
		
		createDataGrid();
	}
		
	private void createDataGrid() {
		Column<Record, String> firstNameColumn = new Column<Record, String>(
				new TextCell()) {
			
			@Override
			public String getValue(Record object) {
				return ((Record) object).getTitle();
				
			}
			
		};
		
		firstNameColumn.setSortable(true);
		appDataGrid.listHandler.setComparator(
				firstNameColumn, new Comparator<Record>() {
			
			public int compare(Record o1, Record o2) {
				return ((Record) o1).getTitle().compareTo(
						((Record) o2).getTitle());
				
			}
			
		});
		
		appDataGrid.cellTable.addColumn(firstNameColumn, "First Name");
	}

	@UiHandler("acceptButton")
	void handleClick(ClickEvent e) {
		presenter.onButtonClicked();
	}

	public void setData(List<Record> data) {		
		appDataGrid.setData(data);
		
	}

	public void setPresenter(Presenter presenter) {
		this.presenter = presenter;
		
	}

	public TextBox getTextField() {
		return titleField;
	}

}
