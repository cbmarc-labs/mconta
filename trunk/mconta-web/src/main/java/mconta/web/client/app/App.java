package mconta.web.client.app;

import mconta.web.client.app.presenter.impl.MainPresenter;
import mconta.web.client.app.view.impl.MainViewImpl;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;

public class App implements EntryPoint  {
	
	MainPresenter main = new MainPresenter(new MainViewImpl());
	
	public void onModuleLoad() {
		main.go(RootPanel.get());
		
	}
	
}
