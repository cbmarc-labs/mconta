/**
 * 
 */
package mconta.web.client.app.view.impl;

import java.util.List;

import mconta.domain.model.Model;
import mconta.domain.model.Product;
import mconta.web.client.app.rpc.ImageService;
import mconta.web.client.app.rpc.ImageServiceAsync;
import mconta.web.client.app.view.View;
import mconta.web.client.common.event.JQMCollapsibleEvent;
import mconta.web.client.common.event.JQMCollapsibleHandler;
import mconta.web.client.common.rpc.AppAsyncCallback;
import mconta.web.client.common.rpc.CrudService;
import mconta.web.client.common.rpc.CrudServiceAsync;
import mconta.web.client.common.ui.JQMButton;
import mconta.web.client.common.ui.JQMCollapsible;
import mconta.web.client.common.utils.JQMUtils;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.InputElement;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONParser;
import com.google.gwt.json.client.JSONString;
import com.google.gwt.json.client.JSONValue;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FileUpload;
import com.google.gwt.user.client.ui.FormPanel;
import com.google.gwt.user.client.ui.FormPanel.SubmitCompleteEvent;
import com.google.gwt.user.client.ui.FormPanel.SubmitCompleteHandler;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author marc
 *
 */
public class EditProductViewImpl extends Composite implements View {
	
	private final CrudServiceAsync service = GWT.create(CrudService.class);
	private final ImageServiceAsync imageService = GWT.create(ImageService.class);

	private static EditProductViewImplUiBinder uiBinder = GWT
			.create(EditProductViewImplUiBinder.class);

	interface EditProductViewImplUiBinder extends
			UiBinder<Widget, EditProductViewImpl> {}
	
	private static String EMPTY_IMAGE = "blank.jpg";
	
	@UiField FormPanel formPanel;
	@UiField FileUpload fileUpload;
	
	@UiField Image imageField;
	@UiField TextBox nameField;
	@UiField TextBox descriptionField;
	
	@UiField JQMButton acceptButton;
	
	@UiField JQMCollapsible imageGallery;
	@UiField HTMLPanel imageGalleryContent;
	
	Product product;
	
	public EditProductViewImpl() {
		initWidget(uiBinder.createAndBindUi(this));
		
		formPanel.setAction("/mobile/upload");
		formPanel.setEncoding(FormPanel.ENCODING_MULTIPART);
		formPanel.setMethod(FormPanel.METHOD_POST);
				
		nameField.getElement().setId("nameField");
		
		bind();
	}
	
	private void bind() {
		imageGallery.addClickHandler(new JQMCollapsibleHandler(){

			@Override
			public void onExpand(JQMCollapsibleEvent event) {
				doLoadImageGallery();
				
			}

			@Override
			public void onCollapse(JQMCollapsibleEvent event) {
				imageGalleryContent.clear();
				
			}});
		
		acceptButton.addClickHandler(new ClickHandler(){

			@Override
			public void onClick(ClickEvent event) {
				doSave();
				
			}});
		
		imageField.addClickHandler(new ClickHandler(){

			@Override
			public void onClick(ClickEvent event) {
				fileUpload.getElement().<InputElement>cast().click();
				
			}});
		
		formPanel.addSubmitCompleteHandler(new SubmitCompleteHandler(){

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
					imageField.setUrl("uploads/" + product.getPro_image());
				}
				
			}});		
		
		fileUpload.addChangeHandler(new ChangeHandler(){

			@Override
			public void onChange(ChangeEvent event) {
				formPanel.submit();
				
			}});
	}
	
	private void doLoadImageGallery() {
		JQMUtils.popup("Cargando galeria ...");
		imageService.getAll(new AppAsyncCallback<List<String>>(){

			@Override
			public void onSuccess(List<String> result) {
				for(final String i:result) {
					Image image = new Image("uploads/" + i);
					image.addClickHandler(new ClickHandler(){

						@Override
						public void onClick(ClickEvent event) {
							onClickGalleryImage(i);
							
						}});
					
					imageGalleryContent.add(image);
				}
				
			}});
	}
	
	private void onClickGalleryImage(String i) {
		product.setPro_image(i);
		imageField.setUrl("uploads/" + product.getPro_image());
	}
	
	public void setData(Model model) {
		product = (Product) model;
		
		if(model == null)
			product = new Product();
		
		nameField.setValue(product.getPro_name());
		descriptionField.setValue(product.getPro_description());
		
		String image = EMPTY_IMAGE;
		if(product.getPro_image() != null)
			image = product.getPro_image();
		
		imageField.setUrl("uploads/" + image);
		product.setPro_image(image);
		
		formPanel.reset();
	}
	
	private void getData() {
		product.setPro_name(nameField.getValue());
		product.setPro_description(descriptionField.getValue());

	}
	
	private void doSave() {
		getData();
		
		service.saveOrUpdate(product, new AppAsyncCallback<Void>(){

			@Override
			public void onSuccess(Void result) {
				if(product.getPro_id() == null)
					setData(null);
				
				JQMUtils.focus("nameField");
				JQMUtils.popup("Elemento guardado.");
				
			}});
	}

	@Override
	public void go(HasWidgets widget) {
		go(widget, null);
		
	}
	
	public void go(HasWidgets widget, Model model) {		
		widget.clear();
		widget.add(this);
		
		setData(model);
		
		JQMUtils.triggerCreate("primary");
		JQMUtils.focus("nameField");
		
		imageGallery.collapse();
				
	}

}
