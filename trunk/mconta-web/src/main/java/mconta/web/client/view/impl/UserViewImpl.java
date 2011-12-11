/**
 * 
 */
package mconta.web.client.view.impl;

import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Set;

import mconta.core.persistence.Model;
import mconta.core.persistence.User;
import mconta.web.client.presenter.CrudPresenter;
import mconta.web.client.presenter.Presenter;
import mconta.web.client.ui.AppCellTable;
import mconta.web.client.view.CrudView;

import com.google.gwt.cell.client.DateCell;
import com.google.gwt.cell.client.NumberCell;
import com.google.gwt.cell.client.TextCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.CellPreviewEvent;
import com.google.gwt.view.client.CellPreviewEvent.Handler;

/**
 * @author Marc
 *
 */
public class UserViewImpl extends Composite implements CrudView, Editor<User> {

	private static MainUiBinder uiBinder = GWT.create(MainUiBinder.class);
	
	CrudPresenter presenter;

	interface MainUiBinder extends UiBinder<Widget, UserViewImpl> {}

	@UiField TextBox use_name;
	@UiField ListBox use_usergroup;
	@UiField Button submitButton;
	@UiField Button deleteButton;
	@UiField AppCellTable<Model> appCellTable;
	
	Column<Model, Number> use_id_column;
	Column<Model, String> use_name_column;
	Column<Model, String> aud_createdBy_column;
	Column<Model, Date> aud_createdOn_column;
	Column<Model, String> aud_modifiedBy_column;
	Column<Model, Date> aud_modifiedOn_column;
	
	DateTimeFormat dateFormat;
	
	public UserViewImpl() {		
		initWidget(uiBinder.createAndBindUi(this));
		
		dateFormat = DateTimeFormat.getFormat("dd/MM/yyyy HH:mm:ss");
		
		createCellTable();
	}
		
	private void createCellTable() {
		use_id_column = new Column<Model, Number>(new NumberCell()) {
			
			@Override
			public Number getValue(Model object) {
				return ((User) object).getUse_id();
				
			}};
		
		use_name_column = new Column<Model, String>(new TextCell()) {
			
			@Override
			public String getValue(Model object) {
				return ((User) object).getUse_name();
				
			}};
		
		aud_createdBy_column = new Column<Model, String>(new TextCell()){

			@Override
			public String getValue(Model object) {
				return ((User) object).getAud_createdBy();
			}};
		
		aud_createdOn_column = new Column<Model, Date>(new DateCell(dateFormat)){

			@Override
			public Date getValue(Model object) {
				return ((User) object).getAud_createdOn();
			}};
			
		aud_modifiedBy_column = new Column<Model, String>(new TextCell()){

			@Override
			public String getValue(Model object) {
				return ((User) object).getAud_modifiedBy();
			}};
		
		aud_modifiedOn_column = new Column<Model, Date>(new DateCell(dateFormat)){

			@Override
			public Date getValue(Model object) {
				return ((User) object).getAud_modifiedOn();
			}};
		
		
		use_name_column.setSortable(true);
		appCellTable.listHandler.setComparator(
				use_name_column, new Comparator<Model>() {
			
			public int compare(Model o1, Model o2) {
				return ((User) o1).getUse_name().compareTo(
						((User) o2).getUse_name());
				
			}
			
		});
		
		appCellTable.cellTable.addColumn(use_id_column, "ID");
		appCellTable.cellTable.addColumn(use_name_column, "Name");
		appCellTable.cellTable.addColumn(aud_createdBy_column, "Created By");
		appCellTable.cellTable.addColumn(aud_createdOn_column, "Created On");
		appCellTable.cellTable.addColumn(aud_modifiedBy_column, "Modified By");
		appCellTable.cellTable.addColumn(aud_modifiedOn_column, "Modified On");
		
		
		
		
		
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
		this.presenter = (CrudPresenter) presenter;
		
	}

}
