package com.jwy.exam.board.container;

import com.jwy.exam.board.Session;
import com.jwy.exam.board.controller.UserArticleController;
import com.jwy.exam.board.controller.UserMemberController;
import com.jwy.exam.board.repository.ArticleRepository;
import com.jwy.exam.board.repository.MemberRepository;
import com.jwy.exam.board.service.ArticleService;
import com.jwy.exam.board.service.MemberService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Scanner;
@Data
public class Container {
  @Getter
  static Scanner sc;
  @Getter
  static UserArticleController userArticleController;
  @Getter
  static UserMemberController userMemberController;
  @Getter
  static Session session;
  @Getter
  static ArticleRepository articleRepository;
  @Getter
  static ArticleService articleService;
  @Getter
  static MemberRepository memberRepository;
  @Getter
  static MemberService memberService;
  static{
    sc=new Scanner(System.in);
    articleRepository = new ArticleRepository();
    articleService = new ArticleService();
    memberRepository = new MemberRepository();
    memberService = new MemberService();
    userArticleController=new UserArticleController();
    userMemberController=new UserMemberController();
    session=new Session();
  }
}
