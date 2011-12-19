package mconta.web.client.ui;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

public class AppDialogBox extends DialogBox {
	
	private Label dialogText;
    private Button affirmativeButton;
    private Button cancelButton;
    private VerticalPanel container;
	
	public AppDialogBox() {
		dialogText = new Label();
		
		affirmativeButton = new Button("Accept");
		cancelButton = new Button("Cancel");
		
		container = new VerticalPanel();
		
		setGlassEnabled(true);
        setAnimationEnabled(false);
        setModal(true);

        init();
		
	}
	
	private void init() {
		container.add(dialogText);
		
		HorizontalPanel hp = new HorizontalPanel();
        hp.add(affirmativeButton);
        hp.add(cancelButton);

        container.add(hp);
        this.add(container);
	}

}
