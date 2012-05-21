/**
 * 
 */
package mconta.web.client.app.presenter;

import java.util.Set;

import mconta.domain.model.Model;
import mconta.web.client.common.presenter.Presenter;

/**
 * @author Marc
 *
 */
public abstract interface CrudPresenter extends Presenter {

	public void doLoad();
	public void doSave();
	public void doNew();
	public void doDelete(Set<Model> selectedSet);
	public void doEdit(Model model);
	
}
