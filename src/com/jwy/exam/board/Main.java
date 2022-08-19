package com.jwy.exam.board;

import java.util.*;

public class Main {
  // 마지막 게시글을 나타내기 위한 변수
  static int last_index_num = 1;
  // {키:값} = {last_index_num, Article(last_index_num,title,body)}
  static Map<Integer, Article> article = new HashMap();

  // 테스트 입력 데이터 메서드
  /*static void CreateTestArticle(int test_article_count) {
    System.out.println("== 테스트 데이터 생성 시작 ==");
    for(int i=1;i<=test_article_count;i++){
      CreateArticle("사용자"+i,"제목"+i, "내용"+i);
    }
    System.out.println("== 테스트 데이터"+test_article_count+"개 생성 ==");
  }*/

  // 게시글 생성 메서드
  static void CreateArticle(Rq rq, Scanner sc) {
    String author= "",title="",body=""; // 입력 변수 : 작성자, 제목, 내용
    Map<String,String> params=rq.getParam();
    System.out.printf("== 게시물 등록 ==\n");
    if(params.containsKey("author")){
      System.out.println("작성자 : "+params.get("author"));
      System.out.printf("제목 : ");
      title = sc.nextLine();
      System.out.printf("내용 : ");
      body = sc.nextLine();
      article.put(last_index_num, new Article(last_index_num,params.get("author"), title, body));
      System.out.printf("%d번 게시물이 등록되었습니다.\n", last_index_num);
      last_index_num++;
    }else{
      System.out.println("작성자 : 비회원 (익명)");
      author="비회원 (익명)";
      System.out.printf("제목 : ");
      title = sc.nextLine();
      System.out.printf("내용 : ");
      body = sc.nextLine();
      article.put(last_index_num, new Article(last_index_num,author, title, body));
      System.out.printf("%d번 게시물이 등록되었습니다.\n", last_index_num);
      last_index_num++;
    }
  }

  // 최근 게시판 출력 메서드
  static void ArticleDetail() {
    System.out.println("== 최근 게시판 상세보기 ==");
    System.out.printf("번호 : %d\n제목 : %s\n내용 : %s\n", article.get(last_index_num - 1).pk, article.get(last_index_num - 1).title, article.get(last_index_num - 1).body);
  }

  // 입력 번호 게시글 출력 메서드
  static void ArticleDetail(int article_num) throws IndexOutOfBoundsException {
    System.out.println("== 게시판 상세보기 ==");
    System.out.printf("번호 : %d\n작성자 : %s\n제목 : %s\n내용 : %s\n", article.get(article_num).pk,article.get(article_num).author, article.get(article_num).title, article.get(article_num).body);
  }

  // 게시글 업데이트 메서드
  static void Article_Update(int article_num, String update_title, String update_body) {
    Article select_article = article.get(article_num);
    select_article.title = update_title;
    select_article.body = update_body;
    System.out.println(article_num+"번 게시글이 업데이트 되었습니다.");
  }
  //  게시글 검색 메서드
  static List<Article> ArticleSearch(String search_title){
    List<Article> search_list=new ArrayList<>();
    for(Article article:article.values()){
      if(article.title.contains(search_title)){
        search_list.add(article);
      }
    }
    return search_list;
  }

  public static void main(String args[]) {
    System.out.println("== 게시판 v 0.1 ==");
    System.out.println("== 프로그램 시작 ==");
    Scanner sc = new Scanner(System.in);
    String input = "";
    System.out.println("테스트 데이터 개수 입력 :");
    //CreateTestArticle(sc.nextInt());
    while (true) {
      String author= "",title="",body=""; // 입력 변수 : 작성자, 제목, 내용
      System.out.print("명령) ");
      input = sc.nextLine();
      Rq rq = new Rq(input);
      if (input.equals("exit")) {
        break;
      } else if (rq.getUrl().equals("/usr/article/write")) {
          CreateArticle(rq,sc);
      } else if (rq.getUrl().equals("/usr/article/detail")) {
        Map<String, String> param = rq.getParam();
        try {
          if (param.containsKey("id")) {                        //  경우 1) ArticleDetail 메소드 매개변수의 id 값이 게시글 수보다 크게 전달될때
            ArticleDetail(Integer.parseInt(param.get("id")));            // NullPointerException 발생 ! ( 재입력 요청 )
          } else {                                              //  경우 2) ArticleDetail 메소드 매개변수의 id 값이 정수가 아닌 형태로 전달될때,
            ArticleDetail(article.size());                               // NumberFormatException 발생 ! ( 재입력 요청 )
          }
        } catch (NumberFormatException e) {
          System.out.println("id 값이 올바르지 않습니다. 재입력 해주세요.");
        } catch (NullPointerException e) {
          System.out.println("최신(마지막) 게시글은 " + article.size() + "번 입니다. 재입력해주세요.");
        }
      } else if (rq.getUrl().equals("/usr/article/list")) {
        boolean orderByDesc=true;
        List<Article> result_list=new ArrayList<>(article.values());
        Map<String, String> param = rq.getParam();
        System.out.println("== 게시물 리스트 ==");
        System.out.println("--------------------");
        System.out.println("번호 / 작성자 / 제목");
        System.out.println("--------------------");
        if(param.containsKey("searchKeyword")){
          result_list=ArticleSearch(param.get("searchKeyword"));
        }
        if (param.containsKey("orderBy") && param.get("orderBy").equals("idAsc")) {
          orderByDesc=false;
        }
        if(orderByDesc){
          for(Article article:Util.reverseList(result_list)){
            System.out.println(article.pk+" / "+article.author+" / "+article.title);
          }
        }else{
          for(Article article:result_list){
            System.out.println(article.pk+" / "+article.author+" / "+article.title);
          }
        }
      } else if (rq.getUrl().equals("/usr/article/update")) {
        Map<String, String> param = rq.getParam();
        int id = 0;
        try {
          if (param.containsKey("id") == false) {
            throw new NullPointerException();    //  id 값이 null일때, NullPointerException 발생 ! (게시글 번호 작성 요청)
          }
          id = Integer.parseInt(param.get("id"));   //  id 값이 정수가 아닐때, NumberFormatException 발생 !  (정수 값으로 입력 요청)
          if (param.containsKey("id") && Integer.parseInt(param.get("id")) <= article.size()) {
            System.out.println("변경할 제목을 입력해주세요 :");
            String update_title = sc.nextLine();
            System.out.println("변경할 내용을 입력해주세요 :");
            String update_body = sc.nextLine();
            Article_Update(Integer.parseInt(param.get("id")), update_title, update_body);
          } else if (param.containsKey("id") && Integer.parseInt(param.get("id")) > article.size()) {
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
        }catch (Exception e){
          System.out.println(e.getMessage());
        }
      } else {
        System.out.printf("입력된 명령어 : %s\n", input);
      }
    }
    for (Integer num : article.keySet()) {
      System.out.println(num + " - " + article.get(num));
    }
    System.out.println("== 프로그램 종료 ==");
    sc.close();
  }
}

// 게시글 Class
class Article {
  int pk;
  String author;
  String title, body;

  // 회원 게시글 생성자 (고유번호, 사용자이름, 제목, 내용)
  Article(int pk, String author,String title, String body) {
    this.pk = pk;
    this.author=author;
    this.title = title;
    this.body = body;
  }
  // 비회원 (익명) 게시글 생성자 (고유번호, 제목, 내용)
  Article(int pk,String title, String body) {
    this.pk = pk;
    this.title = title;
    this.body = body;
  }

  // 출력 형태 : "id : 아이디 / title : 제목 / body : 내용"
  public String toString() {
    return "id : " + pk + " / author : "+author+" / title : " + title + " / body : " + body;
  }
}

class Rq {
  private String url;
  private Map<String, String> url_param;
  private String url_path;

  Rq(String url) {
    this.url = url;
    this.url_param = Util.getParameterFromUrl(url);
    this.url_path = Util.getUrlPathFromUrl(url);
  }

  public Map<String, String> getParam() {
    return url_param;
  }

  public String getUrl() {
    return url_path;
  }
}
class Util {
  public static<T> List<T> reverseList(List<T> list) {
    List<T> reverse = new ArrayList<>(list.size());

    for ( int i = list.size() - 1; i >= 0; i-- ) {
      reverse.add(list.get(i));
    }
    return reverse;
  }
  static Map<String, String> getParameterFromUrl(String queryString) {
    Map<String, String> parameter = new HashMap<>();
    String[] queryStrings = queryString.split("\\?", 2);
    if (queryStrings.length == 1) {
      return parameter;
    }
    String[] query = queryStrings[1].split("\\&");
    for (String bits : query) {
      String[] bit = bits.split("=");
      if (bit.length == 2) {
        parameter.put(bit[0], bit[1]);
      }
    }
    return parameter;
  }

  static String getUrlPathFromUrl(String queryString) {
    return queryString.split("\\?", 2)[0];
  }
}
