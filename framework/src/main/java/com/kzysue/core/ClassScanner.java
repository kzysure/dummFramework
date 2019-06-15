package com.kzysue.core;

import java.io.IOException;
import java.net.JarURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * @author <a href="mailto:kzysure@kzysure.com">kzysure</a>
 * @version 1.0.0
 * @since 1.0.0
 */
public class ClassScanner {
public static List<Class<?>> scanClasses(String packageName) throws IOException {
List<Class<?>> classList = new ArrayList<>();
String path = packageName.replace(".","/");
ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
  Enumeration<URL> resources = classLoader.getResources(path);
  while (resources.hasMoreElements()){
    URL resource =resources.nextElement();
    // 如果是jar包
    if (resource.getProtocol().contains("jar")){
      JarURLConnection jarURLConnection = (JarURLConnection)resource.openConnection();
      String jarFile = jarURLConnection.getJarFile().getName();
      classList.addAll(getClassesFromJar(jarFile,path));
    }else{
      // TODO 处理其他类扫描
    }
  }
return classList;
}
private static List<Class<?>> getClassesFromJar(String jarFilePath,String path) throws IOException {
List<Class<?>> classList = new ArrayList<>();
  JarFile jarFile = new JarFile(jarFilePath);
  Enumeration<JarEntry> jarEntries = jarFile.entries();
  while (jarEntries.hasMoreElements()){
    JarEntry jarEntry = jarEntries.nextElement();
    String entryName = jarEntry.getName();
    if(entryName.startsWith(path)&&entryName.endsWith(".class")){
      String classFullName = entryName.replace("/",".").substring(0,entryName.length()-6);
      try {
        classList.add(Class.forName(classFullName));
      } catch (ClassNotFoundException e) {
        e.printStackTrace();
      }
    }

  }
  return classList;
}
}
