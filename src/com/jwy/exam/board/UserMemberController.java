package com.jwy.exam.board;

import java.util.ArrayList;
import java.util.List;

public class UserMemberController {
  static int index = 1;
  static List<Member> members=new ArrayList<>();

  void Signup(){
    System.out.print("이름을 입력해주세요: ");
    String signup_name=Container.sc.next();
    System.out.print("ID를 입력해주세요: ");
    String signup_id=Container.sc.next();
    System.out.print("비밀번호를 입력해주세요: ");
    String signup_pw=Container.sc.next();
    System.out.print("나이를 입력해주세요: ");
    int signup_age=Container.sc.nextInt();
  }
  void Login(){
    System.out.println("여기부터");
  }
}
