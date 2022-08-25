package com.jwy.exam.board.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Member {
  int pk;
  String name;
  String id;
  String pw;
  int age;
  String regdate;
}
