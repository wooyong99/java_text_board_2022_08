package com.jwy.exam.board;

import java.util.Map;
  //  Rq(request)클래스는 Util클래스에 있는 메소드를 대신하여 호출해주는 클래스이다.
  //  즉, Main 메소드에서 request(요청)을 하기 위한 클래스이다.
class Rq {
  private String url;
  private Map<String, String> url_param;
  private String url_path;
  //  url을 매개변수로 받는 생성자 메소드이다.
  Rq(String url) {
    this.url = url;
    this.url_param = Util.getParameterFromUrl(url);
    this.url_path = Util.getUrlPathFromUrl(url);
  }
  //  Parameter를 return 하는 메소드이다.
  public Map<String, String> getParam() {
    return url_param;
  }
  //  URLPath를 return 하는 메소드이다.
  public String getUrl() {
    return url_path;
  }
}
