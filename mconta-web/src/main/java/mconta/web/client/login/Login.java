/**
 * 
 */
package mconta.web.client.login;

import mconta.web.client.login.rpc.LoginService;
import mconta.web.client.login.rpc.LoginServiceAsync;
import mconta.web.client.login.view.impl.LoginViewImpl;
import mconta.web.shared.UserDto;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.RootLayoutPanel;

/**
 * @author Marc
 *
 */
public class Login implements EntryPoint {
	
	private LoginViewImpl view = GWT.create(LoginViewImpl.class);
	private final LoginServiceAsync loginService = GWT.create(LoginService.class);

	public void onModuleLoad() {
		
		/*loginService.isAuthenticated(new AsyncCallback<UserDto>(){

			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				
			}

			public void onSuccess(UserDto result) {
				if(result==null) {
					showLogin();
				} else {
					Window.Location.assign("/mconta/");
				}
				
			}
			
		});*/
		
		view.getSubmitButton().addClickHandler(new ClickHandler(){

			public void onClick(ClickEvent event) {
				Window.Location.assign("/mconta/");
				
			}});
		
		showLogin();
		
	}
	
	public void showLogin() {
		RootLayoutPanel.get().clear();
		RootLayoutPanel.get().add(view);
	}

}
