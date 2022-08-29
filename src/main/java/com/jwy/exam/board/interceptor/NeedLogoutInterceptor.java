package com.jwy.exam.board.interceptor;

import com.jwy.exam.board.Rq;

public class NeedLogoutInterceptor implements Interceptor {
@Override
  public boolean run(Rq rq){
    if(rq.islogined()==false){
      return true;
    }
    switch (rq.getUrl()){
      case "/usr/member/login":
      case "/usr/member/signup":
        System.out.println("이미 로그인 되었습니다");
        return false;
    }
    return true;
  }
}
