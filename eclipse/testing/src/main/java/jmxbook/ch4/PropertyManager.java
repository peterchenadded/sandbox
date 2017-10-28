package jmxbook.ch4;

import java.io.FileInputStream;
import java.util.Enumeration;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PropertyManager implements PropertyManagerMBean {
	
	static final Logger LOG = LoggerFactory.getLogger(PropertyManager.class);
	private Properties props = null;
	
	public PropertyManager(String path) {
		loadPath(path);
	}
	
	protected void loadPath(String path) {
	   try {
			// load supplied property file
			props = new Properties();
			FileInputStream f = new FileInputStream(path);
			props.load(f);
			f.close();
		} catch(Exception ex) {
			LOG.error("Failed to load file", ex);
		}
	}

	@Override
	public String getProperty(String key) {
		return props.getProperty(key);
	}

	@Override
	public void setProperty(String key, String value) {
		props.setProperty(key, value);
	}

	@Override
	public Enumeration keys() {
		return props.keys();
	}

	@Override
	public void setSource(String path) {
		loadPath(path);
	}

}
