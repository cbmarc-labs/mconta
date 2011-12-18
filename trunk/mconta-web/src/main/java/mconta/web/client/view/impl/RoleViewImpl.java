/**
 * 
 */
package mconta.web.client.view.impl;

import java.util.Comparator;
import java.util.List;
import java.util.Set;

import mconta.domain.model.Model;
import mconta.domain.model.Role;
import mconta.domain.model.User;
import mconta.web.client.presenter.CrudPresenter;
import mconta.web.client.presenter.Presenter;
import mconta.web.client.ui.AppCellTable;
import mconta.web.client.view.CrudView;

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
public class RoleViewImpl extends Composite implements CrudView, Editor<Role> {

	private static MainUiBinder uiBinder = GWT.create(MainUiBinder.class);
	
	CrudPresenter presenter;

	interface MainUiBinder extends UiBinder<Widget, RoleViewImpl> {}

	@UiField TextBox rol_name;
	@UiField Button submitButton;
	@UiField Button deleteButton;
	@UiField AppCellTable<Model> appCellTable;
	
	Column<Model, Number> rol_id_column;
	Column<Model, String> rol_name_column;
	Column<Model, String> rol_users_column;
	
	DateTimeFormat dateFormat;
	
	public RoleViewImpl() {		
		initWidget(uiBinder.createAndBindUi(this));
		
		dateFormat = DateTimeFormat.getFormat("dd/MM/yyyy HH:mm:ss");
		
		createCellTable();
	}
		
	private void createCellTable() {
		rol_id_column = new Column<Model, Number>(new NumberCell()) {
			
			@Override
			public Number getValue(Model object) {
				return ((Role) object).getRol_id();
				
			}};
		
		rol_name_column = new Column<Model, String>(new TextCell()) {
			
			@Override
			public String getValue(Model object) {
				return ((Role) object).getRol_name();
				
			}};
			
		rol_users_column = new Column<Model, String>(new TextCell()) {
			
			@Override
			public String getValue(Model object) {
				StringBuilder build = new StringBuilder();
				List<User> users = ((Role)object).getRol_users();
				
				for(User user : users) {
					build.append(user.getUse_name() + " ");
				}
				
				return build.toString();
				
			}};
		
		rol_name_column.setSortable(true);
		appCellTable.listHandler.setComparator(
				rol_name_column, new Comparator<Model>() {
			
			public int compare(Model o1, Model o2) {
				return ((Role) o1).getRol_name().compareTo(
						((Role) o2).getRol_name());
				
			}
			
		});
		
		appCellTable.cellTable.addColumn(rol_id_column, "ID");
		appCellTable.cellTable.addColumn(rol_name_column, "Name");
		appCellTable.cellTable.addColumn(rol_users_column, "Users");
		
		
		
		
		
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
