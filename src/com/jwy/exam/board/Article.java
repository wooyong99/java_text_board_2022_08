package com.jwy.exam.board;
  //  Article 클래스는 게시판을 구현하고 있다.
class Article {
  int pk;
  String author, title, body, create_time;

  // 회원 게시글 생성자 (고유번호, 사용자이름, 제목, 내용)
  Article(int pk, String author, String title, String body,String create_time) {
    this.pk = pk;
    this.author = author;
    this.title = title;
    this.body = body;
    this.create_time=create_time;
  }

  // 비회원 (익명) 게시글 생성자 (고유번호, 제목, 내용)
  Article(int pk, String title, String body,String create_time) {
    this.pk = pk;
    this.author="비회원 (익명)";
    this.title = title;
    this.body = body;
    this.create_time=create_time;
  }

  // 출력 형태 : "id : 아이디 / title : 제목 / body : 내용"
  public String toString() {
    return "pk : " + pk + " / 작성자 : " + author + " / 제목 : " + title + " / 내용 : " + body+ " / 작성일자 : " + create_time;
  }
}