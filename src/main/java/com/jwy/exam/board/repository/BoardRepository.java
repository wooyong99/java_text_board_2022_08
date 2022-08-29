package com.jwy.exam.board.repository;


import com.jwy.exam.board.dto.Board;
import com.jwy.exam.board.util.Util;
import java.util.ArrayList;
import java.util.List;

public class BoardRepository {
  static int last_index;
  static List<Board> boards;

  public BoardRepository(){
    this.boards=new ArrayList<Board>();
    last_index=1;
  }
  public Board getBoardById(int id){
    for( Board board:boards){
      if(board.getId() == id){
        return board;
      }
    }
    return null;
  }

  public void makeBoard(String code, String name) {
    String regdate=Util.getNowDate();
    String updatedate=regdate;
    Board board=new Board(last_index, Util.getNowDate(),updatedate,name,code);
    boards.add(board);
    last_index++;
  }
  public String getBoardName(int board_id){
    for(Board board:boards){
      if(board.getId()==board_id){
        return board.getName();
      }
    }
    return "존재하지 않는 게시판";
  }
}
