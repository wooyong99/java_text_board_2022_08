package com.jwy.exam.board.repository;

import com.jwy.exam.board.dto.Member;
import lombok.Getter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class MemberRepository {
  private static int last_index ;
  @Getter
  private static List<Member> members ;
  public MemberRepository(){
    last_index = 1;
    members = new ArrayList<>();
  }
  public void createMember(String name, String id, String password, int age, String regdate){
    members.add(new Member(last_index, name, id, password, age, regdate));
    last_index++;
  }
}
