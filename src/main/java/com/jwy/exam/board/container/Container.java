package com.jwy.exam.board.container;

import com.jwy.exam.board.Session;
import com.jwy.exam.board.controller.UserArticleController;
import com.jwy.exam.board.controller.UserMemberController;
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
  static{
    sc=new Scanner(System.in);
    userArticleController=new UserArticleController();
    userMemberController=new UserMemberController();
    session=new Session();
  }
}
