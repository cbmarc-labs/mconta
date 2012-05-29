/**
 * 
 */
package mconta.web.client.app.presenter.impl;

import java.util.List;

import mconta.domain.model.Model;
import mconta.domain.model.Product;
import mconta.web.client.app.rpc.ImageService;
import mconta.web.client.app.rpc.ImageServiceAsync;
import mconta.web.client.common.event.JQMCollapsibleEvent;
import mconta.web.client.common.event.JQMCollapsibleHandler;
import mconta.web.client.common.presenter.Presenter;
import mconta.web.client.common.rpc.AppAsyncCallback;
import mconta.web.client.common.rpc.CrudService;
import mconta.web.client.common.rpc.CrudServiceAsync;
import mconta.web.client.common.ui.JQMCollapsible;
import mconta.web.client.common.utils.JQMUtils;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.InputElement;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONParser;
import com.google.gwt.json.client.JSONString;
import com.google.gwt.json.client.JSONValue;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.FileUpload;
import com.google.gwt.user.client.ui.FormPanel;
import com.google.gwt.user.client.ui.FormPanel.SubmitCompleteEvent;
import com.google.gwt.user.client.ui.FormPanel.SubmitCompleteHandler;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author marc
 *
 */
public class EditProductPresenter implements Presenter {
	
	private static String EMPTY_IMAGE = "blank.jpg";
	private static String UPLOAD_DIR = "uploads/";
	private static String IMAGE_DIR = "img/";
	private static String FORM_IMAGE_ACTION = "/app/upload";
	
	public interface Display {
		TextBox getName();
		HasValue<String> getDescription();
		Image getImage();
		
		JQMCollapsible getCollapsible();
		HTMLPanel getCollapsibleContent();
		
		FormPanel getFormPanel();
		FileUpload getFileUpload();
		
		HasClickHandlers getSaveButton();
		
	    Widget asWidget();
	}
	
	private Product product;
	private final Display display;
	private final CrudServiceAsync service = GWT.create(CrudService.class);
	private final ImageServiceAsync imageService = GWT.create(ImageService.class);
		
	public EditProductPresenter(Display display) {
		display.getFormPanel().setAction(FORM_IMAGE_ACTION);
		display.getFormPanel().setEncoding(FormPanel.ENCODING_MULTIPART);
		display.getFormPanel().setMethod(FormPanel.METHOD_POST);
		
		this.display = display;
		
		bind();
	}
	
	public void bind() {
		display.getSaveButton().addClickHandler(new ClickHandler(){

			@Override
			public void onClick(ClickEvent event) {
				doSave();
				
			}});
		
		display.getImage().addClickHandler(new ClickHandler(){

			@Override
			public void onClick(ClickEvent event) {
				display.getFileUpload().getElement().<InputElement>cast().click();
				
			}});
		
		display.getFormPanel().addSubmitCompleteHandler(new SubmitCompleteHandler(){

			@Override
			public void onSubmitComplete(SubmitCompleteEvent event) {
				String body = event.getResults();
				
				JSONObject response = (JSONObject) JSONParser.parseStrict(body);
				JSONValue value = response.get("error");
				
				if(value != null) {
					Window.alert("error : " + ((JSONString) value).stringValue());
					
				} else {
					value = response.get("fileName");
					product.setPro_image(((JSONString) value).stringValue());
					display.getImage().setUrl(UPLOAD_DIR + product.getPro_image());
				}
				
			}});		
		
		display.getFileUpload().addChangeHandler(new ChangeHandler(){

			@Override
			public void onChange(ChangeEvent event) {
				display.getFormPanel().submit();
				
			}});
		
		display.getCollapsible().addClickHandler(new JQMCollapsibleHandler(){

			@Override
			public void onExpand(JQMCollapsibleEvent event) {
				doLoadImageGallery();
				
			}

			@Override
			public void onCollapse(JQMCollapsibleEvent event) {
				display.getCollapsibleContent().clear();
				
			}});
	}
	
	private void doLoadImageGallery() {
		JQMUtils.popup("Cargando galeria ...");
		imageService.getAll(new AppAsyncCallback<List<String>>(){

			@Override
			public void onSuccess(List<String> result) {
				if(result.isEmpty()) {
					display.getCollapsibleContent().add(new HTML("Empty."));
				} else {
					for(final String i:result) {
						Image image = new Image(UPLOAD_DIR + i);
						image.addClickHandler(new ClickHandler(){
	
							@Override
							public void onClick(ClickEvent event) {
								product.setPro_image(i);
								display.getImage().setUrl(UPLOAD_DIR + product.getPro_image());
								
							}});
						
						display.getCollapsibleContent().add(image);
					}
				}
				
			}});
	}
	
	private void doSave() {
		updateDataFromDisplay();
		
		service.saveOrUpdate(product, new AppAsyncCallback<Void>(){

			@Override
			public void onSuccess(Void result) {
				JQMUtils.popup("Elemento guardado.");
				display.getName().setFocus(true);
				
				if(product.getPro_id() == null) {
					product = new Product();
					updateDisplayFromData();
				}
				
			}});
	}
	
	private void updateDataFromDisplay() {
		product.setPro_name(display.getName().getValue());
		product.setPro_description(display.getDescription().getValue());		
	}
	
	private void updateDisplayFromData() {
		display.getName().setValue(product.getPro_name());
		display.getDescription().setValue(product.getPro_description());
		
		String image = IMAGE_DIR + EMPTY_IMAGE;
		if(product.getPro_image() != null)
			image = UPLOAD_DIR + product.getPro_image(); 
			
		display.getImage().setUrl(image);
		display.getCollapsible().collapse();
	}

	@Override
	public void go(HasWidgets container) {
		go(container, new Product());
		
	}
	
	public void go(HasWidgets container, Model model) {
		container.clear();
		container.add(display.asWidget());
		
		if(model == null)
			model = new Product();
		
		this.product = (Product) model;
		updateDisplayFromData();
		
		display.getName().setFocus(true);
		
	}

}
