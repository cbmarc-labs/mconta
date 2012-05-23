/**
 * 
 */
package mconta.web.client.common.presenter;

import com.google.gwt.user.client.ui.HasWidgets;

/**
 * Presenter interface
 * 
 * @author Marc
 */
public abstract interface Presenter {
	
	public abstract void go(final HasWidgets container);
	public void bind();
	
}
