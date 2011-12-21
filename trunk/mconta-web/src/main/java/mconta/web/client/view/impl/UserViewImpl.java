/**
 * 
 */
package mconta.web.client.view.impl;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

import mconta.domain.model.Model;
import mconta.domain.model.Role;
import mconta.domain.model.User;
import mconta.web.client.event.AppCellTableEvent.AppCellTableHandler;
import mconta.web.client.i18n.AppConstants;
import mconta.web.client.presenter.CrudPresenter;
import mconta.web.client.presenter.Presenter;
import mconta.web.client.presenter.impl.UserPresenter.UserView;
import mconta.web.client.ui.AppCellTable;
import mconta.web.client.ui.ObjectListBox;

import com.google.gwt.cell.client.CheckboxCell;
import com.google.gwt.cell.client.NumberCell;
import com.google.gwt.cell.client.TextCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.editor.client.Editor;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.CellPreviewEvent;
import com.google.gwt.view.client.CellPreviewEvent.Handler;

/**
 * @author Marc
 *
 */
public class UserViewImpl extends Composite 
		implements UserView, Editor<User>, AppCellTableHandler {

	private static MainUiBinder uiBinder = GWT.create(MainUiBinder.class);
	AppConstants i18n = GWT.create(AppConstants.class);
	
	CrudPresenter presenter;

	interface MainUiBinder extends UiBinder<Widget, UserViewImpl> {}

	@UiField TextBox use_name;
	@UiField TextBox use_password;
	@UiField(provided=true) ObjectListBox<Model> use_roles;
	@UiField Button submitButton;
	@UiField AppCellTable<Model> appCellTable;
	
	Column<Model, Number> use_id_column;
	Column<Model, String> use_name_column;
	Column<Model, Boolean> use_enabled_column;
	Column<Model, String> use_roles_column;
	
	DateTimeFormat dateFormat;
	
	public UserViewImpl() {
		use_roles = new ObjectListBox<Model>(true);
		use_roles.setVisibleItemCount(5);
		
		initWidget(uiBinder.createAndBindUi(this));
		
		dateFormat = DateTimeFormat.getFormat("dd/MM/yyyy HH:mm:ss");
		
		appCellTable.addAppCellTableHandler(this);
		
		createCellTable();
	}
		
	private void createCellTable() {
		use_id_column = new Column<Model, Number>(new NumberCell()) {
			
			@Override
			public Number getValue(Model object) {
				return ((User) object).getUse_id();
				
			}};
		appCellTable.cellTable.setColumnWidth(use_id_column, 6.0, Unit.EM);
		
		use_name_column = new Column<Model, String>(new TextCell()) {
			
			@Override
			public String getValue(Model object) {
				return ((User) object).getUse_name();
				
			}};
		appCellTable.cellTable.setColumnWidth(use_name_column, 20.0, Unit.EM);
			
		use_enabled_column = new Column<Model, Boolean>(
				new CheckboxCell(true, false)){

			@Override
			public Boolean getValue(Model object) {
				return ((User)object).getUse_enabled();
				
			}};
		appCellTable.cellTable.setColumnWidth(use_enabled_column, 6.0, Unit.EM);
		use_enabled_column.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		
		use_roles_column = new Column<Model, String>(new TextCell()) {
			
			@Override
			public String getValue(Model object) {
				StringBuilder build = new StringBuilder();
				List<Role> roles = ((User)object).getUse_roles();
				
				String delim = "";
				for(Role role : roles) {
					build.append(delim).append(role.getRol_name());
					delim = ", ";
				}
				
				return build.toString();
				
			}};
		
		
		use_name_column.setSortable(true);
		appCellTable.listHandler.setComparator(
				use_name_column, new Comparator<Model>() {
			
			public int compare(Model o1, Model o2) {
				return ((User) o1).getUse_name().compareTo(
						((User) o2).getUse_name());
				
			}
			
		});
		
		appCellTable.cellTable.addColumn(use_id_column, i18n.cellTable_column_use_id());
		appCellTable.cellTable.addColumn(use_name_column, i18n.cellTable_column_use_name());
		appCellTable.cellTable.addColumn(use_enabled_column, i18n.cellTable_column_use_enabled());
		appCellTable.cellTable.addColumn(use_roles_column, i18n.cellTable_column_use_roles());
		
		
		
		
		
		// TODO default sorting dont work?
		//appCellTable.cellTable.getColumnSortList().push(firstNameColumn);
		
		appCellTable.cellTable.addCellPreviewHandler(new Handler<Model>(){

			public void onCellPreview(CellPreviewEvent<Model> event) {
				boolean isClick = event.getNativeEvent().getType().equals("click");
				
				if (event.getColumn() != 0 && event.getColumn() != 3 && isClick) {
					presenter.doEdit(event.getValue());
					
				}
				
			}});
		
	}

	@UiHandler("submitButton")
	void submitButtonClick(ClickEvent e) {		
		presenter.doSave();
		
	}

	public void setData(List<Model> data) {		
		appCellTable.setData(data);
		
	}

	public void setPresenter(Presenter presenter) {
		this.presenter = (CrudPresenter) presenter;
		
	}

	public void setRoleData(List<Model> list) {		
		use_roles.clear();
		for(Model role: list) {
			use_roles.addItem(((Role)role).getRol_name(), role);
		}
		
	}

	public List<Role> getRoleData() {
		List<Role> roles = new ArrayList<Role>();
		
		if(use_roles.getSelectedIndex() != -1) {
			Role role = (Role) use_roles.getItem(use_roles.getSelectedIndex());
		
			roles.add(role);
		}
		
		return roles;
	}

	public void onDeleteButtonClicked() {
		if(Window.confirm(i18n.common_areyousure())) {
			Set<Model> selectedSet = appCellTable.selectionModel.getSelectedSet();
		
			presenter.doDelete(selectedSet);
		}
		
	}

	public void onAddNewButtonClicked() {
		Window.alert("onAddNewButtonClicked");
		
	}

}
