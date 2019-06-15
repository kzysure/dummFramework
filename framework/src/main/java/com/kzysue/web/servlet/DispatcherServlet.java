package com.kzysue.web.servlet;

import com.kzysue.web.handler.HandlerManager;
import com.kzysue.web.handler.MappingHandler;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * @author <a href="mailto:kzysure@kzysure.com">kzysure</a>
 * @version 1.0.0
 * @since 1.0.0
 */
public class DispatcherServlet implements Servlet {

  @Override
  public void init(ServletConfig config) throws ServletException {

  }

  @Override
  public ServletConfig getServletConfig() {
    return null;
  }

  @Override
  public void service(ServletRequest req, ServletResponse res)
      throws ServletException, IOException {
//    res.getWriter().println("test");
    for (MappingHandler mappingHandler: HandlerManager.mappingHandlerList){
      try {
        if (mappingHandler.handler(req,res)){
          return;
        }
      } catch (IllegalAccessException e) {
        e.printStackTrace();
      } catch (InstantiationException e) {
        e.printStackTrace();
      } catch (InvocationTargetException e) {
        e.printStackTrace();
      }
    }
  }

  @Override
  public String getServletInfo() {
    return null;
  }

  @Override
  public void destroy() {

  }
}
