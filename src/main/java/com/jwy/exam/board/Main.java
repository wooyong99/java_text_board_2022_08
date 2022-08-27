package com.jwy.exam.board;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.*;

/*
public class Main {
  public static void main(String[] args){
    new App().main();
  }
}*/
@Controller
public class Main {

  @GetMapping("/Hello")
  public String home(Model model){
    System.out.println("5시간만에 intellij 에서tomcat, spring mvc 연결 성공....");
    return "index";
  }
}