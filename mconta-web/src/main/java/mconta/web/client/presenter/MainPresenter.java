/**
 * 
 */
package mconta.web.client.presenter;

import mconta.web.client.rpc.AppAsyncCallback;
import mconta.web.client.rpc.RecordService;
import mconta.web.client.rpc.RecordServiceAsync;
import mconta.web.client.view.MainView;
import mconta.web.client.view.View;
import mconta.web.shared.RecordDTO;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.HasWidgets;

/**
 * @author Marc
 *
 */
public class MainPresenter implements Presenter {
	
	private final RecordServiceAsync service = 
			GWT.create(RecordService.class);
	
	private final MainView view;
	
	public MainPresenter(View view) {
		this.view = (MainView) view;
		view.setPresenter(this);
		
		bind();
		
	}
	
	private void bind() {
		
	}
	
	public void doLoad() {
		
		/*service.getAll(new AppAsyncCallback<List<RecordDTO>>(){

			public void onSuccess(List<RecordDTO> result) {
				
				String out = "";
				
				for(int i = 0 ; i < result.size() ; i ++ ) {
					out += result.get(i).getTitle() + "<br>";
				}
				
				view.setData(out);
				
			}});*/
		
	}

	public void go(HasWidgets container) {
		container.clear();
		container.add(view.asWidget());
		
		doLoad();
		
	}

	public void onButtonClicked() {
		view.getTextField().getValue();
		
		RecordDTO record = new RecordDTO();
		record.setTitle(view.getTextField().getValue());
		
		service.save(record, new AppAsyncCallback<Void>(){

			public void onSuccess(Void result) {
				doLoad();
				
			}});
	}

}
