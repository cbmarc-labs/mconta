/**
 * 
 */
package mconta.web.client.app.view.impl;

import java.util.List;

import mconta.domain.model.Model;
import mconta.domain.model.Product;
import mconta.web.client.app.view.View;
import mconta.web.client.common.event.ChangePageEvent;
import mconta.web.client.common.event.EventBus;
import mconta.web.client.common.event.JQMListEvent;
import mconta.web.client.common.event.JQMListHandler;
import mconta.web.client.common.rpc.AppAsyncCallback;
import mconta.web.client.common.rpc.CrudService;
import mconta.web.client.common.rpc.CrudServiceAsync;
import mconta.web.client.common.ui.JQMList;
import mconta.web.client.common.ui.JQMListItem;
import mconta.web.client.common.utils.JQMUtils;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author Marc
 *
 */
public class ListProductsViewImpl extends Composite implements View {
	
	private final CrudServiceAsync service = GWT.create(CrudService.class);

	private static MainUiBinder uiBinder = GWT.create(MainUiBinder.class);
	interface MainUiBinder extends UiBinder<Widget, ListProductsViewImpl> {}

	List<Model> products;
	
	@UiField JQMList list;
	
	public ListProductsViewImpl() {		
		initWidget(uiBinder.createAndBindUi(this));
		
		list.addClickHandler(new JQMListHandler(){

			@Override
			public void onLeftClick(JQMListEvent event, int index) {
				onEditClick(index);
				
			}

			@Override
			public void onRightClick(JQMListEvent event, int index) {
				onDeleteClick(index);
				
			}});
	}

	public void doLoad() {
		JQMUtils.showPageLoading();
		service.getAll(Product.class.getName(), new AppAsyncCallback<List<Model>>(){

			public void onSuccess(List<Model> result) {
				products = result;
				setData(result);
				
				list.refresh();
				
				JQMUtils.hidePageLoading();
				
			}});
	}
	
	public void setData(List<Model> products) {
		list.clear();
		
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
						
			list.add(item);
		}
		
	}
	
	@UiHandler("newButton")
	public void newButtonClick(ClickEvent event) {
		EventBus.getEventBus().fireEvent(ChangePageEvent.edit(null));
		
	}

	@Override
	public void go(HasWidgets widget) {
		widget.clear();
		widget.add(this);
		
		doLoad();
		
	}
	
	public void onEditClick(Integer id) {
		Model model = products.get(id);
		
		EventBus.getEventBus().fireEvent(ChangePageEvent.edit(model));
	}
	
	public void onDeleteClick(Integer id) {
		Model product = products.get(id);
		
		JQMUtils.showPageLoading();
		service.delete(product, new AppAsyncCallback<Void>(){

			@Override
			public void onSuccess(Void result) {
				doLoad();
				
				JQMUtils.popup("Elemento eliminado.");
				
			}});
	}

}
