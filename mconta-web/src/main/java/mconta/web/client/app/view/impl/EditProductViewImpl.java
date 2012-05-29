/**
 * 
 */
package mconta.web.client.app.view.impl;

import mconta.web.client.app.presenter.impl.EditProductPresenter;
import mconta.web.client.common.ui.JQMButton;
import mconta.web.client.common.ui.JQMCollapsible;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FileUpload;
import com.google.gwt.user.client.ui.FormPanel;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author marc
 *
 */
public class EditProductViewImpl extends Composite implements EditProductPresenter.Display {
	
	private static EditProductViewImplUiBinder uiBinder = GWT
			.create(EditProductViewImplUiBinder.class);
	
	interface EditProductViewImplUiBinder extends
			UiBinder<Widget, EditProductViewImpl> {}
	
	@UiField FormPanel formPanel;
	@UiField FileUpload fileUpload;
	
	@UiField TextBox nameField;
	@UiField HasValue<String> descriptionField;
	@UiField Image imageField;
	
	@UiField JQMButton saveButton;
	
	@UiField JQMCollapsible imageGallery;
	@UiField HTMLPanel imageGalleryContent;
	
	public EditProductViewImpl() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	@Override
	public TextBox getName() {
		return nameField;
	}

	@Override
	public HasValue<String> getDescription() {
		return descriptionField;
	}

	@Override
	public Image getImage() {
		return imageField;
	}

	@Override
	public HasClickHandlers getSaveButton() {
		return saveButton;
	}

	@Override
	public FormPanel getFormPanel() {
		return formPanel;
	}

	@Override
	public FileUpload getFileUpload() {
		return fileUpload;
	}

	@Override
	public JQMCollapsible getCollapsible() {
		return imageGallery;
	}

	@Override
	public HTMLPanel getCollapsibleContent() {
		return imageGalleryContent;
	}

}
