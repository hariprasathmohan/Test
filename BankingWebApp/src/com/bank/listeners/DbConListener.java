//$Id$
package com.bank.listeners;


import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.bank.utils.Utils;


public class DbConListener implements ServletContextListener {

    public void contextDestroyed(ServletContextEvent sce)  { 
		
    }
    public void contextInitialized(ServletContextEvent sce)  { 
       //  System.out.println("hello");
    	ServletContext ctx=sce.getServletContext();
    	Utils.connectionClass = ctx.getInitParameter("connectionClass");
    	Utils.connectionUrl = ctx.getInitParameter("connectionDriver");
    	Utils.connectionUsername = ctx.getInitParameter("connectionUserName");
    	Utils.connectionPassword = ctx.getInitParameter("connectionPassword");
    }
	
}
