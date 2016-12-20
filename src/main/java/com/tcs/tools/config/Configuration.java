package com.tcs.tools.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.Vector;

import com.tcs.application.Subscriber;
import com.tcs.application.SubscriptionEvent;

public class Configuration implements Subscriber {
	private Map<String, ConfigElement> configs = new HashMap<>();

	public boolean readConfiguration(File file) {
		if (!file.exists()) {
			return false;
		}
		Properties properties = new Properties();
		try {
			properties.load(new FileInputStream(file));
			if (!properties.isEmpty()) {
				Set<Object> keySet = properties.keySet();
				for (Object key : keySet) {
					if (key instanceof String) {
						String value = properties.getProperty((String) key);
						ConfigElement conf = new ConfigElement();
						conf.setName((String) key);
						conf.setValue(value);
						configs.put(conf.getName(), conf);
					}
				}
			}
			return true;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public Map<String, ConfigElement> getConfiguration(){
		return configs;
	}

	@Override
	public void onSubscriptionEvent(SubscriptionEvent event) throws Exception {

	}

}
