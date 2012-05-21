/**
 * 
 */
package mconta.web.client.app.presenter.impl;

import mconta.web.client.app.view.View;
import mconta.web.client.app.view.impl.RoleViewImpl;
import mconta.web.client.app.view.impl.UserViewImpl;
import mconta.web.client.common.presenter.Presenter;

import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.HasWidgets;

/**
 * @author Marc
 *
 */
public class MainPresenter implements Presenter {
	
	public interface MainView extends View {
		
		public HTMLPanel getUserPanel();
		public HTMLPanel getRolePanel();

	}
	
	private final MainView view;
	
	private Presenter userPresenter;
	private Presenter rolePresenter;
	
	public MainPresenter(View view) {
		this.view = (MainView) view;
		
		userPresenter = new UserPresenter(new UserViewImpl());
		rolePresenter = new RolePresenter(new RoleViewImpl());
		
		userPresenter.go(this.view.getUserPanel());
		rolePresenter.go(this.view.getRolePanel());
		
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
