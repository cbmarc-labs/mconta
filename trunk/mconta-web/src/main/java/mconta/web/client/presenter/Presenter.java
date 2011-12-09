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

	public void onSubmitButtonClicked();
	public void onDeleteButtonClicked(Set<Model> selectedSet);
	
	public void onSubmitUserButtonClicked();
	
	public abstract void go(final HasWidgets container);
	
}
