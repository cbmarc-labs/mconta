/**
 * 
 */
package mconta.web.client.app.presenter.impl;

import java.util.List;

import mconta.domain.model.Model;
import mconta.domain.model.Product;
import mconta.web.client.common.event.ChangePageEvent;
import mconta.web.client.common.event.EventBus;
import mconta.web.client.common.event.JQMListEvent;
import mconta.web.client.common.event.JQMListHandler;
import mconta.web.client.common.presenter.Presenter;
import mconta.web.client.common.rpc.AppAsyncCallback;
import mconta.web.client.common.rpc.CrudService;
import mconta.web.client.common.rpc.CrudServiceAsync;
import mconta.web.client.common.ui.JQMList;
import mconta.web.client.common.ui.JQMListItem;
import mconta.web.client.common.utils.JQMUtils;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author marc
 *
 */
public class ListProductsPresenter implements Presenter {
	
	public interface Display {
		JQMList getList();
		
	    Widget asWidget();
	}

	private List<Model> products;
	private final Display display;
	private final CrudServiceAsync service = GWT.create(CrudService.class);
	
	public ListProductsPresenter(Display display) {
		this.display = display;
		
		bind();
	}
	
	private void bind() {
		display.getList().addClickHandler(new JQMListHandler(){

			@Override
			public void onLeftClick(JQMListEvent event, int index) {
				onEditClick(index);
				
			}

			@Override
			public void onRightClick(JQMListEvent event, int index) {
				onDeleteClick(index);
				
			}});
	}

	private void onEditClick(Integer id) {
		Model model = products.get(id);
		
		EventBus.getEventBus().fireEvent(ChangePageEvent.edit(model));
	}
	
	private void onDeleteClick(Integer id) {
		Model product = products.get(id);
		
		JQMUtils.showPageLoading();
		service.delete(product, new AppAsyncCallback<Void>(){

			@Override
			public void onSuccess(Void result) {
				doLoad();
				
				JQMUtils.popup("Elemento eliminado.");
				
			}});
	}
	
	private void doLoad() {
		JQMUtils.showPageLoading();
		service.getAll(Product.class.getName(), new AppAsyncCallback<List<Model>>(){

			public void onSuccess(List<Model> result) {
				products = result;
				setData(result);
				
				display.getList().refresh();
				
				JQMUtils.hidePageLoading();
				
			}});
	}
	
	private void setData(List<Model> products) {
		display.getList().clear();
		
		for (Model prod:products) {
			Product p = (Product) prod;
			
			Anchor delete = new Anchor();
			delete.getElement().setAttribute("data-icon", "delete");
			delete.getElement().setAttribute("data-theme", "c");
			JQMListItem item = new JQMListItem(p.getPro_name(), new Anchor(), delete);
			
			item.setDescription(p.getPro_description());
			item.setCount(3);
			
			Image image = new Image("uploads/" + p.getPro_image());
			item.setImage(image);
						
			display.getList().add(item);
		}
		
	}
	
	@Override
	public void go(HasWidgets container) {
		container.clear();
		container.add(display.asWidget());
		
		doLoad();
	}

}
