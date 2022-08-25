package com.jwy.exam.board.controller;

import com.jwy.exam.board.Rq;
import com.jwy.exam.board.Session;
import com.jwy.exam.board.container.Container;
import com.jwy.exam.board.dto.Article;
import com.jwy.exam.board.dto.Member;
import com.jwy.exam.board.util.Util;

import java.text.SimpleDateFormat;
import java.util.*;

public class UserArticleController {
  // 마지막 게시글을 나타내기 위한 변수
  static int last_index_num = 1;
  // {키:값} = {last_index_num, Article(last_index_num,title,body)}
  public static Map<Integer, Article> article = new HashMap();
  // 테스트 입력 데이터 메서드
  public void CreateTestArticle(int test_article_count) {
    System.out.println("== 게시글 테스트 데이터 생성 시작 ==");
    for (int i = 1; i <= test_article_count; i++) {
      Date date=new Date();
      SimpleDateFormat create_time=new SimpleDateFormat("yy-MM-dd HH:mm");
      article.put(last_index_num, new Article(last_index_num, "사용자" + i, "제목" + i, "내용" + i,create_time.format(date)));
      last_index_num++;
    }
    System.out.println("       Loading ...   ");
    System.out.println("== 게시글 테스트 데이터 " + (last_index_num - 1) + "개 생성 ==");
  }

  // 게시글 생성 메서드
  public void CreateArticle(Rq rq) {
    String title, body; // 입력 변수 : 제목, 내용
    Date date=new Date();
    SimpleDateFormat create_time=new SimpleDateFormat("yy-MM-dd HH:mm");
    System.out.printf("== 게시물 등록 ==\n");
    if(rq.islogined()){
      Member logined_member=(Member) rq.getSessionAttri("logined_member");
      System.out.println("작성자 : "+logined_member.getId());
      System.out.printf("제목 : ");
      title = Container.getSc().nextLine();
      System.out.printf("내용 : ");
      body = Container.getSc().nextLine();
      article.put(last_index_num, new Article(last_index_num, logined_member.getId(), title, body, create_time.format(date)));
      System.out.printf("%d번 게시물이 등록되었습니다.\n", last_index_num);
      last_index_num++;
    }else{
      System.out.println("작성자 : 비회원 (익명)");
      System.out.printf("제목 : ");
      title = Container.getSc().nextLine();
      System.out.printf("내용 : ");
      body = Container.getSc().nextLine();
      article.put(last_index_num, new Article(last_index_num, "비회원 (익명)", title, body, create_time.format(date)));
      System.out.printf("%d번 게시물이 등록되었습니다.\n", last_index_num);
      last_index_num++;
    }
  }

  // 입력 번호 게시글 출력 메서드
  public void ArticleDetail(Rq rq) {
    int param=rq.getIntparam("id",0);
    if(param==0){
      System.out.println("id값을 입력해주세요.");
    }else{
      try{
        System.out.printf("번호 : %d\n작성자 : %s\n제목 : %s\n내용 : %s\n작성일자 : %s\n",
        article.get(param).getPk(), article.get(param).getAuthor(), article.get(param).getTitle(), article.get(param).getBody(),article.get(param).getCreate_time());
      }catch(NullPointerException e){
        System.out.println(param+"번 게시글은 없는 게시글입니다.");
      }

    }
  }

  // 게시글 업데이트 메서드
  public void Article_Update(Rq rq) {
    Map<String, String> params = rq.getParam();
    int id_param=rq.getIntparam("id",0);
    if(id_param==0){
      System.out.println("게시글 번호를 입력해주세요.");
      return ;
    }else{
      Article select_article=article.get(id_param);
      if(article.containsKey(id_param)){
        System.out.print("변경할 제목을 입력해주세요 :");
        String update_title = Container.getSc().nextLine();
        System.out.print("변경할 내용을 입력해주세요 :");
        String update_body = Container.getSc().nextLine();
        select_article.setTitle(update_title);
        select_article.setBody(update_body);
        System.out.println(id_param+"번 게시글 변경되었습니다.");
      }else{
        System.out.println(id_param+"번 게시글은 없는 게시글입니다.");
      }
    }
  }

  //  게시글 검색 메서드
  public void ArticleSearch(Rq rq) {
    List<Article> result_list = new ArrayList<>();
    Map<String, String> params = rq.getParam();
    System.out.println("== 게시물 리스트 ==");
    String keyword_param=rq.getStrparam("searchKeyword","");
    if(keyword_param==""){
      result_list=new ArrayList<>(article.values());
    }else{
      for (Article article : article.values()) {
        if (article.getTitle().contains(params.get("searchKeyword"))) {
          result_list.add(article);
        }
      }
    }
    String order_param=rq.getStrparam("orderBy","");
    if(order_param.equals("idAsc")) {
      for (Article article : result_list) {
        System.out.println(article.getPk() + " / " + article.getAuthor() + " / " + article.getTitle() + " / " + article.getCreate_time());
      }
    }else{
      for(Article article: Util.reverseList(result_list)){
        System.out.println(article.getPk() + " / " + article.getAuthor() + " / " + article.getTitle()+ " / " +article.getCreate_time());
      }
    }
  }
  // 게시글 삭제 메서드
  public void ArticleDelete(Rq rq){
    Map<String, String> params=rq.getParam();
    int id_param=rq.getIntparam("id",0);
    if(id_param==0){
      System.out.println("삭제할 게시글 번호를 입력해주세요.");
      return ;
    }else{
      if(article.containsKey(id_param)){
        article.remove(id_param);
        System.out.println(id_param+"번 게시글이 삭제되었습니다.");
        return;
      }else{
        System.out.println(id_param+"번 게시글을 찾을 수 없습니다.");
      }
  }
}
}
