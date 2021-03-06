/* ********************************************************************************
 * All rights reserved.
 ******************************************************************************* */
package com.tcs.tool.UI;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;

import com.tcs.application.Application;
import com.tcs.application.Subscriber;
import com.tcs.application.SubscriptionEvent;
import com.tcs.tools.UI.utils.DialogUtils;

public class CreditsDialog extends JDialog implements Subscriber {
	private static final long serialVersionUID = 1L;
	public static final String CREDITS_SHOW = "creditsShow";
	private final JLabel lable = new JLabel();
	private final JButton ok = new JButton();

	public CreditsDialog() {

		Application.getSubscriptionManager().subscribe(this, CREDITS_SHOW);
		this.setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);
		DialogUtils.addHideOnEscapeListener(this);
		this.setSize(400, 300);
		this.setResizable(false);
		DialogUtils.setCenterLocation(this);
		this.setTitle("Credits");
		ok.setText("OK");
		this.setLayout(new BorderLayout());
		final String text = "<html><body>" + "<center>" + "<h2><span style=\"color: #339966;\">NODE Netconf Connector</span></h2>"
				+ "<h3><span style=\"color: #ff6600;\">Version : 1.0-alpha</span></h3>" + "<hr />"
				+ "<h4><span style=\"color: #339966;\">Developer : Kiran Paladugu</span></h4>"
				+ "<p><span style=\"color: #333300;\">&nbsp;Please give your feedback  and sugegestions to improve the tool.&nbsp;</span></p>"
				+ "<p><span style=\"text-decoration: underline;\"><span style=\"color: #0000ff; text-decoration: underline;\">paladugukiran@gmail.com</span></span></p>"
				+ "<p>&nbsp;</p>" + "</center>" + "</body></html>";
		lable.setText(text);
		ok.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(final ActionEvent e) {
				setVisible(false);
			}
		});
		this.add(lable, BorderLayout.CENTER);
		this.add(ok, BorderLayout.SOUTH);
	}

	@Override
	public void onSubscriptionEvent(final SubscriptionEvent event) throws Exception {
		switch (event.getEvent()) {
		case CREDITS_SHOW:
			this.setVisible(true);
			break;
		default:
			break;
		}
	}
}
