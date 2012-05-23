/**
 * 
 */
package mconta.web.client.app.presenter.impl;

import java.util.List;
import java.util.Set;

import mconta.domain.model.Model;
import mconta.domain.model.Role;
import mconta.domain.model.User;
import mconta.web.client.app.event.AppEventBus;
import mconta.web.client.app.event.RoleEvent;
import mconta.web.client.app.event.RoleEvent.RoleHandler;
import mconta.web.client.app.event.UserEvent;
import mconta.web.client.app.presenter.CrudPresenter;
import mconta.web.client.app.rpc.AppAsyncCallback;
import mconta.web.client.app.ui.AppCellTable;
import mconta.web.client.app.view.CrudView;
import mconta.web.client.app.view.View;
import mconta.web.client.app.view.impl.UserViewImpl;
import mconta.web.client.common.rpc.CrudService;
import mconta.web.client.common.rpc.CrudServiceAsync;

import com.google.gwt.cell.client.FieldUpdater;
import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.client.ui.HasWidgets;

/**
 * @author Marc
 *
 */
public class UserPresenter implements CrudPresenter, RoleHandler {
	
	public interface UserView extends CrudView {
		
		public void setRoleData(List<Model> list);
		public Column<Model, Boolean> getUse_enabled_column();
		public AppCellTable<Model> getAppCellTable();

	}
	
	private final CrudServiceAsync service = GWT.create(CrudService.class);
	interface Driver extends SimpleBeanEditorDriver<User, UserViewImpl> {}
	
	private AppEventBus eventBus = AppEventBus.getEventBus();
	
	Driver driver = GWT.create(Driver.class);
	
	User entity;
	
	private final UserView view;
	
	public UserPresenter(View view) {
		this.view = (UserView) view;
		
		entity = new User();
		
		driver.initialize((UserViewImpl) view);
		driver.edit(entity);
		
		eventBus.addHandler(RoleEvent.getType(), this);
		
		bind();
		
	}
	
	public void bind() {
		view.setPresenter(this);
		
		view.getUse_enabled_column().setFieldUpdater(
				new FieldUpdater<Model, Boolean>(){

			public void update(int index, Model object, Boolean value) {
				((User)object).setUse_enabled(value);
				
				doUpdate(object);
				
			}});
		
	}
	
	public void doUpdate(Model object) {		
		service.saveOrUpdate(object, new AppAsyncCallback<Void>(){

			public void onSuccess(Void result) {
				doLoad();
				
			}});
		
	}

	public void go(HasWidgets container) {
		container.clear();
		container.add(view.asWidget());
		
		doLoad();
		
	}
	
	public void doLoad() {
		service.getAll(Role.class.getName(), new AppAsyncCallback<List<Model>>(){

			public void onSuccess(List<Model> result) {
				view.setRoleData(result);
				
			}});
		
		service.getAll(User.class.getName(), new AppAsyncCallback<List<Model>>(){

			public void onSuccess(List<Model> result) {
				view.setData(result);
				
			}});
		
	}

	public void doSave() {
		entity = driver.flush();
		
		service.saveOrUpdate(entity, new AppAsyncCallback<Void>(){

			public void onSuccess(Void result) {
				doNew();
				doLoad();
				eventBus.fireEvent(new UserEvent());
				
			}});
	}
	
	public void doNew() {
		entity = new User();
		driver.edit(entity);
		
	}

	public void doDelete(Set<Model> selectedSet) {		
		service.deleteAll(selectedSet, new AppAsyncCallback<Void>(){

			public void onSuccess(Void result) {
				doLoad();
				
				eventBus.fireEvent(new UserEvent());
				
			}});
		
	}

	public void doEdit(Model model) {
		entity = (User) model;		
		driver.edit((User) model);
		
	}

	public void onRoleEvent() {
		doLoad();
		
	}

}
