/**
 * 
 */
package mconta.web.client.view;

import mconta.web.client.presenter.Presenter;

import com.google.gwt.user.client.ui.Widget;

/**
 * @author Marc
 *
 */
public interface View {
	
	public void setPresenter(Presenter presenter);
	Widget asWidget();

}
