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
		
		Class [] classArray= {SpringMVCConfig.class};
		return classArray;
	}

	@Override
	protected String[] getServletMappings() {
		System.out.println("getServletMappings");
		String arg[]= {"/"};
		// TODO Auto-generated method stub
		return arg;
	}

}
