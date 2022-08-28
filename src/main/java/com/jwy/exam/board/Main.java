package com.jwy.exam.board;


import com.jwy.exam.board.container.Container;
import com.jwy.exam.board.dto.Article;
import com.jwy.exam.board.service.ArticleService;
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
  @GetMapping("/")
  public String home(Model model){
    ArticleService articleService=Container.getArticleService();
    Map<Integer, Article> articles=articleService.getArticles();
    model.addAttribute("articles",articles);
    return "index";
  }
}