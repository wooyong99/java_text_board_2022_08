package com.jwy.exam.accounts;

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
  public void setID(String id){
    this.id=id;
  }
  public void setPassowrd(String password){
    this.password=password;
  }
  public void setNum(String num){
    this.num=num;
  }

  public String toString() {
    return String.format("%s / %s / %s / %s", name, id, password, num);
  }
}

class User_man extends User {
  String gender="남자";
  User_man(String name, String id, String password, String num) {
    super(name, id, password, num);
  }

}

class User_woman extends User {
  String gender="여자";
  User_woman(String name, String id, String password, String num) {
    super(name, id, password, num);

  }
}