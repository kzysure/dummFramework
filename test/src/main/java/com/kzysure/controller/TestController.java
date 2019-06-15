package com.kzysure.controller;

import com.kzysue.beans.AutoWired;
import com.kzysue.web.mvc.Controller;
import com.kzysue.web.mvc.RequestMapping;
import com.kzysue.web.mvc.RequestParam;
import com.kzysure.service.SalaryService;

/**
 * @author <a href="mailto:kzysure@kzysure.com">kzysure</a>
 * @version 1.0.0
 * @since 1.0.0
 */
@Controller
public class TestController {
  @AutoWired
  private SalaryService salaryService;
@RequestMapping("/getSalary")
  public String test(@RequestParam("name") String name,@RequestParam("exp")String exp){
  System.out.println("name:"+name+"||exp:"+exp);
  Integer sum = 1000*Integer.valueOf(exp);
return name+"的工资为"+salaryService.calcuSalary(Integer.valueOf(exp)).toString()+"元";
}
}
