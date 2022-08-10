package com.jwy.exam.accounts;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
class SignUpException extends IOException {
  SignUpException(String m){
    super(m);
  }
}
public class Main {
  static int index_num=1;
  static Map<Integer, User> map=new HashMap<Integer, User>();
  static boolean id_check(String input_id) {
    for(int i=1;i<=map.size();i++){
      if(input_id.equals(map.get(i).id)){
        return false;
      }
    }return true;
  }
  static void SignUp(String name, String id, String password, String num, boolean gender) throws SignUpException{
    if(id_check(id)==false){
      throw new SignUpException("아이디 중복");
    }else{
      if (gender == true) {
        map.put(index_num, new User_man(name, id, password, num, gender));
        index_num++;
      } else {
        map.put(index_num, new User_woman(name, id, password, num, gender));
        index_num++;
      }
    }
  }
  public static void main(String args[]){
    System.out.println("==프로그램 시작==");
    File file=new File("input.txt");
    try{
      Scanner sc=new Scanner(file);
      System.out.println("회원가입을 하시겠습니까?");
      String input=sc.next();
      while(true){
        if(input.equals("네")) {
          String name, id, password, num;
          boolean gender;
          System.out.printf("이름/아이디/비밀번호/전화번호를 입력해주세요.\n");
          name = sc.next();
          id = sc.next();
          password = sc.next();
          num = sc.next();
          gender = sc.nextBoolean();
          SignUp(name,id,password,num,gender);
        }else if(input.equals("exit")){
          break;
        }
        else{
          break;
        }
        System.out.println("회원가입을 하시겠습니까?");
        input=sc.next();
      }
      System.out.println("== 프로그램 종료 ==");
    }catch(SignUpException e1){
      System.out.println(e1.getMessage());
    }
    catch(Exception e){
      System.out.println(e.getMessage());
    }
    try{
      SignUp("aaa","a","asd","341123",true);
    }catch(SignUpException e){
      System.out.println(e.getMessage());
      System.out.println("아이디를 다시 입력해주세요");
      Scanner sc1=new Scanner(System.in);
    }
    for(Map.Entry<Integer,User>lst:map.entrySet()){
      System.out.println(lst.getKey()+" - "+lst.getValue());
    }
  }
}
abstract class User{
  String name;
  String id;
  String password;
  String num;
  boolean gender;
  User(String name, String id, String password, String num){
    this.name=name;
    this.id=id;
    this.password=password;
    this.num=num;
  }
  public String toString(){
    return String.format("%s / %s / %s / %s",name,id,password, num);
  }
}
class User_man extends User{
  User_man(String name, String id, String password, String num, boolean gender){
    super(name,id,password, num);
    this.gender=gender;
  }

}
class User_woman extends User {
  User_woman(String name, String id, String password, String num, boolean gender) {
    super(name, id, password, num);
    this.gender = gender;
  }
}