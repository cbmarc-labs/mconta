/**
 * 
 */
package mconta.web.client.view;

import java.util.List;

import mconta.core.persistence.Model;

/**
 * @author Marc
 *
 */
public interface MainView extends View {
	
	public void setData(List<Model> data);

}
