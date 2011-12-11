/**
 * 
 */
package mconta.web.client.presenter;

import mconta.web.client.view.MainView;
import mconta.web.client.view.View;
import mconta.web.client.view.impl.GroupViewImpl;
import mconta.web.client.view.impl.UserViewImpl;

import com.google.gwt.user.client.ui.HasWidgets;

/**
 * @author Marc
 *
 */
public class MainPresenter implements Presenter {
	
	private final MainView view;
	
	private Presenter userPresenter;
	private Presenter groupPresenter;
	
	public MainPresenter(View view) {
		this.view = (MainView) view;
		view.setPresenter(this);
		
		userPresenter = new UserPresenter(new UserViewImpl());
		groupPresenter = new GroupPresenter(new GroupViewImpl());
		
		userPresenter.go(this.view.getUserPanel());
		groupPresenter.go(this.view.getGroupPanel());
		
		bind();
		
	}
	
	private void bind() {
		
	}

	public void go(HasWidgets container) {
		container.clear();
		container.add(view.asWidget());
		
	}

}
