package mconta.client;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import org.mortbay.jetty.Connector;
import org.mortbay.jetty.Server;
import org.mortbay.jetty.nio.SelectChannelConnector;
import org.mortbay.jetty.webapp.WebAppContext;
import org.mortbay.thread.QueuedThreadPool;

public class Main extends JFrame {
    
	private static final long serialVersionUID = 1L;
	private static Server server;
	private static Integer JETTY_PORT = 8080;
	private static String MCONTA_MODULE_CONTEXT = "mconta";
	private static String MOBILE_MODULE_CONTEXT = "mobile";
	
	public Main() {		
		setTitle("MCONTA");
		setSize(600, 400);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

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
		mcontaApp.setContextPath("/" + MCONTA_MODULE_CONTEXT);
		server.addHandler(mcontaApp);

		WebAppContext mobileApp = new WebAppContext();
		mobileApp.setWar("../mconta-mobile/target/mconta-mobile-0.0.1-SNAPSHOT.war");
		mobileApp.setContextPath("/" + MOBILE_MODULE_CONTEXT);
		server.addHandler(mobileApp);
				
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
