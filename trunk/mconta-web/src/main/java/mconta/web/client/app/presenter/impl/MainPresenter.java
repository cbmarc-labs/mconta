/**
 * 
 */
package mconta.web.client.app.presenter.impl;

import mconta.domain.model.Model;
import mconta.web.client.app.view.impl.EditProductViewImpl;
import mconta.web.client.app.view.impl.ListProductsViewImpl;
import mconta.web.client.app.view.impl.MenuViewImpl;
import mconta.web.client.common.event.ChangePageEvent;
import mconta.web.client.common.event.ChangePageHandler;
import mconta.web.client.common.event.EventBus;
import mconta.web.client.common.presenter.Presenter;
import mconta.web.client.common.ui.JQMPage;
import mconta.web.client.common.utils.JQMUtils;

import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author marc
 *
 */
public class MainPresenter implements Presenter, ChangePageHandler {
	
	public interface Display {
		JQMPage getMain();
		HTMLPanel getPrimary();
		HTMLPanel getSecondary();
		
	    Widget asWidget();
	}
	
	private final Display display;
	MenuViewImpl menu = new MenuViewImpl();
	EditProductPresenter edit = new EditProductPresenter(new EditProductViewImpl());
	ListProductsPresenter list = new ListProductsPresenter(new ListProductsViewImpl());
		
	public MainPresenter(Display display) {
		this.display = display;
		
		bind();
	}
	
	public void bind() {
		EventBus.getEventBus().addHandler(ChangePageEvent.getType(), this);
	}

	@Override
	public void go(HasWidgets container) {
		container.clear();
		container.add(display.asWidget());
		
		display.getSecondary().add(menu);
		list.go(display.getPrimary());
		
		JQMUtils.changePage(display.getMain().getElement().getId());
		
	}

	@Override
	public void onList(ChangePageEvent event) {
		list.go(display.getPrimary());
		
	}

	@Override
	public void onEdit(ChangePageEvent event, Model model) {
		edit.go(display.getPrimary(), model);
		JQMUtils.triggerCreate(display.getPrimary().getElement().getId());
		
	}

}
