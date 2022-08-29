package com.jwy.exam.board.interceptor;

import com.jwy.exam.board.Rq;

public class NeedLoginInterceptor implements Interceptor {
@Override
  public boolean run(Rq rq){
    if(rq.islogined()) {
      return true;
    }
    switch(rq.getUrl()){
      case "/usr/article/write":
      case "/usr/article/update":
      case "/usr/article/delete":
        System.out.println("로그인 후 이용해주세요.");
        return false;
      case "/usr/member/logout":
        System.out.println("로그아웃 상태입니다.");
        return false;
    }
    return true;
  }
}
