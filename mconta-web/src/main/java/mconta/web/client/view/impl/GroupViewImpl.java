/**
 * 
 */
package mconta.web.client.view.impl;

import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Set;

import mconta.core.persistence.UserGroup;
import mconta.core.persistence.Model;
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
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.CellPreviewEvent;
import com.google.gwt.view.client.CellPreviewEvent.Handler;

/**
 * @author Marc
 *
 */
public class GroupViewImpl extends Composite implements CrudView, Editor<UserGroup> {

	private static MainUiBinder uiBinder = GWT.create(MainUiBinder.class);
	
	CrudPresenter presenter;

	interface MainUiBinder extends UiBinder<Widget, GroupViewImpl> {}

	@UiField TextBox ugr_name;
	@UiField Button submitButton;
	@UiField Button deleteButton;
	@UiField AppCellTable<Model> appCellTable;
	
	Column<Model, Number> ugr_id_column;
	Column<Model, String> ugr_name_column;
	Column<Model, String> aud_createdBy_column;
	Column<Model, Date> aud_createdOn_column;
	Column<Model, String> aud_modifiedBy_column;
	Column<Model, Date> aud_modifiedOn_column;
	
	DateTimeFormat dateFormat;
	
	public GroupViewImpl() {		
		initWidget(uiBinder.createAndBindUi(this));
		
		dateFormat = DateTimeFormat.getFormat("dd/MM/yyyy HH:mm:ss");
		
		createCellTable();
	}
		
	private void createCellTable() {
		ugr_id_column = new Column<Model, Number>(new NumberCell()) {
			
			@Override
			public Number getValue(Model object) {
				return ((UserGroup) object).getUgr_id();
				
			}};
		
		ugr_name_column = new Column<Model, String>(new TextCell()) {
			
			@Override
			public String getValue(Model object) {
				return ((UserGroup) object).getUgr_name();
				
			}};
		
		aud_createdBy_column = new Column<Model, String>(new TextCell()){

			@Override
			public String getValue(Model object) {
				return ((UserGroup) object).getAud_createdBy();
			}};
		
		aud_createdOn_column = new Column<Model, Date>(new DateCell(dateFormat)){

			@Override
			public Date getValue(Model object) {
				return ((UserGroup) object).getAud_createdOn();
			}};
			
		aud_modifiedBy_column = new Column<Model, String>(new TextCell()){

			@Override
			public String getValue(Model object) {
				return ((UserGroup) object).getAud_modifiedBy();
			}};
		
		aud_modifiedOn_column = new Column<Model, Date>(new DateCell(dateFormat)){

			@Override
			public Date getValue(Model object) {
				return ((UserGroup) object).getAud_modifiedOn();
			}};
		
		
		ugr_name_column.setSortable(true);
		appCellTable.listHandler.setComparator(
				ugr_name_column, new Comparator<Model>() {
			
			public int compare(Model o1, Model o2) {
				return ((UserGroup) o1).getUgr_name().compareTo(
						((UserGroup) o2).getUgr_name());
				
			}
			
		});
		
		appCellTable.cellTable.addColumn(ugr_id_column, "ID");
		appCellTable.cellTable.addColumn(ugr_name_column, "Name");
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
