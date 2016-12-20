package com.tcs.tool.UI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.Set;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import com.tcs.application.Subscriber;
import com.tcs.application.SubscriptionEvent;
import com.tcs.tools.UI.utils.LayoutUtils;
import com.tcs.tools.config.ConfigElement;
import com.tcs.tools.config.Configuration;

public class ConfigurationDialog extends JDialog implements Subscriber {
	private JButton reload;
	private JLabel fileName;
	private JScrollPane scrollPane;
	private JPanel configView;
	private LayoutUtils layOutUtils;
	private boolean flag;
	
	public ConfigurationDialog() {
		this.setSize(600,600);
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.setLayout(new BorderLayout());
		reload = new JButton("Reload");
		configView = new JPanel();
		configView.setLayout(new GridBagLayout());
		scrollPane = new JScrollPane(configView);
		this.add(scrollPane,BorderLayout.CENTER);
		this.add(reload, BorderLayout.SOUTH);
		fileName = new JLabel();
		this.add(fileName, BorderLayout.NORTH);
		layOutUtils = LayoutUtils.getUtils("Configuration");
		
		reload.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				configView.removeAll();
//				configView.repaint();
				layOutUtils = LayoutUtils.getUtils(null);
				readConf();
				configView.revalidate();
				configView.repaint();
			}
		});
	}
	
	public void readConf(){
		Path filePath = Paths.get(System.getProperty("user.home")).resolve(".tcs").resolve("conf").resolve("nodeConnectorUISettings.conf");
		fileName.setText(filePath.toString());
		Configuration conf = new Configuration();
		conf.readConfiguration(filePath.toFile());
		Map<String, ConfigElement> configs = conf.getConfiguration();
		Set<String> confKeys = configs.keySet();
		for(String confKey:confKeys){
			addConfigurationElement(configs.get(confKey));
		}
	}

	@Override
	public void onSubscriptionEvent(SubscriptionEvent event) throws Exception {
		switch (event.getEvent()) {

		}
	}
	public void addConfigurationElement(ConfigElement element){
		JComponent comp = element.generateComponent();
		if(!flag){
			comp.setBorder(BorderFactory.createLineBorder(Color.BLUE));
		}
		flag = !flag;
		configView.add(comp,layOutUtils.getNextRowConstaints());
		System.out.println("Adding confgiration element:"+element);
	}
	
	public static void main(String args[]){		
		ConfigurationDialog dialog = new ConfigurationDialog();
		dialog.readConf();
		dialog.setVisible(true);
	}

}
