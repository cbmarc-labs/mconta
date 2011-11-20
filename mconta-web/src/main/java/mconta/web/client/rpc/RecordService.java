/**
 * 
 */
package mconta.web.client.rpc;

import mconta.core.persistence.Record;

import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * @author Marc
 *
 */
@RemoteServiceRelativePath("recordService")
public interface RecordService extends CrudService<Record> {
		
}
