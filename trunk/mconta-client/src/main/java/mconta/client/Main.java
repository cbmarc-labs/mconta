package mconta.client;

import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import org.jdesktop.jdic.browser.WebBrowser;
import org.mortbay.jetty.Server;
import org.mortbay.jetty.webapp.WebAppContext;

public class Main extends JFrame {
    
	private static final long serialVersionUID = 1L;
	
	public Main() {
		WebBrowser webBrowser = new WebBrowser();
		try {
			webBrowser.setURL(new URL("http://localhost:8080/"));
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
			
		}
		
		setTitle("MCONTA");
		setSize(600, 400);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		add(webBrowser);
	}

	public static void main( String[] args ) throws Exception {
		//String webappDirLocation = "src/main/webapp/";
		String webappDirLocation = "../mconta-web/target/mconta-web-0.0.1-SNAPSHOT/";
		
		Server server = new Server(8080);
		
		WebAppContext webapp = new WebAppContext();
        webapp.setContextPath("/");
        webapp.setDescriptor(webappDirLocation + "/WEB-INF/web.xml");
        //webapp.setWar(webappDirLocation + "mconta-web-0.0.1-SNAPSHOT.war");
        webapp.setResourceBase(webappDirLocation);
        server.setHandler(webapp);
		server.isStarted();
				
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				Main main = new Main();
				main.setVisible(true);
				
			}
			
		});
		
		server.start();
		server.join();
    }
}
