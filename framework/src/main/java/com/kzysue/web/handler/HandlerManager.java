package com.kzysue.web.handler;

import com.kzysue.web.mvc.Controller;
import com.kzysue.web.mvc.RequestMapping;
import com.kzysue.web.mvc.RequestParam;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.List;

/**
 * @author <a href="mailto:kzysure@kzysure.com">kzysure</a>
 * @version 1.0.0
 * @since 1.0.0
 */
public class HandlerManager {
  public static List<MappingHandler> mappingHandlerList = new ArrayList<>();
  public static void resolveMappingHandler(List<Class<?>> classList){
    for (Class<?> cls:classList){
      if(cls.isAnnotationPresent(Controller.class)){
        parseHandlerFromController(cls);
      }
      }
    }
    public static void parseHandlerFromController(Class<?> clazz){
      /**
       * get all methods from class.
       */
      Method[] methods = clazz.getDeclaredMethods();
      for (Method method:methods){
        /**
         * if not annotationed by requestMapping
         * continue directly
         */
        if (!method.isAnnotationPresent(RequestMapping.class)){
          continue;
        }
        String uri =method.getDeclaredAnnotation(RequestMapping.class).value();
        List<String> paramNameList = new ArrayList<>();
        for (Parameter parameter:method.getParameters()){
          if (parameter.isAnnotationPresent(RequestParam.class)){
            paramNameList.add(parameter.getDeclaredAnnotation(RequestParam.class).value());
          }
        }
        String[] params = paramNameList.toArray(new String[paramNameList.size()]);
        MappingHandler mappingHandler = new MappingHandler(uri,method,clazz,params);
        mappingHandlerList.add(mappingHandler);

      }

    }
  }



