/**
 * 
 */
package mconta.web.client.login.presenter;

import mconta.web.client.login.rpc.LoginService;
import mconta.web.client.login.rpc.LoginServiceAsync;
import mconta.web.client.login.view.View;
import mconta.web.shared.dto.UserDto;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HasWidgets;

/**
 * @author Marc
 *
 */
public class LoginPresenter {
	
	public interface LoginView extends View {
	
		public Button getSubmitButton();
	
	}
	
	private final LoginServiceAsync loginService = 
			GWT.create(LoginService.class);
	
	private final LoginView view;
	
	public LoginPresenter(View view) {
		this.view = (LoginView) view;
		
		bind();
		
	}
	
	public void bind() {
		view.getSubmitButton().addClickHandler(new ClickHandler(){

			public void onClick(ClickEvent event) {
				onSubmitButtonClicked();
				
			}});
	}

	protected void onSubmitButtonClicked() {
		loginService.login("admin", "admin", new AsyncCallback<UserDto>(){

			public void onFailure(Throwable caught) {
				Window.alert("login failed");
				
			}

			public void onSuccess(UserDto result) {
				History.newItem("mconta");
				
			}});
		
	}

	public void go(HasWidgets container) {
		container.clear();
		container.add(view.asWidget());
		
	}

}
