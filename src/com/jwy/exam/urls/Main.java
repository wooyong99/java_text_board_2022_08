package com.jwy.exam.urls;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
  Map<String,Object> url=new HashMap(){{
    put("title","");
    put("body","");
  }};
  public static void main(String args[]){
    /*String queryString = "title=제목1&body=내용1";
    String[] queryString_arr=queryString.split("&");*/
    List<String> paramNames=new ArrayList();
    List<Integer> paramValues=new ArrayList();
    String queryString="a= &b=20&c=30&a=100";
    String[] query=queryString.split("&");
    for(String q:query){
      if(q.contains("=")==false){
        continue;
      }else {
        String[] bit = q.split("=");
        if(bit[0]==(null)||bit[1]==(null)){
          continue;
        }else{
          System.out.println(bit[0]);
          System.out.println(bit[1]);
        }
      }
     /* paramNames.add(bit[0]);
      paramValues.add(Integer.parseInt(bit[1]));*/
    }/*
    for(int i=0;i<paramNames.size();i++){
      System.out.println(paramNames.get(i));
      System.out.println(paramValues.get(i));
    }*/
  }
}
