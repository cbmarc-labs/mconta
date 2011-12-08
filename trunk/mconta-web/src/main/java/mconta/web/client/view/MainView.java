/**
 * 
 */
package mconta.web.client.view;

import java.util.List;

import mconta.core.persistence.Record;

import com.google.gwt.user.client.ui.TextBox;


/**
 * @author Marc
 *
 */
public interface MainView extends View {
	
	public TextBox getTextField();
	public void setData(List<Record> data);

}
