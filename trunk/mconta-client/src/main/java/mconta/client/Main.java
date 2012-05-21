package mconta.client;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import org.mortbay.jetty.Connector;
import org.mortbay.jetty.Server;
import org.mortbay.jetty.nio.SelectChannelConnector;
import org.mortbay.jetty.webapp.WebAppContext;
import org.mortbay.thread.QueuedThreadPool;

/**
 * Main is the main program class.
 * 
 * @author marc
 *
 */
public class Main extends JFrame {
    
	/**
	 * serialVersionUID is the serial version
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * server is the Jetty web server
	 */
	private static Server server;
	
	/**
	 * JETTY_PORT is the port of the Jetty server
	 */
	private static Integer JETTY_PORT = 8080;
	
	/**
	 * APP_MODULE_CONTEXT is the context of the application
	 */
	private static String APP_MODULE_CONTEXT = "mconta";
	
	/**
	 * Main() is the main application
	 * 
	 * @author marc
	 */
	public Main() {		
		setTitle("MCONTA");
		setSize(600, 400);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	/**
	 * main() is the main application 
	 * 
	 * @param args
	 * @throws Exception
	 */
	public static void main( String[] args ) throws Exception {
		
		server = new Server(JETTY_PORT);

		QueuedThreadPool threadPool = new QueuedThreadPool();
		threadPool.setMaxThreads(100);
		server.setThreadPool(threadPool);
		
		SelectChannelConnector connector = new SelectChannelConnector();
		connector.setPort(Integer.valueOf(JETTY_PORT));
		connector.setMaxIdleTime(30000);
		connector.setConfidentialPort(8443);
		server.setConnectors(new Connector[] {connector});
		
		WebAppContext mcontaApp = new WebAppContext();
		mcontaApp.setWar("../mconta-web/target/mconta-web-0.0.1-SNAPSHOT.war");
		mcontaApp.setContextPath("/" + APP_MODULE_CONTEXT);
		server.addHandler(mcontaApp);
				
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				Main main = new Main();
				main.setVisible(true);
				
			}
			
		});
		
		server.start();
		server.setStopAtShutdown(true);
		server.setSendServerVersion(true);
		server.join();
    }
	
}
