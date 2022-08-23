package com.jwy.exam.board;

import java.text.SimpleDateFormat;
import java.util.*;

public class UserArticleController {
  // 마지막 게시글을 나타내기 위한 변수
  static int last_index_num = 1;
  // {키:값} = {last_index_num, Article(last_index_num,title,body)}
  static Map<Integer, Article> article = new HashMap();
  Scanner sc=Container.sc;
  // 테스트 입력 데이터 메서드
  void CreateTestArticle(int test_article_count) {
    System.out.println("== 테스트 데이터 생성 시작 ==");
    for (int i = 1; i <= test_article_count; i++) {
      Date date=new Date();
      SimpleDateFormat create_time=new SimpleDateFormat("yy-MM-dd HH:mm");
      article.put(last_index_num, new Article(last_index_num, "사용자" + i, "제목" + i, "내용" + i,create_time.format(date)));
      last_index_num++;
    }
    System.out.println("       Loading ...   ");
    System.out.println("== 테스트 데이터 " + (last_index_num - 1) + "개 생성 ==");
  }

  // 게시글 생성 메서드
  void CreateArticle(Rq rq) {
    String title, body; // 입력 변수 : 작성자, 제목, 내용
    Date date=new Date();
    SimpleDateFormat create_time=new SimpleDateFormat("yy-MM-dd HH:mm");
    Map<String, String> params = rq.getParam();
    System.out.printf("== 게시물 등록 ==\n");
    if (params.containsKey("author")) {
      System.out.println("작성자 : " + params.get("author"));
      System.out.printf("제목 : ");
      title = Container.sc.nextLine();
      System.out.printf("내용 : ");
      body = Container.sc.nextLine();
      article.put(last_index_num, new Article(last_index_num, params.get("author"), title, body, create_time.format(date)));
      System.out.printf("%d번 게시물이 등록되었습니다.\n", last_index_num);
      last_index_num++;
    } else {
      System.out.println("작성자 : 비회원 (익명)");
      System.out.printf("제목 : ");
      title = Container.sc.nextLine();
      System.out.printf("내용 : ");
      body = Container.sc.nextLine();
      article.put(last_index_num, new Article(last_index_num, title, body, create_time.format(date)));
      System.out.printf("%d번 게시물이 등록되었습니다.\n", last_index_num);
      last_index_num++;
    }
  }

  // 입력 번호 게시글 출력 메서드
  void ArticleDetail(Rq rq) {
    Map<String, String> params = rq.getParam();
    int id = 0;
    try {
      if (params.containsKey("id")) {                        //  경우 1) ArticleDetail 메소드 매개변수의 id 값이 게시글 수보다 크게 전달될때
        id = Integer.parseInt(params.get("id"));                          // NullPointerException 발생 ! ( 재입력 요청 )
        System.out.println("== "+params.get("id")+"번 게시판 상세보기 ==");
        System.out.printf("번호 : %d\n작성자 : %s\n제목 : %s\n내용 : %s\n작성일자 : %s\n",
            article.get(Integer.parseInt(params.get("id"))).pk, article.get(Integer.parseInt(params.get("id"))).author, article.get(Integer.parseInt(params.get("id"))).title, article.get(Integer.parseInt(params.get("id"))).body,article.get(Integer.parseInt(params.get("id"))).create_time);
      } else {                                              //  경우 2) ArticleDetail 메소드 매개변수의 id 값이 정수가 아닌 형태로 전달될때,
        System.out.println("== 최근 게시판 상세보기 ==");                   // NumberFormatException 발생 ! ( 재입력 요청 )
        System.out.printf("번호 : %d\n작성자 : %s\n제목 : %s\n내용 : %s\n작성일자 : %s\n",
            article.get(article.size()).pk, article.get(article.size()).author, article.get(article.size()).title, article.get(article.size()).body,article.get(article.size()).create_time);
      }
    } catch (NumberFormatException e) {
      System.out.println("id 값이 올바르지 않습니다. 재입력 해주세요.");
    } catch (NullPointerException e) {
      System.out.println(params.get("id") + "번 게시글은 없는 게시글입니다.");
    }
  }

  // 게시글 업데이트 메서드
  void Article_Update(Rq rq) {
    Map<String, String> params = rq.getParam();
    int id = 0;
    try {
      if (params.containsKey("id") == false) {
        throw new NullPointerException();    //  id 값이 null일때, NullPointerException 발생 ! (게시글 번호 작성 요청)
      }
      id = Integer.parseInt(params.get("id"));   //  id 값이 정수가 아닐때, NumberFormatException 발생 !  (정수 값으로 입력 요청)
      if (params.containsKey("id") && Integer.parseInt(params.get("id")) <= article.size()) {
        System.out.print("변경할 제목을 입력해주세요 :");
        String update_title = Container.sc.nextLine();
        System.out.print("변경할 내용을 입력해주세요 :");
        String update_body = Container.sc.nextLine();
        Article select_article = article.get(Integer.parseInt(params.get("id")));
        select_article.title = update_title;
        select_article.body = update_body;
        System.out.println(select_article.pk + "번 게시글이 업데이트 되었습니다.");
      } else if (params.containsKey("id") && Integer.parseInt(params.get("id")) > article.size()) {
        throw new IndexOutOfBoundsException();   //  id 값이 잘 주입 되었지만, 게시글수 보다 클 경우, IndexOutOfBoundsException 발생 ! (재입력 요청)
      } else {
        throw new Exception();    //  예외 경우의 수 발생 시, Exception 발생 ! (오류 메세지 출력)
      }
    } catch (NullPointerException e) {
      System.out.println("업데이트할 게시글 번호를 입력해주세요.");
    } catch (NumberFormatException e) {
      System.out.println("id값은 정수형태로 입력해주세요.");
    } catch (IndexOutOfBoundsException e) {
      System.out.println("최신(마지막) 게시글은 " + article.size() + "번 입니다. 재입력해주세요.");
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }

  //  게시글 검색 메서드
  void ArticleSearch(Rq rq) {
    List<Article> result_list = new ArrayList<>();
    Map<String, String> params = rq.getParam();
    System.out.println("== 게시물 리스트 ==");
    System.out.println("--------------------");
    System.out.println("번호 / 작성자 / 제목 / 작성일자");
    System.out.println("--------------------");
    if (params.containsKey("searchKeyword")) {
      for (Article article : article.values()) {
        if (article.title.contains(params.get("searchKeyword"))) {
          result_list.add(article);
        }
      }
    } else {
      result_list = new ArrayList<>(article.values());
    }
    if (params.containsKey("orderBy") && params.get("orderBy").equals("idAsc")) {
      for (Article article : result_list) {
        System.out.println(article.pk + " / " + article.author + " / " + article.title+ " / " +article.create_time);
      }
    } else {
      for (Article article : Util.reverseList(result_list)) {
        System.out.println(article.pk + " / " + article.author + " / " + article.title+ " / " +article.create_time);
      }
    }
  }
  // 게시글 삭제 메서드
  void ArticleDelete(Rq rq){
    Map<String, String> params=rq.getParam();
    int rq_id=0;
    try{
      if(params.containsKey("id") == false){
        throw new NullPointerException();
      }
      rq_id=Integer.parseInt(params.get("id"));
      if(params.containsKey("id") && article.containsKey(rq_id)){
        article.remove(rq_id);
        System.out.println("== 게시물 리스트 ==");
        System.out.println("--------------------");
        System.out.println("번호 / 작성자 / 제목 / 작성일자");
        System.out.println("--------------------");
        for(Integer pk:article.keySet()){
          System.out.println(pk + " / " + article.get(pk).author + " / " + article.get(pk).title+" / " + article.get(pk).create_time);
        }
        System.out.println(rq_id+"번 게시글이 삭제되었습니다.");
      }else if(params.containsKey("id") && (article.containsKey(rq_id)==false)){
        throw new IndexOutOfBoundsException();
      }
    }catch(NullPointerException e){
      System.out.println("삭제하실 게시물을 입력해주세요.");
    }catch (NumberFormatException e){
      System.out.println("id값을 정수 형태로 입력해주세요.");
    }catch(IndexOutOfBoundsException e){
      System.out.println(rq_id+"번 게시글을 찾을 수 없습니다");
    }
  }
}
