package com.jwy.exam.board;

public class Member {
  int pk;
  String name;
  String id;
  String pw;
  int age;
  String regdate;
  Member(int pk, String name, String id, String pw, int age, String regdate){
    this.pk=pk;
    this.name=name;
    this.id=id;
    this.pw=pw;
    this.age=age;
    this.regdate=regdate;
  }
  public String toString(){
    return "번호: "+pk+" / 이름 : "+name+" / 아이디 : "+id+" / 비밀번호 : "+pw+" / 나이 : "+age+" / 가입일자 : "+regdate ;
  }
}
