package com.jwy.exam.board;


import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserMemberController {
  static int index = 1;
  static List<Member> members = new ArrayList<>();

  void CreateTestMember(int testMember_count) {
    Random random = new Random();
    Date date = new Date();
    SimpleDateFormat regdate = new SimpleDateFormat("yy-MM-dd HH:mm");
    for (int i = index; i <= testMember_count; i++) {
      members.add(new Member(index, "user" + i, "id" + i, "password" + i, random.nextInt(40), regdate.format(date)));
      index++;
    }
  }

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

  void Signup() {
    Date date = new Date();
    SimpleDateFormat regdate = new SimpleDateFormat("yy-MM-dd HH:mm");
    System.out.println("=== 회원가입 ===");
    System.out.print("이름을 입력해주세요: ");
    String signup_name = Container.sc.next();
    System.out.print("ID를 입력해주세요: ");
    String signup_id = Container.sc.next();
    System.out.print("비밀번호를 입력해주세요: ");
    String signup_pw = Container.sc.next();
    System.out.print("나이를 입력해주세요: ");
    int signup_age = Container.sc.nextInt();
    members.add(new Member(index, signup_name, signup_id, signup_pw, signup_age, regdate.format(date)));
    index++;
    //  테스트를 하기 위해 잠시 주석처리.
    /*if(id_validation(signup_id) && pw_validation(signup_pw)){
      members.add(new Member(index,signup_name,signup_id,signup_pw,signup_age));
      index++;
      System.out.println(signup_name+"님 가입을 환영합니다.");
    }else if(id_validation(signup_id) && pw_validation(signup_pw)==false){
      System.out.println("비밀번호를 다시 설정해주세요.");
    }else{
      System.out.println("아이디를 다시 설정해주세요.");
    }*/
  }

  void Login() {
    System.out.println("=== 로그인 ===");
    System.out.print("아이디를 입력해주세요 :");
    String login_id = Container.sc.nextLine();
    Member login_member = getLoginID(login_id);
    if(login_member==null){
      System.out.println("아이디를 다시 입력해주세요.");
      return ;
    }
    System.out.print("비밀번호를 입력해주세요 :");
    String login_pw = Container.sc.nextLine();
    if(login_pw.equals(login_member.pw)){
      System.out.println(login_member.name+"님 로그인 성공 !");
    }else{
      System.out.println("비밀번호를 다시 입력해주세요.");
    }
  }
  //  로그인 아이디 확인.
  Member getLoginID(String login_id) {
    for (Member member : members) {
      if (member.id.equals(login_id)) {
        return member;
      }
    }
    return null;
  }
}