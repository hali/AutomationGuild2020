package com.sample.automation.website

class PropertyReader {
	Properties props = new Properties()
	File propsFile = new File('env.properties')
	
	String getBaseURL() {
		props.load(propsFile.newDataInputStream())
		return props.getProperty('website.url')
	}
}