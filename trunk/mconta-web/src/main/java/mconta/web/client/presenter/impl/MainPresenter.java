/**
 * 
 */
package mconta.web.client.presenter.impl;

import mconta.web.client.presenter.Presenter;
import mconta.web.client.view.View;
import mconta.web.client.view.impl.GroupViewImpl;
import mconta.web.client.view.impl.UserViewImpl;

import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.HasWidgets;

/**
 * @author Marc
 *
 */
public class MainPresenter implements Presenter {
	
	public interface MainView extends View {
		
		public HTMLPanel getUserPanel();
		public HTMLPanel getGroupPanel();

	}
	
	private final MainView view;
	
	private Presenter userPresenter;
	private Presenter groupPresenter;
	
	public MainPresenter(View view) {
		this.view = (MainView) view;
		
		userPresenter = new UserPresenter(new UserViewImpl());
		groupPresenter = new GroupPresenter(new GroupViewImpl());
		
		userPresenter.go(this.view.getUserPanel());
		groupPresenter.go(this.view.getGroupPanel());
		
		bind();
		
	}
	
	public void bind() {
		view.setPresenter(this);
		
	}

	public void go(HasWidgets container) {
		container.clear();
		container.add(view.asWidget());
		
	}

}