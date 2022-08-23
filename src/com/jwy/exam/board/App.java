package com.jwy.exam.board;

import java.text.SimpleDateFormat;
import java.util.*;

public class App {
  public static void main() {
    System.out.println("== 게시판 v 0.1 ==");
    System.out.println("== 프로그램 시작 ==");
    System.out.print("테스트 데이터 개수 입력 :");
    int testArticle_count = Integer.parseInt(Container.sc.nextLine());
    Container.userArticleController.CreateTestArticle(testArticle_count);
    while (true) {
      System.out.print("명령) ");
      String input=Container.sc.nextLine();
      Rq rq = new Rq(input);
      if (input.equals("exit")) {
        break;
      } else if (rq.getUrl().equals("/usr/article/write")) {    //  Create (생성) 메소드
        Container.userArticleController.CreateArticle(rq);
      } else if (rq.getUrl().equals("/usr/article/detail")) {   //  Detail (읽기) 메소드
        Container.userArticleController.ArticleDetail(rq);
      } else if (rq.getUrl().equals("/usr/article/list")) {     //  List (출력) 메소드
        Container.userArticleController.ArticleSearch(rq);
      } else if (rq.getUrl().equals("/usr/article/update")) {   //  Search (검색) 메소드
        Container.userArticleController.Article_Update(rq);
      }else if(rq.getUrl().equals("/usr/article/delete")){      //  Delete (삭제) 메소드
        Container.userArticleController.ArticleDelete(rq);
      }else if(rq.getUrl().equals("/usr/member/login")){
        Container.userMemberController.Login();
      }
      System.out.printf("입력된 명령어 : %s\n",input);
      System.out.println("--------------------");
    }
    for (Integer num : Container.userArticleController.article.keySet()) {
      System.out.println(num + " - " + Container.userArticleController.article.get(num));
    }
    System.out.println("== 프로그램 종료 ==");
    Container.sc.close();
  }
}
