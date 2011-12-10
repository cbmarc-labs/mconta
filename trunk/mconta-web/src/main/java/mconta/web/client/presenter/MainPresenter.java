/**
 * 
 */
package mconta.web.client.presenter;

import java.util.List;
import java.util.Set;

import mconta.core.persistence.Model;
import mconta.core.persistence.Record;
import mconta.web.client.rpc.AppAsyncCallback;
import mconta.web.client.rpc.CrudService;
import mconta.web.client.rpc.CrudServiceAsync;
import mconta.web.client.view.CrudView;
import mconta.web.client.view.View;
import mconta.web.client.view.impl.RecordViewImpl;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.google.gwt.user.client.ui.HasWidgets;

/**
 * @author Marc
 *
 */
public class MainPresenter implements CrudPresenter {
	
	private final CrudServiceAsync service = GWT.create(CrudService.class);
	interface Driver extends SimpleBeanEditorDriver<Record, RecordViewImpl> {}
	
	Driver driver = GWT.create(Driver.class);
	
	Record record;
	
	private final CrudView view;
	
	public MainPresenter(View view) {
		this.view = (CrudView) view;
		view.setPresenter(this);
		
		record = new Record();
		
		driver.initialize((RecordViewImpl) view);
		driver.edit(record);
		
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
		service.getAll(Record.class.getName(), new AppAsyncCallback<List<Model>>(){

		public void onSuccess(List<Model> result) {			
			view.setData(result);
			
		}});
		
	}

	public void doSave() {
		record = driver.flush();
		
		service.saveOrUpdate(record, new AppAsyncCallback<Void>(){

			public void onSuccess(Void result) {
				record = new Record();
				driver.edit(record);
				
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
		this.record = (Record) model;
		driver.edit((Record) model);
		
	}

}
