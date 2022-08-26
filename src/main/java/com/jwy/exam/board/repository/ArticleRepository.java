package com.jwy.exam.board.repository;

import com.jwy.exam.board.dto.Article;
import lombok.Data;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;
public class ArticleRepository {
  // 마지막 게시글을 나타내기 위한 변수
  private static int last_index;
  // {키:값} = {last_index_num, Article(last_index_num,title,body)}
  @Getter
  private static Map<Integer, Article> articles;
  public ArticleRepository(){
    last_index = 1;
    articles=new HashMap<>();
  }
  public void createArticle(String author, String title, String body, String regdate){
    articles.put(last_index, new Article(last_index, author, title, body, regdate));
    last_index++;
  }
}
