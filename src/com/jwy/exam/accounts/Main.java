package com.jwy.exam.accounts;
import com.jwy.exam.board.*;
import javax.xml.validation.Validator;
import java.io.File;
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
  // 테스트 유저 데이터 생성 메소드
  static void CreateUser() {
    SignUp("정우용", "wulovesk1", "qe134d1!", "1111", true);
    SignUp("정우용", "wulovesk2", "afv3d13A!", "1111", true);
    SignUp("정우용", "wulovesk3", "dasc13s1!", "1111", true);
    SignUp("정우용", "wulovesk4", "134daad!", "1111", true);
    SignUp("정우용", "wulovesk5", "vvfsx321!", "1111", true);
    SignUp("정우용", "wulovesk6", "q@ac33A!", "1111", true);
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
    Scanner sc = new Scanner(System.in);
    System.out.println("아이디를 다시 입력해주세요:  ");
    String id = sc.next();
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
      Scanner sc = new Scanner(System.in);
      String pw = sc.next();
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
  static void SignUp(String name, String id, String password, String num, boolean gender) {
    String validation_id = id_validation(id);
    String validation_pw = pw_validation(password);
    if (gender == true) {
      map.put(index_num, new User_man(name, validation_id, validation_pw, num, gender));
      index_num++;
    } else {
      map.put(index_num, new User_woman(name, validation_id, validation_pw, num, gender));
      index_num++;
    }
  }
  // 로그인 메소드 (로그인 시, com.jwy.exam.board.Main 메소드 실행)
  static void Login(String id, String password){
    try{
      for(Integer user_idx : map.keySet()){
        if(map.get(user_idx).id.equals(id)&&map.get(user_idx).password.equals(password)){
          com.jwy.exam.board.Main.main();
        }else{
          throw new NullPointerException("해당 아이디 정보가 없습니다.");
        }
      }
    }catch(NullPointerException e){
      System.out.println(e.getMessage());
    }
  }

  public static void main(String args[]) {
    System.out.println("==프로그램 시작==");
    Scanner sc = new Scanner(System.in);
    CreateUser();
    while (true) {
      System.out.println("명령어를 입력해주세요.(login / signup / exit ?");
      String input = sc.next();
      if (input.equals("signup")) {
        String name, id, password, num;
        boolean gender;
        System.out.printf("이름/아이디/비밀번호/전화번호를 입력해주세요.\n");
        name = sc.next();
        id = sc.next();
        password = sc.next();
        num = sc.next();
        gender = sc.nextBoolean();
        SignUp(name, id, password, num, gender);
      } else if (input.equals("exit")) {
        break;
      } else if(input.equals("login")){
        System.out.println("아이디, 비밀번호를 입력해주세요.");
        Login(sc.next(),sc.next());
      } else {
        break;
      }

    }
    System.out.println("== 프로그램 종료 ==");
    // User 리스트 출력
    for (Integer index : map.keySet()) {
      System.out.println(index + " : " + map.get(index));
    }
  }
}

abstract class User {
  String name;
  String id;
  String password;
  String num;
  boolean gender;

  User(String name, String id, String password, String num) {
    this.name = name;
    this.id = id;
    this.password = password;
    this.num = num;
  }

  public String toString() {
    return String.format("%s / %s / %s / %s", name, id, password, num);
  }
}

class User_man extends User {
  User_man(String name, String id, String password, String num, boolean gender) {
    super(name, id, password, num);
    this.gender = gender;
  }

}

class User_woman extends User {
  User_woman(String name, String id, String password, String num, boolean gender) {
    super(name, id, password, num);
    this.gender = gender;
  }
}