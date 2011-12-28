/**
 * 
 */
package mconta.web.client.mconta.presenter;

import com.google.gwt.user.client.ui.HasWidgets;

/**
 * @author Marc
 *
 */
public abstract interface Presenter {
	
	public abstract void go(final HasWidgets container);
	public void bind();
	
}
