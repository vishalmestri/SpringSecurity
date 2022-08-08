package com.vishal.spring.security.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class WebXMLConfig extends AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	protected Class<?>[] getRootConfigClasses() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		// TODO Auto-generated method stub
		
		Class []  classarray= { SpringMVCConfig.class};
		return classarray;
	}

	@Override
	protected String[] getServletMappings() {
		// TODO Auto-generated method stub
		String urls[]= {"/"};
		return urls;
	}

}
