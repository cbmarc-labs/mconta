/**
 * 
 */
package mconta.web.client.presenter.impl;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import mconta.core.persistence.Model;
import mconta.core.persistence.User;
import mconta.core.persistence.UserGroup;
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
public class UserPresenter implements CrudPresenter {
	
	private final CrudServiceAsync service = GWT.create(CrudService.class);
	interface Driver extends SimpleBeanEditorDriver<User, UserViewImpl> {}
	
	Driver driver = GWT.create(Driver.class);
	
	User user;
	
	private final CrudView view;
	
	public UserPresenter(View view) {
		this.view = (CrudView) view;
		
		user = new User();
		
		driver.initialize((UserViewImpl) view);
		driver.edit(user);
		
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
		service.getAll(User.class.getName(), new AppAsyncCallback<List<Model>>(){

			public void onSuccess(List<Model> result) {
				view.setData(result);
				
			}});
		
	}

	public void doSave() {
		user = driver.flush();
		
		if(user.getUse_id() == null) {
			user.setAud_createdBy("anonymous");
			user.setAud_createdOn(new Date());
			user.setUse_usergroup(new HashSet<UserGroup>());
		}
		
		user.setAud_modifiedBy("anonymous");
		user.setAud_modifiedOn(new Date());
		
		service.saveOrUpdate(user, new AppAsyncCallback<Void>(){

			public void onSuccess(Void result) {
				user = new User();
				driver.edit(user);
				
				doLoad();
				
			}});
	}

	public void doDelete(Set<Model> selectedSet) {		
		service.deleteAll(selectedSet, new AppAsyncCallback<Void>(){

			public void onSuccess(Void result) {
				doLoad();
				
			}});
		
	}

	public void doEdit(Model model) {
		this.user = (User) model;
		driver.edit((User) model);
		
	}

}
