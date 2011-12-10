/**
 * 
 */
package mconta.web.client.presenter;

import java.util.Set;

import mconta.core.persistence.Model;

/**
 * @author Marc
 *
 */
public abstract interface CrudPresenter extends Presenter {

	public void doLoad();
	public void doSave();
	public void doDelete(Set<Model> selectedSet);
	public void doEdit(Model model);
	
}
