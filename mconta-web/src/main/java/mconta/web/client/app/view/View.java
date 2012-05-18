/**
 * 
 */
package mconta.web.client.app.view;

import mconta.web.client.app.presenter.Presenter;

import com.google.gwt.user.client.ui.Widget;

/**
 * @author Marc
 *
 */
public interface View {
	
	public void setPresenter(Presenter presenter);
	Widget asWidget();

}
