/**
 * 
 */
package mconta.web.client.app.view.impl;

import mconta.web.client.app.presenter.impl.MainPresenter;
import mconta.web.client.common.ui.JQMPage;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author marc
 *
 */
public class MainViewImpl extends Composite implements MainPresenter.Display {

	private static MainViewImplUiBinder uiBinder = GWT
			.create(MainViewImplUiBinder.class);

	interface MainViewImplUiBinder extends UiBinder<Widget, MainViewImpl> {
	}
	
	@UiField JQMPage main;
	@UiField HTMLPanel primary;
	@UiField HTMLPanel secondary;
	
	public MainViewImpl() {
		initWidget(uiBinder.createAndBindUi(this));
		
		primary.getElement().setId("primary");
		
		primary.setStyleName("content-primary");
		secondary.setStyleName("content-secondary");
	}

	@Override
	public HTMLPanel getPrimary() {
		return primary;
	}

	@Override
	public HTMLPanel getSecondary() {
		return secondary;
	}

	@Override
	public JQMPage getMain() {
		return main;
	}

}
