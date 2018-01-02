
// Write a method to replace all spaces in a string with '%20'. 
// You may assume that the string has sufficient space at the end of the string to hold the additional characters
// , and that you are given the "true" length of the string. 
// (Note: if implementing in Java, please use a character array so that you can perform this operation inplace.)

// I: "asd rty dfddffd dfhgf" , n: true length
// O: "asd%20rty%20dfddffd%20dfhgf" 

// perform in place
// use char arr

// 1)
// create a char array
// iterate, at occurence of a ws
// remove ws, insert '%20', shift the following chars

// a) iterate rest of the string 
// TC: n^2 SP:1

// 2)
// reverse iterate
// shift elms as we go
// when come across ws insert %20 and adjust the iteration index


// package exercise.main;

import org.apache.commons.lang3.ArrayUtils;


class Solution {

  // str: "a b"
  // trueLength: 5
  // strArr a, ,b
  // offset ( , )
  // chars a, ,b,x,x

  // i=0
  // c=a

  // i=1
  // c=' '
  // chars a,%,2,0,b

  // i=2
  // c=2

  // i=3
  // c=0

  // i=4
  // c=b

  // ret a%20b

  // other tests
  // "  " 2 spaces
  // space at the end / at the beginning

  public static String replaceWS(String str, int trueLength) {

    if(str == null) return null;
    if(str.isEmpty()) return str;

    char[] strArr = str.toCharArray();
    char[] offset = new char[trueLength - str.length()];
    char[] chars = (char[])ArrayUtils.addAll(strArr, offset);

    for(int i = 0; i < chars.length; i++) {
      char c = chars[i];

      if(c == ' ') { // ws check
        insertAt(i, chars);
      }
    }

    return new String(chars);

  }

  // n= 1
  // chars a, ,b,x,x

  // i = 4
  // chars a, ,b,x,b

  // i = 3
  // chars a, ,b, ,b

  // i = 2
  // chars a, ,a, ,b

  // i = 1
  // chars a,a,a, ,b

  // chars a,%,a, ,b  
  // chars a,%,2, ,b  
  // chars a,%,2,0,b

  public static void insertAt(int n, char[] chars) {
    for(int i = chars.length - 1; i > n+2; i--) {
      chars[i] = chars[i-2];
    }

    chars[n] = '%';
    chars[n+1] = '2';
    chars[n+2] = '0';
  }

  public static void main(String[] args) {
    System.out.println(replaceWS2("a b", 5));
    System.out.println(replaceWS2("a  b", 8));
    System.out.println(replaceWS2("  ab  ", 14));
    System.out.println(replaceWS2("  a b x ", 18));
    System.out.println(replaceWS2("", 0));
    System.out.println(replaceWS2(null, 0));
    System.out.println(replaceWS2("  ", 6));
  }

  // String ??? Capital

  // str =  "a b"
  // n =  5
  // offset = 2
  // chars = a, ,b,null,null

  // i=4
  // current=b
  // chars = a, ,b,null,b

  // i=3
  // current=" "
  // chars = a, ,b,0,b
  // chars = a, ,2,0,b
  // chars = a,%,2,0,b
  // i = 1
  // offset = 0

  // i = 0
  // current=a
  // chars = a,%,2,0,b

  // ret a%20b

  public static String replaceWS2(String str, int n) {
    if(str == null) return null; // throw ex
    if(str.isEmpty()) return str;
    if(str.length() == n) return str;

    int offset = n - str.length();

    char[] offsetArr = new char[offset];
    char[] chars = ArrayUtils.addAll(str.toCharArray(), offsetArr);

    for(int i = n-1; i > -1; i--){

      char current = chars[i - offset];

      if(current == ' ') {
        chars[i] = '0';
        chars[i - 1] = '2';
        chars[i - 2] = '%';
        i = i - 2;
        offset = offset - 2;
        if(offset == 0) break;
      } else {
        chars[i] = chars[i - offset];
      }
    }

    return new String(chars);
  }
}













