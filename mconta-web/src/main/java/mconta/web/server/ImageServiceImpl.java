/**
 * 
 */
package mconta.web.server;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import mconta.web.client.app.rpc.ImageService;
import mconta.web.shared.ServerException;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * @author Marc
 *
 */
@SuppressWarnings("serial")
public class ImageServiceImpl
	extends RemoteServiceServlet implements ImageService {
	
	private static final String UPLOAD_DIRECTORY = "uploads";
	
	public List<String> getAll() throws ServerException {
		List<String> list = new ArrayList<String>();
		
		String dirName = getServletContext().getRealPath("/") + UPLOAD_DIRECTORY;
		
		File dir = new File(dirName);
		File[] files = dir.listFiles();
		
		if(files != null) {			
			for (int it = 0; it < files.length; it++)
				list.add(files[it].getName());
		}
		
		return list;
		
	}
	
}
