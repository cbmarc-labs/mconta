/**
 * 
 */
package mconta.web.client.presenter;

import java.util.List;
import java.util.Set;

import mconta.core.persistence.Model;
import mconta.core.persistence.Record;
import mconta.core.persistence.User;
import mconta.web.client.rpc.AppAsyncCallback;
import mconta.web.client.rpc.CrudService;
import mconta.web.client.rpc.CrudServiceAsync;
import mconta.web.client.view.MainView;
import mconta.web.client.view.View;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.HasWidgets;

/**
 * @author Marc
 *
 */
public class MainPresenter implements Presenter {
	
	private final CrudServiceAsync service = 
			GWT.create(CrudService.class);
	
	private final MainView view;
	
	public MainPresenter(View view) {
		this.view = (MainView) view;
		view.setPresenter(this);
		
		bind();
		
	}
	
	private void bind() {
		
	}
	
	public void doLoad() {
		service.getAll(Record.class.getName(), new AppAsyncCallback<List<Model>>(){

		public void onSuccess(List<Model> result) {			
			view.setData(result);
			
		}});
		
		service.getAll(User.class.getName(), new AppAsyncCallback<List<Model>>(){

			public void onSuccess(List<Model> result) {
				view.setUserData(result);
				
			}});
	}

	public void go(HasWidgets container) {
		container.clear();
		container.add(view.asWidget());
		
		doLoad();
		
	}

	public void onSubmitButtonClicked() {
		Record record = new Record();
		record.setTitle(view.getTextField().getValue());
		
		service.save(record, new AppAsyncCallback<Void>(){

			public void onSuccess(Void result) {
				doLoad();
				
			}});
	}

	public void onDeleteButtonClicked(Set<Model> selectedSet) {		
		service.deleteAll(selectedSet, new AppAsyncCallback<Void>(){

			public void onSuccess(Void result) {
				doLoad();
				
			}});
		
	}

	public void onSubmitUserButtonClicked() {
		User user = new User();
		user.setUsername(view.getUsernameTextField().getValue());
		
		service.save(user, new AppAsyncCallback<Void>(){

			public void onSuccess(Void result) {
				doLoad();
				
			}});
		
	}

}
