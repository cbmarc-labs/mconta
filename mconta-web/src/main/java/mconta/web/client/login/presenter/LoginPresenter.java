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
 * LoginPresenter class
 * 
 * @author Marc
 */
public class LoginPresenter {
	
	/**
	 * LoginView interface
	 * 
	 * @author marc
	 */
	public interface LoginView extends View {
	
		/**
		 * getSubmitButton()
		 * 
		 * @return
		 */
		public Button getSubmitButton();
	
	}
	
	private final LoginServiceAsync loginService = 
			GWT.create(LoginService.class);
	
	private final LoginView view;
	
	/**
	 * LoginPresenter()
	 * 
	 * @param view
	 * @author marc
	 */
	public LoginPresenter(View view) {
		this.view = (LoginView) view;
		
		bind();
		
	}
	
	/**
	 * bind()
	 * 
	 * @author marc
	 */
	public void bind() {
		view.getSubmitButton().addClickHandler(new ClickHandler(){

			public void onClick(ClickEvent event) {
				onSubmitButtonClicked();
				
			}});
	}

	/**
	 * onSubmitButtonClicked()
	 * 
	 * @author marc
	 */
	protected void onSubmitButtonClicked() {
		loginService.login("admin", "admin", new AsyncCallback<UserDto>(){

			public void onFailure(Throwable caught) {
				Window.alert("login failed");
				
			}

			public void onSuccess(UserDto result) {
				History.newItem("mconta");
				
			}});
		
	}

	/**
	 * go()
	 * 
	 * @param container
	 * @author marc
	 */
	public void go(HasWidgets container) {
		container.clear();
		container.add(view.asWidget());
		
	}

}
