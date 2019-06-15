package com.kzysue.starter;

import com.kzysue.beans.BeanFactory;
import com.kzysue.core.ClassScanner;
import com.kzysue.web.handler.HandlerManager;
import com.kzysue.web.server.TomcatServer;
import java.util.List;
import org.apache.catalina.LifecycleException;

/**
 * @author <a href="mailto:kzysure@kzysure.com">kzysure</a>
 * @version 1.0.0
 * @since 1.0.0
 */
public class StartRunApplication {
public static void run(Class<?> cls,String[] args){
  TomcatServer tomcatServer = new TomcatServer(args);
  try {
    tomcatServer.StartServer();
    List<Class<?>> classList = ClassScanner.scanClasses("com.kzysure");
    HandlerManager.resolveMappingHandler(classList);
    BeanFactory.initBean(classList);
  }catch (Exception e){
    e.printStackTrace();
  }
  System.out.println("Hello Framework!");
}
}
