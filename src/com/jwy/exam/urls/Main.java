package com.jwy.exam.urls;

import java.util.*;

public class Main {
  public static void main(String args[]){
    String queryString="/usr/article/detail?id=3&pw=5";
    Rq rq=new Rq(queryString);
    System.out.println("urlpath : "+rq.getUrl());
    System.out.println("params : "+rq.getParam());
  }
}
class Rq{
  private String url;
  private Map<String,String> url_param;
  private String url_path;
  Rq(String url){
    this.url=url;
    this.url_param=Util2.getParameterFromUrl(url);
    this.url_path=Util2.getUrlPathFromUrl(url);
  }
  public Map<String,String> getParam(){
    return this.url_param;
  }
  public String getUrl(){
      return this.url_path;
}}
// url 파싱로직 방법1
// "?" 기준으로 나눈다 -> "&" 기준으로 나눈다 -> "="미포함 || 공백 포함 || "="2개 이상 조건에 부합하지 않는다면
// "=" 기준으로 나누고, 길이가 2개라면 param Map에 추가해서 리턴한다.
class Util{
  static Map<String,String> getParam(String queryStr){
    Map<String,String> param=new HashMap<>();
    String query=queryStr.substring(queryStr.indexOf("?")+1);
    for(String q:query.split("&")) {
      if (q.contains("=") == false || q.contains(" ") == true || q.matches("[=]{2,}") == true) {
        continue;
      } else {
        String[] bit = q.split("=");
        if (bit.length == 2) {
          param.put(bit[0], bit[1]);
        }
      }
    }return param;
  }
}
// url 파싱로직 방법2
// "?" 기준으로 나눈다 -> "&" 기준으로 나눈다. -> "=" 기준으로 나눈다
// 그리고 최종 bit의 길이가 2개라면 parameter Map에 추가해서 리턴한다.
class Util2{
  static Map<String,String> getParameterFromUrl(String queryString){
    Map<String, String> parameter=new HashMap<>();
    //String queryStrings=queryString.substring(queryString.indexOf("?")+1);
    String[] queryStrings=queryString.split("\\?",2);
    if(queryStrings.length==1){
      return parameter;
    }
    String[] query=queryStrings[1].split("\\&");
    for(String bits:query){
      String[] bit=bits.split("=");
      if(bit.length==2){
        parameter.put(bit[0],bit[1]);
      }
    }
    return parameter;
  }
  static String getUrlPathFromUrl(String queryString){
    return queryString.split("\\?",2)[0];
  }
}
