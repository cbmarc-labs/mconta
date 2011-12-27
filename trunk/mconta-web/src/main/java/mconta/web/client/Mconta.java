package mconta.web.client;

import mconta.web.client.presenter.impl.MainPresenter;
import mconta.web.client.view.impl.MainViewImpl;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;

public class Mconta implements EntryPoint {

	public void onModuleLoad() {
		
		new MainPresenter(new MainViewImpl()).go(RootPanel.get("mainContainer"));
		
	}
	
}
