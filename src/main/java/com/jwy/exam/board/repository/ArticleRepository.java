package com.jwy.exam.board.repository;

import com.jwy.exam.board.dto.Article;
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
  public void createArticle(int board_id,String author, String title, String body, String regdate){
    articles.put(last_index, new Article(board_id, author, title, body, regdate,0));
    last_index++;
  }

  public void viewsCount(Article article) {
    for(Article sel_article: articles.values()){
      if(sel_article.equals(article)){
        sel_article.setViews(sel_article.getViews()+1);
        return ;
      }
    }
  }
}
