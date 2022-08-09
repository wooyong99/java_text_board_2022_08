package com.jwy.exam.board;

import java.util.Scanner;
//push 연습 1
public class Main {
  public static void main(String[] args) {
    System.out.println("== 게시판 v 0.1 ==");
    System.out.println("== 프로그램 시작 ==");
    System.out.print("명령) ");
    Scanner sc=new Scanner(System.in);
    String input =sc.nextLine().trim();
    System.out.printf("입력된 명령어: %s \n",input);
    System.out.println("== 프로그램 종료 ==");
    sc.close();
  }
}