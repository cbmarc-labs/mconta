/**
 * 
 */
package mconta.web.client.view;

import java.util.Comparator;
import java.util.List;
import java.util.Set;

import mconta.core.persistence.Model;
import mconta.core.persistence.Record;
import mconta.core.persistence.User;
import mconta.web.client.presenter.Presenter;
import mconta.web.client.ui.AppCellTable;

import com.google.gwt.cell.client.NumberCell;
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
	@UiField Button submitButton;
	@UiField Button deleteButton;
	@UiField AppCellTable<Model> appCellTable;
	
	@UiField TextBox usernameField;
	@UiField Button userSubmitButton;
	@UiField Button deleteUserButton;
	@UiField AppCellTable<Model> appCellTableUser;
	
	Column<Model, Number> idColumn;
	Column<Model, String> firstNameColumn;
	
	public MainViewImpl() {		
		initWidget(uiBinder.createAndBindUi(this));
		
		createCellTable();
	}
		
	private void createCellTable() {
		firstNameColumn = new Column<Model, String>(
				new TextCell()) {
			
			@Override
			public String getValue(Model object) {
				return ((Record) object).getTitle();
				
			}
			
		};
		
		idColumn = new Column<Model, Number>(
				new NumberCell()) {
			
			@Override
			public Number getValue(Model object) {
				return ((Record) object).getId();
				
			}
			
		};
		
		firstNameColumn.setSortable(true);
		appCellTable.listHandler.setComparator(
				firstNameColumn, new Comparator<Model>() {
			
			public int compare(Model o1, Model o2) {
				return ((Record) o1).getTitle().compareTo(
						((Record) o2).getTitle());
				
			}
			
		});
		
		appCellTable.cellTable.addColumn(idColumn, "ID");
		appCellTable.cellTable.addColumn(firstNameColumn, "First Name");
		// TODO default sorting dont work?
		//appCellTable.cellTable.getColumnSortList().push(firstNameColumn);
		
		
		
		Column<Model, String> usernameColumn = new Column<Model, String>(new TextCell()) {

			@Override
			public String getValue(Model object) {
				return ((User) object).getUsername();
			}};
		
		appCellTableUser.cellTable.addColumn(usernameColumn, "Username");
		
	}

	@UiHandler("submitButton")
	void acceptButtonClick(ClickEvent e) {
		presenter.onSubmitButtonClicked();
		
	}
	
	@UiHandler("userSubmitButton")
	void userSubmitButtonClick(ClickEvent e) {
		presenter.onSubmitUserButtonClicked();
		
	}
	
	@UiHandler("deleteButton")
	void deleteButtonClick(ClickEvent e) {
		Set<Model> selectedSet = appCellTable.selectionModel.getSelectedSet();
		
		presenter.onDeleteButtonClicked(selectedSet);
	}
	
	@UiHandler("deleteUserButton")
	void deleteUserButtonClick(ClickEvent e) {
		Set<Model> selectedSet = appCellTableUser.selectionModel.getSelectedSet();
		
		presenter.onDeleteButtonClicked(selectedSet);
	}

	public void setData(List<Model> data) {		
		appCellTable.setData(data);
		
	}

	public void setPresenter(Presenter presenter) {
		this.presenter = presenter;
		
	}

	public TextBox getTextField() {
		return titleField;
	}

	public TextBox getUsernameTextField() {
		return this.usernameField;
	}

	public void setUserData(List<Model> data) {
		this.appCellTableUser.setData(data);
		
	}

}
