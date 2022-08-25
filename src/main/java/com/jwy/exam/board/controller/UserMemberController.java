package com.jwy.exam.board.controller;


import com.jwy.exam.board.Rq;
import com.jwy.exam.board.Session;
import com.jwy.exam.board.container.Container;
import com.jwy.exam.board.dto.Member;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserMemberController {
  static int index = 1;
  public static List<Member> members = new ArrayList<>();

  public void CreateTestMember(int testMember_count) {
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

  // 아이디 중복 체크 메소드 ( true : 사용가능 / false: 사용불가 )
  boolean id_check(String input_id) {
    for (Member member : members) {
      if (member.getId().equals(input_id)) {
        System.out.println(member.getId());
        return false;
      }
    }
    return true;
  }
  //  회원가입 메소드
  public void Signup(Rq rq) {
    if(rq.islogined()){
      System.out.println("로그인 상태입니다.");
      return;
    }
    Date date = new Date();
    SimpleDateFormat regdate = new SimpleDateFormat("yy-MM-dd HH:mm");
    System.out.println("=== 회원가입 ===");
    System.out.print("이름을 입력해주세요: ");
    String signup_name = Container.getSc().nextLine();
    System.out.print("ID를 입력해주세요: ");
    String signup_id = Container.getSc().nextLine();
    if(!(id_check(signup_id))){
      System.out.println(signup_id+"은 중복아이디입니다.");
      return ;
    }
    System.out.print("비밀번호를 입력해주세요: ");
    String signup_pw = Container.getSc().nextLine();
    System.out.print("나이를 입력해주세요: ");
    int signup_age = Integer.parseInt(Container.getSc().nextLine());
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
  //  로그인 아이디 확인.
  public Member getLoginID(String login_id) {
    for (Member member : members) {
      if (member.getId().equals(login_id)) {
        return member;
      }
    }
    return null;
  }
  //  로그인메소드
  public void Login(Rq rq) {
    if(rq.islogined()){
      System.out.println("로그인 상태입니다.");
      return ;
    }
    System.out.println("=== 로그인 ===");
    System.out.print("아이디를 입력해주세요 :");
    String login_id = Container.getSc().nextLine();
    Member login_member = getLoginID(login_id);
    if(login_member==null){
      System.out.println("아이디를 다시 입력해주세요.");
      return ;
    }
    System.out.print("비밀번호를 입력해주세요 :");
    String login_pw = Container.getSc().nextLine();
    if(login_pw.equals(login_member.getPw())){
      System.out.println(login_member.getId()+"님 로그인 성공 !");
      rq.login(login_member);

    }else{
      System.out.println("비밀번호를 다시 입력해주세요.");
    }
  }
  //  로그아웃 메소드
  public void Logout(Rq rq) {
    if(rq.islogined()){
      rq.logout();
      System.out.println("로그아웃 되었습니다.");
    }else{
      System.out.println("로그인 후 이용해주세요.");
    }
  }
}