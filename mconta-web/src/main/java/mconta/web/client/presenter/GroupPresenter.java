/**
 * 
 */
package mconta.web.client.presenter;

import java.util.Date;
import java.util.List;
import java.util.Set;

import mconta.core.persistence.Model;
import mconta.core.persistence.UserGroup;
import mconta.web.client.rpc.AppAsyncCallback;
import mconta.web.client.rpc.CrudService;
import mconta.web.client.rpc.CrudServiceAsync;
import mconta.web.client.view.CrudView;
import mconta.web.client.view.View;
import mconta.web.client.view.impl.GroupViewImpl;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.google.gwt.user.client.ui.HasWidgets;

/**
 * @author Marc
 *
 */
public class GroupPresenter implements CrudPresenter {
	
	private final CrudServiceAsync service = GWT.create(CrudService.class);
	interface Driver extends SimpleBeanEditorDriver<UserGroup, GroupViewImpl> {}
	
	Driver driver = GWT.create(Driver.class);
	
	UserGroup entity;
	
	private final CrudView view;
	
	public GroupPresenter(View view) {
		this.view = (CrudView) view;
		view.setPresenter(this);
		
		entity = new UserGroup();
		
		driver.initialize((GroupViewImpl) view);
		driver.edit(entity);
		
		bind();
		
	}
	
	private void bind() {
		
	}

	public void go(HasWidgets container) {
		container.clear();
		container.add(view.asWidget());
		
		doLoad();
		
	}
	
	public void doLoad() {
		service.getAll(UserGroup.class.getName(), new AppAsyncCallback<List<Model>>(){

		public void onSuccess(List<Model> result) {			
			view.setData(result);
			
		}});
		
	}

	public void doSave() {
		entity = driver.flush();
		
		if(entity.getUgr_id() == null) {
			entity.setAud_createdBy("anonymous");
			entity.setAud_createdOn(new Date());
		}
		
		entity.setAud_modifiedBy("anonymous");
		entity.setAud_modifiedOn(new Date());
		
		service.saveOrUpdate(entity, new AppAsyncCallback<Void>(){

			public void onSuccess(Void result) {
				entity = new UserGroup();
				driver.edit(entity);
				
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
		this.entity = (UserGroup) model;
		driver.edit((UserGroup) model);
		
	}

}
