package com.jwy.exam.board;


import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
public class Main {
  // 마지막 게시글을 나타내기 위한 변수
  static int last_index_num=1;
  // {키:값} = {last_index_num, Article(last_index_num,title,body)}
  static Map <Integer, Article> article=new HashMap();
  // 테스트 입력 데이터 메서드
  static void CreateTestArticle(){
    CreateArticle("제목 1","내용 1");
    CreateArticle("제목 2","내용 2");
    CreateArticle("제목 3","내용 3");
    CreateArticle("제목 4","내용 4");
    CreateArticle("제목 5","내용 5");
    CreateArticle("제목 6","내용 6");
  }
  // 게시글 생성 메서드
  static void CreateArticle(String title,String body){
    article.put(last_index_num,new Article(last_index_num,title,body));
    System.out.printf("%d번 게시물이 등록되었습니다.\n",last_index_num);
    last_index_num++;
  }
  // 최근 게시판 출력 메서드
  static void ArticleDetail(){
    System.out.println("== 최근 게시판 상세보기 ==");
    System.out.printf("번호 : %d\n제목 : %s\n내용 : %s\n",article.get(last_index_num-1).id,article.get(last_index_num-1).title,article.get(last_index_num-1).body);
  }
  // 입력 번호 게시글 출력 메서드
  static void ArticleDetail(int article_num)throws IndexOutOfBoundsException{
    System.out.println("== 게시판 상세보기 ==");
    System.out.printf("번호 : %d\n제목 : %s\n내용 : %s\n",article.get(article_num).id,article.get(article_num).title,article.get(article_num).body);
  }
  // 게시글 업데이트 메서드
  static void Article_Update(int article_num,String update_title, String update_body){
    Article select_article=article.get(article_num);
    select_article.title=update_title;
    select_article.body=update_body;

  }
  public static void main(String args[]) {
    System.out.println("== 게시판 v 0.1 ==");
    System.out.println("== 프로그램 시작 ==");
    Scanner sc=new Scanner(System.in);
    String input ="";
    CreateTestArticle();
    while(true){
      String title="";
      String body="";
      System.out.print("명령) ");
      input = sc.nextLine();
      Rq rq=new Rq(input);
      if(input.equals("exit")){
        break;
      }else if(rq.getUrl().equals("/usr/article/write")){
        System.out.printf("== 게시물 등록 ==\n");
        System.out.printf("제목 : ");
        title=sc.nextLine();
        System.out.printf("내용 : ");
        body=sc.nextLine();
        CreateArticle(title,body);
      }else if(rq.getUrl().equals("/usr/article/detail")){
        Map<String,String> param=rq.getParam();
        try{
          if(param.containsKey("id")){
            ArticleDetail(Integer.parseInt(param.get("id")));
          }else{
            ArticleDetail(article.size());
          }
        }catch (NumberFormatException e){
          System.out.println("id 값이 올바르지 않습니다.");
        }catch(NullPointerException e){
          System.out.println("최신(마지막) 게시글은 "+article.size()+"번 입니다. 재입력해주세요.");
        }
      }else if(rq.getUrl().equals("/usr/article/list")){
        Map<String,String> param=rq.getParam();
        System.out.println("== 게시물 리스트 ==");
        System.out.println("--------------------");
        System.out.println("번호 / 제목");
        System.out.println("--------------------");
        if(param.containsKey("orderBy") && param.get("orderBy").equals("idAsc")) {
          for (Integer index : article.keySet()) {
            System.out.println(article.get(index).id + " / " + article.get(index).title);
          }
        }else{
          for(int i=last_index_num-1;i>=1;i--){
            System.out.println(article.get(i).id+" / "+article.get(i).title);
          }
        }
      }else if(rq.getUrl().equals("/usr/article/update")) {
        Map<String, String> param= rq.getParam();
        try {
            if(param.containsKey("id")&&Integer.parseInt(param.get("id"))<=article.size()){
              System.out.println("변경할 제목을 입력해주세요 :");
              String update_title = sc.nextLine();
              System.out.println("변경할 내용을 입력해주세요 :");
              String update_body = sc.nextLine();
              Article_Update(Integer.parseInt(param.get("id")),update_title,update_body);
            } else{
              throw new IndexOutOfBoundsException("업데이트할 게시글 번호를 입력해주세요.");
            }
        } catch (IndexOutOfBoundsException e) {
          System.out.println(e.getMessage());
        } catch (Exception e) {
          System.out.println(e.getMessage());
        }
      }else if(input.equals("exit")){
        break;
      } else{
        System.out.printf("입력된 명령어 : %s\n",input);
      }
    }
    for(Integer num:article.keySet()){
      System.out.println(num+" : "+article.get(num));
    }
    System.out.println("== 프로그램 종료 ==");
    sc.close();
  }
}
  // 게시글 Class
class Article{
  int id;
  String title, body;
  // 매개변수 (아이디, 제목, 내용)를 갖는 생성자.
  Article(int id, String title, String body){
    this.id=id;
    this.title=title;
    this.body=body;
  }
  // 출력 형태 : "id : 아이디 / title : 제목 / body : 내용"
  public String toString(){return "id : "+id+" / title : "+title+" / body : "+body;}
}
class Rq{
  private String url;
  private Map<String,String> url_param;
  private String url_path;
  Rq(String url){
    this.url=url;
    this.url_param=Util2.getParameterFromUrl(url);
    this.url_path=Util2.getUrlPathFromUrl(url);
  }
  public Map<String,String> getParam(){
    return url_param;
  }
  public String getUrl(){
    return url_path;
  }}




// url 파싱로직 방법2
// "?" 기준으로 나눈다 -> "&" 기준으로 나눈다. -> "=" 기준으로 나눈다
// 그리고 최종 bit의 길이가 2개라면 parameter Map에 추가해서 리턴한다.
class Util2{
  static Map<String,String> getParameterFromUrl(String queryString){
    Map<String, String> parameter=new HashMap<>();
    String[] queryStrings=queryString.split("\\?",2);
    if(queryStrings.length==1){
      return parameter;
    }
    String[] query=queryStrings[1].split("\\&");
    for(String bits:query){
      String[] bit=bits.split("=");
      if(bit.length==2){
        parameter.put(bit[0],bit[1]);
      }
    }
    return parameter;
  }
  static String getUrlPathFromUrl(String queryString){
    return queryString.split("\\?",2)[0];
  }
}
