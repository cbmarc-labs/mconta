/**
 * 
 */
package mconta.web.client.presenter;

import com.google.gwt.user.client.ui.HasWidgets;

/**
 * @author Marc
 *
 */
public abstract interface Presenter {

	public void onButtonClicked();
	public abstract void go(final HasWidgets container);
	
}
