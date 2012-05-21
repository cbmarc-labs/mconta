/**
 * 
 */
package mconta.web.client.login.view.impl;

import mconta.web.client.login.presenter.LoginPresenter.LoginView;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

/**
 * LoginViewImpl class
 * 
 * @author Marc
 */
public class LoginViewImpl extends Composite implements LoginView {
	
	/**
	 * LoginViewUiBinder
	 * 
	 * @author marc
	 */
	private static LoginViewUiBinder uiBinder = GWT
			.create(LoginViewUiBinder.class);

	/**
	 * LoginViewUiBinder
	 * 
	 * @author marc
	 *
	 */
	interface LoginViewUiBinder extends UiBinder<Widget, LoginViewImpl> {}

	@UiField TextBox email;
	@UiField PasswordTextBox password;
	@UiField Button submit_button;
	@UiField Anchor register_link;
	@UiField SimplePanel message;

	public LoginViewImpl() {
		initWidget(uiBinder.createAndBindUi(this));
		submit_button.setText("Login");
		register_link.setText("Register");
	}

	public String getEmailValue(){
		return email.getValue();
	}

	public String getPasswordValue(){
		return password.getValue();
	}

	public Button getSubmitButton(){
		return submit_button;
	}

	public Anchor getRegisterLink(){
		return register_link;
	}


	public TextBox getEmailBox(){
		return email;
	}

	public PasswordTextBox getPasswordBox(){
		return password;
	}

	public SimplePanel getMessage(){
		return message;
	}
}
