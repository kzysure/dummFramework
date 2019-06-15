package com.kzysue.web.handler;

import com.kzysue.beans.BeanFactory;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author <a href="mailto:kzysure@kzysure.com">kzysure</a>
 * @version 1.0.0
 * @since 1.0.0
 */
public class MappingHandler {
  private String uri;
  private Method method;
  private Class<?> controller;
  private String[] args;

  public boolean handler(ServletRequest request, ServletResponse response)
      throws IllegalAccessException, InstantiationException, InvocationTargetException, IOException {
    if (!uri.equals(((HttpServletRequest)request).getRequestURI())){
      return false;
    }
    Object[] parameter = new Object[args.length];
    for (int i=0;i<args.length;i++) {
      Object param = request.getParameter(args[i]);
      parameter[i]=param;
    }
    Object ctl = BeanFactory.getBean(controller);
    Object resp = method.invoke(ctl,parameter);
    ((HttpServletResponse)response).setHeader("Content-type", "text/html;charset=UTF-8");
    response.setCharacterEncoding("UTF-8");
    response.getWriter().println(resp.toString());
    return true;
  }
  public MappingHandler(String uri, Method method, Class<?> controller, String[] args) {
    this.uri = uri;
    this.method = method;
    this.controller = controller;
    this.args = args;
  }
}
