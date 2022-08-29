package com.jwy.exam.board.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Board {
  int id;
  String regdate;
  String updateDate;
  String name;
  String code;

}
