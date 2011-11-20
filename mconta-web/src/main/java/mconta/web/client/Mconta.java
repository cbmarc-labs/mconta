package mconta.web.client;

import mconta.web.client.presenter.MainPresenter;
import mconta.web.client.rpc.AppAsyncCallback;
import mconta.web.client.rpc.LoginService;
import mconta.web.client.rpc.LoginServiceAsync;
import mconta.web.client.view.MainViewImpl;
import mconta.web.shared.UserDTO;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.RootPanel;

public class Mconta implements EntryPoint {
	
	private final LoginServiceAsync loginService = GWT
			.create(LoginService.class);

	public void onModuleLoad() {
		
		// DO LOGIN
		/*loginService.login("anonymous", "", new AppAsyncCallback<UserDTO>() {

			public void onSuccess(UserDTO result) {
				new MainPresenter(new MainViewImpl()).go(RootPanel.get("mainContainer"));
				
			}
			
		});*/
		
		new MainPresenter(new MainViewImpl()).go(RootPanel.get("mainContainer"));
		
	}
	
}
