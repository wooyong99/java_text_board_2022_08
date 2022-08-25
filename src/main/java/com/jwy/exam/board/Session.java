package com.jwy.exam.board;

import java.util.HashMap;
import java.util.Map;

public class Session {
  Map<String, Object> storage=new HashMap<>();

  public void setAttribute(String key, Object value){
    storage.put(key, value);
  }
  public Object getAttribute(String key){
    return storage.get(key);
  }
  public void removeAttribute(String key) {
    storage.remove(key);
  }
  public boolean hasAttribute(String key) {
    return storage.containsKey(key);
  }
}
