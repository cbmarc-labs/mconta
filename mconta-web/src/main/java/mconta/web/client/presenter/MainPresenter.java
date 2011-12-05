/**
 * 
 */
package mconta.web.client.presenter;

import java.util.List;

import mconta.core.persistence.Model;
import mconta.core.persistence.Record;
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
			
			String out = "";
			
			for(int i = 0 ; i < result.size() ; i ++ ) {				
				Record dto = (Record) result.get(i);
				
				out += dto.getTitle() + "<br>";
			}
			
			view.setData(out);
			
		}});
	}

	public void go(HasWidgets container) {
		container.clear();
		container.add(view.asWidget());
		
		doLoad();
		
	}

	public void onButtonClicked() {
		view.getTextField().getValue();
		
		Record record = new Record();
		record.setTitle(view.getTextField().getValue());
		
		service.save(record, new AppAsyncCallback<Void>(){

			public void onSuccess(Void result) {
				doLoad();
				
			}});
	}

}
