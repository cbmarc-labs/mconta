package mconta.web.client.app;

import mconta.domain.model.Model;
import mconta.web.client.app.view.View;
import mconta.web.client.app.view.impl.EditProductViewImpl;
import mconta.web.client.app.view.impl.ListProductsViewImpl;
import mconta.web.client.app.view.impl.MainViewImpl;
import mconta.web.client.common.event.ChangePageEvent;
import mconta.web.client.common.event.ChangePageHandler;
import mconta.web.client.common.event.EventBus;
import mconta.web.client.common.utils.JQMUtils;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;

public class App implements EntryPoint, ChangePageHandler  {
	
	View mainView = new MainViewImpl();
	EditProductViewImpl editProductView = new EditProductViewImpl();
	View listProductsView = new ListProductsViewImpl();
	
	public void onModuleLoad() {
		EventBus.getEventBus().addHandler(ChangePageEvent.getType(), this);
		
		mainView.go(RootPanel.get("secondary"));
		listProductsView.go(RootPanel.get("primary"));
		
		JQMUtils.triggerCreate("primary");
		JQMUtils.triggerCreate("secondary");
	}

	@Override
	public void onList(ChangePageEvent event) {
		listProductsView.go(RootPanel.get("primary"));
		
	}

	@Override
	public void onEdit(ChangePageEvent event, Model model) {
		editProductView.go(RootPanel.get("primary"), model);
		
	}
	
}
