package com.jwy.exam.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class Home {
    @RequestMapping("/")
    public String home(){
        return "index";
    }
    @RequestMapping("/free")
    public String free(){return "free";}
    @RequestMapping("/notice")
    public String notice(){return "notice";}

}
