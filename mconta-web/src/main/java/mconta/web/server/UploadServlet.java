/**
 * 
 */
package mconta.web.server;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;

/**
 * @author marc
 *
 */
public class UploadServlet extends HttpServlet {
	
	private static final String UPLOAD_DIRECTORY = "uploads";
	
	// 1048576 bytes = 1024 Kilobytes = 1 Megabyte
	private static final long UPLOAD_MAX_SIZE = 1048576;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		
		// process only multipart requests
		if (ServletFileUpload.isMultipartContent(req)) {
			
			// Create a factory for disk-based file items
			FileItemFactory factory = new DiskFileItemFactory();
			
			// Create a new file upload handler
			ServletFileUpload upload = new ServletFileUpload(factory);
			upload.setSizeMax(UPLOAD_MAX_SIZE);
			
			// Parse the request
			try {
				@SuppressWarnings("unchecked")
				List<FileItem> items = upload.parseRequest(req);
				
				for (FileItem item : items) {
					// process only file upload - discard other form item types
					if (item.isFormField()) continue;
		
					String fileName = item.getName();
					// get only the file name not whole path
					if (fileName != null) {
						fileName = FilenameUtils.getName(fileName);
					}
					
					// Check the mimetype of the file, only images allowed
					String mimetype = getServletContext().getMimeType(fileName);
					if(mimetype == null || !mimetype.startsWith("image")) {
						throw new IOException("Only image types.");
					}
					
					String root = getServletContext().getRealPath("/") + UPLOAD_DIRECTORY + 
							System.getProperty("file.separator");
					File file = new File(root, fileName);
					
					if(file.exists())
						file = renameFile(file);
					
					if (file.createNewFile()) {
						item.write(file);
						
						resp.setStatus(HttpServletResponse.SC_CREATED);
						resp.setContentType("text/html");
						resp.getWriter().printf("{ \"fileName\": \"%s\" }", fileName);
						resp.flushBuffer();
					} else
						throw new IOException("The file already exists in repository.");
					}
				} catch (Exception e) {
					resp.setStatus(HttpServletResponse.SC_CREATED);
					resp.setContentType("text/html");
					resp.getWriter().printf("{ \"error\": \"%s\" }", e.getMessage());
					resp.flushBuffer();
				}
			
				} else {
					resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
					resp.setContentType("text/html");
					resp.getWriter().printf("{ \"error\": \"%s\" }", 
							"Request contents type is not supported by the servlet.");
					resp.flushBuffer();
				}
		}
	
	private File renameFile(File file) {
		String name = file.getName();
		String body = name;
		String ext = "";
		
		int dot = name.lastIndexOf(".");
        if (dot != -1) {
        	body = name.substring(0, dot);
            ext = name.substring(dot); // includes "."
        }

		int count = 0;
		do {
			String newName = body + "." + count + ext;
			file = new File(file.getParent(), newName);
			count ++;
		} while(file.exists() && count < 9999);
		
		return file;
	}

}
