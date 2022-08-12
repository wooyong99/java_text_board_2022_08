package com.jwy.exam.urls;

import sun.security.rsa.RSAUtil;

import java.util.*;

public class Main {
  // Prameter의 이름과 값을 두개의 리스트로 분리.
  static List<String> paramNames=new ArrayList();
  static List<Object> paramValues=new ArrayList();
  // 파싱 로직 :
  // 1. 매개변수로 받은 url을  &를 기준으로 분리한다.
  // 2. "="기호 미포함 및 2개 이상 / 공백 포함은 탐색하지 않는다.
  // 3. 위 조건을 통과한 query는 이름과 값으로 나누기 위해 "=" 기준으로 분리한다.
  // 4. 배열의 개수가 2개인 bit만을 리스트에 저장한다.
  public static void main(String args[]){

    String queryString="https://search.naver.com/search.naver?where=nexearch&sm=top_hty&fbm=1&ie=utf8&query=%EC%A0%95%EC%9A%B0%EC%9A%A9";
    String[] query=queryString.split("&");
    for(String q:query){
      if(q.contains("=")==false || q.contains(" ")==true|| q.matches("[=]{2,}")==true){
        continue;
      }else {
        String[] bit = q.split("=");
        if(bit.length==2){
          paramNames.add(bit[0]);
          paramValues.add((Object)(bit[1]));
        }
      }
    }for(int i=0;i<paramNames.size();i++){
      System.out.println("==== "+paramNames.get(i)+" - "+paramValues.get(i)+" =========");
    }
  }
}
