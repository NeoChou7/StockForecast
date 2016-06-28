package neo.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import neo.batch.BatchFetchData;

public class ContextStartupListener implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		new BatchFetchData().startFetch();
	}

}
