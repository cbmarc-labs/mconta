/**
 * 
 */
package mconta.web.client.presenter;

import java.util.Set;

import mconta.core.persistence.Model;

import com.google.gwt.user.client.ui.HasWidgets;

/**
 * @author Marc
 *
 */
public abstract interface Presenter {

	public void doSave();
	public void doDelete(Set<Model> selectedSet);
	public void doEdit(Model model);
	
	public abstract void go(final HasWidgets container);
	
}
