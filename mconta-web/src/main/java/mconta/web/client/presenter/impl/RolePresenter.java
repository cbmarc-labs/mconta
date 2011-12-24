/**
 * 
 */
package mconta.web.client.presenter.impl;

import java.util.List;
import java.util.Set;

import mconta.domain.model.Model;
import mconta.domain.model.Role;
import mconta.web.client.event.AppEventBus;
import mconta.web.client.event.RoleEvent;
import mconta.web.client.event.UserEvent;
import mconta.web.client.event.UserEvent.UserHandler;
import mconta.web.client.presenter.CrudPresenter;
import mconta.web.client.rpc.AppAsyncCallback;
import mconta.web.client.rpc.CrudService;
import mconta.web.client.rpc.CrudServiceAsync;
import mconta.web.client.view.CrudView;
import mconta.web.client.view.View;
import mconta.web.client.view.impl.RoleViewImpl;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.google.gwt.user.client.ui.HasWidgets;

/**
 * @author Marc
 *
 */
public class RolePresenter implements CrudPresenter, UserHandler {
	
	private final CrudServiceAsync service = GWT.create(CrudService.class);
	interface Driver extends SimpleBeanEditorDriver<Role, RoleViewImpl> {}
	
	private AppEventBus eventBus = AppEventBus.getEventBus();
	
	Driver driver = GWT.create(Driver.class);
	
	Role entity;
	
	private final CrudView view;
	
	public RolePresenter(View view) {
		this.view = (CrudView) view;
		
		entity = new Role();
		
		driver.initialize((RoleViewImpl) view);
		driver.edit(entity);
		
		eventBus.addHandler(UserEvent.getType(), this);
		
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
			view.setData(result);
			
		}});
		
	}

	public void doSave() {
		entity = driver.flush();
		
		service.saveOrUpdate(entity, new AppAsyncCallback<Void>(){

			public void onSuccess(Void result) {
				doNew();				
				doLoad();
				
				eventBus.fireEvent(new RoleEvent());
				
			}});
	}

	public void doDelete(Set<Model> selectedSet) {
		service.deleteAll(selectedSet, new AppAsyncCallback<Void>(){

			public void onSuccess(Void result) {
				eventBus.fireEvent(new RoleEvent());
				doLoad();
				
			}});
		
	}

	public void doEdit(Model model) {
		this.entity = (Role) model;		
		driver.edit((Role) model);
		
	}

	public void onUserEvent() {
		doLoad();
		
	}

	public void doNew() {
		entity = new Role();
		driver.edit(entity);
		
	}

}
