package com.jwy.exam.board;


import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
//push 연습 1
public class Main {
  static int index_num=1;
  static Map <Integer, Article> article=new HashMap();
  static void CreateTestArticle(){
    CreateArticle("제목 1","내용 1");
    CreateArticle("제목 2","내용 2");
    CreateArticle("제목 3","내용 3");
    CreateArticle("제목 4","내용 4");
    CreateArticle("제목 5","내용 5");
    CreateArticle("제목 6","내용 6");
  }
  static void ArticleDetail(int article_num){
    System.out.println("== 게시판 상세보기 ==");
    System.out.printf("번호 : %d\n제목 : %s\n내용 : %s\n",article.get(article_num).id,article.get(article_num).title,article.get(article_num).body);
  }
  static void CreateArticle(String title,String body){
    article.put(index_num,new Article(index_num,title,body));
    System.out.printf("%d번 게시물이 등록되었습니다.\n",index_num);
    index_num++;
  }
  public static void main(String[] args) {
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
      if(input.equals("exit")){
        break;
      }else if(input.equals("/usr/article/write")){
        System.out.printf("== 게시물 등록 ==\n");
        System.out.printf("제목 : ");
        title=sc.nextLine();
        System.out.printf("내용 : ");
        body=sc.nextLine();
        CreateArticle(title,body);
      }else if(input.contains("/usr/article/detail/")){
        String[] article_num=input.split("/");
        //if(input=="/usr/article/detail/"+)
        ArticleDetail(Integer.parseInt(article_num[article_num.length-1]));
      }else if(input.equals("/usr/article/list")){
        System.out.println("== 게시물 리스트 ==");
        System.out.println("--------------------");
        System.out.println("번호 / 제목");
        System.out.println("--------------------");
        for(int i=index_num-1;i>=1;i--){
          System.out.println(article.get(i).id+" / "+article.get(i).title);
        }
      } else{
        System.out.printf("입력된 명령어 : %s\n",input);
      }
    }
    System.out.println("== 프로그램 종료 ==");
    System.out.println(article);
    sc.close();
  }
}
class Article{
  int id;
  String title;
  String body;
  Article(int id, String title, String body){
    this.id=id;
    this.title=title;
    this.body=body;
  }
  public String toString(){
    return "id : "+id+" / title : "+title+" / body : "+body;
  }
}