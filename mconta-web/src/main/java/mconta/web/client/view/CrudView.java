/**
 * 
 */
package mconta.web.client.view;

import java.util.List;

import mconta.domain.model.Model;

/**
 * @author Marc
 *
 */
public interface CrudView extends View {
	
	public void setData(List<Model> data);

}
