package com.jwy.exam.board.service;

import com.jwy.exam.board.dto.Board;
import com.jwy.exam.board.repository.BoardRepository;

import java.util.List;

public class BoardService {
  BoardRepository boardRepository;
  public BoardService(){
    this.boardRepository=new BoardRepository();
  }
  public Board getBoardById(int id){
    return boardRepository.getBoardById(id);
  }
  public void createTestData(){
    makeBoard("notice","공지사항");
    makeBoard("free","자유게시판");
  }
  public void makeBoard(String code, String name){
    boardRepository.makeBoard(code,name);
  }
  public String getBoardName(int board_id){
    return boardRepository.getBoardName(board_id);
  }

}
