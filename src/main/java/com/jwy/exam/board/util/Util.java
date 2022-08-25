package com.jwy.exam.board.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Util {
  //  매개변수로 받은 List를 역순으로 return 하는 메소드이다..
  public static <T> List<T> reverseList(List<T> list) {
    List<T> reverse = new ArrayList<>(list.size());

    for (int i = list.size() - 1; i >= 0; i--) {
      reverse.add(list.get(i));
    }
    return reverse;
  }

  //  매개변수로 받은 queryString에서 Parmeter를 return 하는 메소드이다.
  public static Map<String, String> getParameterFromUrl(String queryString) {
    Map<String, String> parameter = new HashMap<>();
    String[] queryStrings = queryString.split("\\?", 2);
    if (queryStrings.length == 1) {
      return parameter;
    }
    String[] query = queryStrings[1].split("\\&");
    for (String bits : query) {
      String[] bit = bits.split("=");
      if (bit.length == 2) {
        parameter.put(bit[0], bit[1]);
      }
    }
    return parameter;
  }
  //  매개변수로 받은 queryString에서 URLPath(경로)를 return 하는 메소드이다.
  public static String getUrlPathFromUrl(String queryString) {
    return queryString.split("\\?", 2)[0];
  }
}