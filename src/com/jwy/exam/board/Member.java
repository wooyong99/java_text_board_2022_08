package com.jwy.exam.board;

public class Member {
  int pk;
  String name;
  String id;
  String pw;
  int age;
  Member(int pk, String name, String id, String pw, int age){
    this.pk=pk;
    this.name=name;
    this.id=id;
    this.pw=pw;
    this.age=age;
  }
  public String toString(){
    return "번호: "+pk+" / 이름 : "+name+" / 아이디 : "+name+" / 비밀번호 : "+pw+" / 나이 : "+age ;
  }
}
