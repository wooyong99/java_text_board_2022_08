package com.jwy.exam.board;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserMemberController {
  static int index = 1;
  static List<Member> members=new ArrayList<>();

  // 아이디 유효성 검사 메소드 ( 8글자 이상, 아이디 중복, 특수문자 포함여부) 검사
  boolean id_validation(String input_id) {
    Pattern p = Pattern.compile("^[a-z0-9가-힣A-Zㅏ-ㅣㄱ-ㅎ]*$");
    Matcher m = p.matcher(input_id);
    if (input_id.length() >= 8 && id_check(input_id) && m.matches() == true) {
      return true;
    } else {
      return false;
    }
  }

  // 비밀번호 유효성 검사 메소드 ( 영문, 숫자, 특수문자 포함 여부 )
  boolean pw_validation(String input_pw) {
    Pattern p = Pattern.compile("^(?=.*[A-Za-z])(?=.*\\d)(?=.*[$@$!%*#?&])[A-Za-z\\d$@$!%*#?&]{8,}$");
    Matcher m = p.matcher(input_pw);
    if (m.matches() == true) {
      return true;
    } else {
      return false;
    }
  }

  // 아이디 중복 체크 메소드
  boolean id_check(String input_id) {
    for (Member member : members) {
      if (member.id.equals(input_id)) {
        return false;
      }
    }
    return true;
  }
  void Signup(){
    System.out.println("=== 회원가입 ===");
    System.out.print("이름을 입력해주세요: ");
    String signup_name=Container.sc.next();
    System.out.print("ID를 입력해주세요: ");
    String signup_id=Container.sc.next();
    System.out.print("비밀번호를 입력해주세요: ");
    String signup_pw=Container.sc.next();
    System.out.print("나이를 입력해주세요: ");
    int signup_age=Container.sc.nextInt();
    if(id_validation(signup_id) && pw_validation(signup_pw)){
      members.add(new Member(index,signup_name,signup_id,signup_pw,signup_age));
      index++;
      System.out.println(signup_name+"님 가입을 환영합니다.");
    }else if(id_validation(signup_id) && pw_validation(signup_pw)==false){
      System.out.println("비밀번호를 다시 설정해주세요.");
    }else{
      System.out.println("아이디를 다시 설정해주세요.");
    }
  }
  void Login(){
    System.out.println("=== 로그인 ===");
    System.out.print("아이디를 입력해주세요 :");
    String login_id=Container.sc.next();
    System.out.print("비밀번호를 입력해주세요 :");
    String login_pw=Container.sc.next();
    for(Member login_member:members){
      if(login_member.id.equals(login_id)){
        if(login_member.pw.equals(login_pw)){
          System.out.println("로그인 성공 !");
          return ;
        }else{
          System.out.println("비밀번호가 틀렸습니다.");
          return ;
        }
      }
    }
    System.out.println("잘못된 아이디입니다.");
    return ;
  }
}
