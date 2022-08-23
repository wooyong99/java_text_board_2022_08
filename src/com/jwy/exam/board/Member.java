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
}
