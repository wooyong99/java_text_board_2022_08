package com.jwy.exam.board.service;

import com.jwy.exam.board.repository.MemberRepository;

public class MemberService {
  MemberRepository memberRepository;
  public MemberService(){
    memberRepository=new MemberRepository();
  }
}
