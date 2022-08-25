package com.jwy.exam.board;

import com.jwy.exam.board.container.Container;
import com.jwy.exam.board.dto.Member;
import com.jwy.exam.board.util.Util;

import java.util.Map;

//  Rq(request)클래스는 Util클래스에 있는 메소드를 대신하여 호출해주는 클래스이다.
//  즉, Main 메소드에서 request(요청)을 하기 위한 클래스이다.
public class Rq {
  private String url;
  private Map<String, String> url_param;
  private String url_path;

  public Rq() {
  }

  //  url을 매개변수로 받는 생성자 메소드이다.
  Rq(String url) {
    this.url = url;
    this.url_param = Util.getParameterFromUrl(url);
    this.url_path = Util.getUrlPathFromUrl(url);
  }

  public int getIntparam(String paramname, int defaultValue) {
    if (url_param.containsKey(paramname) == false) {
      return defaultValue;
    }
    try {
      return Integer.parseInt(url_param.get(paramname));
    } catch (NumberFormatException e) {
      return defaultValue;
    }
  }

  public String getStrparam(String paramname, String defaultValue) {
    if (url_param.containsKey(paramname)) {
      return url_param.get(paramname);
    }
    return defaultValue;
  }

  //  Parameter를 return 하는 메소드이다.
  public Map<String, String> getParam() {

    return url_param;
  }

  //  URLPath를 return 하는 메소드이다.
  public String getUrl() {

    return url_path;
  }

  //  Session 클래스의 storage(Map 형태 저장소)에 set하기 위한 메소드
  public void setSessionAttri(String key, Object value) {
    Session session = Container.getSession();
    session.setAttribute(key, value);
  }

  //  Session 클래스의 storage(Map 형태 저장소)에서 get하기 위한 메소드
  public Object getSessionAttri(String key) {
    Session session = Container.getSession();
    return session.getAttribute(key);
  }

  //  Session 클래스의 strorage(Map 형태 저장소)에 login값 저장하는 메소드.
  public void login(Member login_member) {
    Session session = Container.getSession();
    session.setAttribute("logined_member", login_member);
  }

  //  Session 클래스의 storage(Map 형태 저장소)에 login값을 삭제하는 메소드. 즉, logout하는 메소드.
  public void logout() {
    Session session = Container.getSession();
    session.removeAttribute("logined_member");
  }

  //  login 상태를 알려주는 메소드
  public boolean islogined() {
    Session session = Container.getSession();
    return session.hasAttribute("logined_member");
  }

  //  QuesryString을 urlpath와 parameter를 return 하는 메소드
  public void setCommand(String queryString) {
    this.url_path = Util.getUrlPathFromUrl(queryString);
    this.url_param = Util.getParameterFromUrl(queryString);
  }

}
