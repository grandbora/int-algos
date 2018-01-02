// Implement a function void reverse(char* str) in C or C++ which reverses a null- terminated string.

// size fits, encoding Ascii
// null throws ex
// empty strn returns empty
// duplicates ? may exist

// asd -> dsa
// "" -> ""
// 121121 => 121121

// n = String length

// 1)
// reverse iterate
// accumulate each char

// TC: n
// SC: n




class Solution {

  public static void main(String[] args) {

    System.out.println(reverse("asd"));
    System.out.println(reverse2("asd"));

    System.out.println(reverse(""));
    System.out.println(reverse2(""));

    System.out.println(reverse("BORAaaaaaBORA"));
    System.out.println(reverse2("BORAaaaaaBORA"));
  }

  // "asd"

  // acc = ""

  // i = 2
  // c = d
  // acc = d

  // i = 1
  // c = s
  // acc = ds

  // i = 0
  // c = a
  // acc = dsa

  // i = -1

  // ret dsa

  public static String reverse(String str) {

    if(str == null){
      throw new IllegalArgumentException("...");
    }

    if(str.isEmpty()) {
      return str;
    }

    String acc = "";

    for(int i = str.length() - 1; i > -1; i-- ) {
      char c = str.charAt(i);
      acc = acc + c;
    }

    return acc;
  }


  public static String reverse2(String str) {

    if(str == null){
      throw new IllegalArgumentException("...");
    }

    if(str.isEmpty()) {
      return str;
    }

    if(str.length() == 1) {
      return str;
    }

    char lastChar = str.charAt(str.length() - 1);
    String allButLastChar = str.substring(0, str.length() - 1);

    return lastChar + reverse2(allButLastChar);
  }
}









