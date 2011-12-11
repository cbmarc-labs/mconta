/**
 * 
 */
package mconta.web.client.view.impl;

import mconta.web.client.presenter.Presenter;
import mconta.web.client.view.MainView;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author Marc
 *
 */
public class MainViewImpl extends Composite implements MainView {

	private static MainUiBinder uiBinder = GWT.create(MainUiBinder.class);

	interface MainUiBinder extends UiBinder<Widget, MainViewImpl> {}
	
	Presenter presenter;
	
	@UiField HTMLPanel userpanel;
	@UiField HTMLPanel grouppanel;
	
	public MainViewImpl() {		
		initWidget(uiBinder.createAndBindUi(this));
		
	}

	public void setPresenter(Presenter presenter) {
		this.presenter = presenter;
		
	}

	public HTMLPanel getUserPanel() {
		return userpanel;
	}

	public HTMLPanel getGroupPanel() {
		return grouppanel;
	}

}
