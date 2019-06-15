package com.kzysue.web.server;

import com.kzysue.web.servlet.DispatcherServlet;
import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.startup.Tomcat;
import org.apache.catalina.startup.Tomcat.FixContextListener;

/**
 * @author <a href="mailto:kzysure@kzysure.com">kzysure</a>
 * @version 1.0.0
 * @since 1.0.0
 */
public class TomcatServer {
  private Tomcat tomcat;
  private String[] args;

  public TomcatServer(String[] args) {
    this.args = args;
  }
  public void StartServer() throws LifecycleException {
    tomcat = new Tomcat();
    tomcat.setPort(6699);
    tomcat.start();
    Context context = new StandardContext();
    context.setPath("");
    context.addLifecycleListener(new FixContextListener());
    DispatcherServlet servlet = new DispatcherServlet();
    Tomcat.addServlet(context,"dispathcherServlet",servlet).setAsyncSupported(true);
    context.addServletMappingDecoded("/","dispathcherServlet");
    tomcat.getHost().addChild(context);
    Thread thread = new Thread("tomcat_await_thred"){
      @Override
      public void run(){
        TomcatServer.this.tomcat.getServer().await();
      }
    };
    thread.setDaemon(false);
    thread.start();
  }
}
