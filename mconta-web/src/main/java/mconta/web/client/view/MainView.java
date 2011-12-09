/**
 * 
 */
package mconta.web.client.view;

import java.util.List;

import mconta.core.persistence.Model;

import com.google.gwt.user.client.ui.TextBox;


/**
 * @author Marc
 *
 */
public interface MainView extends View {
	
	public TextBox getTextField();
	
	public TextBox getUsernameTextField();
	
	public void setData(List<Model> data);
	
	public void setUserData(List<Model> data);

}
