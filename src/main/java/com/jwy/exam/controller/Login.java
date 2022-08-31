package com.jwy.exam.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class Login {
    @RequestMapping("/login")
    public String login(){
        return "login";
    }
    @RequestMapping("/login_check")
    public String login_check(String user_id, String user_pw, Model model){
        if(user_id.equals("admin") && user_pw.equals("1234")){
            return "login_success";
        }else{
            model.addAttribute("input_id",user_id);
            model.addAttribute("input_pw",user_pw);
            return "login_fail";
        }
    }
}
