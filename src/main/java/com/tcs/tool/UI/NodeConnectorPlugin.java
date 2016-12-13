/* ********************************************************************************
 * All rights reserved.
 ******************************************************************************* */
package com.tcs.tool.UI;

import com.tcs.application.AbstractPlugin;
import com.tcs.application.Application;
import com.tcs.application.Subscriber;
import com.tcs.application.SubscriptionEvent;
import com.tcs.application.pluign.PluginManager;

public class NodeConnectorPlugin extends AbstractPlugin implements Subscriber {

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.tcs.application.Subscriber#onSubscriptionEvent(com.tcs.application.
	 * SubscriptionEvent)
	 */
	private boolean started;

	@Override
	public void onSubscriptionEvent(final SubscriptionEvent event) {
	}

	public void startUIPlugin() {
		if (!started) {
			final MainWindow window = new MainWindow();
			window.requestFocus();
			window.setVisible(true);
			/*
			 * StartUi starter = new StartUi(window); try {
			 * SwingUtilities.invokeAndWait(starter); } catch
			 * (InvocationTargetException e) { e.printStackTrace(); } catch
			 * (InterruptedException e) { e.printStackTrace(); }
			 */
			started = true;
			Application.getSubscriptionManager().notifySubscriber(PluginManager.PLUGIN_STARTED, this);
		}
	}

	public void stopPlugin() {

	}

}
