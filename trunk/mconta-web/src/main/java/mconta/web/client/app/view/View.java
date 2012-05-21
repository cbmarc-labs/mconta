/**
 * 
 */
package mconta.web.client.app.view;

import mconta.web.client.common.presenter.Presenter;

import com.google.gwt.user.client.ui.Widget;

/**
 * View interface
 * 
 * @author Marc
 */
public interface View {
	
	/**
	 * setPresenter
	 * 
	 * @param presenter
	 */
	public void setPresenter(Presenter presenter);
	
	/**
	 * asWidget
	 * 
	 * @return
	 */
	Widget asWidget();

}
