package com.jwy.exam.board.controller;

import com.jwy.exam.board.Rq;
import com.jwy.exam.board.Session;
import com.jwy.exam.board.container.Container;
import com.jwy.exam.board.dto.Article;
import com.jwy.exam.board.dto.Board;
import com.jwy.exam.board.dto.Member;
import com.jwy.exam.board.service.ArticleService;
import com.jwy.exam.board.service.BoardService;
import com.jwy.exam.board.util.Util;

import java.text.SimpleDateFormat;
import java.util.*;

public class UserArticleController {
  ArticleService articleService ;
  Map<Integer,Article> articles ;
  BoardService boardService;
  public UserArticleController(){
    articleService =Container.getArticleService();
    articles=articleService.getArticles();
    boardService = Container.getBoardService();
  }
  // 테스트 입력 데이터 메서드
  public void CreateTestArticle(int test_article_count) {
    articleService.createTestDate(test_article_count);
  }

  // 게시글 생성 메서드
  public void CreateArticle(Rq rq) {
    String title, body; // 입력 변수 : 제목, 내용
    int board_id=rq.getIntparam("boardId",0);
    if(board_id==0){
      System.out.println("게시판 번호를 입력해주세요.");
      return ;
    }
    if(boardService.getBoardById(board_id)==null){
      System.out.println("해당 게시판은 존재하지 않습니다.");
      return ;
    }else{

    }
    System.out.printf("== %s 등록 ==\n",boardService.getBoardName(board_id));
    if(rq.islogined()){
      Member logined_member=(Member) rq.getSessionAttri("logined_member");
      System.out.println("작성자 : "+logined_member.getId());
      System.out.printf("제목 : ");
      title = Container.getSc().nextLine();
      System.out.printf("내용 : ");
      body = Container.getSc().nextLine();
      articleService.createArticle(board_id,logined_member.getId(), title, body, rq.getTime());
    }
  }

  // 입력 번호 게시글 출력 메서드
  public void ArticleDetail(Rq rq) {
    int param=rq.getIntparam("id",0);
    if(param==0){
      System.out.println("id값을 입력해주세요.");
    }else{
      Article article=articles.get(param);
      try{
        Board board=boardService.getBoardById(article.getBoard_id());
        System.out.printf("게시판 : %s\n작성자 : %s\n제목 : %s\n내용 : %s\n작성일자 : %s\n",
        boardService.getBoardName(article.getBoard_id()), article.getAuthor(), article.getTitle(), article.getBody(),article.getCreate_time());
      }catch(NullPointerException e){
        System.out.println(param+"번 게시글은 없는 게시글입니다.");
        return ;
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
      Article select_article=articles.get(id_param);
      if(articles.containsKey(id_param)){
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
    int board_id=rq.getIntparam("boardId",0);
    if(!(board_id==0) && boardService.getBoardById(board_id)!=null){
      for(Integer pk:articles.keySet()){
        if(articles.get(pk).getBoard_id()==board_id){
          result_list.add(articles.get(pk));
        }
      }
      System.out.printf("== %s 리스트 ==\n",boardService.getBoardName(board_id));
    }else{
      result_list= new ArrayList<>(articles.values());
      System.out.println("== 게시물 리스트 ==\n");
    }
    List<Article> filter_list=new ArrayList<>();
    String keyword_param=rq.getStrparam("searchKeyword","");
    if(keyword_param!=""){
      for (Article article : result_list) {
        if (article.getTitle().contains(keyword_param)) {
          filter_list.add(article);
        }
      }
    }else{
      filter_list=result_list;
    }
    String order_param=rq.getStrparam("orderBy","");
    if(order_param.equals("idAsc")) {
      for (Article article : filter_list) {
        System.out.println(boardService.getBoardName(article.getBoard_id()) + " / " + article.getAuthor() + " / " + article.getTitle() + " / " + article.getCreate_time());
      }
    }else{
      for(Article article: Util.reverseList(filter_list)){
        System.out.println(boardService.getBoardName(article.getBoard_id()) + " / " + article.getAuthor() + " / " + article.getTitle()+ " / " +article.getCreate_time());
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
      if(articles.containsKey(id_param)){
        articles.remove(id_param);
        System.out.println(id_param+"번 게시글이 삭제되었습니다.");
        return;
      }else{
        System.out.println(id_param+"번 게시글을 찾을 수 없습니다.");
      }
  }
}
}
