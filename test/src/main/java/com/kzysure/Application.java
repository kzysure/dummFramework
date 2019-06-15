package com.kzysure;

import com.kzysue.core.ClassScanner;
import com.kzysue.starter.StartRunApplication;
import java.util.List;

/**
 * @author <a href="mailto:kzysure@kzysure.com">kzysure</a>
 * @version 1.0.0
 * @since 1.0.0
 */
public class Application {

  public static void main(String[] args) {
    System.out.println("Hello Tiny-Framework!");
    StartRunApplication.run(Application.class,args);

  }
}
