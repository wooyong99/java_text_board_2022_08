package com.jwy.exam.board.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//  Article 클래스는 게시판을 구현하고 있다.
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Article {
  int pk;
  String author, title, body, create_time;
}
