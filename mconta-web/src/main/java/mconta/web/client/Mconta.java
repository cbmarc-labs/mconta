package mconta.web.client;

import mconta.web.client.login.presenter.LoginPresenter;
import mconta.web.client.login.rpc.LoginService;
import mconta.web.client.login.rpc.LoginServiceAsync;
import mconta.web.client.login.view.impl.LoginViewImpl;
import mconta.web.client.mconta.presenter.impl.MainPresenter;
import mconta.web.client.mconta.view.impl.MainViewImpl;
import mconta.web.shared.UserDto;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.RunAsyncCallback;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.RootPanel;

public class Mconta implements EntryPoint, ValueChangeHandler<String>  {
	
	private final LoginServiceAsync loginService = 
			GWT.create(LoginService.class);

	public void onModuleLoad() {
		
		loginService.isAuthenticated(new AsyncCallback<UserDto>(){

			public void onFailure(Throwable caught) {
				Window.alert("Service Error");
				
			}

			public void onSuccess(UserDto result) {
				if(result == null)
					showLogin();
				else
					showApplication();
				
			}});
		
		History.addValueChangeHandler(this);
		
	}

	protected void showLogin() {
		GWT.runAsync(new RunAsyncCallback() {

			public void onFailure(Throwable reason) {
				Window.alert("runAsync Error");
				
			}

			public void onSuccess() {
				new LoginPresenter(new LoginViewImpl()).go(RootPanel.get("mainContainer"));
				
			}});
		
	}

	protected void showApplication() {
		GWT.runAsync(new RunAsyncCallback() {

			public void onFailure(Throwable reason) {
				Window.alert("runAsync Error");
				
			}

			public void onSuccess() {
				new MainPresenter(new MainViewImpl()).go(RootPanel.get("mainContainer"));
				
			}});
		
	}

	public void onValueChange(ValueChangeEvent<String> event) {
		String token = event.getValue();
		
		if(token != null) {
			
			if(token.startsWith("mconta")) {
				showApplication();
			}
			
		}
		
	}
	
}
