package com.company;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class InputValidation {

   private final Scanner input = new Scanner(System.in);


   public String inputStringCheck(String displayMsg) {

      System.out.print(displayMsg);
      String strValue = input.nextLine();//get the input value

      while (!testLettersCheck(strValue)) {// input value sending from nameCheck method

         System.out.println("\t\tIt should be a string value , re-enter");
         System.out.print(displayMsg);
         strValue = input.nextLine();//if input value is not string
      }
      return strValue;
   }

   public boolean testLettersCheck(String strValue){
      if (!strValue.matches("[a-zA-Z ]+")) {//it is string check method,a to z , A to Z and space check special code
         return false;
      }
      return true;
   }


   public int inputIntegerCheck(String displayMsg){

      System.out.print(displayMsg);
      String intValue = input.nextLine();//get the input value
      while (!testInputInteger(intValue)) {// input value sending from testIntegerInput method
         System.out.println("\t\tIt should be a integer value, re-enter.");
         System.out.print(displayMsg);
         intValue = input.nextLine();//if input value is not integer

      }
      return Integer.parseInt(intValue);
   }

   public boolean testInputInteger(String number){

      if (!number.matches("[a-zA-Z ]+")){//it is integer check method,a to z , A to Z and space check special code
         return true;
      }else {
         return false;
      }
   }

   public String inputDateCheck(String displayMsg){

      System.out.print(displayMsg);
      String dateValue = input.nextLine();

      while (!testInputDate(dateValue)){

         System.out.println("\t\tIt should be a integer value, re-enter.");
         System.out.print(displayMsg);
         dateValue = input.nextLine();//if input value is not integer

      }
      return dateValue;
   }

   public boolean testInputDate(String dateValue){

         SimpleDateFormat dFormat = new SimpleDateFormat("dd-MM-yyyy");
         Date inputDate=null;

         try {
            inputDate = dFormat.parse(dateValue);
            return true;
         } catch (ParseException e) {
            return false;
         }


   }







}
