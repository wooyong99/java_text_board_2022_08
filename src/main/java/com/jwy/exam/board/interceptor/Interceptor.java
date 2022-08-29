package com.jwy.exam.board.interceptor;

import com.jwy.exam.board.Rq;

public interface Interceptor {
  boolean run(Rq rq);
}
