/**
 * 
 */
package mconta.web.client.view;

import java.util.Comparator;
import java.util.List;
import java.util.Set;

import mconta.core.persistence.Model;
import mconta.core.persistence.Record;
import mconta.web.client.presenter.Presenter;
import mconta.web.client.ui.AppCellTable;

import com.google.gwt.cell.client.TextCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.CellPreviewEvent;
import com.google.gwt.view.client.CellPreviewEvent.Handler;

/**
 * @author Marc
 *
 */
public class MainViewImpl extends Composite implements MainView, Editor<Record> {

	private static MainUiBinder uiBinder = GWT.create(MainUiBinder.class);
	
	Presenter presenter;

	interface MainUiBinder extends UiBinder<Widget, MainViewImpl> {}

	@UiField TextBox rec_title;
	@UiField Button submitButton;
	@UiField Button deleteButton;
	@UiField AppCellTable<Model> appCellTable;
	
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
				return ((Record) object).getRec_title();
				
			}
			
		};
		
		firstNameColumn.setSortable(true);
		appCellTable.listHandler.setComparator(
				firstNameColumn, new Comparator<Model>() {
			
			public int compare(Model o1, Model o2) {
				return ((Record) o1).getRec_title().compareTo(
						((Record) o2).getRec_title());
				
			}
			
		});
		
		appCellTable.cellTable.addColumn(firstNameColumn, "First Name");
		// TODO default sorting dont work?
		//appCellTable.cellTable.getColumnSortList().push(firstNameColumn);
		
		appCellTable.cellTable.addCellPreviewHandler(new Handler<Model>(){

			public void onCellPreview(CellPreviewEvent<Model> event) {
				boolean isClick = event.getNativeEvent().getType().equals("click");
				
				if (event.getColumn() != 0 && isClick) {
					presenter.doEdit(event.getValue());
					
				}
				
			}});
		
	}

	@UiHandler("submitButton")
	void submitButtonClick(ClickEvent e) {
		presenter.doSave();
		
	}
	
	@UiHandler("deleteButton")
	void deleteButtonClick(ClickEvent e) {
		Set<Model> selectedSet = appCellTable.selectionModel.getSelectedSet();
		
		presenter.doDelete(selectedSet);
	}

	public void setData(List<Model> data) {		
		appCellTable.setData(data);
		
	}

	public void setPresenter(Presenter presenter) {
		this.presenter = presenter;
		
	}

}
