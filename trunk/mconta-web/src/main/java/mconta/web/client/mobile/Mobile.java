package mconta.web.client.mobile;

import mconta.web.client.mobile.view.EditViewImpl;
import mconta.web.client.mobile.view.JQMPage;
import mconta.web.client.mobile.view.LoginViewImpl;
import mconta.web.client.mobile.view.PopUpViewImpl;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.dom.client.Document;

public class Mobile implements EntryPoint {

	public void onModuleLoad() {
		setShowTrigger(this);
		
		Document.get().getBody().appendChild(new JQMPage().getElement()); 
		Document.get().getBody().appendChild(new LoginViewImpl().getElement());
		Document.get().getBody().appendChild(new PopUpViewImpl().getElement());
		Document.get().getBody().appendChild(new EditViewImpl().getElement());
		
		jqmInit();
	}
	
	public static native void errorAlert(String msg) /*-{
		
		$doc.getElementById("popupviewimpltext").innerHTML = msg;
		$wnd.$.mobile.changePage('#popupviewimpl');
	  
	}-*/;
	
	public void compute(String data) {

		errorAlert(data);

	}
	
	public native void setShowTrigger(Mobile x)/*-{
		
		$wnd.compute = function (data) {
			x.@mconta.web.client.mobile.Mobile::compute(Ljava/lang/String;)(data);
		};

	}-*/;
	
	native void jqmInit() /*-{
		
		$wnd.$.mobile.changePage("#page1", "pop", false, true);
		
	}-*/;
  
}
