package com.jwy.exam.accounts;

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

  static String id_validation(String input_id) {
    Pattern p = Pattern.compile("^[a-z0-9가-힣A-Zㅏ-ㅣㄱ-ㅎ]*$");
    Matcher m = p.matcher(input_id);
    try {
      if (input_id.length() >= 8 && id_check(input_id) && m.matches() == true) {
        return input_id;
      } else {
        throw new ValException("8글자 이상 / 특수문자 제외 / 중복 x");
      }
    } catch (ValException e) {
      System.out.println(e.getMessage());
    }
    Scanner sc = new Scanner(System.in);
    System.out.println("아이디를 다시 입력해주세요:  ");
    String id = sc.next();
    return id_validation(id);
  }
  static boolean id_check(String input_id) {
    for (Integer u : map.keySet()) {
      if (map.get(u).id.equals(input_id)) {
        return false;
      }
    }
    return true;
  }
  static void SignUp(String name, String id, String password, String num, boolean gender) {
    String validation_id = id_validation(id);
    if (gender == true) {
      map.put(index_num, new User_man(name, validation_id, password, num, gender));
      index_num++;
    } else {
      map.put(index_num, new User_woman(name, validation_id, password, num, gender));
      index_num++;
    }
  }
  public static void main(String args[]) {
    System.out.println("==프로그램 시작==");
    File file = new File("input.txt");
    try {
      Scanner sc = new Scanner(file);
      System.out.println("회원가입을 하시겠습니까?");
      String input = sc.next();
      while (true) {
        if (input.equals("네")) {
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
        } else {
          break;
        }
        System.out.println("회원가입을 하시겠습니까?");
        input = sc.next();
      }
      System.out.println("== 프로그램 종료 ==");
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
    SignUp("aaa", "a", "asd", "341123", true);
    for (Map.Entry<Integer, User> lst : map.entrySet()) {
      System.out.println(lst.getKey() + " - " + lst.getValue());
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