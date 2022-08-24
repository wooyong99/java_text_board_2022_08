package com.jwy.exam.board;

import java.util.HashMap;
import java.util.Map;

public class Session {
  Map<String, Object> storage=new HashMap<>();

  void setAttribute(String key, Object value){
    storage.put(key, value);
  }
  Object getAttribute(String key){
    return storage.get(key);
  }
  void removeAttribute(String logined_member) {
    storage.remove(logined_member);
  }
}
