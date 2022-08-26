package com.jwy.exam.board.service;

import com.jwy.exam.board.dto.Article;
import com.jwy.exam.board.repository.ArticleRepository;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

public class ArticleService {
  ArticleRepository articleRepository ;

  public ArticleService(){
    articleRepository = new ArticleRepository();
  }

  public void createTestDate(int test_article_count){
    System.out.println("== 게시글 테스트 데이터 생성 시작 ==");
    for (int i = 1; i <= test_article_count; i++) {
      Date date=new Date();
      SimpleDateFormat create_time=new SimpleDateFormat("yy-MM-dd HH:mm");
      createArticle("사용자"+i,"제목"+i,"내용"+i,create_time.format(date));
    }
    System.out.println("       Loading ...   ");
    System.out.println("== 게시글 테스트 데이터 " + test_article_count + "개 생성 ==");
  }
  //  articleReposiory의 articles를 가져오는 함수
  public Map<Integer, Article> getArticles(){
    return articleRepository.getArticles();
  }
  //  Article 생성하는 메소드
  public void createArticle(String author, String title, String body, String regdate){
    articleRepository.createArticle(author, title,body,regdate);
  }
}
