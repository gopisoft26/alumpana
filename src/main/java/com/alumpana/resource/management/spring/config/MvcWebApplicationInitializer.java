package com.alumpana.resource.management.spring.config;
import javax.servlet.Filter;

import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;
import com.alumpana.resource.management.spring.WhatMan;

/**
 * @author Sivakumar ARUMUGAM
 * 
 */

public class MvcWebApplicationInitializer 
      extends AbstractAnnotationConfigDispatcherServletInitializer {

  // Load database and spring security configuration
  @Override
  protected Class<?>[] getRootConfigClasses() {
    return new Class[] { WhatMan.class, WebSecurityConfiguration.class };
  }

  // Load spring web configuration
  @Override
  protected Class<?>[] getServletConfigClasses() {
    return new Class[] { WhatMan.class };
  }

  @Override
  protected String[] getServletMappings() {
    return new String[] { "/" };
  }
  
  @Override
  protected Filter[] getServletFilters() {
  	Filter [] singleton = { new CORSFilter()};
  	return singleton;
  }

}