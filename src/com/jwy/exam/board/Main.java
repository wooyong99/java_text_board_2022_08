package com.jwy.exam.board;


import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
//push 연습 1
public class Main {
  static int index_num=1;
  static Map <Integer, Article> user_map=new HashMap();
  public static void main(String[] args) {
    System.out.println("== 게시판 v 0.1 ==");
    System.out.println("== 프로그램 시작 ==");
    Scanner sc=new Scanner(System.in);
    String input ="";
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
        System.out.printf("%d번 게시물이 등록되었습니다.\n",index_num);
        user_map.put(index_num,new Article(index_num,title,body));
        index_num++;
      }else if(input.equals("/usr/article/detail")){
        System.out.println("== 게시판 상세보기 ==");
        System.out.printf("번호 : %d\n",user_map.get(index_num-1).id);
        System.out.printf("제목 : %s\n",user_map.get(index_num-1).title);
        System.out.printf("내용 : %s\n",user_map.get(index_num-1).body);
      }else if(input.equals("/usr/article/list")){
        System.out.println("== 게시물 리스트 ==");
        System.out.println("--------------------");
        System.out.println("번호 / 제목");
        System.out.println("--------------------");
        for(int i=index_num-1;i>=1;i--){
          System.out.println(user_map.get(i).id+" / "+user_map.get(i).title);
        }
      } else{
        System.out.printf("입력된 명령어 : %s\n",input);
      }
    }
    System.out.println("== 프로그램 종료 ==");
    System.out.println(user_map);
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