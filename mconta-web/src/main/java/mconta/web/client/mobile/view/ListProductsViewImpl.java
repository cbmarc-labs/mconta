/**
 * 
 */
package mconta.web.client.mobile.view;

import java.util.List;

import mconta.domain.model.Model;
import mconta.domain.model.Product;
import mconta.web.client.common.rpc.AppAsyncCallback;
import mconta.web.client.common.rpc.CrudService;
import mconta.web.client.common.rpc.CrudServiceAsync;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

/**
 * EditProductViewImpl class
 * 
 * @author marc
 *
 */
public class ListProductsViewImpl extends Composite {
	
	private final CrudServiceAsync service = GWT.create(CrudService.class);

	private static MyUiBinder uiBinder = GWT.create(MyUiBinder.class);
	interface MyUiBinder extends UiBinder<Widget, ListProductsViewImpl> {}
	
	List<Model> products;
	
	@UiField Element list;
	
	public ListProductsViewImpl() {
		initWidget(uiBinder.createAndBindUi(this));
		
		setTrigger(this);
	}
	
	public void go() {
		doLoad();
	}
	
	public void doLoad() {
		service.getAll(Product.class.getName(), new AppAsyncCallback<List<Model>>(){

			public void onSuccess(List<Model> result) {
				products = result;
				setData(result);
				
				String page = getElement().getId();
				//EventBus.getEventBus().fireEvent(new ChangePageEvent(page));
				
			}});
	}
	
	public void setData(List<Model> products) {
		StringBuilder build = new StringBuilder();
		
		for (Model prod:products) {
			Product p = (Product) prod;
			
			build.append("<li>");
			build.append("<a href=\"javascript:onPlusClick('" + products.indexOf(p) + "')\">");
			build.append("<img src=\"img/" + p.getPro_image() + "\" />");
			build.append("<h3>" + p.getPro_name() + "</h3><p>" + p.getPro_description() + "</p>");
			build.append("<span class=\"ui-li-count\" style=\"margin-right:10px\" id=\"pq-" + products.indexOf(p) + "\">"+p.getPro_quantity()+"</span>");
			build.append("</a>");
			build.append("<a href=\"javascript:onMinusClick('" + products.indexOf(p) + "')\" data-icon=\"minus\" data-theme=\"c\"></a>");
			build.append("</li>");
		}
		
		list.setInnerHTML(build.toString());
	}
	
	public void onPlusClick(String index) {
		Product product = (Product) products.get(Integer.parseInt(index));
		
		final int quantity = product.getPro_quantity();
		if(quantity < 25) {
			product.setPro_quantity(quantity + 1);
			doSave(product, index);
			
		} else
			Window.alert("Not more than 25.");
		
	}
	
	public void onMinusClick(String index) {
		Product product = (Product) products.get(Integer.parseInt(index));
		int quantity = product.getPro_quantity();
				
		if(quantity > 0) {
			product.setPro_quantity(quantity - 1);
			doSave(product, index);
			
		}
	}
	
	public void doSave(final Product product, final String index) {
		service.saveOrUpdate(product, new AppAsyncCallback<Void>(){

			@Override
			public void onSuccess(Void result) {
				setQuantity(index, product.getPro_quantity());
			
			}});
	}
	
	public native void setQuantity(String i, int qnt) /*-{
	
		$doc.getElementById('pq-'+i).innerText = qnt;
	
	}-*/;
	
	/**
	 * setTrigguer()
	 * 
	 * @param x
	 */
	public native void setTrigger(ListProductsViewImpl x)/*-{
		
		$wnd.onPlusClick = function (data) {
			x.@mconta.web.client.mobile.view.ListProductsViewImpl::onPlusClick(Ljava/lang/String;)(data);
		};
		
		$wnd.onMinusClick = function (data) {
			x.@mconta.web.client.mobile.view.ListProductsViewImpl::onMinusClick(Ljava/lang/String;)(data);
		};

	}-*/;

}
