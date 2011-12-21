/**
 * 
 */
package mconta.web.client.presenter.impl;

import java.util.List;
import java.util.Set;

import mconta.domain.model.Model;
import mconta.domain.model.Role;
import mconta.domain.model.User;
import mconta.web.client.event.AppEventBus;
import mconta.web.client.event.RoleEvent;
import mconta.web.client.event.RoleEvent.RoleHandler;
import mconta.web.client.event.UserEvent;
import mconta.web.client.presenter.CrudPresenter;
import mconta.web.client.rpc.AppAsyncCallback;
import mconta.web.client.rpc.CrudService;
import mconta.web.client.rpc.CrudServiceAsync;
import mconta.web.client.view.CrudView;
import mconta.web.client.view.View;
import mconta.web.client.view.impl.UserViewImpl;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.google.gwt.user.client.ui.HasWidgets;

/**
 * @author Marc
 *
 */
public class UserPresenter implements CrudPresenter, RoleHandler {
	
	public interface UserView extends CrudView {
		
		public void setRoleData(List<Model> list);
		public List<Role> getRoleData();

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
		entity.setUse_roles(view.getRoleData());
		
		service.saveOrUpdate(entity, new AppAsyncCallback<Void>(){

			public void onSuccess(Void result) {
				entity = new User();
				driver.edit(entity);
				
				doLoad();
				
				eventBus.fireEvent(new UserEvent());
				
			}});
	}

	public void doDelete(Set<Model> selectedSet) {		
		service.deleteAll(selectedSet, new AppAsyncCallback<Void>(){

			public void onSuccess(Void result) {
				doLoad();
				
				eventBus.fireEvent(new UserEvent());
				
			}});
		
	}

	public void doEdit(Model model) {
		this.entity = (User) model;
		driver.edit((User) model);
		
	}

	public void onEvent() {
		doLoad();
		
	}

}
