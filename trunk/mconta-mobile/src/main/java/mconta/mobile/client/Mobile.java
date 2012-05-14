package mconta.mobile.client;

import mconta.mobile.client.view.JQMPage;
import mconta.mobile.client.view.LoginViewImpl;
import mconta.mobile.client.view.PopUpViewImpl;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.dom.client.Document;

public class Mobile implements EntryPoint {

	public void onModuleLoad() {
		setShowTrigger(this);
		
		Document.get().getBody().appendChild(new JQMPage().getElement()); 
		Document.get().getBody().appendChild(new LoginViewImpl().getElement());
		Document.get().getBody().appendChild(new PopUpViewImpl().getElement());
		
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
			x.@mconta.mobile.client.Mobile::compute(Ljava/lang/String;)(data);
		};

	}-*/;
	
	native void jqmInit() /*-{
		
		$wnd.$.mobile.changePage("#page1", "pop", false, true);
		
	}-*/;
  
}
