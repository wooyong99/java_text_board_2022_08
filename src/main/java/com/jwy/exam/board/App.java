package com.jwy.exam.board;

import com.jwy.exam.board.container.Container;
import com.jwy.exam.board.dto.Member;

public class App {
  public static void main() {
    System.out.println("== 게시판 v 0.1 ==");
    System.out.println("== 프로그램 시작 ==");
    System.out.print("게시글 테스트 데이터 개수 입력 :");
    int testArticle_count = Integer.parseInt(Container.getSc().nextLine());
    Container.getUserArticleController().CreateTestArticle(testArticle_count);
    System.out.print("회원 테스트 데이터 개수 입력 :");
    int testMember_count = Integer.parseInt(Container.getSc().nextLine());
    Container.getUserMemberController().CreateTestMember(testMember_count);
    Session session = Container.getSession();
    while (true) {
      Member logined_member=(Member) session.getAttribute("logined_member");
      String prompt="";
      if(logined_member==null){
        prompt="명령";
      }else{
        prompt=logined_member.getId();
      }
      System.out.printf("%s ) ",prompt);
      String input=Container.getSc().nextLine();
      Rq rq = new Rq(input);
      if (input.equals("exit")) {
        break;
      } else if (rq.getUrl().equals("/usr/article/write")) {    //  Create (생성) 메소드
        Container.getUserArticleController().CreateArticle(rq,session);

      } else if (rq.getUrl().equals("/usr/article/detail")) {   //  Detail (읽기) 메소드
        Container.getUserArticleController().ArticleDetail(rq);

      } else if (rq.getUrl().equals("/usr/article/list")) {     //  List (출력) 메소드
        Container.getUserArticleController().ArticleSearch(rq);

      } else if (rq.getUrl().equals("/usr/article/update")) {   //  Search (검색) 메소드
        Container.getUserArticleController().Article_Update(rq);

      }else if(rq.getUrl().equals("/usr/article/delete")){      //  Delete (삭제) 메소드
        Container.getUserArticleController().ArticleDelete(rq);

      }else if(rq.getUrl().equals("/usr/member/login")){        //  Login (로그인) 메소드
        Container.getUserMemberController().Login(rq,session);

      }else if(rq.getUrl().equals("/usr/member/logout")){       //  Logout  (로그아웃) 메소드
        Container.getUserMemberController().Logout(session);

      } else if(rq.getUrl().equals("/usr/member/signup")){       //  Signup  (회원가입) 메소드
        Container.getUserMemberController().Signup(session);

      }
      System.out.printf("입력된 명령어 : %s\n",input);
      System.out.println("--------------------");
    }
    for (Integer num : Container.getUserArticleController().article.keySet()) {
      System.out.println(num + " - " + Container.getUserArticleController().article.get(num));
    }
    for(Member member:Container.getUserMemberController().members){
      System.out.println(member.toString());
    }
    System.out.println("== 프로그램 종료 ==");
    Container.getSc().close();
  }
}
