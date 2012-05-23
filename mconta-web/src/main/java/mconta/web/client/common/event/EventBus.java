/**
 * 
 */
package mconta.web.client.common.event;

import com.google.gwt.event.shared.HandlerManager;

/**
 * @author marc
 *
 */
public class EventBus extends HandlerManager {
	
	private static EventBus eventBus = new EventBus();

	public EventBus() {
		super(null);
		
	}
	
	public static EventBus getEventBus() {
		return eventBus;
		
	}

}
