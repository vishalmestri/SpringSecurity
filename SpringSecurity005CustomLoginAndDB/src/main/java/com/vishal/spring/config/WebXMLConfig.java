package com.vishal.spring.config;
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
		Class[] configurationClassArray = {SpringConfig.class};
		return configurationClassArray;
	}

	@Override
	protected String[] getServletMappings() {
		// TODO Auto-generated method stub
		String urls[]= {"/"};
		return urls;
	}

}
