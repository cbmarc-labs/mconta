/**
 * 
 */
package mconta.web.client.rpc;

import mconta.web.shared.RecordDTO;

import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * @author Marc
 *
 */
@RemoteServiceRelativePath("recordService")
public interface RecordService extends CrudService<RecordDTO> {
		
}
