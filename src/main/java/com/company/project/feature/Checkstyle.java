package com.company.project.feature;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Checkstyle {

  int NUMBER = 5;

  public void simpleLoop() {

    for(int i = 0; i < 10; i++) {
      System.out.println("*");
    }
  }

  public void longColumn() {
    System.out.println(new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").format(Calendar.getInstance().getTime()));
  }
}
