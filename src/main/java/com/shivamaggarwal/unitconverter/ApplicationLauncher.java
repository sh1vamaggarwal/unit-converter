package com.shivamaggarwal.unitconverter;

import com.shivamaggarwal.unitconverter.context.ApplicationConfiguration;
import jakarta.servlet.ServletContext;
import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class ApplicationLauncher {

    public static void main(String[] args) throws LifecycleException {
        Tomcat tomcat = new Tomcat();
        tomcat.setPort(8080);
        tomcat.getConnector();

        Context tomcatContext = tomcat.addContext("", null);
        var servletContext = tomcatContext.getServletContext();
        var webContext = createWebContext(servletContext);
        var dispatcherServlet = new DispatcherServlet(webContext);
        var wrapper = Tomcat.addServlet(tomcatContext, "dispatcherServlet", dispatcherServlet);
        wrapper.setLoadOnStartup(1);
        wrapper.addMapping("/*");

        tomcat.start();

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            try {
                System.out.println("Shutting down Tomcat...");
                tomcat.stop();
                tomcat.destroy(); // optional but clean
            } catch (Exception e) {
                e.printStackTrace();
            }
        }));

        tomcat.getServer().await();
    }

    public static WebApplicationContext createWebContext(ServletContext servletContext) {
       var context = new AnnotationConfigWebApplicationContext();
       context.register(ApplicationConfiguration.class);
       context.setServletContext(servletContext);
       context.refresh();
       context.registerShutdownHook();
       return context;
    }
}
