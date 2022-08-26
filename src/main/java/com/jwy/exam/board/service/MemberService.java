package com.jwy.exam.board.service;

import com.jwy.exam.board.dto.Article;
import com.jwy.exam.board.dto.Member;
import com.jwy.exam.board.repository.MemberRepository;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class MemberService {
  MemberRepository memberRepository;
  public MemberService(){
    memberRepository=new MemberRepository();
  }
  public void createTestData(int testMember_count){
    Random random = new Random();
    Date date = new Date();
    SimpleDateFormat regdate = new SimpleDateFormat("yy-MM-dd HH:mm");
    for (int i = 1; i <= testMember_count; i++) {
      memberRepository.createMember("user" + i, "id" + i, "password" + i, random.nextInt(40), regdate.format(date));
    }
  }
  public void createMember(String name, String id, String password, int age, String regdate){
    memberRepository.createMember(name, id, password, age, regdate);
  }
  public List<Member> getMembers(){
    return memberRepository.getMembers();
  }
}
