package com.jwy.exam.accounts;
import com.jwy.exam.board.Main.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class ValException extends IOException {
  ValException(String m) {
    super(m);
  }
}

public class Main {
  static int index_num = 1;
  static Map<Integer, User> map = new HashMap<Integer, User>();
  Scanner sc = Container.sc;
  // 테스트 유저 데이터 생성 메소드
  static void CreateUser() {
    map.put(index_num, new User_man("정우용", "wulovesk", "we93923!", "010-1111-2222"));
    index_num++;
    map.put(index_num, new User_man("홍길동", "ghdrlfehd", "pxodch1@u#", "010-2222-3322"));
    index_num++;
    map.put(index_num, new User_man("피카츄", "vlzkcb112", "addd3w2!", "010-9921-2442"));
    index_num++;
    map.put(index_num, new User_man("김철수", "rlacjftn", "zxcxs192#", "010-1231-2352"));
    index_num++;
  }
  // 아이디 유효성 검사 메소드 ( 8글자 이상, 아이디 중복, 특수문자 포함여부) 검사
  static String id_validation(String input_id) {
    Pattern p = Pattern.compile("^[a-z0-9가-힣A-Zㅏ-ㅣㄱ-ㅎ]*$");
    Matcher m = p.matcher(input_id);
    try {
      if (input_id.length() >= 8 && id_check(input_id) && m.matches() == true) {
        return input_id;
      } else {
        throw new ValException("아이디 유효성 검사 실패(중복/특수문자/8글자이하 x)");
      }
    } catch (ValException e) {
      System.out.println(e.getMessage());
    }
    System.out.println("아이디를 다시 입력해주세요:  ");
    String id = Container.sc.next();
    return id_validation(id);
  }

  // 비밀번호 유효성 검사 메소드 ( 영문, 숫자, 특수문자 포함 여부 )
  static String pw_validation(String input_pw) {
    Pattern p = Pattern.compile("^(?=.*[A-Za-z])(?=.*\\d)(?=.*[$@$!%*#?&])[A-Za-z\\d$@$!%*#?&]{8,}$");
    Matcher m = p.matcher(input_pw);
    try {
      if (m.matches() == true) {
        return input_pw;
      } else {
        throw new ValException("비밀번호 유효성 검사 실패");
      }
    } catch (ValException e) {
      System.out.println(e.getMessage());
      System.out.printf("비밀번호를 다시 입력해주세요: ");
      String pw = Container.sc.next();
      return pw_validation(pw);
    }
  }

  // 아이디 중복 체크 메소드
  static boolean id_check(String input_id) {
    for (Integer u : map.keySet()) {
      if (map.get(u).id.equals(input_id)) {
        return false;
      }
    }
    return true;
  }

  // 회원가입 메소드
  static void SignUp() {
    String name, id, password, num;
    boolean gender;
    System.out.println("==== 회원가입 ====");
    System.out.printf("이름 입력: ");
    name = Container.sc.next();
    System.out.printf("아이디 입력: ");
    id = Container.sc.next();
    System.out.printf("비밀번호 입력: ");
    password = Container.sc.next();
    System.out.printf("전화번호를 입력 : ");
    num = Container.sc.next();
    System.out.println("성별 (남자:true/ 여자:false) :");
    gender = Container.sc.nextBoolean();
    System.out.println("    Loading ...   ");
    String validation_id = id_validation(id);
    String validation_pw = pw_validation(password);
    if (gender == true) {
      map.put(index_num, new User_man(name, validation_id, validation_pw, num));
      index_num++;
      System.out.println("회원가입 완료.");
    } else {
      map.put(index_num, new User_woman(name, validation_id, validation_pw, num));
      index_num++;
      System.out.println("회원가입 완료.");
    }
  }
  // 로그인 메소드
  static String Login(){
    System.out.print("아이디 입력: ");
    String id=Container.sc.next();
    System.out.print("비밀번호 입력: ");
    String password=Container.sc.next();
    for(Integer user_idx : map.keySet()){
      if(map.get(user_idx).id.equals(id) && map.get(user_idx).password.equals(password)){
        System.out.println(map.get(user_idx).name+"님, 환영합니다 !");
        return null;
      }
    }
    for(Integer user_idx : map.keySet()){
      if(map.get(user_idx).id.equals(id)){
        System.out.println("잘못된 비밀번호입니다.");
        return null;
      }
    }
    System.out.println("해당 정보가 없습니다.");
    return null;
  }

  public static void main(String args[]) {
    System.out.println("==프로그램 시작==");
    CreateUser();
    while (true) {
      System.out.print("명령어를 입력해주세요.(login / signup / exit) ?");
      String input=Container.sc.next();
      if (input.equals("signup")) {
        SignUp();

      } else if(input.equals("login")){
        Login();

      } else if (input.equals("exit")) {
        break;
      }
    }
    System.out.println("== 프로그램 종료 ==");
    // User 리스트 출력
//    for (Integer index : map.keySet()) {
//      System.out.println(index + " : " + map.get(index));
//    }
  }
}

